package Models;

import Helpers.Deck;

public class SharedComponentModel { 
    private int threeSpaceTiles;
	private int irrigationTiles;
    private int[] palaceTiles;
    private Deck<PalaceCard> palaceCardDeck;
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
    }
    
    public SharedComponentModel(int numThreeTiles, int numIrrigation, int[] palaceTiles, Deck<PalaceCard> deck, PalaceCard festivalCard){
    	//constructor for loading game
    	this.threeSpaceTiles = numThreeTiles;
    	this.irrigationTiles = numIrrigation;
    	this.palaceTiles = palaceTiles;
    	this.palaceCardDeck = deck;
    	this.festivalCard = festivalCard;
    }
    
    public void initFestivalCard(){
    	this.festivalCard = palaceCardDeck.draw();
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
		return palaceCardDeck.draw();
	}
	
	public PalaceCard drawFestivalCard(){
		//return the current festival card, and draw a new card to be the festivalCard
		PalaceCard card = festivalCard;
		this.festivalCard = drawFromDeck();
		return card;
	}

}
