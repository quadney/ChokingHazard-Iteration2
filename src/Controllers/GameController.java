package Controllers;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;

import ChokingHazard.GameFrame;
import ChokingHazard.GameManager;
import Models.GameModel;
import Models.JavaPlayer;
import Views.GameContainerPanel;

public class GameController {
	GameFrame gameFrame;
	GameManager gameManager;
	GameModel currentGame;
	GameContainerPanel currentGamePanel;
	//BoardController board;
	//PlayerController players;
	//SharedComponentController shared;
	
	public GameController(GameFrame frame){
		//should we just create them here instead of passing it in?
		this.gameFrame = frame;
		gameFrame.giveGameController(this);
		this.gameManager = new GameManager();
		
	}
	
	public void createNewGame(int numPlayers, String[] playerNames, String[] playerColors){
		//create controllers
		
		//currentGame = new GameModel();   ????
		//board = new BoardController();
		//players = new PlayerController(numPlayers, playerNames, playerColors);
		//shared = new SharedComponentController();
		
		//currentGamePanel = new GameContainerPanel(board.getBoardPanel(), players.getPlayerPanels(), shared.getSharedPanel());
		//gameFrame.setFrameContent(currentGamePanel);
	}
	
	public boolean loadGame(File file){
		//calls the game manager to do the parsing
		// Sydney doesn't know how that works so if this us unnecessary feel free to do what you want
		gameManager.loadGame(file);
		return true;
	}
	
	public boolean saveGame(){
		//calls the game manager, see loadGame
		gameManager.saveGame();
		return true;
	}
	
	public void userPressedKey(KeyEvent e){
		//check if the key that is pressed is the button to show the user's festival card.
		if(e.getKeyCode() == 70){
			//the user is pressing (and holding) the F button
			//display the user's Festival Cards
			//player.displayFestivalCard(indexOfCurrentPlayer);
		}
	}
	
	public void keyPressed(KeyEvent e){
		//this is used only for when users want to show their festival cards
		//when the key is released, then the festival cards will be hidden once again
		if(currentGame != null){
			userPressedKey(e);
		}
	}
	
	public void keyReleased(KeyEvent e){
		//key released is when a user lifts their finger from a key
		if(currentGame != null){
			userReleasedKey(e);
		}
	}
	
	private void userReleasedKey(KeyEvent e){
		System.out.println(e.getKeyCode());
		//TODO key codes for switching between modes: planning mode, replay mode, and normal mode
		//TODO key codes for holding a festival, picking up a festival card/palacecard
		switch(e.getKeyCode()){
		case 8:
			//released delete, delete a developer from the board

		case 9:
			//released tab, tab through developers

			break;
		case 10:
			//released enter, place tile/developer onto board. 

			break;	
		case 27:
			//released Esc, cancel action

			break;
		case 32:
			//released the space bar, rotate

			break;
			
	// using these arrow keys for movement of a developer for testing purposes
		case 37:
			//pressed left arrow, move the viewport left
			//currentGame.moveComponentAroundBoard(-50, 0);
			break;
		case 38:
			//pressed up arrow, move the viewport up
			//currentGame.moveComponentAroundBoard(0, -50);
			break;
		case 39:
			//pressed the right arrow, move the viewport right
			//currentGame.moveComponentAroundBoard(50, 0);
			break;
		case 40:
			//pressed the down arrow, move the viewport down
			//currentGame.moveComponentAroundBoard(0, 50);
			break;
	// --------------------------------------------------------------------
			
		case 50:
			//released 2, select two space tile

			break;
		case 51:
			//released 3, select three space tile

			break;
		case 68:
			//released D, add new developer onto board

			break;
		case 70:
			//released F, now need to hide the user's Festival Cards
			
			break;
		case 73:
			//released I, add new Irrigation tile

			break;
		case 80:
			//released P, new palace tile, need to ask for value of Tile

			break;
		case 82:
			//released R, place rice tile
			
			break;
		case 84:
			//released T, use action token

			break;
		case 85:
			//released U, upgrade palace
			
			break;
		case 86:
			//released V, place Village

			break;
		case 88:
			//released X, end turn

			break;		
		case 98:
			//released 2 button numerical key - move developer down
			//currentGame.moveComponentAroundBoard(0, 50);
			break;
		case 100:
			//released 4 button numerical key - move developer left
			//currentGame.moveComponentAroundBoard(-50, 0);
			break;
		case 102:
			//released 6 button numerical key - move developer right
			//currentGame.moveComponentAroundBoard(50, 0);
			break;
		case 104:
			//released 8 button numerical key - move developer up
			//currentGame.moveComponentAroundBoard(0, -50);
			break;
		}
	}
	
	

}
