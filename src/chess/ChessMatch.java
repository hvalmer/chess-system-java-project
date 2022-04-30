package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Queen;
import chess.pieces.Rook;

/*classe que � o cora��o do jogo de xadrez
 * onde vai ter as regras do jogo de xadrez
 * Essa classe sabe da dimens�o de um tabuleiro de xadrez*/
public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;

	// declarando as listas de pe�as do tatubeiro e pe�as capturadas, lista gen�rica
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public ChessMatch() {
		board = new Board(8, 8);
		turn = 1;
		// a partida sempre inicia com a pe�a branca
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	// apenas mostrar m�todos get para turn e currentPlayer
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	// m�todo para expor a propriedade check
	public boolean getCheck() {
		return check;
	}

	public boolean getCheckMate() {
		return checkMate;
	}

	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
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

	// imprimindo os movimentos poss�veis
	public boolean[][] possibleMovies(ChessPosition sourcePosition) {
		// convertendo para posi��o de matriz
		Position position = sourcePosition.toPosition();
		// validando a posi��o, depois que o usu�rio entrar com ela
		validateSourcePosition(position);
		// retornar os movimentos poss�veis da pe�a nessa posi��o
		return board.piece(position).possibleMoves();
	}

	// m�todo para mover a pe�a de no xadrez
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		// convertendo para posi��es da matriz
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		// validando uma posi��o de destino
		validateTargetPosition(source, target);
		// validando se na posi��o de origem h� uma pe�a
		validateSourcePosition(source);
		// makeMove() - resp. realizar o movimento da pe�a
		Piece capturedPeace = makeMove(source, target);
		// testando se o movimento colocou o pr�prio jogador em xeque
		// caso isso aconte�a, tem que desfazer o movimento (undoMove)
		if (testCheck(currentPlayer)) {
			undoMove(source, target, capturedPeace);
			throw new ChessException("You can�t put youself in check");
		}

		ChessPiece movedPeace = (ChessPiece) board.piece(target);

		// testando se o oponente n�o ficou em xeque
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		// se ajogada feita deixou o oponente em checkmate, o jogo vai acabar
		if (testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		} else {
			// ap�s executar uma jogada, chamar o m�todo..., para trocar o turno(cor da
			// pe�a)
			nextTurn();
		}

		// testando se a pe�a movida foi um pe�o, que moveu duas casas
		// nesse caso, o pe�o estar� vulter�vel a en passant
		if (movedPeace instanceof Pawn
				&& (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)) {
			enPassantVulnerable = movedPeace;
		} else {
			enPassantVulnerable = null;
		}

		return (ChessPiece) capturedPeace;
	}

	// opera��o makeMove, l�gica do movimento
	private Piece makeMove(Position source, Position target) {
		ChessPiece p = (ChessPiece) board.removePiece(source);
		p.increaseMoveCount();
		// remove a poss�vel pe�a que esteja na posi��o de destino, por padr�o a pe�a
		// capturada(capturedPiece)
		Piece capturedPiece = board.removePiece(target);
		board.placePiece(p, target);

		// sempre que fizer um movimento e nesse movimento capturar uma pe�a,
		// retiro(remove) a pe�a da lista de pe�as do tabuleiro(piecesOnTheBoard)
		// e adiciono esse pe�a na lista de pe�as capturadas
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}

		// movendo o rei para a direita - Rook pequeno
		// rei anda duas casas direita
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
			// movendo a torre que esta 3colunas a direita do rei
			Position sourceTorre = new Position(source.getRow(), source.getColumn() + 3);
			// posicao de destino da torre, uma coluna a direita do rei
			Position targetTorre = new Position(source.getRow(), source.getColumn() + 1);
			// retirando a torre de onde esta
			ChessPiece rook = (ChessPiece) board.removePiece(sourceTorre);
			// colocando a torre na posicao de destino dela
			board.placePiece(rook, targetTorre);
			// incrementando a quantidade de movimentos da torre
			rook.increaseMoveCount();
		}

		// movendo o rei para a esquerda - Rook grande
		// rei anda duas casas esquerda
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
			// movendo a torre que esta 3colunas a esquerda do rei
			Position sourceTorre = new Position(source.getRow(), source.getColumn() - 4);
			// posicao de destino da torre, uma coluna a esquerda do rei
			Position targetTorre = new Position(source.getRow(), source.getColumn() - 1);
			// retirando a torre de onde esta
			ChessPiece rook = (ChessPiece) board.removePiece(sourceTorre);
			// colocando a torre na posicao de destino dela
			board.placePiece(rook, targetTorre);
			// incrementando a quantidade de movimentos da torre
			rook.increaseMoveCount();
		}

		// implementando o en passant no makeMove
		// o pe�o s� pode andar na diagonal para uma casa vazia, se for um en passant
		if (p instanceof Pawn) {
			// testando se pe�o pode andar na diagonal e � capturou pe�a, significa en
			// passant
			if (source.getRow() != target.getColumn() && capturedPiece == null) {
				// posi��o do pe�o capturado
				Position pawPosition;
				if (p.getColor() == Color.WHITE) {
					// sendo o pe�o BRANCO, o pe�o capturado estar� abaixo dela, linha + 1
					pawPosition = new Position(target.getRow() + 1, target.getColumn());
				} else {
					// sendo o pe�o PRETO, o pe�o capturado estar� abaixo dela, linha - 1
					pawPosition = new Position(target.getRow() - 1, target.getColumn());
				}
				// removendo o pe�o do tabuleiro
				capturedPiece = board.piece(pawPosition);
				// adicionando o pe�o na lista de pe�as capturadas
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			}
		}

		return capturedPiece;
	}

	// m�todo para desfazer o movimento quando tentar se mover e se colocar em xeque
	private void undoMove(Position source, Position target, Piece capturedPiece) {
		// desfazendo toda a l�gica do makeMove do movimento da pe�a
		ChessPiece p = (ChessPiece) board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);

		if (capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}

		// DESFAZENDO O MOVIMENTO
		// desfazendo o movimento o rei - Rook pequeno
		// rei anda duas casas direita
		if (p instanceof King && target.getColumn() == source.getColumn() + 2) {
			// movendo a torre que esta 3colunas a esquerda do rei
			Position sourceTorre = new Position(source.getRow(), source.getColumn() + 3);
			// posicao de destino da torre, uma coluna a direita do rei
			Position targetTorre = new Position(source.getRow(), source.getColumn() + 1);
			// removendo a torre da posicao de destino
			ChessPiece rook = (ChessPiece) board.removePiece(targetTorre);
			// devolvendo a torre para a posicao de origem
			board.placePiece(rook, sourceTorre);
			// decrementando a quantidade de movimentos da torre
			rook.decreaseMoveCount();
		}

		// movendo o rei para a esquerda - Rook grande
		// rei anda duas casas esquerda
		if (p instanceof King && target.getColumn() == source.getColumn() - 2) {
			// movendo a torre que esta 3colunas a direita do rei
			Position sourceTorre = new Position(source.getRow(), source.getColumn() - 4);
			// posicao de destino da torre, uma coluna a direita do rei
			Position targetTorre = new Position(source.getRow(), source.getColumn() - 1);
			// removendo a torre da posicao de destino
			ChessPiece rook = (ChessPiece) board.removePiece(targetTorre);
			// devolvendo a torre para a posicao de origem
			board.placePiece(rook, sourceTorre);
			// decrementando a quantidade de movimentos da torre
			rook.decreaseMoveCount();
		}

		// implementando o en passant no undoMove
		// desfazendo o movimento en passant
		if (p instanceof Pawn) {
			if (source.getRow() != target.getColumn() && capturedPiece == enPassantVulnerable) {
				ChessPiece pawn = (ChessPiece)board.removePiece(target);
				// posi��o do pe�o capturado
				Position pawPosition;
				if (p.getColor() == Color.WHITE) {
					//devolvendo o pe�o PRETO para a linha 3 do tabuleiro
					pawPosition = new Position(3, target.getColumn());
				} else {
					//devolvendo o pe�o BRANCO para a linha 4 do tabuleiro
					pawPosition = new Position(4, target.getColumn());
				}
				board.placePiece(pawn, pawPosition);
			}
		}
	}

	// m�todo para valida��o da posi��o de origem, agora com duas verifica��es
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("There is no piece on source position");
		}
		/*
		 * testando...pegando a pe�a do tabuleiro na posi��o tal, fa�o o down casting p/
		 * ChessPiece e testo a cor dela, se a cor for diferente da cuurrentPlayer,
		 * significa � uma pe�a do advers�rio, n�o posso mov�-la lan�ando uma exception,
		 * caso o jogador estiver tentando mov�-la
		 */
		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {
			throw new ChessException("The chosen piece is not yours!");
		}
		// testar se existe movimentos poss�veis para a pe�a
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("There is no possible moves for the chosen piece");
		}
	}

	// m�todo para valida��o da posi��o de destino
	private void validateTargetPosition(Position source, Position target) {
		/*
		 * posi��o de destino � v�lida em rela��o a de origem? Position target � um
		 * movimento poss�vel em rela��o a pe�a que tiver na Position source
		 */
		if (!board.piece(source).possibleMove(target)) {
			throw new ChessException("The chose piece can't move to target position");
		}
	}

	// m�todo para trocar o turno(cores das pe�as)
	private void nextTurn() {
		turn++;
		// express�o condicional tern�ria...se o jogador atual for White, ent�o ele vira
		// Black, caso contr�rio vira White
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// m�todo para devolver um oponente de uma cor...se BRANCO/PRETO se PRETO/BRANCO
	private Color opponent(Color color) {
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	// m�todo para localizar um rei(king) de uma determinada cor, varrendo as pe�as
	// do jogo localizando o rei daquela cor
	private ChessPiece king(Color color) {
		// express�es l�mbida(x->) forma padr�o de filtar uma lista
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		for (Piece p : list) {
			// testando...
			if (p instanceof King) {
				return (ChessPiece) p;
			}
		}
		// lan�ando uma exce��o, caso n�o encontrar nenhum rei(king)
		throw new IllegalStateException("There is no " + color + "king on the board");
	}

	// m�todo do rei
	private boolean testCheck(Color color) {
		// pegando a posi��o do rei no formato de matriz
		Position kingPosition = king(color).getChessPosition().toPosition();
		// lista das pe�as no tabuleiro filtradas com a cor do oponente desse rei
		List<Piece> opponentPieces = piecesOnTheBoard.stream()
				.filter(x -> ((ChessPiece) x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Piece p : opponentPieces) {
			// testando...matriz de movimentos poss�veis da pe�a p
			boolean[][] matriz = p.possibleMoves();
			// se na matriz, a posi��o correspondente a posi��o do rei(king) for true,
			// significa que o rei est� em check
			if (matriz[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
		}
		// esgotando todas as pe�as adversarias, e nenhuma dessas pe�as estiver na
		// matriz de movimento(true), o rei n�o est� em check
		return false;
	}

	private boolean testCheckMate(Color color) {
		// testando a possibilidade de n�o estar em check
		if (!testCheck(color)) {
			return false;
		}
		// testando se todas as pe�as dessa cor, n�o tiver um movimento poss�vel pra
		// ela, que tire do check, ela est� em checkmate
		List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece) x).getColor() == color)
				.collect(Collectors.toList());
		// for para percorrer todas as pe�as p pertencentes a minha lista
		for (Piece p : list) {
			boolean[][] matriz = p.possibleMoves();
			// percorrendo a matriz
			for (int i = 0; i < board.getRows(); i++) {// percorrendo as linhas da matriz
				for (int j = 0; j < board.getColumns(); j++) {// percorrendo as colunas da matriz
					if (matriz[i][j]) {
						Position source = ((ChessPiece) p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);
						// fazendo o movimento para testar se o rei da minha cor(branco/preto) ainda
						// est� em check
						boolean testCheck = testCheck(color);
						// desfazer o movimento
						undoMove(source, target, capturedPiece);
						// testando...n�o estando em check, significa que o movimento tirou o rei do
						// check
						if (!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	// m�todo para receber as coordenadas do xadrez
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		// converter toPosition para posi��o de matriz
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		// colocando a pe�a(piece) dentro da lista de pe�as do tabuleiro
		piecesOnTheBoard.add(piece);
	}

	// m�todo respons�vel por iniciar a partida de xadrez, colocando as pe�as no
	// tabuleiro
	private void initialSetup() {
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('b', 1, new Knight(board, Color.WHITE));
		placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('d', 1, new Queen(board, Color.WHITE));
		// this...auto referencia do rei na partida
		placeNewPiece('e', 1, new King(board, Color.WHITE, this));
		placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
		placeNewPiece('g', 1, new Knight(board, Color.WHITE));
		placeNewPiece('h', 1, new Rook(board, Color.WHITE));
		// this...auto referencia do pe�o na partida
		placeNewPiece('a', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('b', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('c', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('d', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('e', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('f', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('g', 2, new Pawn(board, Color.WHITE, this));
		placeNewPiece('h', 2, new Pawn(board, Color.WHITE, this));

		placeNewPiece('a', 8, new Rook(board, Color.BLACK));
		placeNewPiece('b', 8, new Knight(board, Color.BLACK));
		placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('d', 8, new Queen(board, Color.BLACK));
		// this...auto referencia do rei na partida
		placeNewPiece('e', 8, new King(board, Color.BLACK, this));
		placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
		placeNewPiece('g', 8, new Knight(board, Color.BLACK));
		placeNewPiece('h', 8, new Rook(board, Color.BLACK));
		// this...auto referencia do pe�o na partida
		placeNewPiece('a', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('b', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('c', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('d', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('e', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('f', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('g', 7, new Pawn(board, Color.BLACK, this));
		placeNewPiece('h', 7, new Pawn(board, Color.BLACK, this));
	}
}
