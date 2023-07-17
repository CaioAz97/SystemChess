package Chess;

import boardgame.Borda;
import boardgame.Peça;
import boardgame.Posiçao;

public abstract class ChessPiece extends Peça {

	private Color color;

	public ChessPiece(Borda borda, Color color) {
		super(borda);
		this.color = color;
	}


	public Color getColor() {
		return color;
	}
	
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Posiçao position) {
		ChessPiece p = (ChessPiece)getBorda().piece(position);
		return p != null && p.getColor() != color;
	}
	
	
}
