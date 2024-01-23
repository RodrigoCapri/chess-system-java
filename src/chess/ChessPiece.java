package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//ChessPiece também é uma Piece
//Classe que estende a classe Piece
public abstract class ChessPiece extends Piece{
	
	private Color color;
	
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return this.moveCount;
	}
	
	//Incrementa um movimento na peça
	public void increaseMoveCount() {
		this.moveCount++;
	}
	
	//Decrementa um movimento na peça
	public void decreaseMoveCount() {
		this.moveCount--;
	}
	
	//Retorna uma posição de xadrez (não de matriz)
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	//Protected pois vai ser acessada apenas pelo pacote e subclasses
	//Verifica se existe uma peça adversária na posição especificada
	protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);
		return p != null && p.getColor() != this.color;
	}
	
}
