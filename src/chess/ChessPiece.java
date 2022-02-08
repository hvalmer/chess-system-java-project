package chess;

import boardgame.Board;
import boardgame.Piece;

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
}
