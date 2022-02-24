package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {

	//construtor repassa a chamada para a super classe
	public Bishop(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {//convertendo uma peça rei(king) p String
		return "B"; 
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean [][] matriz = new boolean [getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0,0);
		
		//verificando na diagonal()
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		return matriz;
	}
}
