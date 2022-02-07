package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	//c�digos especiais das cores para imprimir no console
	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	//cores do texto
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	//cores do fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	//m�todo para ler(read) uma posi��o da pe�a
	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String st = sc.nextLine();
			char column = st.charAt(0);
			//recortar o string a partir da posi��o 1 e converter(parseInt) para inteiro
			int row = Integer.parseInt(st.substring(1));
			return new ChessPosition(column, row);
		}
		catch(RuntimeException ex) {
			throw new InputMismatchException("Error reading ChessPosition. Valid values are form a1 to h8");
		}
	}
	
	// m�todo para imprimir as linhas e colunas
	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");// imprime a linha
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j]);// imprime a pe�a
			}
			System.out.println();// quebra de linha pra pr�xima linha
		}
		System.out.println("  a b c d e f g h");
	}

	// m�todo auxiliar para imprimir uma �nica pe�a
	private static void printPiece(ChessPiece piece) {
		if (piece == null) {
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
	}
}
