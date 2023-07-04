package Chess;

import Chess.pieces.Rei;
import Chess.pieces.Torre;
import boardgame.Borda;
import boardgame.Posiçao;

public class ChessMatch {

	private Borda board;
	
	public ChessMatch() {
		
		board = new Borda(8, 8);
		initialSetup();
	}
	
	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	private void initialSetup() {
		board.PlacePiece(new Torre(board, Color.WHITE), new Posiçao(0, 0));
		board.PlacePiece(new Torre(board, Color.BLACK), new Posiçao(7, 0));
		board.PlacePiece(new Torre(board, Color.BLACK), new Posiçao(7, 7));
		board.PlacePiece(new Torre(board, Color.WHITE), new Posiçao(0, 7));
		
		board.PlacePiece(new Rei(board, Color.BLACK), new Posiçao(0,4));
		board.PlacePiece(new Rei(board, Color.WHITE), new Posiçao(7,4));
		
		
	}
}
