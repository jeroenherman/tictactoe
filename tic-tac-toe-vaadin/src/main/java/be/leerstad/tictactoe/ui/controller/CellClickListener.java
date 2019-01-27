package be.leerstad.tictactoe.ui.controller;

import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

import be.leerstad.tictactoe.service.manager.GameManager;
import be.leerstad.tictactoe.ui.CellUI;

public class CellClickListener implements ClickListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7273696174095523190L;
	private GameManager gameManager;
	
	public  CellClickListener(GameManager gameManager) {
		this.gameManager = gameManager;
	}

	@Override
	public void buttonClick(ClickEvent event) {
	
		if (event.getComponent().getClass().equals(CellUI.class)) {
			CellUI cellUI = (CellUI)event.getComponent();
			//cell.setSeed(gameManager.getCurrentPlayer());
			//cell.updateImage();
			
			Notification.show(gameManager.playerMove(cellUI.getCellDTO()));
					
		}

	}

}
