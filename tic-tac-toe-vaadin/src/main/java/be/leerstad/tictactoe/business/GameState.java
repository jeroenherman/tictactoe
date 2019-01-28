package be.leerstad.tictactoe.business;

/**
 *  Enumerations for the various states of the game
 */
public enum GameState {  
   PLAYING("Game in progress"), DRAW("Its a draw!"), CROSS_WON("Player 1 won"), NOUGHT_WON("Player 2 won!"), RESET("Game has been reset");
   	private String value;

	private GameState(String value) {
		this.value = value;
	}
   	public String toString() {
   		return value;
   	}
}