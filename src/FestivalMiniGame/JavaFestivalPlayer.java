package FestivalMiniGame;

import java.util.ArrayList;

import Models.JavaPlayer;
import Models.PalaceCard;

public class JavaFestivalPlayer {
	private JavaPlayer player;
	private boolean isInFestival;
	private ArrayList<PalaceCard> palaceCards;
	int tabCount;

	public JavaFestivalPlayer(JavaPlayer player, ArrayList<PalaceCard> validPalaceCards) {
		//call this when the player has the ability to be in the festival
		//if they are not able, don't create a FestivalPlayer
		this.player = player;
		this.palaceCards = validPalaceCards;
		this.isInFestival = true;
		this.tabCount = 0;
	}
	
	public String getName(){
		return this.player.getName();
	}
	public String getColor(){
		return this.player.getColor();
	}
	public int getNumPalaceCards(){
		return this.palaceCards.size();
	}
	
	public boolean checkIfInFestival(){
		return this.isInFestival;
	}
	
	public void incrementTab(){
		tabCount = (tabCount + 1) % palaceCards.size();
	}
	
	public void dropPlayerFromFestival(){
		this.isInFestival = false;
	}
	
	public PalaceCard getSelectedPalaceCard(){
		//this should only be called if the player is valid
		PalaceCard selectedCard = palaceCards.get(tabCount);
		palaceCards.remove(tabCount);
		tabCount = 0;
		return selectedCard;
	}
	
	public PalaceCard getTabbedPalaceCard(){
		return palaceCards.get(tabCount);
	}

	public void endTurn(){
		tabCount = 0;
	}
}
