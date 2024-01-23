package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		
		List<ChessPiece> captured = new ArrayList<ChessPiece>();

		while ( !chessMatch.getCheckMate() ) { //Enquanto a partida não estiver em checkmate
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);

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

				//Efetua o movimento e captura uma possivel peça
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				//Verifica se uma peça foi capturada e adiciona na lista de peças capturadas
				if( capturedPiece != null) {
					captured.add(capturedPiece);
				}

			} catch (ChessException | InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine(); // Aguarda o usuário apertar Enter
			}

		}
		
		//Imprime a partida finalizada
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);

	}

}
