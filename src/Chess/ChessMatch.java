package Chess;

import Chess.pieces.Rei;
import Chess.pieces.Torre;
import boardgame.Borda;
import boardgame.Peça;
import boardgame.Posiçao;

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

	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		Posiçao position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	public ChessPiece performeChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Posiçao source = sourcePosition.toPosition();
		Posiçao target = targetPosition.toPosition();
		validateSourcePosition(source);
		validateTargetPosition(source, target);
		Peça capturedPiece = makeMove(source, target);
		return (ChessPiece) capturedPiece;

	}

	private Peça makeMove(Posiçao source, Posiçao target) {
		Peça p = board.removePiece(source);
		Peça capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		return capturedPiece;
	}

	private void validateSourcePosition(Posiçao position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não há peça na posição de origem");
		}
		if(!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessException("não há movimentos possíveis para a peça escolhida");
			
		}
	}
	
	
	private void validateTargetPosition(Posiçao source, Posiçao target) {
		if(!board.piece(source).possibleMove(target)) {
			throw new ChessException("a peça escolhida não pode se movar para posição de destino");
		}
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
