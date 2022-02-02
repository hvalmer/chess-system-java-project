package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

/*classe que é o coração do jogo de xadrez
 * onde vai ter as regras do jogo de xadrez
 * Essa classe sabe da dimensão de um tabuleiro de xadrez*/
public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		board = new Board(8, 8);
		initialSetup();
	}
	/*método retorna uma matriz de peças 
	 * de xadrez correspondente a partida(ChessMatch)
	 * ChessPiece é a camada de xadrez e não o board
	 * e liberar para o Program a camada de xadrez e não 
	 * a camada de tabuleiro*/
	public ChessPiece[][] getPieces(){
		ChessPiece[][] matriz = new ChessPiece[board.getRows()][board.getColumns()];
		/*percorrendo a matriz do tabuleiro(board)
		 * e cada peça do tabuleiro, fazer um downcasting para ChessPiece
		 * fazer um for() para percorrer a matriz*/
		for(int i=0; i<board.getRows(); i++) {
			for(int j=0; j<board.getColumns(); j++) {
				//downcasting(ChessPiece) interpreta como uma peça de xadrez e não como uma peça comum
				matriz[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return matriz;
	}
	
	//método responsável por iniciar a partida de xadrez, colocando as peças no tabuleiro
	private void initialSetup() {
		board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
		board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
		board.placePiece(new King(board, Color.WHITE), new Position(7, 4));
	}
}
