package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMacth;
import chess.ChessPiece;
import chess.ChessPosition;

//Projeto jogo de Xadrez
public class Program {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ChessMacth chessMatch = new ChessMacth();

		while (true) {
			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());

				System.out.println();

				System.out.print("Source: ");
				ChessPosition source = UI.readCheesPosition(sc);
				System.out.println();

				System.out.print("Target: ");
				ChessPosition target = UI.readCheesPosition(sc);
				System.out.println();

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

			} catch (ChessException | InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine(); // Aguarda o usuário apertar Enter
			}

		}

	}

}
