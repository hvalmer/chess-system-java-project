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
	public String toString() {// convertendo uma pe�a rei(king) p String
		return "K";
	}

	// m�todo pode mover o rei para uma determinada posi��o
	private boolean canMove(Position position) {
		ChessPiece chp = (ChessPiece)getBoard().piece(position);
		// verificando se chp n�o � nula e tamb�m � uma pe�a advers�ria
		//poder� mover o rei qdo. a posi��o for igual a nulo OU a cor for diferente
		return chp == null || chp.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];

		Position p = new Position(0, 0);

		// testando as 8 posi��es poss�veis que o rei pode se mover
		// posi��o above(acima) do rei
		p.setValues(position.getRow() - 1, position.getColumn());
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posi��o below(abaixo) do rei
		p.setValues(position.getRow() + 1, position.getColumn());
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posi��o left(esquerda) do rei
		p.setValues(position.getRow(), position.getColumn() - 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posi��o right(direita) do rei
		p.setValues(position.getRow(), position.getColumn() + 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posi��o nw(noroeste) do rei
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posi��o ne(nordeste) do rei
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posi��o sw(sudoeste) do rei
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// posi��o se(sudeste) do rei
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		// testando...
		if (getBoard().positionExistis(p) && canMove(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		return matriz;
	}
}
