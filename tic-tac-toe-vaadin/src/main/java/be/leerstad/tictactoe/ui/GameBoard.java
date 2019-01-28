package be.leerstad.tictactoe.ui;

import java.util.Observable;
import java.util.Observer;

import com.vaadin.ui.GridLayout;

import be.leerstad.tictactoe.business.GameState;
import be.leerstad.tictactoe.business.Seed;
import be.leerstad.tictactoe.service.dto.CellDTO;
import be.leerstad.tictactoe.service.manager.GameManager;

public class GameBoard extends GridLayout implements Observer {

	/**
	 * Gameboard has 3 rows and 3 columns 
	 */
	private static final long serialVersionUID = 6919576414506140491L;
	private static final int ROWS =3;
	private static final int COLUMNS =3;
	private GameManager gameManager;
	public GameBoard(GameManager gameManager) {
		this.gameManager = gameManager;
		gameManager.addObserver(this);
		init();	
	}
	
	public void init() {
		generateMatrixGrid(ROWS,COLUMNS );
	}
	
	 private void generateMatrixGrid(final int rows, final int columns) {
	        removeAllComponents();
	        setRows(rows);
	        setColumns(columns);
	 
	        for (int row = 0; row < getRows(); row++) {
	            for (int col = 0; col < getColumns(); col++) {
	            
	                final CellUI cell = new CellUI(	new CellDTO(row+1,col+1,Seed.EMPTY), gameManager);
	                addComponent(cell);
	            }
	        }
	    }

	@Override
	public void update(Observable o, Object arg) {
		if((arg!=null)&&arg.equals(GameState.RESET))
		init();
	}
}
