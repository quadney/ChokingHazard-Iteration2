package FestivalMiniGame;

import Models.PalaceCard;

public class HoldFestivalModel {
	JavaFestivalPlayer[] players;
	int indexOfCurrentPlayer;
	PalaceCard festivalCard;
	int valueOfPalaceCity;
	int indexOfPlayerWhoStartedFestival;
	
	public HoldFestivalModel(JavaFestivalPlayer[] festivalPlayers, int currentPlayer, PalaceCard festCard, int valueOfPalaceCity){
		this.players = festivalPlayers;
		this.indexOfCurrentPlayer = currentPlayer;
		this.indexOfPlayerWhoStartedFestival = currentPlayer;
		this.festivalCard = festCard;
		this.valueOfPalaceCity = valueOfPalaceCity;
	}
	
	public int getCurrentPlayer(){
		return this.indexOfCurrentPlayer;
	}
	
	public int getCurrentPlayerNumOfPalaceCards(){
		return this.players[indexOfCurrentPlayer].getNumPalaceCards();
	}
	
	public int getCurrentPlayerTabCount(){
		return this.players[indexOfCurrentPlayer].getTabCount();
	}
	
	public String getTabbedHashKey(){
		return this.players[indexOfCurrentPlayer].getTabbedImageString();
	}
	
	public int endTurn(){
		players[indexOfCurrentPlayer].endTurn();
		incrementPlayer();
		while(!players[indexOfCurrentPlayer].checkIfInFestival())
			incrementPlayer();
		return indexOfCurrentPlayer;
	}
	
	public boolean ifHadFullCycleTurnCheck(){
		return (indexOfCurrentPlayer == indexOfPlayerWhoStartedFestival);
	}
	
	public PalaceCard tabThroughPalaceCards(){
		players[indexOfCurrentPlayer].incrementTab();
		return players[indexOfCurrentPlayer].getTabbedPalaceCard();
	}
	
	public PalaceCard selectPalaceCard(){
		PalaceCard selectedCard = players[indexOfCurrentPlayer].getSelectedPalaceCard();
		return selectedCard;
	}
	
	public void endTabbing(){
		players[indexOfCurrentPlayer].cancelTabbing();
	}
	
	private void incrementPlayer(){
		indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) %players.length;
	}
	
	public int dropCurrentPlayer(){
		players[indexOfCurrentPlayer].dropPlayerFromFestival();
		return indexOfCurrentPlayer;
	}
	
	public boolean isThereOnlyOnePlayerLeft(){
		int numPlayers = 0;
		for(int i = 0; i < players.length; i++){
			if(players[i].checkIfInFestival())
				numPlayers++;
			if(numPlayers < 1)
				return false;;
		}
		return true;
	}
	
	public void endFestival(){
		//TODO
	}

}
