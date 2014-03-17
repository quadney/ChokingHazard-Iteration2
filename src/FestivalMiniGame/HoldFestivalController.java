package FestivalMiniGame;

import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import Models.PalaceCard;

public class HoldFestivalController {
	private HoldFestivalPanel festPanel;
	private HoldFestivalModel festModel;
	
	public HoldFestivalController(JavaFestivalPlayer[] festivalPlayers, int currentPlayerIndex, PalaceCard festCard, int palaceValue){
		this.festModel = new HoldFestivalModel(festivalPlayers, currentPlayerIndex, festCard, palaceValue);
		this.festPanel = new HoldFestivalPanel(festivalPlayers, currentPlayerIndex, festCard.getType(), palaceValue);
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
		
		//increment index and reflect the next player's turn in the panel
		//end the player turn in the model. if returns -1 then that means that the festival is ending
		int nextPlayer = festModel.endTurn();
		
		if(nextPlayer < 0){
			//present that its the end of the festival
			//calculate the winner
			//present a thing with numpoints won and who got it and shit
		}
		else{
			festPanel.startPlayerTurn(nextPlayer);
			startTurn();
		}
	}
	
	private void startTurn(){
		if(festModel.ifHadFullCycleTurnCheck()){
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
		festModel.tabThroughPalaceCards();
		festPanel.tabThroughPlayerPalaceCards(festModel.getCurrentPlayerTabCount(), festModel.getCurrentPlayerNumOfPalaceCards(), festModel.getCurrentPlayer(), festModel.getTabbedHashKey());
	}
	
	public void playSelectedPalaceCard(){
		PalaceCard card = festModel.selectPalaceCard();
		festPanel.playPalaceCardAtIndex(festModel.getCurrentPlayer(), card.getType(), festModel.getCurrentPlayerNumOfPalaceCards());
	}
	
	public void dropPlayerFromFestival(){
		//drop the current player
		int index = festModel.dropCurrentPlayer();
		
		//hide his information from this
		festPanel.dropCurrentPlayer(index);
		finishTurn();
	}
	
	public void cancelTabbing(){
		festModel.endTabbing();
		festPanel.cancelTabbing(festModel.getCurrentPlayer(), festModel.getCurrentPlayerNumOfPalaceCards());
	}

}
