package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;
	
	public Board(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	//m�todo retorna a pe�a dada com a linha e coluna
	public Piece piece(int row, int column) {
		return pieces[row][column];
	}
	
	//sobrecarga do m�todo
	public Piece piece(Position position) {
		return pieces[position.getRow()][position.getColumn()];
	}
	
	//m�todo respons�vel por colocar a pe�a na posi��o do tabuleiro
	public void placePiece(Piece piece, Position position) {
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
}