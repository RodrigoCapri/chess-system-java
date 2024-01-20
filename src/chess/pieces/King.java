package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Classe do Rei
public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);

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

		return mat;
	}

	// Verifica se pode mover a peça para a posição
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	@Override
	public String toString() {
		return "K"; // Retorna a inicial da peça
	}

}
