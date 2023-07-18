package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Borda;
import boardgame.Posiçao;

public class Peao extends ChessPiece{

	public Peao(Borda borda, Color color) {
		super(borda, color);
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBorda().getRows()][getBorda().getColumns()];

		Posiçao p = new Posiçao(0, 0);

		if (getColor() == Color.WHITE) {
			p.setValues(position.getRow() - 1, position.getColumn());
			if (getBorda().positionExists(p) && !getBorda().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow() - 2, position.getColumn());
			Posiçao p2 = new Posiçao(position.getRow() - 1, position.getColumn());
			if (getBorda().positionExists(p) && !getBorda().thereIsAPiece(p) && getBorda().positionExists(p2)
					&& !getBorda().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow() - 1, position.getColumn() - 1);
			if (getBorda().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow() - 1, position.getColumn() + 1);
			if (getBorda().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		else {
			p.setValues(position.getRow() + 1, position.getColumn());
			if (getBorda().positionExists(p) && !getBorda().thereIsAPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow() + 2, position.getColumn());
			Posiçao p2 = new Posiçao(position.getRow() + 1, position.getColumn());
			if (getBorda().positionExists(p) && !getBorda().thereIsAPiece(p) && getBorda().positionExists(p2)
					&& !getBorda().thereIsAPiece(p2) && getMoveCount() == 0) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow() + 1, position.getColumn() - 1);
			if (getBorda().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}

			p.setValues(position.getRow() + 1, position.getColumn() + 1);
			if (getBorda().positionExists(p) && isThereOpponentPiece(p)) {
				mat[p.getRow()][p.getColumn()] = true;
			}
		}
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}	
}
