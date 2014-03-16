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
	
	public int endTurn(){
		players[indexOfCurrentPlayer].endTurn();
		incrementPlayer();
		while(!players[indexOfCurrentPlayer].checkIfInFestival())
			incrementPlayer();
		return indexOfCurrentPlayer;
	}
	
	public boolean startTurn(){
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
