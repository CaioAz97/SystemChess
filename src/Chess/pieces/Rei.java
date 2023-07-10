package Chess.pieces;

import Chess.ChessPiece;
import Chess.Color;
import boardgame.Borda;
import boardgame.Posiçao;

public class Rei extends ChessPiece {

	public Rei(Borda borda, Color color) {
		super(borda, color);
		
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean canMoves(Posiçao position) {
	ChessPiece p = (ChessPiece)getBorda().piece(position);	
		return p == null || p.getColor() != getColor();
	}
	
	
	@Override
	public boolean[][] possibleMoves() {
		boolean[][] mat = new boolean[getBorda().getRows()][getBorda().getColumns()];
		
		Posiçao p = new Posiçao(0, 0);
		
		// above 
		p.setValues(position.getRow() -1 , position.getColumn());
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//below
		p.setValues(position.getRow() +1 , position.getColumn());
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//left
		p.setValues(position.getRow(), position.getColumn() -1);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//right
		p.setValues(position.getRow(), position.getColumn() +1);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//NW
		p.setValues(position.getRow()-1, position.getColumn() -1);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//NE
		p.setValues(position.getRow()-1, position.getColumn() +1);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//SW
		p.setValues(position.getRow()+1, position.getColumn() -1);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//SE
		p.setValues(position.getRow()+1, position.getColumn() +1);
		if(getBorda().positionExists(p) && canMoves(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		return mat;
	}

}
