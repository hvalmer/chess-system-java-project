package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	// dependencia para a partida
	private ChessMatch chessMatch;

	// construtor repassa a chamada para a super classe
	public Pawn(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
	}

	// l�gica de como o Pe�o pode se mover no xadrez
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] matriz = new boolean[getBoard().getRows()][getBoard().getColumns()];
		// implementando os movimentos poss�veis da torre
		Position p = new Position(0, 0);

		// movendo o pe�o branco na linha com uma posi��o acima -1, regra geral
		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumn());
			// testando se a posi��o acima da linha dele(-1) existir e tiver vazia, pode
			// mover pra cima
			if (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			// movendo o pe�o duas casas pra frente (-2)
			p.setValues(position.getRow() - 2, position.getColumn());
			// testando se a primeira casa est� livre
			Position p2 = new Position(position.getRow() - 1, position.getColumn());
			if (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExistis(p2)
					&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			// movendo o pe�o na diagonal esquerda
			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			// testando se a posi��o na diagonal acima da linha dele(-1) existir e tiver
			// vazia
			if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			// movendo o pe�o na diagonal direita
			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			// testando se a posi��o na diagonal acima da linha dele(-1) existir e tiver
			// vazia
			if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}

			/*
			 * Tratamento para o en passant WHITE - quando o pe�o branco estiver na casa
			 * diagonal liberada, e tiver um outro pe�o(preto) ao lado, vulneravel ao en
			 * passant. S� poder� fazer o en passant, qando tiver na linha 5 do xadrez(3 da
			 * matriz)
			 */
			if (position.getRow() == 3) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExistis(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					// movendo o pe�o branco para uma linha acima e n�o para o lugar do pe�o preto
					matriz[left.getRow() - 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExistis(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					// movendo o pe�o branco para uma linha acima e n�o para o lugar do pe�o preto
					matriz[right.getRow() - 1][right.getColumn()] = true;
				}
			}
		}
		// movendo o pe�o preto para baixo
		else {
			p.setValues(position.getRow() + 1, position.getColumn());
			// testando se a posi��o abaixo da linha dele(+1) existir e tiver vazia, pode
			// mover pra baixo
			if (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			// movendo o pe�o duas casas pra frente (+2)
			p.setValues(position.getRow() + 2, position.getColumn());
			// testando se a primeira casa est� livre
			Position p2 = new Position(position.getRow() + 1, position.getColumn());
			if (getBoard().positionExistis(p) && !getBoard().thereIsAPiece(p) && getBoard().positionExistis(p2)
					&& !getBoard().thereIsAPiece(p2) && getMoveCount() == 0) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			// movendo o pe�o na diagonal esquerda
			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			// testando se a posi��o na diagonal acima da linha dele(+1) existir e tiver
			// vazia
			if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}
			// movendo o pe�o na diagonal direita
			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			// testando se a posi��o na diagonal acima da linha dele(+1) existir e tiver
			// vazia
			if (getBoard().positionExistis(p) && isThereOpponentPiece(p)) {
				matriz[p.getRow()][p.getColumn()] = true;
			}

			/*
			 * Tratamento para o en passant BLACK - quando o pe�o preto estiver na casa
			 * diagonal liberada, e tiver um outro pe�o(branco) ao lado, vulneravel ao en
			 * passant. S� poder� fazer o en passant, qando tiver na linha 4 do xadrez(4 da
			 * matriz)
			 */
			if (position.getRow() == 4) {
				Position left = new Position(position.getRow(), position.getColumn() - 1);
				if (getBoard().positionExistis(left) && isThereOpponentPiece(left)
						&& getBoard().piece(left) == chessMatch.getEnPassantVulnerable()) {
					// movendo o pe�o branco para uma linha acima e n�o para o lugar do pe�o preto
					matriz[left.getRow() + 1][left.getColumn()] = true;
				}
				Position right = new Position(position.getRow(), position.getColumn() + 1);
				if (getBoard().positionExistis(right) && isThereOpponentPiece(right)
						&& getBoard().piece(right) == chessMatch.getEnPassantVulnerable()) {
					// movendo o pe�o branco para uma linha acima e n�o para o lugar do pe�o preto
					matriz[right.getRow() + 1][right.getColumn()] = true;
				}
			}
		}
		
		return matriz;
	}

	@Override
	public String toString() {
		return "P";
	}
}
