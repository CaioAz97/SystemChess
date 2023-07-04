package Chess;

import boardgame.Borda;
import boardgame.Peça;

public class ChessPiece extends Peça {

	private Color color;

	public ChessPiece(Borda borda, Color color) {
		super(borda);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
}
