package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Classe do Cavalo
public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// L -> ACIMA - ESQUERDA
		p.setValues( position.getRow() - 2, position.getColumn() - 1 );
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L -> ACIMA - DIREITA
		p.setValues( position.getRow() - 2, position.getColumn() + 1 );
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L -> ESQUERDA - ACIMA
		p.setValues( position.getRow() - 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L -> ESQUERDA - ABAIXO
		p.setValues( position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L -> ABAIXO - ESQUERDA
		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L -> ABAIXO - DIREITA
		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L -> DIREITA - ACIMA
		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		// L -> DIREITA - ABAIXO
		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && this.canMove(p)) { // Se esta posição existe e pode mover para esta posição
			mat[p.getRow()][p.getColumn()] = true;
		}

		return mat;
	}

	// Verifica se pode mover a peça para a posição
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		//Se a posição estiver vazia e não tiver peça do próprio jogador
		//Então retorna true, pode mover para esta posição
		return p == null || p.getColor() != getColor();
	}

	@Override
	public String toString() {
		return "N";
	}

}
