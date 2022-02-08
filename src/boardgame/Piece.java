package boardgame;

public abstract class Piece {

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

	//m�todo abstrato, poss�veis movimentos
	public abstract boolean[][] possibleMoves();
	
	//Hook Method...m�todo que faz um gancho com a subclasse
	//possibleMoves est� chamando uma poss�vel implementa��o de alguma subclasse concreta da classe Piece
	//se a pe�a � possivel ou n�o mover para essa dada posi��o
	//o m�todo concreto(possibleMove) est� utilizando o m�todo abstrato(possibleMoves)
	public boolean possibleMove(Position position){
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	//opera��o que diz se existe ao menos um movimento poss�vel para essa pe�a
	public boolean isThereAnyPossibleMove() {
		//vari�vel auxiliar
		//retorna uma matriz de booleano, varrendo a matriz p verificar se existe pelo menos uma posi��o que seja verdadeira
		boolean [][] matriz = possibleMoves();//chamada do m�todo abstrato
		for(int i=0; i<matriz.length; i++) {
			for(int j=0; j<matriz.length; j++) {
				//testando...
				if(matriz[i][j]) {
					return true;//existe um movimento poss�vel
				}
			}
		}
		return false;//� existe nenhum movimento poss�vel
	}
}
