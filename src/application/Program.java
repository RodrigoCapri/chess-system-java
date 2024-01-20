package application;

import java.util.Scanner;

import chess.ChessMacth;
import chess.ChessPiece;
import chess.ChessPosition;

//Projeto jogo de Xadrez
public class Program {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMacth chessMatch = new ChessMacth();
		
		while(true) {
			UI.printBoard(chessMatch.getPieces());
			
			System.out.println();
			
			System.out.print("Source: ");
			ChessPosition source = UI.readCheesPosition(sc);
			System.out.println();
			
			System.out.print("Target: ");
			ChessPosition target = UI.readCheesPosition(sc);
			System.out.println();
			
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
		}
		
	}

}
