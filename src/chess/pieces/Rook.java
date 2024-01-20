package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//Classe da Torre
public class Rook extends ChessPiece{

	public Rook(Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];
		
		return mat;
	}
	
	@Override
	public String toString() { //Retorna a inical da peça
		return "R";
	}
	
}
