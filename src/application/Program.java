package application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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
		//declarando uma lista de pe�as, juntamente com a partida
		List<ChessPiece> captured = new ArrayList<>();
		
		//fun��o para imprimir as pe�as da partida
		while(true) {
			try {
				//chamando o m�todo que limpa a tela de xadrez, a cada vez que voltar no while
				UI.clearScreen();
				//argumentos() para imprimir a partida
				UI.printMatch(chessMatch, captured);
				System.out.println();
				//digitar a posi��o de origem
				System.out.print("Source: ");
				//lendo a posi��o de origem
				//imprimindo as posi��es poss�veis, a partir de uma posi��o de origem
				ChessPosition source = UI.readChessPosition(sc);
				
				//declarando uma matriz booleana
				boolean[][] possibleMovies = chessMatch.possibleMovies(source);
				//limpando a tela
				UI.clearScreen();
				//imprimindo novamente o tabuleiro, passando os movimentos poss�veis
				//possibleMovies...colorindo as posi��es que a pe�a pode mover
				UI.printBoard(chessMatch.getPieces(), possibleMovies);				
				System.out.println();
				//digitar a posi��o de destino
				System.out.print("Target: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				//fazendo a chamada
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				
				//controle das pe�as capturadas, adicionando � lista de pe�as capturadas
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
			}
			catch(ChessException ex) {
				System.out.println(ex.getMessage());//imprime a mensagem para o usu�rio
				sc.nextLine();//o programa aguarda eu apertar ENTER
			}
			catch(InputMismatchException ex) {
				System.out.println(ex.getMessage());//imprime a mensagem para o usu�rio
				sc.nextLine();//o programa aguarda eu apertar ENTER
			}
		}
	}
}
