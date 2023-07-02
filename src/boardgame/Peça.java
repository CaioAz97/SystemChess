package boardgame;

public class Peça {
	
	protected Posiçao position;
	private Borda board;
	
	
	
	
	public Peça( Borda borda) {
		this.board = board;
		position = null;
	}


	protected Borda getBorda() {
		return board;
	}

}
