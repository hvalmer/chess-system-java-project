package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	// construtor repassa a chamada para a super classe
	public Knight(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {// convertendo uma peça cavalo(Knight) p String
		return "N";
	}

	// método pode mover o cavalo para uma determinada posição
	private boolean canMove(Position position) {
		ChessPiece chp = (ChessPiece)getBoard().piece(position);
		return chp == null || chp.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// testando as 8 posições possíveis que o cavalo pode se mover
		p.setValues(position.getRow() - 1, position.getColumn() - 2);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColumn() - 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() - 2, position.getColumn() + 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		return matriz;
	}
}
