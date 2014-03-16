package Controllers;

import Helpers.Deck;
import Models.PalaceCard;
import Models.SharedComponentModel;
import Views.SharedComponentPanel;

public class SharedComponentController {
	private SharedComponentModel sharedModel;
	private SharedComponentPanel sharedPanel;

	public SharedComponentController(){
		//new game constructor
		this.sharedModel = new SharedComponentModel();
		this.sharedPanel = new SharedComponentPanel(sharedModel.getThreeSpaceTiles(), sharedModel.getIrrigationTiles(), 
				sharedModel.getPalaceTiles(), sharedModel.getNumberPalaceCards(), sharedModel.getFestivalCardType());
		
		//there's no festival card for this deck. so when dealPalaceCards(numPlayers) is called, it will create a festival card
	}
	public SharedComponentController(int threeTiles, int irrigationTiles, int[] palaceTiles, Deck<PalaceCard> deck, PalaceCard festivalCard, Deck<PalaceCard> discardDeck){
		//load game constructor
		this.sharedModel = new SharedComponentModel(threeTiles, irrigationTiles, palaceTiles, deck, festivalCard, discardDeck);
		this.sharedPanel = new SharedComponentPanel(threeTiles, irrigationTiles, palaceTiles, deck.size(), festivalCard.getType());
	}
	
	public SharedComponentPanel getSharedComponentPanel(){
		return this.sharedPanel;
	}
	
	public PalaceCard drawFromDeck(){
		PalaceCard card = sharedModel.drawFromDeck();
		sharedPanel.drawFromPalaceDeck(sharedModel.getNumberPalaceCards());
		return card;
	}
	
	public PalaceCard drawFestivalCard(){
		PalaceCard card = this.sharedModel.getFestivalCard().deepCopy();
		sharedModel.drawFestivalCard();
		sharedPanel.drawFestivalCard(sharedModel.getNumberPalaceCards(), sharedModel.getFestivalCardType());
		return card;
	}
	
	public PalaceCard[][] dealPalaceCards(int numPlayers){
		//deal the cards to players
		PalaceCard[][] cards = new PalaceCard[numPlayers][3];
		for(int i = 0; i < numPlayers; i++){
			int j = 0;
			while(j < 3){
				cards[i][j] = drawFromDeck();
				j++;
			}
		}
		return cards;
	}

	public boolean selectPalaceTile(int value) {
		return sharedModel.hasPalaceTile( value );
	}

	public boolean hasPalaceTileValue(int value) {
		return sharedModel.hasPalaceTile( value );
	}
	
	//called if the player has placed a three piece tile.
	//checks to see if there enough three piece tiles to allow this to happen
	//if it does, it changes the value in the mode and tells the panel to update
	public boolean useThreeTile(){
		if(sharedModel.useThreeTile()){
			sharedPanel.updateThreePieceTiles(sharedModel.getThreeSpaceTiles());
			return true;
		}
		return false;
	}
}
