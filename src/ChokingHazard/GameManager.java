package ChokingHazard;

import java.awt.event.KeyEvent;
import java.io.File;

import Controllers.GameController;
import Models.GameModel;
import Views.GamePanel;

public class GameManager {
	GameFrame frame;
	GameController currentGame;
	
	public GameManager(){
		
	}
	
	private void setGameFrame(GameFrame gf){
		this.frame = gf;
	}
	
	public void giveGameFrame(GameFrame gf){
		setGameFrame(gf);
	}
	
	public void createNewGame(int numPlayers, String playersAndTheirNames){
		//parse the string because that has all the player information
		//create new players based on it
		currentGame = new GameController(new GameModel(numPlayers), new GamePanel(numPlayers), playersAndTheirNames, numPlayers);
		
		frame.setFrameContent(currentGame.getGamePanel());
	}
	
	public boolean loadGame(File file){
		//returns true if successfully loaded
		//returns false if not.
		return true;
	}
	
	public boolean saveGame(){
		return true;
	}
	
	public void keyPressed(KeyEvent e){
		//this is used only for when users want to show their festival cards
		//when the key is released, then the festival cards will be hidden once again
		if(currentGame != null){
			currentGame.userPressedKey(e);
		}
	}
	
	public void keyReleased(KeyEvent e){
		//key released is when a user lifts their finger from a key
		if(currentGame != null){
			currentGame.userReleasedKey(e);
		}
	}

}
