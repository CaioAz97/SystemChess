package boardgame;

public class Borda {

	private int rows;
	private int columns;
	private Peça [][] pieces;
	
	public Borda(int rows, int columns) {
		if (rows < 1 || columns < 1) {
			throw new BoardException("ERRO NA CRIAÇÃO DE TABULEIRO: é necessário que haja uma linha e uma coluna. ");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Peça[rows][columns];
	}
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}
	
	public Peça piece (int row, int column) {
		if (!positionExists(row, column)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[row][column];
	}
	
	public Peça piece (Posiçao position) {
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
		
	}
	
	public void placePiece(Peça piece, Posiçao position) {
		if (thereIsAPiece(position)) {
			throw new BoardException("There is already a piece on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
	}

	private  boolean positionExists(int row, int column) {
		return row >=0 && row < rows && column >=0 && column < columns;
}
	public boolean positionExists(Posiçao position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsAPiece(Posiçao position) {	
		if (!positionExists(position)) {
			throw new BoardException("Position not on the board");
	}
		return piece(position) != null;
		
	}
	
}