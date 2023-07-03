package boardgame;

public class Borda {

	private int rows;
	private int columns;
	private Peça [][] pieces;
	public Borda(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		pieces = new Peça[rows][columns];
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		this.columns = columns;
	}
	
	public Peça piece (int row, int column) {
		return pieces[row][column];
	}
	
	public Peça piece (Posiçao position) {
		return pieces[position.getRow()][position.getColumn()];
		
	}
}