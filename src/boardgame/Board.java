package boardgame;

public class Board {

	private int rows;
	private int columns;
	private Piece[][] pieces;

	// construtor com argumentos
	public Board(int rows, int columns) {
		// implementando programação defensiva com um Exception persolanizado
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

	// método retorna a peça dada com a linha e coluna
	public Piece piece(int row, int column) {
		// implementando programação defensiva com um Exception persolanizado
		if (!positionExistis(row, column)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[row][column];
	}

	// sobrecarga do método
	public Piece piece(Position position) {
		// implementando programação defensiva com um Exception persolanizado
		if (!positionExistis(position)) {
			throw new BoardException("Position not on the board!");
		}
		return pieces[position.getRow()][position.getColumn()];
	}

	// método responsável por colocar a peça na posição do tabuleiro
	public void placePiece(Piece piece, Position position) {
		// verificando se já existe uma peça na posição
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}
	
	//método para remover uma peça
	public Piece removePiece(Position position) {
		//programação defensiva
		if(!positionExistis(position)) {
			throw new BoardException("Position not on the board!");
		}
		if(piece(position) == null) {
			return null;
		}
		Piece aux = piece(position);
		//a peça foi retirada do tabuleiro, não tem posição mais(null)
		aux.position = null;
		//matriz de peças, o null indica que não tem mais peças na posição da matriz
		pieces[position.getRow()][position.getColumn()] = null;
		return aux;
	}

	// método auxiliar
	private boolean positionExistis(int row, int column) {
		// condição completa pra vê se uma posição existe
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}

	// testando se a posição existe
	public boolean positionExistis(Position position) {
		return positionExistis(position.getRow(), position.getColumn());
	}

	// testando se existe uma peça nessa posição
	public boolean thereIsAPiece(Position position) {
		// implementando programação defensiva com um Exception persolanizado
		if (!positionExistis(position)) {
			throw new BoardException("Position not on the board!");
		}
		return piece(position) != null;
	}
}
