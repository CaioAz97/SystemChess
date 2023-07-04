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
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}
	
	
	private void initialSetup() {
		placeNewPiece('B', 6, new Torre(board, Color.WHITE));
		placeNewPiece('E', 8, new Rei(board, Color.BLACK));
		placeNewPiece('E', 1, new Rei(board, Color.WHITE));
	}
}
