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
	public String toString() {// convertendo uma peça torre(Rook) p String
		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// implementando os movimentos possíveis da torre
		Position p = new Position(0, 0);

		// verificando posições acima(above) da peça...position é a posição da peça
		p.setValues(position.getRow() - 1, position.getColumn());
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia, vou marcar a posição como
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
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia, vou marcar a posição como
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
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia, vou marcar a posição como
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
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia, vou marcar a posição como
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
		return matriz;
	}
}
