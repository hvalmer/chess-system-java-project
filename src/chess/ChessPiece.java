package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//ChessPiece é uma subclasse de Piece
public abstract class ChessPiece extends Piece {

	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);//o board repassa achamada p o construtor da superclasse, que é o construtor da classe Piece
		this.color = color;
	}

	//apenas o get, pq com o set se modifica a cor, e não é o caso
	public Color getColor() {
		return color;
	}
	
	//método para retornar uma posição, no formato do xadrez na classe ChessPiece
	//o método estático fromPosition pega o position e converte para posição de xadrez(ChessPosition)
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	//operação genérica, vai ser reaproveitada em todas as outras peças
	//protected - operação acessada somente pelo pacote chess e as subclasses chess.pieces
	protected boolean isThereOpponentPiece(Position position) {
		//implementação para verificar se existe uma peça adversária em position
		ChessPiece chp = (ChessPiece)getBoard().piece(position);
		//testando se a cor da minha peça é diferente da cor da peça adversária
		return chp != null && chp.getColor() != color;
	}
}
