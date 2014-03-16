package FestivalMiniGame;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class HoldFestivalController {
	private HoldFestivalPanel festPanel;
	private HoldFestivalModel festModel;
	
	public HoldFestivalController(HoldFestivalModel model, HoldFestivalPanel panel){
		this.festModel = model;
		this.festPanel = panel;
	}
	
	public void keyPressed(KeyEvent e){
		//tab
		//enter
	}
	
	public JPanel getFestivalPanel(){
		return this.festPanel;
	}
	
	public void finishFestival(){
		
	}
	
	public void finishTurn(){
		
	}
	
	public void calculateWinner(){
		
	}
	
	public void playSelectedPalaceCard(){
		
	}

}
