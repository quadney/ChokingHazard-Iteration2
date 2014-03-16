package Controllers;

import java.awt.event.KeyEvent;
import java.io.File;

import ChokingHazard.GameFrame;
import ChokingHazard.GameManager;
import Models.GameModel;
import Models.Actions.MActions.*;
import Views.GameContainerPanel;

public class GameController {
	private GameFrame gameFrame;
	private GameManager gameManager;
	private GameModel currentGame;
	private GameContainerPanel currentGamePanel;
	private BoardController board;
	private PlayerController players;
	private SharedComponentController shared;
	
	public GameController(GameFrame frame){
		//should we just create them here instead of passing it in?
		this.gameFrame = frame;
		gameFrame.giveGameController(this);
		this.gameManager = new GameManager();
		
	}
	
	public void createNewGame(int numPlayers, String[] playerNames, String[] playerColors){
		//create controllers
		
		currentGame = new GameModel(numPlayers);
		board = new BoardController();
		players = new PlayerController(numPlayers, playerNames, playerColors);
		shared = new SharedComponentController();
		
		//this initializes the dealing of the palace cards
		//the shared dealPalaceCards returns the dealt palace cards
		//and then the player sets those dealt cards as the cards
		players.dealPalaceCards(shared.dealPalaceCards(numPlayers));
		
		currentGamePanel = new GameContainerPanel(board.getBoardPanel(), players.getPlayerPanels(), shared.getSharedComponentPanel());
		gameFrame.setFrameContent(currentGamePanel);
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
	
	public boolean getCurrentGameExists(){
		if (currentGame == null){
			return false;
		}
		return true;
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

	public void userPressedKey(KeyEvent e){
		//check if the key that is pressed is the button to show the user's festival card.
		if(e.getKeyCode() == 70){
			//the user is pressing (and holding) the F button
			//TODO this can only be called if in Play Mode
			currentGamePanel.displayPalaceCardFrame(this.players.getPlayerAtIndex(0));
		}
	}
	
	private void userReleasedKey(KeyEvent e){
		System.out.println(e.getKeyCode());
		//TODO key codes for switching between modes: planning mode, replay mode, and normal mode
		//TODO key codes for holding a festival, picking up a festival card/palace card
		switch(e.getKeyCode()){
		case 8:
			//released delete, delete a developer from the board
			//need all the type checks and where they are to delete a developer
			break;
		case 9:
			//released tab, tab through developers

			break;
		case 10:
			//released enter, place tile/developer onto board.
			

			break;	
		case 27:
			//escapes out of a selected action
			//tells the current game about the event so that it makes SelectedAction to null
			//also updates the board panel so that the image is canceled
			currentGame.pressEsc();
			board.pressEsc();

			break;
		case 32:
			//released the space bar, rotate
			//tells the current game to tell the selectedAction to do pressSpace()
			//will only tell the board about the change if it was a rotatable tile action
			currentGame.pressSpace();
				updateBoardControllerWithSelectedAction();
			break;
			
	// using these arrow keys for movement of developers and tiles
		case 37:
			if(currentGame.pressLeft())
				updateBoardControllerWithSelectedAction();
			break;
		case 38:
			if(currentGame.pressUp())
				updateBoardControllerWithSelectedAction();
			break;
		case 39:
			if(currentGame.pressRight())
				updateBoardControllerWithSelectedAction();
			break;
		case 40:
			if(currentGame.pressDown());
				updateBoardControllerWithSelectedAction();
			break;
	// --------------------------------------------------------------------
			
		case 50: //released 2, select two space tile
			//check if the player has enough two tiles and AP to select a two tile action a two tile action
			//player.checkIfSelectionValid(currentGame.getPlayerIndex(), )
			if(currentGame.setSelectedAction(new SelectTwoTileAction("twoTile"))){
				updateBoardControllerWithSelectedAction();
			}

			break;
		case 51: //released 3, select three space tile
			//check if player has enough AP and if sharedComponent has any more 3 tiles (I could check game state but we could always change how the game state works...)
			//if(players.checkIfSelectionValid() && shared.checkIfSelectionValid())
			if(currentGame.setSelectedAction(new SelectThreeTileAction("threeTile"))){
				updateBoardControllerWithSelectedAction();
			}

			break;
		case 68: //released D, add new developer onto board
			//currentGame.setSelectedActionDeveloper(new MAction("")); //somehow know the developer hash with the player color

			break;
		case 73: //released I, add new Irrigation tile
			//check if player has enough AP, has enough AP, and if there is enough in shared 
			if(currentGame.setSelectedAction(new SelectIrrigationTileAction("irrigationTile"))){
				updateBoardControllerWithSelectedAction();
			}
			break;
		case 80:
			//TODO ask for value
			//released P, new palace tile, need to ask for value of Tile
			
			//somewhere needs to check if a value if valid
			//if value <=10, > 0 and %2 between 0 and 5 or just check if its 2, 4, 6 ,8, 10
			int value = GameFrame.getPalaceValueFromUser();
			//currentGame.setSelectedAction(new SelectPalaceTileAction("palace" + value + "Tile", value)); //this makes the selected action of getting a tile
			

			if( shared.selectPalaceTile( value ) && players.selectPalaceTile( value, currentGame.getPlayerIndex() ) )
				board.selectPalaceTile( value );
			break;
		case 82:
			//released R, place rice tile
			//TODO check if the player has enough villages and that they have some AP left to do this
			if(players.checkIfRiceTileSelectionValid(currentGame.getPlayerIndex())){
				if(currentGame.setSelectedAction(new SelectRiceTileAction("riceTile"))){
					updateBoardControllerWithSelectedAction();
				}
			}
			break;
		case 84:
			//does not become a selected action, just does a regular action!
			//released T, use action token

			break;
		case 86:
			//released V, place Village
			//TODO check if the player has enough villages and that they have some AP left to do this
			if(true){
				if(currentGame.setSelectedAction(new SelectVillageTileAction("villageTile"))){
					updateBoardControllerWithSelectedAction();
				}
			}
			break;
		case 88:
			//check if the player has placed a land tile so they can get out of their turn
			//released X, end turn

			break;		
		}
	}
	
	private void updateBoardControllerWithSelectedAction(){
		if (currentGame.getSelectedAction() instanceof SelectRotatableTileAction){
			board.updateSelectedTileAction(currentGame.getSelectedActionX(), currentGame.getSelectedActionY(), currentGame.getSelectedActionImageKey(), ((SelectRotatableTileAction)currentGame.getSelectedAction()).getRotationState());
		}
		else if(currentGame.getSelectedAction() instanceof SelectOneSpaceTileAction){
			board.updateSelectedTileAction(currentGame.getSelectedActionX(), currentGame.getSelectedActionY(), currentGame.getSelectedActionImageKey(), 0);
		}
		else{//developer
			//TODO this is to tell the view that the developer path has changed
		}
	}
	
}
