package boardgame;

public class Piece {
	
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
	
}
