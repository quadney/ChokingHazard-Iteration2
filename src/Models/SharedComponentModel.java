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
	
	public void incrementThreeTile(){
		//should increment three tiles
	}
	
	public boolean useThreeTile(){
		//this should attempt to decrement the three tile
		//return if this is allowed
		return false;
	}

}
