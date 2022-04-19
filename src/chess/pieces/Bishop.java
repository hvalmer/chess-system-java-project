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
	public String toString() {//convertendo uma peça Bispo p String
		return "B"; 
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// implementando os movimentos possíveis do Bispo
		Position p = new Position(0, 0);

		// verificando posições na diagonal noroeste(nw) da peça...position é a posição da peça
		p.setValues(position.getRow() - 1, position.getColumn() -1);
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia, vou marcar a posição como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() -1, p.getColumn() -1);;
		}
		// se existe uma casa e se essa casa possui uma peça adversária
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}
		// verificando posições na diagonal nordeste(ne) da peça...position é a posição da peça
		p.setValues(position.getRow() -1, position.getColumn() + 1);
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia, vou marcar a posição como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow()-1, p.getColumn() +1);
		}
		// se existe uma casa e se essa casa possui uma peça adversária
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posições na diagonal sudeste(se) da peça...position é a posição da peça
		p.setValues(position.getRow() +1, position.getColumn() + 1);
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia, vou marcar a posição como
		// verdadeira(true)
		while (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() + 1, p.getColumn() + 1);
		}
		// se existe uma casa e se essa casa possui uma peça adversária
		if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
			matriz[p.getRow()][p.getColumn()] = true;
		}

		// verificando posições na diagonal sudoeste(sw) da peça...position é a posição da peça
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		// enquanto a posição p existir e ñ tiver uma peça lá, ou seja, posição vazia, vou marcar a posição como
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
