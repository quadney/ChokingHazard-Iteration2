package FestivalMiniGame;

import java.util.ArrayList;

import Models.JavaPlayer;
import Models.PalaceCard;

public class JavaFestivalPlayer {
	private JavaPlayer player;
	private boolean isInFestival;
	private ArrayList<PalaceCard> palaceCards;
	private int tabCount;
	private int festivalBid;
	private boolean startedFestival;

	public JavaFestivalPlayer(JavaPlayer player, ArrayList<PalaceCard> validPalaceCards, boolean startedFest) {
		//call this when the player has the ability to be in the festival
		//if they are not able, don't create a FestivalPlayer
		this.player = player;
		this.palaceCards = validPalaceCards;
		this.isInFestival = true;
		this.tabCount = -1;
		this.festivalBid = 0;
		this.startedFestival = startedFest;
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
	
	public boolean checkIfInFestival(){
		return this.isInFestival;
	}
	
	public void awardFamePoints(int pointsToAward){
		System.out.println("PLayer fame points before adding: "+this.player.getFamePoints());
		this.player.changeFamePoints(pointsToAward);
		System.out.println("PLayer fame points after adding: "+this.player.getFamePoints());
	}
	
	public void addFestivalBid(int points){
		this.festivalBid += points;
	}
	public int getFestivalBid(){
		return this.festivalBid;
	}
	
	public void incrementTab(){
		if(palaceCards.size() == 0){
			System.out.println("palace card size == 0");
			tabCount = -1;
		}
		else{
			tabCount = (tabCount + 1) % palaceCards.size();
		}
	}
	
	public void dropPlayerFromFestival(){
		this.isInFestival = false;
		this.festivalBid = 0;
		this.tabCount = -1;
	}
	
	public PalaceCard getSelectedPalaceCard(){
		//this gets called when the player plays a palacecard in the festival
		PalaceCard selectedCard = palaceCards.get(tabCount);
		//test to make sure that this will work
		//TODO
		player.removePalaceCard(selectedCard);
		palaceCards.remove(tabCount);
		tabCount = -1;
		return selectedCard;
	}
	
	public PalaceCard getTabbedPalaceCard(){
		incrementTab();
		if(tabCount < 0){
			return null;
		}
		return palaceCards.get(tabCount);
	}
	
	public String getTabbedImageString(){
		return palaceCards.get(tabCount).getType();
	}
	
	public void cancelTabbing(){
		tabCount = -1;
	}

	public void endTurn(){
		tabCount = -1;
	}
	
	public void changeStartedFestivalStatus(boolean status){
		this.startedFestival = status;
	}
}
