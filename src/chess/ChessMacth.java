package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMacth {

	private Board board;
	
	public ChessMacth() {
		this.board = new Board(8, 8);
		this.initialSetup();
	}
	
	//Retorna a matriz de peças da  partida de xadrez
	public ChessPiece[][] getPieces(){
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for(int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	//Metodo responsavel por iniciar a partida de xadrez
	//Colocando as peças no tabuleiro
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1)); //Adiciona a torre
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4)); //Adiciona o Rei
		board.placePiece(new King(board, Color.WHITE), new Position(0, 4)); //Adiciona o Rei
	}
	
}
