package be.leerstad.tictactoe;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import be.leerstad.tictactoe.service.manager.GameManager;
import be.leerstad.tictactoe.ui.GameBoard;
import be.leerstad.tictactoe.ui.GameMenu;
import be.leerstad.tictactoe.ui.InfoPanel;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("TicTacToeTheme")
public class TicTacToeUI extends UI {
	private GameManager gameManager = new GameManager();
	

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        final HorizontalLayout hlayout = new HorizontalLayout();
        layout.addComponent(new GameMenu(gameManager));
        hlayout.addComponent(new GameBoard(gameManager)); 
        hlayout.addComponent(new InfoPanel(gameManager));
        layout.addComponent(hlayout);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "TicTacToeUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = TicTacToeUI.class, productionMode = false)
    public static class TicTacToeUIServlet extends VaadinServlet {
    }
}
