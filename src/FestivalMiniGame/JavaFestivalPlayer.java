package FestivalMiniGame;

import java.util.ArrayList;

import Models.JavaPlayer;
import Models.PalaceCard;

public class JavaFestivalPlayer {
	private JavaPlayer player;
	private boolean isInFestival;
	private ArrayList<PalaceCard> palaceCards;
	private ArrayList<PalaceCard> discardedPalaceCards;
	private int tabCount;
	private int festivalPoints;
	private boolean startedFestival;

	public JavaFestivalPlayer(JavaPlayer player, ArrayList<PalaceCard> validPalaceCards, boolean startedFest) {
		//call this when the player has the ability to be in the festival
		//if they are not able, don't create a FestivalPlayer
		this.player = player;
		this.palaceCards = validPalaceCards;
		this.discardedPalaceCards = new ArrayList<PalaceCard>();
		this.isInFestival = true;
		this.tabCount = 0;
		this.festivalPoints = 0;
		this.startedFestival = startedFest;
	}
	
	public JavaFestivalPlayer(){
		this.isInFestival = false;
	}
	
	public boolean startedFestival(){
		return this.startedFestival;
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
	public int getTabCount(){
		return this.tabCount;
	}
	public ArrayList<PalaceCard> getDiscardedCards(){
		return discardedPalaceCards;
	}
	
	public boolean checkIfInFestival(){
		return this.isInFestival;
	}
	
	public void addFestivalPoints(int points){
		this.festivalPoints += points;
	}
	public int getFestivalPoints(){
		return this.festivalPoints;
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
		discardedPalaceCards.add(selectedCard);
		palaceCards.remove(tabCount);
		tabCount = 0;
		System.out.println(discardedPalaceCards.get(0).getType());
		return selectedCard;
	}
	
	public PalaceCard getTabbedPalaceCard(){
		return palaceCards.get(tabCount);
	}
	
	public String getTabbedImageString(){
		return palaceCards.get(tabCount).getType();
	}
	
	public void cancelTabbing(){
		tabCount = 0;
	}

	public void endTurn(){
		tabCount = 0;
	}
}
