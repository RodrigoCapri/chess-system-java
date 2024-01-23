package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

//Classe do Peão
public class Pawn extends ChessPiece{

	public Pawn(Board board, Color color) {
		super(board, color);
		
	}

	@Override
	public boolean[][] possibleMoves() {
		
		boolean mat[][] = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);
		
		if( this.getColor() == Color.WHITE ) {
			//Define uma posição para cima
			p.setValues(this.position.getRow() - 1 , this.position.getColumn());
			//Verifica se a peça pode ir para esta posição
			if( this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p) ) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//Define duas posição para cima
			p.setValues(this.position.getRow() - 2 , this.position.getColumn());
			Position p2 = new Position( this.position.getRow() - 1 , this.position.getColumn() );
			//Verifica se a peça pode ir para esta posição e se é o primeiro movimento da peça
			if( this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p) && //Teste se duas casa pra frente é um movimento possivel
				this.getBoard().positionExists(p2) && !this.getBoard().thereIsAPiece(p2) && //Testa também se não há nenhum empedimento na primeira posição
				this.getMoveCount() == 0 ) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			
			//Posição diagonal se houver peça para capturar
			p.setValues(this.position.getRow() - 1 , this.position.getColumn() - 1);
			//Verifica se a peça pode ir para esta posição
			if( this.getBoard().positionExists(p) && this.isThereOpponentPiece(p) ) { //Teste se a posição existe e se tem alguma peça adversária
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//Posição diagonal se houver peça para capturar
			p.setValues(this.position.getRow() - 1 , this.position.getColumn() + 1);
			//Verifica se a peça pode ir para esta posição
			if( this.getBoard().positionExists(p) && this.isThereOpponentPiece(p) ) { //Teste se a posição existe e se tem alguma peça adversária
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			
		}else {
			//Define uma posição para cima
			p.setValues(this.position.getRow() + 1 , this.position.getColumn());
			//Verifica se a peça pode ir para esta posição
			if( this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p) ) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//Define duas posição para cima
			p.setValues(this.position.getRow() + 2 , this.position.getColumn());
			Position p2 = new Position( this.position.getRow() + 1 , this.position.getColumn() );
			//Verifica se a peça pode ir para esta posição e se é o primeiro movimento da peça
			if( this.getBoard().positionExists(p) && !this.getBoard().thereIsAPiece(p) && //Teste se duas casa pra frente é um movimento possivel
				this.getBoard().positionExists(p2) && !this.getBoard().thereIsAPiece(p2) && //Testa também se não há nenhum empedimento na primeira posição
				this.getMoveCount() == 0 ) {
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			
			//Posição diagonal se houver peça para capturar
			p.setValues(this.position.getRow() + 1 , this.position.getColumn() - 1);
			//Verifica se a peça pode ir para esta posição
			if( this.getBoard().positionExists(p) && this.isThereOpponentPiece(p) ) { //Teste se a posição existe e se tem alguma peça adversária
				mat[p.getRow()][p.getColumn()] = true;
			}
			
			//Posição diagonal se houver peça para capturar
			p.setValues(this.position.getRow() + 1 , this.position.getColumn() + 1);
			//Verifica se a peça pode ir para esta posição
			if( this.getBoard().positionExists(p) && this.isThereOpponentPiece(p) ) { //Teste se a posição existe e se tem alguma peça adversária
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}

}
