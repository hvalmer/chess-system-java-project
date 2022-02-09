package chess;

import boardgame.Board;
import boardgame.Piece;
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

	/*
	 * método retorna uma matriz de peças de xadrez correspondente a
	 * partida(ChessMatch) ChessPiece é a camada de xadrez e não o board e liberar
	 * para o Program a camada de xadrez e não a camada de tabuleiro
	 */
	public ChessPiece[][] getPieces() {
		ChessPiece[][] matriz = new ChessPiece[board.getRows()][board.getColumns()];
		/*
		 * percorrendo a matriz do tabuleiro(board) e cada peça do tabuleiro, fazer um
		 * downcasting para ChessPiece fazer um for() para percorrer a matriz
		 */
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				// downcasting(ChessPiece) interpreta como uma peça de xadrez e não como uma
				// peça comum
				matriz[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return matriz;
	}
	
	//método para mover a peça de no xadrez
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		//convertendo para posições da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		//validando uma posição de destino
		validateTargetPosition(source, target);
		//validando se na posição de origem há uma peça
		validateSourcePosition(source);
		//makeMove() - resp. realizar o movimento da peça
		Piece capturedPeace = makeMove(source, target);
		return (ChessPiece)capturedPeace;
	}
	
	//operação makeMove, lógica do movimento
	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		//remove a possível peça que esteja na posição de destino, por padrão a peça capturada(capturedPiece)
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}

	//método para validação da posição de origem, agora com duas verificações
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position"); 
		}
		//testar se existe movimentos possíveis para a peça
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}
	
	//método para validação da posição de destino
	private void validateTargetPosition(Position source, Position target) {
		/*posição de destino é válida em relação a de origem?
		 * Position target é um movimento possível em relação a peça que tiver na Position source*/
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chose piece can't move to target position");
		}
	}

	//método para receber as coordenadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());//converter toPosition para posição de matriz
	}
	
	// método responsável por iniciar a partida de xadrez, colocando as peças no
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
