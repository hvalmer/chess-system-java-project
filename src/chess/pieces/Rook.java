package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	//construtor repassa a chamada para a super classe
	public Rook(Board board, Color color) {
		super(board, color);
	}
	
	@Override
	public String toString() {//convertendo uma peça torre(Rook) p String
		return "R"; 
	}
	
	@Override
	public boolean[][] possibleMoves() {
		boolean [][] matriz = new boolean [getBoard().getRows()][getBoard().getColumns()];
		return matriz;
	}
}
