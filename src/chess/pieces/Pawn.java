package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	//construtor repassa a chamada para a super classe
	public Pawn(Board board, Color color) {
		super(board, color);
	}
	

	//l�gica de como o Pe�o pode se mover no xadrez
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// implementando os movimentos poss�veis da torre
		Position p = new Position(0, 0);
		
		//movendo o pe�o branco na linha com uma posi��o acima -1, regra geral
		if(getColor() == Color.WHITE) {
			p.setValues(position.getRow() -1, position.getColumn());
			//testando se a posi��o acima da linha dele(-1) existir e tiver vazia, pode mover pra cima
			if(getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			//movendo o pe�o duas casas pra frente (-2)
			p.setValues(position.getRow() -2, position.getColumn());
			//testando se a primeira casa est� livre
			Position p2 = new Position(position.getRow() -1, position.getColumn());
			if(getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p) &&
			   getBoard().positionExistis(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0 ) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			//movendo o pe�o na diagonal esquerda
			p.setValues(position.getRow() -1, position.getColumn() -1);
			//testando se a posi��o na diagonal acima da linha dele(-1) existir e tiver vazia
			if(getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			//movendo o pe�o na diagonal direita
			p.setValues(position.getRow() -1, position.getColumn() +1);
			//testando se a posi��o na diagonal acima da linha dele(-1) existir e tiver vazia
			if(getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
		}
		//movendo o pe�o preto para baixo
		else {
			p.setValues(position.getRow() +1, position.getColumn());
			//testando se a posi��o abaixo da linha dele(+1) existir e tiver vazia, pode mover pra baixo
			if(getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			//movendo o pe�o duas casas pra frente (+2)
			p.setValues(position.getRow() +2, position.getColumn());
			//testando se a primeira casa est� livre
			Position p2 = new Position(position.getRow() +1, position.getColumn());
			if(getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p) &&
			   getBoard().positionExistis(p2) && !getBoard().thereIsAPiece(p2) && getMoveCount() == 0 ) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			//movendo o pe�o na diagonal esquerda
			p.setValues(position.getRow() +1, position.getColumn() -1);
			//testando se a posi��o na diagonal acima da linha dele(+1) existir e tiver vazia
			if(getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			//movendo o pe�o na diagonal direita
			p.setValues(position.getRow() +1, position.getColumn() +1);
			//testando se a posi��o na diagonal acima da linha dele(+1) existir e tiver vazia
			if(getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
		}
		return matriz;
	}
	
	@Override
	public String toString() {
		return "p";
	}
}
