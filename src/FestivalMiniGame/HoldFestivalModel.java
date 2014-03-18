package FestivalMiniGame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import Models.PalaceCard;

public class HoldFestivalModel {
	private ArrayList<JavaFestivalPlayer> players;
	private HashMap<Integer, ArrayList<PalaceCard>> discardedPalaceTiles;
	private HashMap<String, Integer> famePointsAwarded;
	private int indexOfCurrentPlayer;
	private PalaceCard festivalCard;
	private int valueOfPalaceCity;
	private int highestBid;
	private int currentBid;
	
	public HoldFestivalModel(ArrayList<JavaFestivalPlayer> festivalPlayers, PalaceCard festCard, int valueOfPalaceCity){
		this.players = festivalPlayers;
		this.indexOfCurrentPlayer = getBeginningPlayerIndex();
		this.festivalCard = festCard;
		this.valueOfPalaceCity = valueOfPalaceCity;
		this.discardedPalaceTiles = new HashMap<Integer, ArrayList<PalaceCard>>();
		this.famePointsAwarded = new HashMap<String, Integer>(10);
		initFamePointsHashMap();
		this.highestBid = 0;
		this.currentBid = 0;
	}
	
	public int getCurrentPlayer(){
		return this.indexOfCurrentPlayer;
	}
	
	public int getCurrentPlayerNumOfPalaceCards(){
		return this.players.get(indexOfCurrentPlayer).getNumPalaceCards();
	}
	
	public int getCurrentPlayerTabCount(){
		return this.players.get(indexOfCurrentPlayer).getTabCount();
	}
	
	public String getTabbedHashKey(){
		return this.players.get(indexOfCurrentPlayer).getTabbedImageString();
	}
	
	public HashMap<Integer, ArrayList<PalaceCard>> getDiscardedPalaceCards(){
		return this.discardedPalaceTiles;
	}
	
	public int getNumPlayersInFestival(){
		int numPlayers = 0;
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).checkIfInFestival())
				numPlayers++;
		}
		return numPlayers;
	}
	
	public int getBeginningPlayerIndex(){
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).startedFestival()) return i;
		}
		return -1;
	}
	
	public void startTurn(){
		//if player can start turn, then clear the bin num
		currentBid = 0;
	}
	
	public boolean canEndTurn(){
		return highestBid <= currentBid;
	}
	
	public int endTurn(){
		players.get(indexOfCurrentPlayer).endTurn();
		incrementPlayer();
		while(!players.get(indexOfCurrentPlayer).checkIfInFestival())
			incrementPlayer();
		return indexOfCurrentPlayer;
	}
	
	public boolean ifHadFullCycleTurnCheck(){
		if(players.get(indexOfCurrentPlayer).startedFestival()){
			highestBid = 0;
			currentBid = 0;
			return true;
		}
		return false;
	}
	
	public PalaceCard tabThroughPalaceCards(){
		players.get(indexOfCurrentPlayer).incrementTab();
		return players.get(indexOfCurrentPlayer).getTabbedPalaceCard();
	}
	
	public PalaceCard selectPalaceCard(){
		PalaceCard selectedCard = players.get(indexOfCurrentPlayer).getSelectedPalaceCard();
		currentBid++;
		if(currentBid > highestBid) highestBid = currentBid;
		return selectedCard;
	}
	
	public void endTabbing(){
		players.get(indexOfCurrentPlayer).cancelTabbing();
	}
	
	private void incrementPlayer(){
		indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % players.size();
	}
	
	public int dropCurrentPlayer(){
		//TODO
		players.get(indexOfCurrentPlayer).dropPlayerFromFestival();
		return indexOfCurrentPlayer;
	}
	
	public boolean isThereOnlyOnePlayerLeft(){
		int numPlayers = 0;
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).checkIfInFestival())
				numPlayers++;
			if(numPlayers < 1)
				return false;;
		}
		return true;
	}
	
	public boolean checkIfEveryoneIsOutOfCards(){
		//returns true if everyone is out of cards
		//if everyone is out of cards then the game ends and a winner is calculated
		for(int i = 0; i < players.size(); ++i){
			if(players.get(i).getNumPalaceCards() < 0)
				return false;
		}
		return true;
	}
	
	public JavaFestivalPlayer calculateWinner(){
		calculatePlayersPointsForRound();
		int max = getMaxPoints();
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getFestivalPoints() == max)
				return players.get(i);
		}
		return null;
	}
	
	public boolean checkForTies(){
		//calculate points for each player
		calculatePlayersPointsForRound();
		int max = getMaxPoints();
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getFestivalPoints() == max)
				return true;
		}
		return false;
	}
	
	public ArrayList<JavaFestivalPlayer> getPlayersInTie(){
		ArrayList<JavaFestivalPlayer> playersInTie = new ArrayList<JavaFestivalPlayer>();
		int max = getMaxPoints();
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getFestivalPoints() == max)
				playersInTie.add(players.get(i));
		}
		return playersInTie;
	}
	
	private int getMaxPoints(){
		int max = players.get(0).getFestivalPoints();
		for(int i = 0; i < players.size(); i++){
			if(players.get(i).getFestivalPoints() > max)
				max = players.get(i).getFestivalPoints();
		}
		return max;
	}
	
	private void calculatePlayersPointsForRound(){
		for(int i = 0; i < players.size(); ++i){
			ArrayList<PalaceCard> playersCards = players.get(i).getDiscardedCards();
			for (PalaceCard palaceCard : playersCards) {
				players.get(i).addFestivalPoints(palaceCard.compareForPoints(festivalCard));
			}
		}
	}
	
	public int getFamePointsWon(boolean ifTie){
		if(ifTie){
			return famePointsAwarded.get(valueOfPalaceCity+"_tie");
		}
		return famePointsAwarded.get(valueOfPalaceCity);
	}
	
	private void initFamePointsHashMap(){
		famePointsAwarded.put("2", new Integer(1));
		famePointsAwarded.put("4", new Integer(2));
		famePointsAwarded.put("6", new Integer(3));
		famePointsAwarded.put("8", new Integer(4));
		famePointsAwarded.put("10", new Integer(5));
		famePointsAwarded.put("2_tie", new Integer(0));
		famePointsAwarded.put("4_tie", new Integer(1));
		famePointsAwarded.put("6_tie", new Integer(2));
		famePointsAwarded.put("8_tie", new Integer(2));
		famePointsAwarded.put("10_tie", new Integer(3));
	}

}
