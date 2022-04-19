package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

	// construtor repassa a chamada para a super classe
	public Queen(Board board, Color color) {
		super(board, color);
	}

	@Override
	public String toString() {// convertendo uma pe�a Rainha(Queen) p String
		return "Q";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// implementando os movimentos poss�veis da Rainha
		Position p = new Position(0, 0);

		// L�gica dos movimentos acima, abaixo, esquerda, direita

		// verificando posi��es acima(above) da pe�a...position � a posi��o da pe�a
		p.setValues(position.getRow() - 1, position.getColumn());
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia,
		// vou marcar a posi��o como
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
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia,
		// vou marcar a posi��o como
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
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia,
		// vou marcar a posi��o como
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
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia,
		// vou marcar a posi��o como
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

		// l�gica das diagonais

		// verificando posi��es na diagonal noroeste(nw) da pe�a...position � a posi��o
		// da pe�a
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia,
		// vou marcar a posi��o como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
			;
		}
		// se existe uma casa e se essa casa possui uma pe�a advers�ria
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		// verificando posi��es na diagonal nordeste(ne) da pe�a...position � a posi��o
		// da pe�a
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia,
		// vou marcar a posi��o como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		// se existe uma casa e se essa casa possui uma pe�a advers�ria
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posi��es na diagonal sudeste(se) da pe�a...position � a posi��o
		// da pe�a
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia,
		// vou marcar a posi��o como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		// se existe uma casa e se essa casa possui uma pe�a advers�ria
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posi��es na diagonal sudoeste(sw) da pe�a...position � a posi��o
		// da pe�a
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		// enquanto a posi��o p existir e � tiver uma pe�a l�, ou seja, posi��o vazia,
		// vou marcar a posi��o como
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
