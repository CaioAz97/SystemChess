package Chess;

import Chess.pieces.Rei;
import Chess.pieces.Torre;
import boardgame.Borda;

public class ChessMatch {

	private Borda board;

	public ChessMatch() {

		board = new Borda(8, 8);
		initialSetup();
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i = 0; i < board.getRows(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetup() {
		placeNewPiece('C', 1, new Torre(board, Color.WHITE));
		placeNewPiece('C', 2, new Torre(board, Color.WHITE));
		placeNewPiece('D', 2, new Torre(board, Color.WHITE));
		placeNewPiece('E', 2, new Torre(board, Color.WHITE));
		placeNewPiece('E', 1, new Torre(board, Color.WHITE));
		placeNewPiece('D', 1, new Rei(board, Color.WHITE));

		placeNewPiece('C', 7, new Torre(board, Color.BLACK));
		placeNewPiece('C', 8, new Torre(board, Color.BLACK));
		placeNewPiece('D', 7, new Torre(board, Color.BLACK));
		placeNewPiece('E', 7, new Torre(board, Color.BLACK));
		placeNewPiece('E', 8, new Torre(board, Color.BLACK));
		placeNewPiece('D', 8, new Rei(board, Color.BLACK));
	}
}
