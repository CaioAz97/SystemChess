package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Borda;

public class Torre extends ChessPiece {

	public Torre(Borda borda, Color color) {
		super(borda, color);
	}

	@Override
	public String toString() {
		return "T";
	}
}
