package FestivalMiniGame;

import java.util.ArrayList;

import Models.JavaPlayer;
import Models.PalaceCard;

public class JavaFestivalPlayer {
	private JavaPlayer player;
	private boolean isInFestival;
	private ArrayList<PalaceCard> palaceCards;

	public JavaFestivalPlayer(JavaPlayer player, ArrayList<PalaceCard> validPalaceCards) {
		//call this when the player has the ability to be in the festival
		//if they are not able, don't create a FestivalPlayer
		this.player = player;
		this.palaceCards = validPalaceCards;
		this.isInFestival = true;
	}
	
	public boolean checkIfInFestival(){
		return this.isInFestival;
	}
	
	public PalaceCard getSelectedPalaceCard(int index){
		//this should only be called if the player is valid
		return palaceCards.get(index);
	}


}
