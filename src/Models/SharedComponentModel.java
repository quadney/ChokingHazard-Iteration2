package Models;

public class SharedComponentModel {
	private int irrigationTiles; 
    private int threeSpaceTiles;
    private int[] palaceTiles;
    private int[] palaceCards;
    //private int currentFaceUpFestivalCard;
    
    public SharedComponentModel(){
    	this.irrigationTiles = 10;
		this.threeSpaceTiles = 56;
		this.palaceTiles = new int[]{6, 7, 8, 9, 10};
		this.palaceCards = new int[]{5, 5, 5, 5, 5, 5};
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
	public int getNumberPalaceCards() {
		int numPalaceCards = 0;
		for(int i = 0; i < palaceCards.length; ++i){
			numPalaceCards += palaceCards[i];
		}
		return numPalaceCards;
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

}
