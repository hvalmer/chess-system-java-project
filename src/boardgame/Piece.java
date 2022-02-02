package boardgame;

public class Piece {

	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	/* sem setBoard() para não alterar 
	 * protected: somente classes dentro do mesmo pacote 
	 * e subclasses é que vão poder acessar um
	 * tabuleiro de uma peça. O tabuleiro é de uso interno
	 * da camada de tabuleiro, não sendo acessível pela camada
	 * de xadrez. O tabuleiro é de uso interno da camada de tabuleiro */
	protected Board getBoard() {
		return board;
	}

}
