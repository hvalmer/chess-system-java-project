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
	public String toString() {//convertendo uma pe�a Bispo p String
		return "B"; 
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// implementando os movimentos poss�veis do Bispo
		Position p = new Position(0, 0);

		// verificando posi��es na diagonal noroeste(nw) da pe�a...position � a posi��o da pe�a
		p.setValues(position.getRow() - 1, position.getColumn() -1);
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia, vou marcar a posi��o como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() -1, p.getColumn() -1);;
		}
		// se existe uma casa e se essa casa possui uma pe�a advers�ria
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		// verificando posi��es na diagonal nordeste(ne) da pe�a...position � a posi��o da pe�a
		p.setValues(position.getRow() -1, position.getColumn() + 1);
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia, vou marcar a posi��o como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow()-1, p.getColumn() +1);
		}
		// se existe uma casa e se essa casa possui uma pe�a advers�ria
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posi��es na diagonal sudeste(se) da pe�a...position � a posi��o da pe�a
		p.setValues(position.getRow() +1, position.getColumn() + 1);
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia, vou marcar a posi��o como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		// se existe uma casa e se essa casa possui uma pe�a advers�ria
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posi��es na diagonal sudoeste(sw) da pe�a...position � a posi��o da pe�a
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia, vou marcar a posi��o como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		// se existe uma casa e se essa casa possui uma pe�a advers�ria
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		return matriz;
	}
}
