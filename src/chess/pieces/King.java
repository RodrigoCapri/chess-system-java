package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

//Classe do Rei
public class King extends ChessPiece{

	public King(Board board, Color color) {
		super(board, color);
		
	}
	
	@Override
	public String toString() {
		return "K"; //Retorna a inicial da peça
	}

}
