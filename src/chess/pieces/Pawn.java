package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	//construtor repassa a chamada para a super classe
	public Pawn(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {//convertendo uma peça rei(king) p String
		return "P"; 
	}

}
