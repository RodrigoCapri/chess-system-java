package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMacth {

	private Board board;

	public ChessMacth() {
		this.board = new Board(8, 8);
		this.initialSetup();
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
	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateSourcePosition(source); //Verifica se há uma peça na posição inicial
		validateTargerPosition(source, target);
		
		Piece capturedPiece = makeMove(source, target); //Captura uma peça se houver no movimento ja com a posição de matriz
		
		return (ChessPiece) capturedPiece;
	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source); //Retira a peça na posição de origem
		Piece capturedPiece = board.removePiece(target); //Remove a possível peça que esteja na posiçao de destino
		
		board.placePiece(p, target); //Coloca a peça tirada da posição de origem e coloca na posição de destino
		
		return capturedPiece;
	}
	
	//Verifica se a posição de destino é um movimento possivel da peça de sua posição de origem
	private void validateTargerPosition(Position source, Position target) {
		if( !board.piece(source).possibleMove(target) ) { //Se não for um movimento possível, lança uma exceção
			throw new ChessException("Movimento invalido para esta peca!");
		}
	}

	//Verifica se há uma peça na posição, e se há movimento possivel
	private void validateSourcePosition(Position position) {
		if( !board.thereIsAPiece(position) ) {
			throw new ChessException("Nao existe peca nesta posicao de origem!");
		}
		if( !board.piece(position).isThereAnyPossibleMove() ) { //Se não existe algum movimento possivel?
			throw new ChessException("Nao existe movimentos possiveis para a peca escolhida!");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	// Metodo responsavel por iniciar a partida de xadrez
	// Colocando as peças no tabuleiro
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}

}
