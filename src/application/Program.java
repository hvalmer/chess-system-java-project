package application;

import java.util.Scanner;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		//declarando o Scanner
		Scanner sc = new Scanner(System.in);
		//instanciando uma partida de xadrez
		ChessMatch chessMatch = new ChessMatch();
		//fun��o para imprimir as pe�as da partida
		while(true) {
			UI.printBoard(chessMatch.getPieces());
			System.out.println();
			//digitar a posi��o de origem
			System.out.print("Source: ");
			//lendo a posi��o de origem
			ChessPosition source = UI.readChessPosition(sc);
			System.out.println();
			//digitar a posi��o de destino
			System.out.print("Target: ");
			ChessPosition target = UI.readChessPosition(sc);
			
			//fazendo a chamada
			ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			
		}
	}

}
