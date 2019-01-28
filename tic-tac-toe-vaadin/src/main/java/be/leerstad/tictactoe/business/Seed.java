package be.leerstad.tictactoe.business;

/**
 * Enumerations for the seeds and cell contents
 */
public enum Seed { 

   EMPTY(""), CROSS("Player 1 your move!"), NOUGHT("Player 2 your move!");
   private String value;
	private Seed(String value) {
		this.value = value;
	}
	public String toString() {
		return this.value;
	}
}	
	
