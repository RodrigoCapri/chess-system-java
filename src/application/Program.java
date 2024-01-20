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

				//Usuário entra com a posição de origem
				System.out.print("Source: ");
				ChessPosition source = UI.readCheesPosition(sc);
				System.out.println();
				
				//Imprime na tela os possiveis moviemntos a partir da peça escolhida
				boolean [][]possibleMovies = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMovies);

				//Usuário entra com a posiçao de destino
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
