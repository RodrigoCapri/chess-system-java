package boardgame;

public abstract class Piece {
	
	//Protected: somente classes e subclasses do mesmo pacote vão poder acessar o atributo
	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	protected Board getBoard() {
		return board;
	}
	
	//Abstrata pois cada peça que herdar esta classe tem seus movimentos distintos
	public abstract boolean[][] possibleMoves();
	
	//Retorna se a posição desejada é possivel movimentar-se
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	//Verifica se há pelo menos uma posição válida para mover a peça
	public boolean isThereAnyPossibleMove() {
		boolean [][] mat = possibleMoves();
		
		for(int i=0; i<mat.length; i++) {
			for(int j=0; j<mat.length; j++) {
				if( mat[i][j] )
					return true;
			}
		}
		
		return false;
	}
	
}
