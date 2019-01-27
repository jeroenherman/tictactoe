package be.leerstad.tictactoe.service.dto;

import be.leerstad.tictactoe.business.Seed;

public class CellDTO {
	private int row;
	private int col;
	private Seed seed;
	
	
	public CellDTO(int row, int col, Seed seed) {
		super();
		this.row = row;
		this.col = col;
		this.seed = seed;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public Seed getSeed() {
		return seed;
	}
	public void setSeed(Seed seed) {
		this.seed = seed;
	}
	
	

}
