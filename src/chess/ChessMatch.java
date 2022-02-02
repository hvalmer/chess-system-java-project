package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

/*classe que � o cora��o do jogo de xadrez
 * onde vai ter as regras do jogo de xadrez
 * Essa classe sabe da dimens�o de um tabuleiro de xadrez*/
public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	/*m�todo retorna uma matriz de pe�as 
	 * de xadrez correspondente a partida(ChessMatch)
	 * ChessPiece � a camada de xadrez e n�o o board
	 * e liberar para o Program a camada de xadrez e n�o 
	 * a camada de tabuleiro*/
	public ChessPiece[][] getPieces(){
		ChessPiece[][] matriz = new ChessPiece[board.getRows()][board.getColumns()];
		/*percorrendo a matriz do tabuleiro(board)
		 * e cada pe�a do tabuleiro, fazer um downcasting para ChessPiece
		 * fazer um for() para percorrer a matriz*/
		for(int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns(); j++) {
				//downcasting(ChessPiece) interpreta como uma pe�a de xadrez e n�o como uma pe�a comum
				matriz[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return matriz;
	}
	
	//m�todo respons�vel por iniciar a partida de xadrez, colocando as pe�as no tabuleiro
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
	}
}
