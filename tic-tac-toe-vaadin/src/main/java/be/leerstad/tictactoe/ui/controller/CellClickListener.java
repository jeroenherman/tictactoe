package be.leerstad.tictactoe.ui.controller;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

import be.leerstad.tictactoe.business.GameState;
import be.leerstad.tictactoe.service.dto.GameMode;
import be.leerstad.tictactoe.service.manager.GameManager;
import be.leerstad.tictactoe.ui.CellUI;

public class CellClickListener implements ClickListener {

	/**
	 * Controller class ClickEvent is also responsible for single player or dual player mode execution
	 */
	private static final long serialVersionUID = 7273696174095523190L;
	private GameManager gameManager;

	public CellClickListener(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void buttonClick(ClickEvent event) {

		if (gameManager.getCurrentState().equals(GameState.PLAYING)
				&& event.getComponent().getClass().equals(CellUI.class)) {
			CellUI cellUI = (CellUI) event.getComponent();
			if (gameManager.getGameMode().equals(GameMode.SINGLE)) 
				Notification.show(gameManager.singlePlayer(cellUI.getCellDTO()));
			if (gameManager.getGameMode().equals(GameMode.DUAL))
				Notification.show(gameManager.dualPlayer(cellUI.getCellDTO()));
		} else
			Notification.show(gameManager.getCurrentState().toString() + " Please Reset...");

	}

}
