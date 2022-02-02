package application;

import chess.ChessPiece;

public class UI {

	//m�todo para imprimir as linhas e colunas
	public static void printBoard(ChessPiece[][] pieces) {
		for(int i=0; i<pieces.length; i++) {
			System.out.print((8 - i) + " ");//imprime a linha
			for(int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j]);//imprime a pe�a
			}
			System.out.println();//quebra de linha pra pr�xima linha
		}
		System.out.println("  a b c d e f g h");
	}
	
	//m�todo auxiliar para imprimir uma �nica pe�a
	private static void printPiece(ChessPiece piece) {
		if(piece == null) {
			System.out.print("-");
		}
		else {
			System.out.print(piece);
		}
		System.out.print(" ");
	}
}
