package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		
		//declarando o Scanner
		Scanner sc = new Scanner(System.in);
		//instanciando uma partida de xadrez
		ChessMatch chessMatch = new ChessMatch();
		//função para imprimir as peças da partida
		while(true) {
			try {
				//chamando o método que limpa a tela de xadrez, a cada vez que voltar no while
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());
				System.out.println();
				//digitar a posição de origem
				System.out.print("Source: ");
				//lendo a posição de origem
				//imprimindo as posições possíveis, a partir de uma posição de origem
				ChessPosition source = UI.readChessPosition(sc);
				
				//declarando uma matriz booleana
				boolean[][] possibleMovies = chessMatch.possibleMovies(source);
				//limpando a tela
				UI.clearScreen();
				//imprimindo novamente o tabuleiro, passando os movimentos possíveis
				//possibleMovies...colorindo as posições que a peça pode mover
				UI.printBoard(chessMatch.getPieces(), possibleMovies);				
				System.out.println();
				//digitar a posição de destino
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				//fazendo a chamada
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			}
			catch(ChessException ex) {
				System.out.println(ex.getMessage());//imprime a mensagem para o usuário
				sc.nextLine();//o programa aguarda eu apertar ENTER
			}
			catch(InputMismatchException ex) {
				System.out.println(ex.getMessage());//imprime a mensagem para o usuário
				sc.nextLine();//o programa aguarda eu apertar ENTER
			}
		}
	}
}
