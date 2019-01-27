package be.leerstad.tictactoe.service.manager;

import java.util.Observable;

import be.leerstad.tictactoe.business.Board;
import be.leerstad.tictactoe.business.GameState;
import be.leerstad.tictactoe.business.Seed;
import be.leerstad.tictactoe.service.dto.CellDTO;

public class GameManager extends Observable {
	private Board board; // the game board
	private GameState currentState; // the current state of the game (of enum GameState)
	private Seed currentPlayer; // the current player (of enum Seed)
	
	 public GameManager() {
	      board = new Board();  // allocate game-board
	      // Initialize the game-board and current status
	      initGame();
	   }

	/** Initialize the game-board contents and the current states */
	public void initGame() {
		board.init(); // clear the board contents
		currentPlayer = Seed.CROSS; // CROSS plays first
		currentState = GameState.PLAYING; // ready to play
	}

	/**
	 * The player with "theSeed" makes one move, with input validation. Update
	 * Cell's content, Board's currentRow and currentCol.
	 */
	public String playerMove(CellDTO cellDTO) {
		int row, col;
		String message = "";
			 row = cellDTO.getRow() - 1; // arrays are 0 based
			 col = cellDTO.getCol() - 1; // arrays are 0 based
			if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS && board.getSeed(row, col) == Seed.EMPTY) {
				board.setSeed(row, col, currentPlayer);
				board.setCurrentRow(row);
				board.setCurrentCol(col);
				cellDTO.setSeed(currentPlayer);
				setChanged();
				notifyObservers(cellDTO); // change cell image via cellDTO
				message = checkGameState();
				if (currentState.equals(GameState.PLAYING)) {
				message = "Player: "+switchPlayer().toString();
				
				}
				
			} else {
				message ="This move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Try again...";
			}
			return message;
	}
	
	private Seed switchPlayer() {
        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        return currentPlayer;
	}

	/** Update the currentState after the player with "theSeed" has moved */
	public void updateGame(Seed theSeed) {
		if (board.hasWon(theSeed)) { // check for win
			currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
		} else if (board.isDraw()) { // check for draw
			currentState = GameState.DRAW;
		}
		// Otherwise, no change to current state (still GameState.PLAYING).
	}
	
	public String checkGameState() {
		  // return message if game-over
		String message ="";
        if (currentState == GameState.CROSS_WON) {
           message ="'X' won!";
        } else if (currentState == GameState.NOUGHT_WON) {
           message ="'O' won!";
        } else if (currentState == GameState.DRAW) {
           message="It's Draw!";
        }
        return message;
	}

	public Seed getCurrentPlayer() {
		return currentPlayer;
	}

}
