package FestivalMiniGame;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

import Models.PalaceCard;

public class HoldFestivalController {
	private HoldFestivalPanel festPanel;
	private HoldFestivalModel festModel;
	private HoldFestivalFrame festFrame;
	
	public HoldFestivalController(HoldFestivalFrame frame, ArrayList<JavaFestivalPlayer> festivalPlayers, PalaceCard festCard, int palaceValue){
		this.festFrame = frame;
		this.festModel = new HoldFestivalModel(festivalPlayers, festCard, palaceValue);
		this.festPanel = new HoldFestivalPanel(festivalPlayers, festModel.getCurrentPlayer(), festCard.getType(), palaceValue);
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
			case 76:
				dropPlayerFromFestival();
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
		//check that the player as made at least the highest bid
		if(festModel.canEndTurn()){
			//set the border of the current player to white
			festPanel.endPlayerTurn(festModel.getCurrentPlayer(), festModel.getCurrentPlayerBid(), festModel.getCurrentPlayerNumOfPalaceCards());
			
			
			//increment index and reflect the next player's turn in the panel
			//end the player turn in the model. if returns -1 then that means that the festival is ending
			int nextPlayer = festModel.endTurn();
			festPanel.startPlayerTurn(nextPlayer);
			startTurn();
		}
		else {
			//alert the player that he needs to drop out from the festival or play another card
			festPanel.tellUserThatHeHasToPlayAPalaceCardOrDropOut();
		}
	}
	
	private void startTurn(){
		if(festModel.ifHadFullCycleTurnCheck()){
			startNewRound();
		}
		else if(festModel.getCurrentPlayerNumOfPalaceCards() == 0){
			if(!festModel.canEndTurn())
				festPanel.tellUserThatHeHasToDropOut();
		}
		
	}
	
	private void startNewRound(){
		boolean noCardsLeft = festModel.checkIfEveryoneIsOutOfCards();
		if(festModel.getNumPlayersInFestival() == 1){
			//if there is only one player left
			endFestival(false);
		}
		else if(festModel.getNumWinners() > 1){
			//if there is a tie
			if(noCardsLeft){
				//they are all out of cards, so they have to end the festival
				endFestival(true);
			}
			else if(festPanel.askIfWouldLikeToSpiltWinnings()){
				endFestival(true);
			}
			
			//there is a tie and they want to continue playing. let them
		}
		else if(noCardsLeft){
			
			endFestival(false);
		}
	}
	
	private void endFestival(boolean thereIsTie){
		//get the winners
		//get the points per winner
		festModel.endFestival(thereIsTie);
		festPanel.displayWinner(festModel.getWinners(), festModel.getFamePointsWon(thereIsTie));
		festFrame.festivalDidReturn(festModel.getDiscardedPalaceCards());
	}
	
	public void tabThroughPalaceCards(){
		PalaceCard cardToHighlight = festModel.tabThroughPalaceCards();
		if(cardToHighlight != null)
			festPanel.tabThroughPlayerPalaceCards(festModel.getCurrentPlayerTabCount(), festModel.getCurrentPlayerNumOfPalaceCards(), festModel.getCurrentPlayer(), festModel.getTabbedHashKey());
	}
	
	public void playSelectedPalaceCard(){
		PalaceCard card = festModel.selectPalaceCard();
		if(card != null){
			festPanel.playPalaceCardAtIndex(festModel.getCurrentPlayer(), card.getType(), festModel.getCurrentPlayerNumOfPalaceCards());
			festPanel.setCurrentPlayerBid(festModel.getCurrentPlayer(), festModel.getCurrentPlayerBid());
		}
	}
	
	public void dropPlayerFromFestival(){
		festPanel.dropCurrentPlayer(festModel.getCurrentPlayer());
		
		int index = festModel.dropCurrentPlayer();
		festPanel.startPlayerTurn(index);
		startTurn();
	}
	
	public void cancelTabbing(){
		festModel.endTabbing();
		festPanel.cancelTabbing(festModel.getCurrentPlayer(), festModel.getCurrentPlayerNumOfPalaceCards());
	}

}
