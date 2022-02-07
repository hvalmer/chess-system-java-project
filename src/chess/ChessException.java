package chess;

import boardgame.BoardException;

//basta capturar ChessException que também captura possíveis BoardException
public class ChessException extends BoardException {
	private static final long serialVersionUID = 1L;

	//construtor que recebe um String
	public ChessException(String msg) {
		super(msg);
	}
}
