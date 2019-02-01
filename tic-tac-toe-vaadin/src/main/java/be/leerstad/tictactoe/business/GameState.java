package be.leerstad.tictactoe.business;

/**
 *  Enumerations for the various states of the game
 */
public enum GameState {  
   PLAYING("Game in progress"), DRAW("Its a draw!"), CROSS_WON("Cross won"), NOUGHT_WON("Nought won!"), RESET("Game has been reset");
   	private String value;

	private GameState(String value) {
		this.value = value;
	}
   	public String toString() {
   		return value;
   	}
}