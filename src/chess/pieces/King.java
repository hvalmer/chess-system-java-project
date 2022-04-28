package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {
	
	//dependencia para a partida
	private ChessMatch chessMatch;

	// construtor repassa a chamada para a super classe
	//associando tamb�m a referencia para a partida ChessMatch chessMatch
	public King(Board board, Color color, ChessMatch chessMatch) {
		super(board, color);
		this.chessMatch = chessMatch;
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
	
	//implementando m�todo auxiliar para testar a condi��o de rock...se a torre est� apta para o rock
	//a inten��o desse m�todo � informar que existe uma torre e essa est� apta para rock
	//quando a quantidade de movimentos dela � igual a 0
	private boolean testRookCastling(Position position) {
		ChessPiece chp = (ChessPiece)getBoard().piece(position);
		return chp != null && chp instanceof Rook && chp.getColor() == getColor() && chp.getMoveCount() == 0;
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
		
		//verificar se o rei pode mover duas casas para a direita e duas para esquerda
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			//testando se as duas posicoes estao vagas e se a torre � se moveu -- rook PEQUENO
			Position posTorreReiDireita = new Position(position.getRow(), position.getColumn() + 3);
			if(testRookCastling(posTorreReiDireita)) {
				//testando se as casas estao vazias
				Position p1 = new Position(position.getRow(), position.getColumn() + 1);
				Position p2 = new Position(position.getRow(), position.getColumn() + 2);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null) {
					//o rei move duas casas direta
					matriz[position.getRow()][position.getColumn() + 2] = true; 
				}
			}
			
			//testando se as duas posicoes estao vagas e se a torre � se moveu -- rook GRANDE
			Position posTorreReiEsquerda = new Position(position.getRow(), position.getColumn() - 4);
			if(testRookCastling(posTorreReiEsquerda)) {
				//testando se as casas estao vazias
				Position p1 = new Position(position.getRow(), position.getColumn() - 1);
				Position p2 = new Position(position.getRow(), position.getColumn() - 2);
				Position p3 = new Position(position.getRow(), position.getColumn() - 3);
				if(getBoard().piece(p1) == null && getBoard().piece(p2) == null && getBoard().piece(p3) == null) {
					//o rei move duas casas esquerda
					matriz[position.getRow()][position.getColumn() - 2] = true;
				}
			}
			
		}

		return matriz;
	}
}
