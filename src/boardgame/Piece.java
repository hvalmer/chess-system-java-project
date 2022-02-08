package boardgame;

public abstract class Piece {

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

	//método abstrato, possíveis movimentos
	public abstract boolean[][] possibleMoves();
	
	//Hook Method...método que faz um gancho com a subclasse
	//possibleMoves está chamando uma possível implementação de alguma subclasse concreta da classe Piece
	//se a peça é possivel ou não mover para essa dada posição
	//o método concreto(possibleMove) está utilizando o método abstrato(possibleMoves)
	public boolean possibleMove(Position position){
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	//operação que diz se existe ao menos um movimento possível para essa peça
	public boolean isThereAnyPossibleMove() {
		//variável auxiliar
		//retorna uma matriz de booleano, varrendo a matriz p verificar se existe pelo menos uma posição que seja verdadeira
		boolean [][] matriz = possibleMoves();//chamada do método abstrato
		for(int i=0; i<matriz.length; i++) {
			for(int j=0; j<matriz.length; j++) {
				//testando...
				if(matriz[i][j]) {
					return true;//existe um movimento possível
				}
			}
		}
		return false;//ñ existe nenhum movimento possível
	}
}
