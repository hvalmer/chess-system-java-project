package application;

import chess.ChessMatch;

public class Program {

	public static void main(String[] args) {
		
		//instanciando uma partida de xadrez
		ChessMatch chessMatch = new ChessMatch();
		//fun��o para imprimir as pe�as da partida
		UI.printBoard(chessMatch.getPieces());
	}

}
