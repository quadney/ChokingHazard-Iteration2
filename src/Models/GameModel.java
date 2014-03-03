package Models;
import Helpers.*;
import Models.Actions.*;

public class GameModel {
	// VARIABLES
	private BoardModel gameBoard;
	private PlayerModel[] players;
	private int indexOfCurrentPlayer;
	private boolean isFinalRound;
	private int irrigationTiles; 
    private int threeSpaceTiles;
    private int[] palaceTiles;
    private int[] palaceCards;
    //private int currentFaceUpFestivalCard;
    
    private Stack<Action> actionHistory; // This holds a history of the actions taken up to the currently held state.
    private Stack<Action> actionReplays; // This holds currently undone actions for the purposes of Replay Mode
    
    public GameModel(int numberPlayers){
		this.isFinalRound = false;
		this.indexOfCurrentPlayer = 0;
		this.irrigationTiles = 10;
		this.threeSpaceTiles = 56;
		this.palaceTiles = new int[]{6, 7, 8, 9, 10};
		this.palaceCards = new int[]{5, 5, 5, 5, 5, 5};
		
		this.gameBoard = new BoardModel();
		this.players = new PlayerModel[numberPlayers];
	}
    
    // ----------- GETTERS ----------- //
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
	
	public void changeFamePoints(int playerIndex, int modifier){
		players[playerIndex].changeFamePoints(modifier);
	}
}
