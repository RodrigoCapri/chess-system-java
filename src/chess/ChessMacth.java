package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMacth {

	private Board board;
	private int turn;
	private Color currentPlayer;

	private List<Piece> piecesOnTheBoard = new ArrayList<Piece>();
	private List<Piece> capturedPieces = new ArrayList<Piece>();

	private boolean checkMate;

	private boolean check;

	public ChessMacth() {
		this.board = new Board(8, 8);

		this.turn = 1;
		this.currentPlayer = Color.WHITE;

		this.check = false;
		this.checkMate = false;

		this.initialSetup();
	}

	public int getTurn() {
		return this.turn;
	}

	public Color getCurrentPlayer() {
		return this.currentPlayer;
	}

	public boolean getCheck() {
		return this.check;
	}

	public boolean getCheckMate() {
		return this.checkMate;
	}

	// Retorna a matriz de peças da partida de xadrez
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}

	// Retorna os movimentos possiveis de uma peça
	public boolean[][] possibleMoves(ChessPosition sourcePosition) {
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}

	// Função para mover uma peça no tabuleiro
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source); // Verifica se há uma peça na posição inicial
		validateTargerPosition(source, target);

		Piece capturedPiece = makeMove(source, target); // Captura uma peça se houver no movimento ja com a posição de
														// matriz

		// Testa se o movimento executado colocou o proprio Rei em check
		if (this.testCheck(currentPlayer)) {
			// Desfaz o movimento
			this.undoMove(source, target, capturedPiece);
			throw new ChessException("Movimento invalido!\nNao pode se colocar em check!");
		}

		// Testa se o oponente fico em check
		check = (this.testCheck(opponent(currentPlayer))) ? true : false;

		// Verifica se a jogada executada deixou o oponente em checkmate
		if (this.testCheckMate(this.opponent(currentPlayer))) {
			
			this.checkMate = true;
			
		} else {
			this.nextTurn(); // Troca o turno
		}

		return (ChessPiece) capturedPiece;
	}

	// Função auxiliar para executar o movimento da uma peça no tabuleiro
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece) board.removePiece(source); // Retira a peça na posição de origem

		//Acrescenta um movimento na peça
		p.increaseMoveCount();
		
		// Remove a possível peça que esteja na posiçao de destino
		// Guarda a possivel peça na variavel para controle de peças capturadas
		Piece capturedPiece = board.removePiece(target);

		board.placePiece(p, target); // Coloca a peça tirada da posição de origem e coloca na posição de destino

		if (capturedPiece != null) { // Se houver peça capturada, então adiciona a lista de capturadas
			this.piecesOnTheBoard.remove(capturedPiece);
			this.capturedPieces.add(capturedPiece);
		}

		return capturedPiece;
	}

	// Desfaz o movimento
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		ChessPiece p = (ChessPiece) board.removePiece(target); // Tira a peça que foi colocado no destino
		
		//Decrementa um movimento na peça
		p.decreaseMoveCount();
		
		board.placePiece(p, source); // Devolve a peça a posição de origem

		// Se houve uma peça capturada no movimento,
		// então esta peça deverá voltar a sua origem
		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target); // Coloca a peça capturada onde estava

			// Tira a peça capturada da lista de peças capturadas
			this.capturedPieces.remove(capturedPiece);
			this.piecesOnTheBoard.add(capturedPiece);
		}
	}

	// Verifica se a posição de destino é um movimento possivel da peça de sua
	// posição de origem
	private void validateTargerPosition(Position source, Position target) {
		if (!board.piece(source).possibleMove(target)) { // Se não for um movimento possível, lança uma exceção
			throw new ChessException("Movimento invalido para esta peca!");
		}
	}

	// Verifica se há uma peça na posição, e se há movimento possivel
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Nao existe peca nesta posicao de origem!");
		}
		if (this.currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("Nao pode mover a peca adversaria!");
		}
		if (!board.piece(position).isThereAnyPossibleMove()) { // Se não existe algum movimento possivel?
			throw new ChessException("Nao existe movimentos possiveis para a peca escolhida!");
		}
	}

	// Avança o turno
	private void nextTurn() {
		this.turn++;
		this.currentPlayer = (this.currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// Muda de oponente
	// Dada uma cor, muda para o oponente desta cor
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// Pega a localização do Rei no tabuleiro
	private ChessPiece king(Color color) {
		// Filtra a lista apenas com as peças de cor selecionada
		List<Piece> list = this.piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			if (p instanceof King) { // Se esta peça for uma instancia de King
				return (ChessPiece) p;
			}
		}

		throw new IllegalStateException("Nao existe o Rei da cor " + color + " no tabuleiro!");
	}

	// Testa se o Rei está em check
	private boolean testCheck(Color color) {
		// Pega a posição do rei no formato de matriz
		Position kingPosition = this.king(color).getChessPosition().toPosition();

		// Lista de peças do oponente (da outra cor)
		List<Piece> opponentPieces = this.piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == this.opponent(color)).collect(Collectors.toList());

		// Verifica se nos movimentos possiveis de todas as peças do oponente
		// alguma está ameaçando o Rei
		for (Piece p : opponentPieces) {
			boolean[][] mat = p.possibleMoves();
			// Se o elemento desta posição da matriz for verdadeiro, então o Rei está em
			// check
			if (mat[kingPosition.getRow()][kingPosition.getColumn()] == true) {
				return true;
			}
		}

		return false;
	}

	// Verifica se há chcek mate
	private boolean testCheckMate(Color color) {

		if (!this.testCheck(color)) { // Se o Rei não está em check então retorna false
			return false;
		}

		// Uma lista com todas as peças da mesma cor
		List<Piece> list = this.piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());

		for (Piece p : list) { // Percorre toda a lista de peças
			boolean[][] mat = p.possibleMoves(); // Pega os movimentos possiveis da peça

			// Percorre toda a matriz de movimentos possiveis
			for (int i = 0; i < this.board.getRows(); i++) {
				for (int j = 0; j < this.board.getColumns(); j++) {
					if (mat[i][j]) { // Se nesta posição é um movimento possivel
						// Mover a peça P para está posição e verificar se o Rei está ainda está em
						// check
						Position source = ((ChessPiece) p).getChessPosition().toPosition(); // Pega a posição da peça no
																							// formato xadrez
						Position target = new Position(i, j); // Cria a posição de destino
						Piece capturedPiece = this.makeMove(source, target); // Executa o movimento
						boolean testeCheck = this.testCheck(color); // Pega o resultado se ainda está em check
						this.undoMove(source, target, capturedPiece); // Desfaz o movimento, pois era apenas para teste

						// Se não estava em check, este movimento tira o Rei do check
						// Então não é check mate
						if (!testeCheck) {
							return false;
						}
					}
				}
			}
		}

		// Se não houver nenhuma solução, então é checkmate
		return true;
	}

	// Adiciona uma peça no tabuleiro
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
		this.piecesOnTheBoard.add(piece); // Adiciona a peça na lista de peças no tabuleiro
	}

	// Metodo responsavel por iniciar a partida de xadrez
	// Colocando as peças no tabuleiro
	private void initialSetup() {
		this.placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		this.placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		this.placeNewPiece('e', 1, new King(board, Color.WHITE));
		this.placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		this.placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		this.placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
		this.placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
		this.placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
		this.placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
		this.placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
		this.placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
		this.placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
		this.placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

		this.placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		this.placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		this.placeNewPiece('e', 8, new King(board, Color.BLACK));
		this.placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		this.placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		this.placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
		this.placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
		this.placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
		this.placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
		this.placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
		this.placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
		this.placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
		this.placeNewPiece('h', 7, new Pawn(board, Color.BLACK));
	}

}
