package FestivalMiniGame;

import java.util.ArrayList;

import Models.JavaPlayer;
import Models.PalaceCard;

public class JavaFestivalPlayer {
	private JavaPlayer player;
	private boolean isInFestival;
	private ArrayList<PalaceCard> palaceCards;
	private ArrayList<PalaceCard> discardedCards;
	private int tabCount;
	private int festivalBid;
	private int famePointsToAward;
	private boolean startedFestival;

	public JavaFestivalPlayer(JavaPlayer player, ArrayList<PalaceCard> validPalaceCards, boolean startedFest) {
		//call this when the player has the ability to be in the festival
		//if they are not able, don't create a FestivalPlayer
		this.player = player;
		this.palaceCards = validPalaceCards;
		this.discardedCards = new ArrayList<PalaceCard>();
		this.isInFestival = true;
		this.tabCount = -1;
		this.festivalBid = 0;
		this.famePointsToAward = 0;
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
	
	public JavaPlayer getJavaPlayer(){
		return this.player;
	}
	
	public boolean checkIfInFestival(){
		return this.isInFestival;
	}
	
	public void addFestivalBid(int points){
		this.festivalBid += points;
	}
	public int getFestivalBid(){
		return this.festivalBid;
	}
	
	public ArrayList<PalaceCard> getDiscardedCards(){
		return this.discardedCards;
	}
	
	public void incrementTab(){
		if(palaceCards.size() == 0){
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
		if(tabCount < 0)
			return null;
		PalaceCard selectedCard = palaceCards.get(tabCount);
		discardedCards.add(selectedCard);
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
	
	public void awardFamePoints(int points){
		this.famePointsToAward += points;
	}
	
	public int getFamePoints(){
		return this.famePointsToAward;
	}
	
	public void changeStartedFestivalStatus(boolean status){
		this.startedFestival = status;
	}
}
