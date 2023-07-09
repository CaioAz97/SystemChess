package boardgame;

public abstract class Peça {
	
	protected Posiçao position;
	private Borda board;
	
	public Peça( Borda board) {
		this.board = board;
		position = null;
	}

	protected Borda getBorda() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
		
	public boolean possibleMove(Posiçao position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean[][] mat = possibleMoves();
		for (int i=0; i<mat.length; i++) {
			for (int j =0; j<mat.length; j++) {
				if(mat[i][j]) {
					return true;
				}
			}
		}
		return false;
		
		
	}
	
	
}
