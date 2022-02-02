package boardgame;

public class Piece {

	protected Position position;
	private Board board;
	
	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	/* sem setBoard() para n�o alterar 
	 * protected: somente classes dentro do mesmo pacote 
	 * e subclasses � que v�o poder acessar um
	 * tabuleiro de uma pe�a. O tabuleiro � de uso interno
	 * da camada de tabuleiro, n�o sendo acess�vel pela camada
	 * de xadrez. O tabuleiro � de uso interno da camada de tabuleiro */
	protected Board getBoard() {
		return board;
	}

}
