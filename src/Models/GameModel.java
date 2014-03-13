package Models;

public class GameModel {
	// VARIABLES
	private BoardModel gameBoard;
	private PlayerModel[] players;
	private int indexOfCurrentPlayer;
	private boolean isFinalRound;
    
    public GameModel(int numberPlayers){
		this.isFinalRound = false;
		this.indexOfCurrentPlayer = 0;
		
		this.gameBoard = new BoardModel();
		this.players = new PlayerModel[numberPlayers];
	}
    
    // ----------- GETTERS ----------- //

}
