package application;

import chess.ChessPiece;

public class UI {

	//método para imprimir as linhas e colunas
	public static void printBoard(ChessPiece[][] pieces) {
		for(int i=0; i<pieces.length; i++) {
			System.out.print((8 - i) + " ");//imprime a linha
			for(int j=0; j<pieces.length; j++) {
				printPiece(pieces[i][j]);//imprime a peça
			}
			System.out.println();//quebra de linha pra próxima linha
		}
		System.out.println("  a b c d e f g h");
	}
	
	//método auxiliar para imprimir uma única peça
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
