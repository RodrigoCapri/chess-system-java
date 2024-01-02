package application;

import chess.ChessMacth;

//Projeto jogo de Xadrez
public class Program {

	public static void main(String[] args) {
		
		ChessMacth chessMatch = new ChessMacth();
		UI.printBoard(chessMatch.getPieces());
		
	}

}
