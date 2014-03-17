package Models;

import java.util.Collections;
import java.util.LinkedList;

import Helpers.Json;
import Helpers.JsonObject;

public class SharedComponentModel implements Serializable<SharedComponentModel> { 
    private int threeSpaceTiles;
	private int irrigationTiles;
    private int[] palaceTiles;
    private LinkedList<PalaceCard> palaceCardDeck;
    private LinkedList<PalaceCard> discardedCardDeck;
    private PalaceCard festivalCard;
    
    public SharedComponentModel(){
    	//constructor for new game
		this.threeSpaceTiles = 56;
		this.irrigationTiles = 10;
		this.palaceTiles = new int[]{6, 7, 8, 9, 10};
		palaceCardDeck = new LinkedList<PalaceCard>();
		for(int i = 0; i < 5; i++){
			palaceCardDeck.push(new PalaceCard(1));
			palaceCardDeck.push(new PalaceCard(2));
			palaceCardDeck.push(new PalaceCard(3));
			palaceCardDeck.push(new PalaceCard(4));
			palaceCardDeck.push(new PalaceCard(5));
			palaceCardDeck.push(new PalaceCard(6));
		}
		Collections.shuffle(palaceCardDeck);
		this.festivalCard = palaceCardDeck.pop();
		this.discardedCardDeck = new LinkedList<PalaceCard>();
    }
    
    public SharedComponentModel(int numThreeTiles, int numIrrigation, int[] palaceTiles, LinkedList<PalaceCard> deck, PalaceCard festivalCard, LinkedList<PalaceCard> discardDeck){
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
		PalaceCard card = palaceCardDeck.pop();
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
		discardedCardDeck.push(card);
	}
	
	public void checkIfDeckIsEmpty(){
		if(palaceCardDeck.size() == 0) 
			refreshPalaceCardDeck();
	}
	
	public void refreshPalaceCardDeck(){
		while(discardedCardDeck.size() > 0){
			palaceCardDeck.push(discardedCardDeck.pop());
		}
		Collections.shuffle(palaceCardDeck);
	}
	
	public boolean hasThreeTile(){
		//this check if you can use a threeTile (has more than 1 left)
		//return true if this is allowed
		return threeSpaceTiles > 0;
	}

	public boolean hasPalaceTile(int value) {
		//this check if you can use a palace tile with this value (has more than 1 left)
		//return true if this is allowed
		if( (value < 2 || value > 10) && value % 2 == 0 ) //by this point it should only be valid values, but that works.
			return false;
		return palaceTiles[value/2 - 1] > 0;
	}
	
	public boolean hasIrrigationTile(){
		//this check if you can use a irrigation tile (has more than 1 left)
		//return true if this is allowed
		return irrigationTiles > 0;
		
		
	}

	@Override
	public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("threeSpaceTiles", Json.jsonValue(threeSpaceTiles + "")),
			Json.jsonPair("irrigationTiles", Json.jsonValue(irrigationTiles + "")),
			Json.jsonPair("palaceTiles", Json.serializeArray(palaceTiles)),
			Json.jsonPair("palaceCardDeck", Json.serializeArray(palaceCardDeck)),
			Json.jsonPair("discardedCardDeck", Json.serializeArray(discardedCardDeck)),
			Json.jsonPair("festivalCard", festivalCard.serialize())
		));
	}

	@Override
	public SharedComponentModel loadObject(JsonObject json) {
		threeSpaceTiles = Integer.parseInt(json.getString("threeSpaceTiles"));
		irrigationTiles = Integer.parseInt(json.getString("irrigationTiles"));
		
		palaceTiles = new int[json.getStringArray("palaceTiles").length];
		for(int x = 0; x < palaceTiles.length; ++x) 
			palaceTiles[x] = Integer.parseInt(json.getStringArray("palaceTiles")[x]);

		palaceCardDeck = new LinkedList<PalaceCard>();
		for(JsonObject card : json.getJsonObjectArray("palaceCardDeck")) 
			palaceCardDeck.push((new PalaceCard(-1)).loadObject(card));

		discardedCardDeck = new LinkedList<PalaceCard>();
		for(JsonObject card : json.getJsonObjectArray("discardedCardDeck")) 
			discardedCardDeck.push((new PalaceCard(-1)).loadObject(card));
		
		festivalCard = (new PalaceCard(-1)).loadObject(json.getJsonObject("festivalCard"));
		
		return this;
	}

}
