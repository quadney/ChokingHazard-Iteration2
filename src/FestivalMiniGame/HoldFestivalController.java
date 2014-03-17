package FestivalMiniGame;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class HoldFestivalController {
	private HoldFestivalPanel festPanel;
	private HoldFestivalModel festModel;
	
	public HoldFestivalController(HoldFestivalModel model, HoldFestivalPanel panel){
		this.festModel = model;
		this.festPanel = panel;
		festPanel.setFestivalController(this);
	}
	
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			case 9:
				//tab
				tabThroughPalaceCards();
				break;
			case 10:
				//enter
				playSelectedPalaceCard();
				break;
			case 27:
				//pressed esc
				cancelTabbing();
				break;
			case 88:
				//finish turn, X
				finishTurn();
		}
	}
	
	public JPanel getFestivalPanel(){
		return this.festPanel;
	}
	
	public void finishTurn(){
		//set the border of the current player to white
		festPanel.endPlayerTurn(festModel.getCurrentPlayer());
		
		//TODO check logic of this
		if(!festModel.isThereOnlyOnePlayerLeft()){
			//increment index and reflect the next player's turn in the panel
			festPanel.startPlayerTurn(festModel.endTurn());
			//call start turn in order to see if a full cycle has happened
			startTurn();
		}
		else{
			//TODO
			festModel.endFestival();
			//finishFestival();
		}
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
	
	public void dropPlayerFromFestival(){
		//drop the current player
		int index = festModel.dropCurrentPlayer();
		
		//hide his information from this
		festPanel.dropCurrentPlayer(index);
		finishTurn();
	}
	
	public void cancelTabbing(){
		
	}

}
