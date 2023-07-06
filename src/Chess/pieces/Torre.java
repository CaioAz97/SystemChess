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

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBorda().getRows()][getBorda().getColumns()];
		return mat;
	}
}
