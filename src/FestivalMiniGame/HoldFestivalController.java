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
		System.out.println("Current Player: "+festModel.getCurrentPlayer()+" numplayers: "+festivalPlayers.size());
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
			festPanel.endPlayerTurn(festModel.getCurrentPlayer());
			
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
			//this user has no more palace cards, ask the all the players if they have enough cards
			//display that the user has no more palace cards and that they will be dropped from the competition
			festPanel.displayThatUserShouldDropOutOfFestival(festModel.getCurrentPlayer());
		}
		
	}
	
	private void startNewRound(){
		System.out.println("starting new round");
		if(festModel.isThereOnlyOnePlayerLeft() || festModel.checkForTies() || festModel.checkIfEveryoneIsOutOfCards()){
			//if there is only one player left
			//if there is a tie
			//if there is more than one player, but there's no tie, but everyone is out of cards
			//get the winner(s)
			ArrayList<JavaFestivalPlayer> winners = festModel.getWinners();
			
			if(winners.size() > 1){
				if(festPanel.askIfWouldLikeToSpiltWinnings(winners)){
					endFestival(winners, true);
				}
			}
			else
				endFestival(winners, false);
		}
	}
	
	private void endFestival(ArrayList<JavaFestivalPlayer> winningPlayers, boolean thereIsTie){
		System.out.println("ending festival, is there a tie? "+thereIsTie);
		//festPanel.displayWinner(winnerIndex);
	}
	
	public void tabThroughPalaceCards(){
		if(festModel.tabThroughPalaceCards() != null)
			festPanel.tabThroughPlayerPalaceCards(festModel.getCurrentPlayerTabCount(), festModel.getCurrentPlayerNumOfPalaceCards(), festModel.getCurrentPlayer(), festModel.getTabbedHashKey());
	}
	
	public void playSelectedPalaceCard(){
		PalaceCard card = festModel.selectPalaceCard();
		festPanel.playPalaceCardAtIndex(festModel.getCurrentPlayer(), card.getType(), festModel.getCurrentPlayerNumOfPalaceCards());
	}
	
	public void dropPlayerFromFestival(){
		//TODO
//		//drop the current player
//		int index = festModel.dropCurrentPlayer();
//		
//		//hide his information from this
//		festPanel.dropCurrentPlayer(index);
//		finishTurn();
	}
	
	public void cancelTabbing(){
		festModel.endTabbing();
		festPanel.cancelTabbing(festModel.getCurrentPlayer(), festModel.getCurrentPlayerNumOfPalaceCards());
	}

}
