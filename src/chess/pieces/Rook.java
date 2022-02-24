package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	// construtor repassa a chamada para a super classe
	public Rook(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {// convertendo uma pe�a torre(Rook) p String
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// implementando os movimentos poss�veis da torre
		Position p = new Position(0, 0);

		// verificando posi��es acima(above) da pe�a...position � a posi��o da pe�a
		p.setValues(position.getRow() - 1, position.getColumn());
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia, vou marcar a posi��o como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			// fazendo a linha dessa posi��o andar mais uma casa pra cima
			p.setRow(p.getRow() - 1);
		}
		// se existe uma casa e se essa casa possui uma pe�a advers�ria
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		// verificando posi��es a esquerda(left) da pe�a...position � a posi��o da pe�a
		p.setValues(position.getRow(), position.getColumn() - 1);
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia, vou marcar a posi��o como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			// fazendo a linha dessa posi��o andar mais uma casa pra esquerda
			p.setColumn(p.getColumn() - 1);
		}
		// se existe uma casa e se essa casa possui uma pe�a advers�ria
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posi��es a direita(right) da pe�a...position � a posi��o da pe�a
		p.setValues(position.getRow(), position.getColumn() + 1);
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia, vou marcar a posi��o como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			// fazendo a linha dessa posi��o andar mais uma casa pra direita
			p.setColumn(p.getColumn() + 1);
		}
		// se existe uma casa e se essa casa possui uma pe�a advers�ria
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posi��es abaixo(below) da pe�a...position � a posi��o da pe�a
		p.setValues(position.getRow() + 1, position.getColumn());
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia, vou marcar a posi��o como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			// fazendo a linha dessa posi��o andar mais uma casa pra cima
			p.setRow(p.getRow() + 1);
		}
		// se existe uma casa e se essa casa possui uma pe�a advers�ria
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		return matriz;
	}
}
