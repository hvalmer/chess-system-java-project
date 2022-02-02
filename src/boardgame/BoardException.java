package boardgame;

public class BoardException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/*construtor que recebe a mensagem
	 * que repassa essa mensagem p o construtor
	 * que é o RuntimeException*/
	public BoardException(String msg) {
		super(msg);
	}

}
