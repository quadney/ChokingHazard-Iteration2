package ChokingHazard;

import java.io.File;

import Controllers.GameController;

public class GameManager {
	GameController currentGame;
	
	public GameManager(){
		
	}
	
	public void createNewGame(int numPlayers, String playersAndTheirNames){
		//parse the string because that has all the player information
		//create new players based on it
		//currentGame = new GameController(new GameModel(numPlayers), new GameContainerPanel(numPlayers), playersAndTheirNames, numPlayers);
	}
	
	public boolean loadGame(File file){
		//returns true if successfully loaded
		//returns false if not.
		return true;
	}
	
	public boolean saveGame(){
		return true;
	}

}
