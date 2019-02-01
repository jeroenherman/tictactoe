package be.leerstad.tictactoe.ui;

import java.util.Observable;
import java.util.Observer;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import be.leerstad.tictactoe.business.GameState;
import be.leerstad.tictactoe.service.dto.PlayerDTO;
import be.leerstad.tictactoe.service.manager.GameManager;

public class InfoPanel extends Panel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6901363380531551162L;
	private GameManager gameManager;

	public InfoPanel(GameManager gamemanager) {
		this.gameManager = gamemanager;
		gamemanager.addObserver(this);

	}

	private void init() {
		// this.setCaption("Info Panel");
		this.setWidth(300, Unit.PIXELS);
		final VerticalLayout layout = new VerticalLayout();
		layout.setWidth(300, Unit.PIXELS);
		layout.setSpacing(false);
		if (gameManager.getCurrentState().equals(GameState.PLAYING))
			layout.addComponent(new Label(gameManager.getCurrentPlayer().toString()));
		layout.addComponent(new Label(gameManager.getGameMode().toString()));
		layout.addComponent(new Label(gameManager.getCurrentState().toString()));
		layout.addComponent(setScore(gameManager.getPlayer1()));
		layout.addComponent(setScore(gameManager.getPlayer2()));
		setContent(layout);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		init();
	}

	private Component setScore(PlayerDTO player) {
		StringBuilder sb = new StringBuilder();
		sb.append("Current Score : ").append(player.getName()).append(" is ").append(player.getScore());
			return new Label (sb.toString());
	}

}