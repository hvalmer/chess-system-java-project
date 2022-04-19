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
	public String toString() {// convertendo uma peça Rainha(Queen) p String
		return "Q";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// implementando os movimentos possíveis da Rainha
		Position p = new Position(0, 0);

		// Lógica dos movimentos acima, abaixo, esquerda, direita

		// verificando posições acima(above) da peça...position é a posição da peça
		p.setValues(position.getRow() - 1, position.getColumn());
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia,
		// vou marcar a posição como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			// fazendo a linha dessa posição andar mais uma casa pra cima
			p.setRow(p.getRow() - 1);
		}
		// se existe uma casa e se essa casa possui uma peça adversária
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		// verificando posições a esquerda(left) da peça...position é a posição da peça
		p.setValues(position.getRow(), position.getColumn() - 1);
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia,
		// vou marcar a posição como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			// fazendo a linha dessa posição andar mais uma casa pra esquerda
			p.setColumn(p.getColumn() - 1);
		}
		// se existe uma casa e se essa casa possui uma peça adversária
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posições a direita(right) da peça...position é a posição da peça
		p.setValues(position.getRow(), position.getColumn() + 1);
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia,
		// vou marcar a posição como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			// fazendo a linha dessa posição andar mais uma casa pra direita
			p.setColumn(p.getColumn() + 1);
		}
		// se existe uma casa e se essa casa possui uma peça adversária
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posições abaixo(below) da peça...position é a posição da peça
		p.setValues(position.getRow() + 1, position.getColumn());
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia,
		// vou marcar a posição como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			// fazendo a linha dessa posição andar mais uma casa pra cima
			p.setRow(p.getRow() + 1);
		}
		// se existe uma casa e se essa casa possui uma peça adversária
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// lógica das diagonais

		// verificando posições na diagonal noroeste(nw) da peça...position é a posição
		// da peça
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia,
		// vou marcar a posição como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() - 1);
			;
		}
		// se existe uma casa e se essa casa possui uma peça adversária
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		// verificando posições na diagonal nordeste(ne) da peça...position é a posição
		// da peça
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia,
		// vou marcar a posição como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() - 1, p.getColumn() + 1);
		}
		// se existe uma casa e se essa casa possui uma peça adversária
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posições na diagonal sudeste(se) da peça...position é a posição
		// da peça
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia,
		// vou marcar a posição como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		// se existe uma casa e se essa casa possui uma peça adversária
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posições na diagonal sudoeste(sw) da peça...position é a posição
		// da peça
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia,
		// vou marcar a posição como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() - 1);
		}
		// se existe uma casa e se essa casa possui uma peça adversária
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		
		return matriz;
	}
}
