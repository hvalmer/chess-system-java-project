package chess;

import boardgame.Board;
import boardgame.Piece;
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

	/*
	 * m�todo retorna uma matriz de pe�as de xadrez correspondente a
	 * partida(ChessMatch) ChessPiece � a camada de xadrez e n�o o board e liberar
	 * para o Program a camada de xadrez e n�o a camada de tabuleiro
	 */
	public ChessPiece[][] getPieces() {
		ChessPiece[][] matriz = new ChessPiece[board.getRows()][board.getColumns()];
		/*
		 * percorrendo a matriz do tabuleiro(board) e cada pe�a do tabuleiro, fazer um
		 * downcasting para ChessPiece fazer um for() para percorrer a matriz
		 */
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				// downcasting(ChessPiece) interpreta como uma pe�a de xadrez e n�o como uma
				// pe�a comum
				matriz[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return matriz;
	}
	
	//m�todo para mover a pe�a de no xadrez
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//convertendo para posi��es da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		//validando uma posi��o de destino
		validateTargetPosition(source, target);
		//validando se na posi��o de origem h� uma pe�a
		validateSourcePosition(source);
		//makeMove() - resp. realizar o movimento da pe�a
		Piece capturedPeace = makeMove(source, target);
		return (ChessPiece)capturedPeace;
	}
	
	//opera��o makeMove, l�gica do movimento
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		//remove a poss�vel pe�a que esteja na posi��o de destino, por padr�o a pe�a capturada(capturedPiece)
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}

	//m�todo para valida��o da posi��o de origem, agora com duas verifica��es
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position"); 
		}
		//testar se existe movimentos poss�veis para a pe�a
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	//m�todo para valida��o da posi��o de destino
	private void validateTargetPosition(Position source, Position target) {
		/*posi��o de destino � v�lida em rela��o a de origem?
		 * Position target � um movimento poss�vel em rela��o a pe�a que tiver na Position source*/
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chose piece can't move to target position");
		}
	}

	//m�todo para receber as coordenadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());//converter toPosition para posi��o de matriz
	}
	
	// m�todo respons�vel por iniciar a partida de xadrez, colocando as pe�as no
	// tabuleiro
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));
        

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
