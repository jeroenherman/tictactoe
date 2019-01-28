package be.leerstad.tictactoe.service.manager;

import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

import be.leerstad.tictactoe.business.Board;
import be.leerstad.tictactoe.business.GameState;
import be.leerstad.tictactoe.business.Seed;
import be.leerstad.tictactoe.service.dto.CellDTO;
import be.leerstad.tictactoe.service.dto.GameMode;

public class GameManager extends Observable {
	private Board board; // the game board
	private GameMode currentGameMode;
	private GameState currentState; // the current state of the game (of enum GameState)
	private Seed currentPlayer; // the current player (of enum Seed)

	public GameManager() {
		board = new Board(); // allocate game-board
		// Initialize the game-board and current status
		currentGameMode = GameMode.DUAL;
		initGame();
	}


	public String singlePlayer(CellDTO cellDTO) {
		String message = "";
		if (getCurrentPlayer().equals(Seed.CROSS))
			message = dualPlayer(cellDTO);
		if (getCurrentPlayer().equals(Seed.NOUGHT)) {
			do {
				int row = ThreadLocalRandom.current().nextInt(1, 4);
				int col = ThreadLocalRandom.current().nextInt(1, 4);
				cellDTO = new CellDTO(row, col, Seed.EMPTY);
				message = dualPlayer(cellDTO);
			} while (currentPlayer.equals(Seed.NOUGHT) && currentState.equals(GameState.PLAYING));
		}
		return message;
	}

	public Seed getCurrentPlayer() {
		return currentPlayer;
	}

	public GameState getCurrentState() {
		return currentState;
	}

	public GameMode getGameMode() {
		return currentGameMode;
	}

	/** Initialize the game-board contents and the current states */
	public void initGame() {
		board.init(); // clear the board contents
		setChanged();
		notifyObservers(GameState.RESET);
		currentPlayer = Seed.CROSS; // CROSS plays first
		currentState = GameState.PLAYING; // ready to play

	}

	public void setGameMode(GameMode gameMode) {
		if (currentState.equals(GameState.PLAYING))
			initGame();
		this.currentGameMode = gameMode;
		setChanged();
		notifyObservers();
	}

	/**
	 * The player with "theSeed" makes one move, with input validation. Update
	 * Cell's content, Board's currentRow and currentCol.
	 */
	public String dualPlayer(CellDTO cellDTO) {
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
			updateGame(currentPlayer);
			message = currentState.toString();
			if (currentState.equals(GameState.PLAYING))
				message =  switchPlayer().toString();

		} else {
			message = "This move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Try again...";
		}
		return message;
	}

	private Seed switchPlayer() {
		currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
		setChanged();
		notifyObservers();
		return currentPlayer;
	}

	/** Update the currentState after the player with "theSeed" has moved */
	public void updateGame(Seed theSeed) {
		if (board.hasWon(theSeed)) { // check for win
			currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
			setChanged();
		} else if (board.isDraw()) { // check for draw
			currentState = GameState.DRAW;
			setChanged();
		}
		// Otherwise, no change to current state (still GameState.PLAYING).
		notifyObservers();
	}

}
