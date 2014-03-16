package Models;

import java.util.ArrayList;

import Helpers.Deck;

public class SharedComponentModel { 
    private int threeSpaceTiles;
	private int irrigationTiles;
    private int[] palaceTiles;
    private Deck<PalaceCard> palaceCardDeck;
    private Deck<PalaceCard> discardedCardDeck;
    private PalaceCard festivalCard;
    
    public SharedComponentModel(){
    	//constructor for new game
		this.threeSpaceTiles = 56;
		this.irrigationTiles = 10;
		this.palaceTiles = new int[]{6, 7, 8, 9, 10};
		palaceCardDeck = new Deck<PalaceCard>();
		for(int i = 0; i < 5; i++){
			palaceCardDeck.add(new PalaceCard(1));
			palaceCardDeck.add(new PalaceCard(2));
			palaceCardDeck.add(new PalaceCard(3));
			palaceCardDeck.add(new PalaceCard(4));
			palaceCardDeck.add(new PalaceCard(5));
			palaceCardDeck.add(new PalaceCard(6));
		}
		palaceCardDeck.shuffle();
		this.festivalCard = palaceCardDeck.draw();
		this.discardedCardDeck = new Deck<PalaceCard>();
    }
    
    public SharedComponentModel(int numThreeTiles, int numIrrigation, int[] palaceTiles, Deck<PalaceCard> deck, PalaceCard festivalCard, Deck<PalaceCard> discardDeck){
    	//constructor for loading game
    	this.threeSpaceTiles = numThreeTiles;
    	this.irrigationTiles = numIrrigation;
    	this.palaceTiles = palaceTiles;
    	this.palaceCardDeck = deck;
    	this.festivalCard = festivalCard;
    	this.discardedCardDeck = discardDeck;
    }
    
	public int getIrrigationTiles() {
		return irrigationTiles;
	}
	public int getThreeSpaceTiles() {
		return threeSpaceTiles;
	}
	public int[] getPalaceTiles() {
		return palaceTiles;
	}
	public PalaceCard getFestivalCard(){
		return this.festivalCard;
	}
	public String getFestivalCardType(){
		return this.festivalCard.getType();
	}
	public int getNumberPalaceCards() {
		return palaceCardDeck.size();
	}
	
	public PalaceCard drawFromDeck(){
		PalaceCard card = palaceCardDeck.draw();
		checkIfDeckIsEmpty();
		return card;
	}
	public PalaceCard drawFestivalCard(){
		//return the current festival card, and draw a new card to be the festivalCard
		PalaceCard card = festivalCard.deepCopy();
		
		//if the deck is empty, refresh it
		checkIfDeckIsEmpty();
		
		this.festivalCard = drawFromDeck();
		return card;
	}
	
	public void discardCard(PalaceCard card){
		discardedCardDeck.add(card);
	}
	
	public void checkIfDeckIsEmpty(){
		if(palaceCardDeck.size() == 0) 
			refreshPalaceCardDeck();
	}
	
	public void refreshPalaceCardDeck(){
		while(discardedCardDeck.size() > 0){
			palaceCardDeck.add(discardedCardDeck.draw());
		}
		palaceCardDeck.shuffle();
	}
	
	public boolean useThreeTile(){
		//this should attempt to decrement the three tile
		//return if this is allowed
		return false;
	}

	public boolean hasPalaceTile(int value) {
		if( value < 0 || value > 4 )
			return false;
		return palaceTiles[value] > 0;
	}

}
