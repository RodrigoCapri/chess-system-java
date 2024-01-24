package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;


//Classe do Bispo
public class Bishop extends ChessPiece{

	public Bishop(Board board, Color color) {
		super(board, color);
		
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// ACIMA - ESQUERDA
		p.setValues( position.getRow() - 1 , position.getColumn() - 1 );
		// Enquanto a posição p exisitir e não tiver uma peça
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // Marca a posição como verdadeira
			p.setValues( p.getRow() - 1 , p.getColumn() - 1 ); //Avança mais uma casa
		}
		// Verifica se a casa existe uma peça adverária
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // Se exisitir, então marca a posição como verdadeira
		}

		// ACIMA - DIREITA
		p.setValues( position.getRow() - 1, position.getColumn() + 1);
		// Enquanto a posição p exisitir e não tiver uma peça
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // Marca a posição como verdadeira
			p.setValues( p.getRow() - 1 , p.getColumn() + 1 ); //Avança mais uma casa
		}
		// Verifica se a casa existe uma peça adverária
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // Se exisitir, então marca a posição como verdadeira
		}

		// ABAIXO - ESQUERDA
		p.setValues( position.getRow() + 1, position.getColumn() - 1);
		// Enquanto a posição p exisitir e não tiver uma peça
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // Marca a posição como verdadeira
			p.setValues( p.getRow() + 1 , p.getColumn() - 1 ); //Avança mais uma casa
		}
		// Verifica se a casa existe uma peça adverária
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // Se exisitir, então marca a posição como verdadeira
		}

		// ABAIXO - DIREITA
		p.setValues( position.getRow() + 1, position.getColumn() + 1);
		// Enquanto a posição p exisitir e não tiver uma peça
		while (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // Marca a posição como verdadeira
			p.setValues( p.getRow() + 1 , p.getColumn() + 1 ); //Avança mais uma casa
		}
		// Verifica se a casa existe uma peça adverária
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true; // Se exisitir, então marca a posição como verdadeira
		}

		return mat;
	}
	
	@Override
	public String toString() {
		return "B";
	}

}
