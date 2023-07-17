package Chess;

import boardgame.Posiçao;

public class ChessPosition {
	
	private char column;
	private int row;
	
	public ChessPosition() {
		
	}

	public ChessPosition(char column, int row) {
		if (column < 'A' || column > 'H' || row < 1 || row > 8) {
			throw new ChessException(" ERRO INSTACIANDO O ChessPosition. Os valores válidos são de A1 a H8. ");
		}
		this.column = column;
		this.row = row;
	}

	public char getColumn() {
		return column;
	}


	public int getRow() {
		return row;
	}

	protected Posiçao toPosition() {
		return new Posiçao(8 - row, column - 'A');
	}
	protected static ChessPosition fromPosition(Posiçao position) {
		return new ChessPosition((char) ('A' + position.getColumn()), 8 - position.getRow());
	}
	
	@Override
	public String toString() {
		return "" + column + row;
	}
	
}
