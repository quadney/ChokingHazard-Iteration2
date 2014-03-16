package FestivalMiniGame;

import Models.PalaceCard;

public class HoldFestivalModel {
	JavaFestivalPlayer[] players;
	int indexOfCurrentPlayer;
	PalaceCard festivalCard;
	int valueOfPalaceCity;
	
	public HoldFestivalModel(JavaFestivalPlayer[] festivalPlayers, int currentPlayer, PalaceCard festCard, int valueOfPalaceCity){
		this.players = festivalPlayers;
		this.indexOfCurrentPlayer = currentPlayer;
		this.festivalCard = festCard;
		this.valueOfPalaceCity = valueOfPalaceCity;
	}
	
	public void endTurn(){
		
	}
	
	public PalaceCard selectPalaceCard(){
		//TODO
		return new PalaceCard(1);
	}

}
