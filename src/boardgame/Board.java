package boardgame;

//Classe tabuleiro
public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	public Board(int rows, int columns) {
		if( rows < 1 || columns < 1 )
			throw new BoardException("Erro criando tabuleiro, a linha e coluna não pode ser menor de 1!");
			
		this.rows = rows;
		this.columns = columns;
		this.pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	//Retorna a peça que está na matriz pela linha e coluna
	public Piece piece(int row, int column) {
		if( !this.positionExists(row, column) )
			throw new BoardException("Position não existe no tabuleiro");
		
		return this.pieces[row][column];
	}
	
	//Retorna a peça que está na matriz pelo objeto Position
	public Piece piece(Position position) {
		if( !this.positionExists(position) )
			throw new BoardException("Position não existe no tabuleiro");
		
		return this.pieces[ position.getRow() ][ position.getColumn() ];
	}
	
	//Adiciona uma peça ao tabuleiro em uma determinada posição
	public void placePiece(Piece piece, Position position) {
		if( this.thereIsAPiece(position) )
			throw new BoardException("Já existe uma peça na posição informada! "+position);
		this.pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position; //Atribui estado não nulo ao position da piece
	}
	
	//Remove uma peça do tabuleiro
	public Piece removePiece(Position position) {
		if( !this.positionExists(position) )
			throw new BoardException("Esta posição não está no tabuleiro!");
		
		if( this.piece(position) == null)
			return null;
		
		Piece aux = this.piece(position);
		aux.position = null;
		this.pieces[position.getRow()][position.getColumn()] = null;
		
		return aux;
	}
	
	//Função que verifica se uma posição existe por linha e coluna
	private boolean positionExists(int row, int column) {
		return (row >= 0 && row < this.rows) && (column >= 0 && column < this.columns);
	}
	
	//Função que verifica se uma posição existe pelo objeto Position
	public boolean positionExists(Position position) {
		return this.positionExists(position.getRow(), position.getColumn());
	}
	
	//Verifica se existe uma peça na posição
	public boolean thereIsAPiece(Position position) {
		if( !this.positionExists(position) )
			throw new BoardException("Position não existe no tabuleiro");
		
		return this.piece(position) != null; //Se for diferente de null, então existe uma peça na posição
	}
	
}
