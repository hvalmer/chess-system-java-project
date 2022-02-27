package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

//ChessPiece � uma subclasse de Piece
public abstract class ChessPiece extends Piece {

	private Color color;

	public ChessPiece(Board board, Color color) {
		super(board);//o board repassa achamada p o construtor da superclasse, que � o construtor da classe Piece
		this.color = color;
	}

	//apenas o get, pq com o set se modifica a cor, e n�o � o caso
	public Color getColor() {
		return color;
	}
	
	//m�todo para retornar uma posi��o, no formato do xadrez na classe ChessPiece
	//o m�todo est�tico fromPosition pega o position e converte para posi��o de xadrez(ChessPosition)
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	//opera��o gen�rica, vai ser reaproveitada em todas as outras pe�as
	//protected - opera��o acessada somente pelo pacote chess e as subclasses chess.pieces
	protected boolean isThereOpponentPiece(Position position) {
		//implementa��o para verificar se existe uma pe�a advers�ria em position
		ChessPiece chp = (ChessPiece)getBoard().piece(position);
		//testando se a cor da minha pe�a � diferente da cor da pe�a advers�ria
		return chp != null && chp.getColor() != color;
	}
}
