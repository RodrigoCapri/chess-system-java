package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMacth;
import chess.ChessPiece;
import chess.Color;

//Classe do Rei
public class King extends ChessPiece {

	// Dependencia para a partida
	private ChessMacth chessMatch;

	public King(Board board, Color color, ChessMacth chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// ACIMA
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ABAIXO
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ESQUERDA
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ESQUERDA
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ACIMA - ESQUERDA
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ACIMA - DIREITA
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ABAIXO - ESQUERDA
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ABAIXO - DIREITA
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// ESPECIAL MOVING CASTLING
		if (this.getMoveCount() == 0 && !this.chessMatch.getCheck()) {// Se o movimento for zero e não está em check
			// #Movimento especial Rock pequeno
			Position posT1 = new Position(position.getRow(), position.getColumn() + 3); //Pega a posição da torre
			if (this.testTookCastling(posT1)) { //Verifica se a torre está apta para o Rock
				//Pega as posições entre o Rei e a torre para verificar se está vazia
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if ((this.getBoard().piece(p1) == null) && (this.getBoard().piece(p2) == null)) {
					//Se estiver vazia, marca como possivel movimento
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			// #Movimento especial Rock grande
			Position posT2 = new Position(position.getRow(), position.getColumn() - 4); //Pega a posição da torre
			if (this.testTookCastling(posT2)) { //Verifica se a torre está apta para o Rock
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if ( (this.getBoard().piece(p1) == null) && 
					 (this.getBoard().piece(p2) == null) &&
					 (this.getBoard().piece(p3) == null) ) {
					//Se estiver vazia, marca como possivel movimento
					mat[position.getRow()][position.getColumn() - 2] = true;
				}
			}
		}

		return mat;
	}

	// Verifica se pode mover a peça para a posição
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	// Função auxiliar para testar se a torre está apta para o Rock
	private boolean testTookCastling(Position position) {
		ChessPiece p = (ChessPiece) this.getBoard().piece(position); // Pega a peça que está na posição

		// Se tudo der verdadeiro, temos uma torre apta para o Rock
		return (p != null) && (p instanceof Rook) && (p.getColor() == this.getColor()) && (p.getMoveCount() == 0);
	}

	@Override
	public String toString() {
		return "K"; // Retorna a inicial da peça
	}

}
