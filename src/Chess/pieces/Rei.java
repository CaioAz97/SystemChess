package Chess.pieces;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;
import boardgame.Borda;
import boardgame.Posiçao;

public class Rei extends ChessPiece {

	private ChessMatch chessMatch;
	
	
	public Rei(Borda borda, Color color, ChessMatch chessMatch) {
		super(borda, color);
		this.chessMatch = chessMatch;
		
	}

	@Override
	public String toString() {
		return "R";
	}

	private boolean canMoves(Posiçao position) {
	ChessPiece p = (ChessPiece)getBorda().piece(position);	
		return p == null || p.getColor() != getColor();
	}
	
	private boolean testTorreRoque(Posiçao position) {
		ChessPiece p = (ChessPiece)getBorda().piece(position);
		return p != null && p instanceof Torre && p.getColor() == getColor() && p.getMoveCount() == 0;
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
		
		// #Movement Especial Castling.
		if(getMoveCount() == 0 && !chessMatch.getCheck()) {
			//#Movement Especial Castling king side.
			Posiçao posT1 = new Posiçao(position.getRow(),position.getColumn() + 3);
			if(testTorreRoque(posT1)) {
				Posiçao p1 = new Posiçao(position.getRow(), position.getColumn() + 1);
				Posiçao p2 = new Posiçao(position.getRow(), position.getColumn() + 2);
				if(getBorda().piece(p1) == null && getBorda().piece(p2) == null) {
					mat[position.getRow()][position.getColumn() + 2] = true;
				}
			}
			//#Movement Especial Castling Queen Side.
			Posiçao posT2 = new Posiçao(position.getRow(),position.getColumn() - 4);
			if(testTorreRoque(posT1)) {
				Posiçao p1 = new Posiçao(position.getRow(), position.getColumn() - 1);
				Posiçao p2 = new Posiçao(position.getRow(), position.getColumn() - 2);
				Posiçao p3 = new Posiçao(position.getRow(), position.getColumn() - 3);
				if(getBorda().piece(p1) == null && getBorda().piece(p2) == null && getBorda().piece(p3) == null) {
					mat[position.getRow()][position.getColumn() -2] = true;
				}	
		}
}
		
		return mat;
	}

}
