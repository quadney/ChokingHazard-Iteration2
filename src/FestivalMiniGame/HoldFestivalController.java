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
	
	public void finishTurn(){
		//check the validity of some shit
			//set the border of the current player to white
			festPanel.endPlayerTurn(festModel.getCurrentPlayer());
			
			//increment index and reflect the next player's turn in the panel
			festPanel.startPlayerTurn(festModel.endTurn());
			//call start turn in order to see if a full cycle has happened
			startTurn();
	}
	
	private void startTurn(){
		if(festModel.startTurn()){
			//a full cycle has happened
			//ask the users shit about their shit. 
			//if returns true, then call finish festival
		}
	}

	private void finishFestival(){
		calculateWinner();
	}
	
	private void calculateWinner(){
		
	}
	
	public void tabThroughPalaceCards(){
		
	}
	
	public void playSelectedPalaceCard(){
		
	}
	
	public void dropPlayerFromFestival(int index){
		
	}

}
