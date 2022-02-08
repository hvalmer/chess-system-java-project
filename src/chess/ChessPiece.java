package chess;

import boardgame.Board;
import boardgame.Piece;

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
}
