package Chess;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Chess.pieces.Bispo;
import Chess.pieces.Cavalo;
import Chess.pieces.Peao;
import Chess.pieces.Rainha;
import Chess.pieces.Rei;
import Chess.pieces.Torre;
import boardgame.Borda;
import boardgame.Peça;
import boardgame.Posiçao;


public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Borda board;
	private boolean check;
	private boolean checkMate;
	private ChessPiece enPassantVulnerable;
	private ChessPiece promoted;
	
	
	private List<Peça> piecesOnTheBoard = new ArrayList<>();
	private List<Peça> capturedPieces = new ArrayList<>();
	
	public ChessMatch() {
		board = new Borda(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();

	}

	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		return check;
	}
	
	public boolean getCheckMate() {
		return checkMate;
	}
	
	public ChessPiece getEnPassantVulnerable() {
		return enPassantVulnerable;
	}
	
	public ChessPiece getPromoted() {
		return promoted;	
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
		
		if(testCheck(currentPlayer)) {
			undoMove(source, target, capturedPiece);
			throw new ChessException("VOCÊ NÃO PODE SE COLOCAR EM CHECK");
		}
		
		ChessPiece movedPiece = (ChessPiece)board.piece(target);
		
		//# ESPECIAL MOVEMENT PROMOTION;
		promoted = null;
		if(movedPiece instanceof Peao) {
			if((movedPiece.getColor() == Color.WHITE && target.getRow() == 0) ||(movedPiece.getColor() == Color.BLACK && target.getRow() == 7)) {
				promoted = (ChessPiece)board.piece(target);
				promoted = replacePromotedPiece("Q");
			}
		}
		
		check = (testCheck(opponent(currentPlayer))) ? true : false;
		
		if(testCheckMate(opponent(currentPlayer))) {
			checkMate = true;
		}
		else {
			
			nextTurn();
		}
		
		//# Especial Move en Passant.
		if(movedPiece instanceof Peao && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2 )) {
			enPassantVulnerable = movedPiece;
		}
		
		else {
			enPassantVulnerable = null;
		}
		
		return (ChessPiece) capturedPiece;
	}
	
	public ChessPiece replacePromotedPiece(String type) {
		if(promoted == null) {
			throw new IllegalStateException("NÃO HÁ PEÇA A SER PROMOVIDA!");
		}
		if(!type.equals("B") && !type.equals("T") && !type.equals("Q") & !type.equals("C")) {
			throw new InvalidParameterException("TIPO DE PROMOÇÃO INVALIDA!");
		} 
		
		Posiçao pos = promoted.getChessPosition().toPosition();
		Peça p = board.removePiece(pos);
		piecesOnTheBoard.remove(p);
		
		ChessPiece newPiece = newPiece(type, promoted.getColor());
		board.placePiece(newPiece, pos);
		piecesOnTheBoard.add(newPiece);
		
		return newPiece;
		
		
	}
	
	private ChessPiece newPiece(String type, Color color) {
		if(type.equals("B")) return new Bispo(board, color);
		if(type.equals("T")) return new Torre(board, color);
		if(type.equals("C")) return new Cavalo(board, color);
		return new Rainha(board, color);
	}
	
	
	private Peça makeMove(Posiçao source, Posiçao target) {
		ChessPiece p = (ChessPiece)board.removePiece(source);
		p.increaseMoveCount();
		Peça capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if(capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);
		}
			
		//#ESPECIAL MOVEMENT CASTLING KINGSIDE.
		if(p instanceof Rei && target.getColumn() == source.getColumn() + 2) {
			Posiçao origemT = new Posiçao(source.getRow(), source.getColumn() + 3);
			Posiçao destinoT = new Posiçao(source.getRow(), source.getColumn() + 1);
			ChessPiece torre = (ChessPiece)board.removePiece(origemT);
			board.placePiece(torre, destinoT);
			torre.increaseMoveCount();
		}
		
		//#ESPECIAL MOVEMENT CASTLING QUEENSIDE.
		if(p instanceof Rei && target.getColumn() == source.getColumn() - 2) {
			Posiçao origemT = new Posiçao(source.getRow(), source.getColumn() - 4);
			Posiçao destinoT = new Posiçao(source.getRow(), source.getColumn() - 1);
			ChessPiece torre = (ChessPiece)board.removePiece(origemT);
			board.placePiece(torre, destinoT);
			torre.increaseMoveCount();
		}
		
		//# ESPECIAL MOVEMENT EN PASSANT
		if(p instanceof Peao) {
			if(source.getColumn() != target.getColumn() && capturedPiece == null) {
				Posiçao peaoP;
				if(p.getColor() == Color.WHITE) {
					peaoP = new Posiçao(target.getRow() +1, target.getColumn());
				}
				else {
					peaoP = new Posiçao(target.getRow() -1, target.getColumn());
				}
				capturedPiece = board.removePiece(peaoP);
				capturedPieces.add(capturedPiece);
				piecesOnTheBoard.remove(capturedPiece);
			}
		}
		
		return capturedPiece;
	}
	
	private void undoMove(Posiçao source, Posiçao target, Peça capturedPiece) {
		ChessPiece p = (ChessPiece)board.removePiece(target);
		p.decreaseMoveCount();
		board.placePiece(p, source);
		
		if(capturedPiece != null) {
			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);
		}
		
		//#ESPECIAL MOVEMENT CASTLING KINGSIDE.
				if(p instanceof Rei && target.getColumn() == source.getColumn() + 2) {
					Posiçao origemT = new Posiçao(source.getRow(), source.getColumn() + 3);
					Posiçao destinoT = new Posiçao(source.getRow(), source.getColumn() + 1);
					ChessPiece torre = (ChessPiece)board.removePiece(destinoT);
					board.placePiece(torre, origemT);
					torre.decreaseMoveCount();
				}
				
				//#ESPECIAL MOVEMENT CASTLING QUEENSIDE.
				if(p instanceof Rei && target.getColumn() == source.getColumn() - 2) {
					Posiçao origemT = new Posiçao(source.getRow(), source.getColumn() - 4);
					Posiçao destinoT = new Posiçao(source.getRow(), source.getColumn() - 1);
					ChessPiece torre = (ChessPiece)board.removePiece(destinoT);
					board.placePiece(torre, origemT);
					torre.decreaseMoveCount();
				}		
				
				//#ESPECIAL MOVEMENT EN PASSANT.
				if(p instanceof Peao) {
					if(source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable) {
						ChessPiece peao = (ChessPiece)board.removePiece(target);
						Posiçao peaoP;
						if(p.getColor() == Color.WHITE) {
							peaoP = new Posiçao(3, target.getColumn());
						}
						else {
							peaoP = new Posiçao(4, target.getColumn());
						}
						board.placePiece(peao, peaoP);
					}
				}	
	}

	private void validateSourcePosition(Posiçao position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessException("Não há peça na posição de origem");
		}
		if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			throw new ChessException("A peça escolhida não é a sua");
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
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}

	private Color opponent(Color color){
		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE; 
	}
	
	private ChessPiece rei(Color color) {
		List<Peça> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor()== color).collect(Collectors.toList());
		for (Peça p : list) {
			if (p instanceof Rei) {
				return (ChessPiece)p;
			}
		}
		throw new IllegalStateException("NÂO EXISTE O REI DA " + color + "NO TABULEIRO");
	}
	
	private boolean testCheck(Color color) {
		Posiçao reiPosition = rei(color).getChessPosition().toPosition();
		List<Peça> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
		for (Peça p : opponentPieces) {
			boolean [][] mat = p.possibleMoves();
			if(mat[reiPosition.getRow()][reiPosition.getColumn()]) {
				return true;
			}
		}
		return false;
	}
	
	private boolean testCheckMate(Color color) {
		if(!testCheck(color)) {
			return false;
		}
		List<Peça> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for(Peça p : list) {
			boolean[][] mat = p.possibleMoves();
			for(int i=0; i<board.getRows(); i++) {
				for (int j=0; j<board.getColumns(); j++) {
					if (mat[i][j]) {
						Posiçao source = ((ChessPiece)p).getChessPosition().toPosition();
						Posiçao target = new Posiçao(i, j);
						Peça capturedPiece = makeMove(source, target);
						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);
						if(!testCheck) {
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	                             
	
	private void initialSetup() {
			placeNewPiece('A', 1, new Torre(board, Color.WHITE));
			placeNewPiece('B', 1, new Cavalo(board, Color.WHITE));
			placeNewPiece('C', 1, new Bispo(board, Color.WHITE));
			placeNewPiece('D', 1, new Rainha(board, Color.WHITE));
			placeNewPiece('E', 1, new Rei(board, Color.WHITE, this));
			placeNewPiece('F', 1, new Bispo(board, Color.WHITE));
			placeNewPiece('G', 1, new Cavalo(board, Color.WHITE));
			placeNewPiece('H', 1, new Torre(board, Color.WHITE));
		  	placeNewPiece('A', 2, new Peao(board, Color.WHITE, this));
	        placeNewPiece('B', 2, new Peao(board, Color.WHITE, this));
	        placeNewPiece('C', 2, new Peao(board, Color.WHITE, this));
	        placeNewPiece('D', 2, new Peao(board, Color.WHITE, this));
	        placeNewPiece('E', 2, new Peao(board, Color.WHITE, this));
	        placeNewPiece('F', 2, new Peao(board, Color.WHITE, this));
	        placeNewPiece('G', 2, new Peao(board, Color.WHITE, this));
	        placeNewPiece('H', 2, new Peao(board, Color.WHITE, this));
	        
	        placeNewPiece('A', 8, new Torre(board, Color.BLACK));
	        placeNewPiece('B', 8, new Cavalo(board, Color.BLACK));
	        placeNewPiece('C', 8, new Bispo(board, Color.BLACK));
	        placeNewPiece('D', 8, new Rainha(board, Color.BLACK));
	        placeNewPiece('E', 8, new Rei(board, Color.BLACK, this));
	        placeNewPiece('F', 8, new Bispo(board, Color.BLACK));
	        placeNewPiece('G', 8, new Cavalo(board, Color.BLACK));
	        placeNewPiece('H', 8, new Torre(board, Color.BLACK));
	        placeNewPiece('A', 7, new Peao(board, Color.BLACK, this));
	        placeNewPiece('B', 7, new Peao(board, Color.BLACK, this));
	        placeNewPiece('C', 7, new Peao(board, Color.BLACK, this));
	        placeNewPiece('D', 7, new Peao(board, Color.BLACK, this));
	        placeNewPiece('E', 7, new Peao(board, Color.BLACK, this));
	        placeNewPiece('F', 7, new Peao(board, Color.BLACK, this));
	        placeNewPiece('G', 7, new Peao(board, Color.BLACK, this));
	        placeNewPiece('H', 7, new Peao(board, Color.BLACK, this));
		}
	
}
