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
		System.out.println(e.getKeyCode());
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
			festPanel.endPlayerTurn(festModel.getCurrentPlayer(), festModel.getCurrentPlayerBid());
			
			
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
		System.out.println("There is only one player left: "+festModel.isThereOnlyOnePlayerLeft());
		System.out.println("Everyone is out of Cards: "+festModel.checkIfEveryoneIsOutOfCards());
		System.out.println("Num of Winners: "+festModel.getNumWinners());
		if(festModel.isThereOnlyOnePlayerLeft() || festModel.checkIfEveryoneIsOutOfCards() || (festModel.getNumWinners() > 1)){
			System.out.println("ending the festival...");
			//if there is only one player left
			//if there is a ti
			//if there is more than one player, but there's no tie, but everyone is out of cards
			//get the winner(s)
			if(festModel.getNumWinners() > 1){
				if(festPanel.askIfWouldLikeToSpiltWinnings()){
					endFestival(true);
				}
			}
			else
				endFestival(false);
		}
	}
	
	private void endFestival(boolean thereIsTie){
		System.out.println("ending festival, is there a tie? "+thereIsTie);
		//festPanel.displayWinner(winnerIndex);
		//get the winners
		//get the points per winner
		festModel.endFestival(thereIsTie);
		System.out.println("festival has ended");
		festFrame.festivalDidReturn(festModel.getDiscardedPalaceCards());
	}
	
	public void tabThroughPalaceCards(){
		if(festModel.tabThroughPalaceCards() != null)
			festPanel.tabThroughPlayerPalaceCards(festModel.getCurrentPlayerTabCount(), festModel.getCurrentPlayerNumOfPalaceCards(), festModel.getCurrentPlayer(), festModel.getTabbedHashKey());
	}
	
	public void playSelectedPalaceCard(){
		PalaceCard card = festModel.selectPalaceCard();
		festPanel.playPalaceCardAtIndex(festModel.getCurrentPlayer(), card.getType(), festModel.getCurrentPlayerNumOfPalaceCards());
		festPanel.setCurrentPlayerBid(festModel.getCurrentPlayer(), festModel.getCurrentPlayerBid());
	}
	
	public void dropPlayerFromFestival(){
		System.out.println("dropping player from festival...");
		//TODO
		
		//hide his information from this
		festPanel.dropCurrentPlayer(festModel.getCurrentPlayer());
		
		int index = festModel.dropCurrentPlayer();
		festPanel.startPlayerTurn(index);
		System.out.println("completed dropping");
	}
	
	public void cancelTabbing(){
		festModel.endTabbing();
		festPanel.cancelTabbing(festModel.getCurrentPlayer(), festModel.getCurrentPlayerNumOfPalaceCards());
	}

}
