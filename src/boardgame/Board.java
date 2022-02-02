package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	// construtor com argumentos
	public Board(int rows, int columns) {
		// implementando programa��o defensiva com um Exception persolanizado
		if (rows < 1 || columns < 1) {
			throw new BoardException("Error creating board: there must be at least 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Piece[rows][columns];
	}

	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	// m�todo retorna a pe�a dada com a linha e coluna
	public Piece piece(int row, int column) {
		// implementando programa��o defensiva com um Exception persolanizado
		if (!positionExistis(row, column)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[row][column];
	}

	// sobrecarga do m�todo
	public Piece piece(Position position) {
		// implementando programa��o defensiva com um Exception persolanizado
		if (!positionExistis(position)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	// m�todo respons�vel por colocar a pe�a na posi��o do tabuleiro
	public void placePiece(Piece piece, Position position) {
		// verificando se j� existe uma pe�a na posi��o
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	// m�todo auxiliar
	private boolean positionExistis(int row, int column) {
		// condi��o completa pra v� se uma posi��o existe
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	// testando se a posi��o existe
	public boolean positionExistis(Position position) {
		return positionExistis(position.getRow(), position.getColumn());
	}

	// testando se existe uma pe�a nessa posi��o
	public boolean thereIsAPiece(Position position) {
		// implementando programa��o defensiva com um Exception persolanizado
		if (!positionExistis(position)) {
			throw new BoardException("Position not on the board!");
		}
		return piece(position) != null;
	}
}
