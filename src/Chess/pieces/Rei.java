package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Borda;

public class Rei extends ChessPiece {

	public Rei(Borda borda, Color color) {
		super(borda, color);
		
	}

	@Override
	public String toString() {
		return "R";
	}

}
