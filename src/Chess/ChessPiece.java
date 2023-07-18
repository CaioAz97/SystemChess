package Chess;

import boardgame.Borda;
import boardgame.Peça;
import boardgame.Posiçao;

public abstract class ChessPiece extends Peça {

	private Color color;
	private int moveCount;
	
	
	public ChessPiece(Borda borda, Color color) {
		super(borda);
		this.color = color;
	}


	public Color getColor() {
		return color;
	}
	
	public int getMoveCount() {
		return moveCount;
	}
	
	
	public void increaseMoveCount() {
		moveCount ++;
	}
	
	public void decreaseMoveCount(){
		moveCount --;
		
	}	
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	protected boolean isThereOpponentPiece(Posiçao position) {
		ChessPiece p = (ChessPiece)getBorda().piece(position);
		return p != null && p.getColor() != color;
	}
	
	
}
