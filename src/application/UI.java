package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// códigos especiais das cores para imprimir no console
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	// cores do texto
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// cores do fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	//método para limpar a tela quando da movimentação no xadrez
	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	// método para ler(read) uma posição da peça
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String st = sc.nextLine();
			char column = st.charAt(0);
			// recortar o string a partir da posição 1 e converter(parseInt) para inteiro
			int row = Integer.parseInt(st.substring(1));
			return new ChessPosition(column, row);
			//lança uma exceção em relação a posição digitada errada InputMismatchException
		} catch (RuntimeException ex) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are form a1 to h8");
		}
	}
	
	//método para exibir o turno(cor da peça) e o jogador atual...
	//imprimindo a partida (tabuleiro e as movimentações do jogador)
	public static void printMatch(ChessMatch chessMatch) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println("Turn: " + chessMatch.getTurn());
		System.out.println("Waiting player: " + chessMatch.getCurrentPlayer());
	}

	// método para imprimir as linhas e colunas
	/*quando for pra imprimir o tabuleiro, sem a questão dos movimentos possíveis, 
	 * vou imprimir 'false', indicando que nenhuma peça é pra ter o fundo colorido*/
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");// imprime a linha
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);// imprime a peça
			}
			System.out.println();// quebra de linha pra próxima linha
		}
		System.out.println("  a b c d e f g h");
	}
	
	// método para imprimir os movimentos possíveis das peças
	/*quando for imprimir o tabuleiro(boolean[][] possibleMovies), considerando
	 * os movimentos possíveis, passando o possibleMovies[i][j]-posições, pintando
	 * o fundo colorido, dependendo dessa variável*/
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMovies) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");// imprime a linha
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMovies[i][j]);// imprime a peça
			}
			System.out.println();// quebra de linha pra próxima linha
		}
		System.out.println("  a b c d e f g h");
	}

	// método auxiliar para imprimir uma única peça
	//boolean background - variável para indicar se vai colorir ou não o fundo da minha peça
	private static void printPiece(ChessPiece piece, boolean background) {
		//testando se a variável background é verdadeira, sendo assim, vai ou não colorir o fundo de azul 
		if(background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}
}
