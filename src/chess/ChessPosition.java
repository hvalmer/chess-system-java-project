package chess;

import boardgame.Position;

public class ChessPosition {

	private char column;
	private int row;
	
	//construtor com argumentos
	public ChessPosition(char column, int row) {
		//programação defensiva
		if(column < 'a' || column > 'h' || row < 1 || row > 8) {
			throw new ChessException("Error instantiating ChessPosition. Valid values are from a1 to h8");
		}
		this.column = column;
		this.row = row;
	}

	//apagando os Set para que as linhas e colunas não sejam livremente alteradas
	public char getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}
	
	//método para converter o ChessPosition para o Position normal da matriz
	protected Position toPosition() {
		return new Position(8 - row, column - 'a');
	}
	
	//dada uma posição na matriz, converter para uma posição de xadrez
	protected static ChessPosition fromPosition(Position position) {
		//método retorna a forma normal da matriz para posição de xadrez
		return new ChessPosition((char)('a' + position.getColumn()), 8 - position.getRow());
	}
	
	//fazendo o toString da posição
	@Override
	public String toString() {
		return "" + column + row;//concatenação de Strings
	}
}
