package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Borda;
import boardgame.Posiçao;

public class Bispo extends ChessPiece {

	public Bispo(Borda borda, Color color) {
		super(borda, color);
	
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean[][] mat = new boolean[getBorda().getRows()][getBorda().getColumns()];
		
		Posiçao p = new Posiçao(0, 0);
		
		// NW

		p.setValues(position.getRow() - 1, position.getColumn()-1);
		while (getBorda().positionExists(p) && !getBorda().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow()-1, p.getColumn()-1);
		}
		if (getBorda().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// NE
		
		p.setValues(position.getRow() -1, position.getColumn() +1);
		while (getBorda().positionExists(p) && !getBorda().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() -1, p.getColumn() +1);
		}
		if (getBorda().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// SE
		
		p.setValues(position.getRow() +1, position.getColumn() + 1);
		while (getBorda().positionExists(p) && !getBorda().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() +1, p.getColumn() +1);
		}
		if (getBorda().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// SW
		p.setValues(position.getRow() + 1, position.getColumn() -1);
		while (getBorda().positionExists(p) && !getBorda().thereIsAPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setValues(p.getRow() +1, p.getColumn() -1);
		}
		if (getBorda().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "B";
	}
}
