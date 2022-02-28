package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

/*classe que é o coração do jogo de xadrez
 * onde vai ter as regras do jogo de xadrez
 * Essa classe sabe da dimensão de um tabuleiro de xadrez*/
public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	//declarando as listas de peças do tatubeiro e peças capturadas, lista genérica
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		//a partida sempre inicia com a peça branca
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	//apenas mostrar métodos get para turn e currentPlayer
	public int getTurn() {
		return turn;
	}
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	//método para expor a propriedade check
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
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
	
	//imprimindo os movimentos possíveis
	public boolean[][] possibleMovies(ChessPosition sourcePosition){
		//convertendo para posição de matriz
		Position position = sourcePosition.toPosition();
		//validando a posição, depois que o usuário entrar com ela
		validateSourcePosition(position); 
		//retornar os movimentos possíveis da peça nessa posição
		return board.piece(position).possibleMoves();
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
		//testando se o movimento colocou o próprio jogador em xeque
		//caso isso aconteça, tem que desfazer o movimento (undoMove)
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPeace);
			throw new ChessException("You can´t put youself in check");
		}
		//testando se o oponente não ficou em xeque
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		//se ajogada feita deixou o oponente em checkmate, o jogo vai acabar
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			//após executar uma jogada, chamar o método..., para trocar o turno(cor da peça)
			nextTurn();
		}
		return (ChessPiece)capturedPeace;
	}
	
	//operação makeMove, lógica do movimento
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		//remove a possível peça que esteja na posição de destino, por padrão a peça capturada(capturedPiece)
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		//sempre que fizer um movimento e nesse movimento capturar uma peça, 
		//retiro(remove) a peça da lista de peças do tabuleiro(piecesOnTheBoard)
		//e adiciono esse peça na lista de peças capturadas
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
		return capturedPiece;
	}
	
	//método para desfazer o movimento quando tentar se mover e se colocar em xeque
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		//desfazendo toda a lógica do makeMove do movimento da peça
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
	}

	//método para validação da posição de origem, agora com duas verificações
	private void validateSourcePosition(Position position) {
		if(!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position"); 
		}
		/*testando...pegando a peça do tabuleiro na posição tal, faço o down 
		 * casting p/ ChessPiece e testo a cor dela, se a cor for diferente
		 * da cuurrentPlayer, significa é uma peça do adversário, não posso movê-la
		 * lançando uma exception, caso o jogador estiver tentando movê-la*/
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours!");
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
	
	//método para trocar o turno(cores das peças)
	private void nextTurn() {
		turn++;
		//expressão condicional ternária...se o jogador atual for White, então ele vira Black, caso contrário vira White
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//método para devolver um oponente de uma cor...se BRANCO/PRETO se PRETO/BRANCO
	private Color opponent(Color color) {
		return(color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	//método para localizar um rei(king) de uma determinada cor, varrendo as peças do jogo localizando o rei daquela cor
	private ChessPiece king(Color color) {
		//expressões lâmbida(x->) forma padrão de filtar uma lista
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Piece p : list) {
			//testando...
			if(p instanceof King) {
				return (ChessPiece)p;
			}
		}
		//lançando uma exceção, caso não encontrar nenhum rei(king)
		throw new IllegalStateException("There is no " + color + "king on the board");
	}
	
	//método do rei
	private boolean testCheck(Color color) {
		//pegando a posição do rei no formato de matriz
		Position kingPosition = king(color).getChessPosition().toPosition();
		//lista das peças no tabuleiro filtradas com a cor do oponente desse rei 
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for(Piece p : opponentPieces) {
			//testando...matriz de movimentos possíveis da peça p
			boolean[][] matriz = p.possibleMoves();
			//se na matriz, a posição correspondente a posição do rei(king) for true, significa que o rei está em check
			if(matriz[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		//esgotando todas as peças adversarias, e nenhuma dessas peças estiver na matriz de movimento(true), o rei não está em check
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		//testando a possibilidade de não estar em check
		if(!testCheck(color)) {
			return false;
		}
		//testando se todas as peças dessa cor, não tiver um movimento possível pra ela, que tire do check, ela está em checkmate
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		//for para percorrer todas as peças p pertencentes a minha lista
		for(Piece p : list) {
			boolean[][] matriz = p.possibleMoves();
			//percorrendo a matriz
			for(int i=0; i<board.getRows(); i++) {//percorrendo as linhas da matriz
				for(int j=0; j<board.getColumns(); j++) {//percorrendo as colunas da matriz
					if(matriz[i][j]) {
						Position source = ((ChessPiece)p).getChessPosition().toPosition();
						Position target = new Position(i,j);
						Piece capturedPiece = makeMove(source, target);
						//fazendo o movimento para testar se o rei da minha cor(branco/preto) ainda está em check
						boolean testCheck = testCheck(color);
						//desfazer o movimento
						undoMove(source, target, capturedPiece);
						//testando...não estando em check, significa que o movimento tirou o rei do check
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	//método para receber as coordenadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		//converter toPosition para posição de matriz
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		//colocando a peça(piece) dentro da lista de peças do tabuleiro
		piecesOnTheBoard.add(piece);
	}
	
	// método responsável por iniciar a partida de xadrez, colocando as peças no
	// tabuleiro
	private void initialSetup() {
		placeNewPiece('h', 7, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
		        

		placeNewPiece('b', 8, new Rook(board, Color.BLACK));
		placeNewPiece('a', 8, new King(board, Color.BLACK));
	}
}
