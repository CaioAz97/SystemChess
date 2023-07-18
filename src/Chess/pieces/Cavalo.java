package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Borda;
import boardgame.Posiçao;

public class Cavalo extends ChessPiece{

	public Cavalo(Borda borda, Color color) {
		super(borda, color);
		
	}

	@Override
	public String toString() {
		return "C";
	}
	private boolean canMoves(Posiçao position) {
		ChessPiece p = (ChessPiece)getBorda().piece(position);	
		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBorda().getRows()][getBorda().getColumns()];
		
		Posiçao p = new Posiçao(0, 0);
		
		
		p.setValues(position.getRow() -1 , position.getColumn() -2);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() -2 , position.getColumn()-1);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		p.setValues(position.getRow() -2, position.getColumn() +1);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		p.setValues(position.getRow() -1, position.getColumn() +2);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		p.setValues(position.getRow() +1, position.getColumn() +2);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		p.setValues(position.getRow() +2, position.getColumn() +1);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
	
		p.setValues(position.getRow() +2, position.getColumn() -1);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		
		p.setValues(position.getRow() +1, position.getColumn() -2);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	
	}
}
