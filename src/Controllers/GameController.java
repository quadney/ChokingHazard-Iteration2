package Controllers;

import java.awt.event.KeyEvent;

import Models.GameModel;
import Views.GamePanel;

public class GameController {
	GameModel gameModel;
	GamePanel gamePanel;
	
	public GameController(GameModel model, GamePanel view){
		this.gameModel = model;
		this.gamePanel = view;
		
	}
	
	public void userPressedKey(KeyEvent e){
		//check if the key that is pressed is the button to show the user's festival card.
	}
	
	public void userReleasedKey(KeyEvent e){
		
	}
	
	

}
