package Chess.pieces;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.Color;
import boardgame.Borda;
import boardgame.Posiçao;

public class Peao extends ChessPiece{
	
	private ChessMatch chessMatch;

	public Peao(Borda borda, Color color, ChessMatch chessMatch) {
		super(borda, color);
		this.chessMatch = chessMatch;
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
			
			//#En Passant WHITE
			if(position.getRow() == 3) {
				Posiçao esquerda = new Posiçao(position.getRow(), position.getColumn() -1);
				if(getBorda().positionExists(esquerda) && isThereOpponentPiece(esquerda) && getBorda().piece(esquerda) == chessMatch.getEnPassantVulnerable());
				mat[esquerda.getRow()-1][esquerda.getColumn()] = true;
			}
			
			if(position.getRow() == 3) {
				Posiçao direita = new Posiçao(position.getRow(), position.getColumn() +1);
				if(getBorda().positionExists(direita) && isThereOpponentPiece(direita) && getBorda().piece(direita) == chessMatch.getEnPassantVulnerable());
				mat[direita.getRow()-1][direita.getColumn()] = true;
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
			//#En Passant BLACK
			if(position.getRow() == 4) {
				Posiçao esquerda = new Posiçao(position.getRow(), position.getColumn() - 1);
				if(getBorda().positionExists(esquerda) && isThereOpponentPiece(esquerda) && getBorda().piece(esquerda) == chessMatch.getEnPassantVulnerable());
				mat[esquerda.getRow() + 1][esquerda.getColumn()] = true;
			}
			
			if(position.getRow() == 3) {
				Posiçao direita = new Posiçao(position.getRow(), position.getColumn() + 1);
				if(getBorda().positionExists(direita) && isThereOpponentPiece(direita) && getBorda().piece(direita) == chessMatch.getEnPassantVulnerable());
				mat[direita.getRow() + 1][direita.getColumn()] = true;
			}
		}
		
		
		
		return mat;
	}
	
	@Override
	public String toString() {
		return "P";
	}	
}
