package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	// construtor repassa a chamada para a super classe
	public King(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {// convertendo uma peça rei(king) p String
		return "K";
	}

	// método pode mover o rei para uma determinada posição
	private boolean canMove(Position position) {
		ChessPiece chp = (ChessPiece)getBoard().piece(position);
		// verificando se chp não é nula e também é uma peça adversária
		//poderá mover o rei qdo. a posição for igual a nulo OU a cor for diferente
		return chp == null || chp.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// testando as 8 posições possíveis que o rei pode se mover
		// posição above(acima) do rei
		p.setValues(position.getRow() - 1, position.getColumn());
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posição below(abaixo) do rei
		p.setValues(position.getRow() + 1, position.getColumn());
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posição left(esquerda) do rei
		p.setValues(position.getRow(), position.getColumn() - 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posição right(direita) do rei
		p.setValues(position.getRow(), position.getColumn() + 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posição nw(noroeste) do rei
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posição ne(nordeste) do rei
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posição sw(sudoeste) do rei
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posição se(sudeste) do rei
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		return matriz;
	}
}
