package chess;

import boardgame.Board;
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
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		this.board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	//Metodo responsavel por iniciar a partida de xadrez
	//Colocando as peças no tabuleiro
	private void initialSetup() {
		placeNewPiece( 'b', 6, new Rook(board, Color.WHITE)); //Adiciona a torre
		placeNewPiece( 'e', 8, new King(board, Color.BLACK)); //Adiciona a torre
		placeNewPiece( 'e', 1, new King(board, Color.WHITE)); //Adiciona a torre
	}
	
}
