package be.leerstad.tictactoe.ui;

import java.util.Observable;
import java.util.Observer;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.themes.ValoTheme;

import be.leerstad.tictactoe.business.Seed;
import be.leerstad.tictactoe.service.dto.CellDTO;
import be.leerstad.tictactoe.service.manager.GameManager;
import be.leerstad.tictactoe.ui.controller.CellClickListener;

public class CellUI extends Button implements Observer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5604026669741275418L;
	private static final ThemeResource NOUGHT = new ThemeResource("img/nought.jpg");
	private static final ThemeResource CROSS=  new ThemeResource("img/cross.jpg");
	private static final ThemeResource EMPTY=  new ThemeResource("img/empty.jpg");
	private CellDTO cellDTO;
	private GameManager gameManager;

	public CellUI(CellDTO cellDTO, GameManager gameManager) {
		this.cellDTO = cellDTO;
		this.gameManager = gameManager;
		setDescription("row: "+cellDTO.getRow()+" col: "+cellDTO.getCol());
		init();
	}
	
	public void init() {
		setIcon(EMPTY);
		addStyleName(ValoTheme.BUTTON_ICON_ONLY);
		//addStyleName(ValoTheme.BUTTON_BORDERLESS);
		setHeight(100, Unit.PIXELS); // logo.png Height
        setWidth(100, Unit.PIXELS); // logo.png Width
        addClickListener(new CellClickListener(gameManager));
        gameManager.addObserver(this);
		
	}

	
	public void updateImage() {
		if (this.cellDTO.getSeed().equals(Seed.CROSS))
				setIcon(CROSS);
		if (this.cellDTO.getSeed().equals(Seed.NOUGHT))
			setIcon(NOUGHT);
		if (this.cellDTO.getSeed().equals(Seed.EMPTY))
			setIcon(EMPTY);
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		if ((arg1!=null)&&(arg1.getClass().equals(CellDTO.class))){
				CellDTO update = (CellDTO) arg1;
				if ((update.getRow()==this.cellDTO.getRow())&&(update.getCol()==this.cellDTO.getCol()))
					setCellDTO(update);
				if (update.getSeed().equals(EMPTY))
					setCellDTO(update);
		}	
	}

	public CellDTO getCellDTO() {
		return cellDTO;
	}

	public void setCellDTO(CellDTO cellDTO) {
		this.cellDTO = cellDTO;
		updateImage();
	}
	
	
	
	
}
