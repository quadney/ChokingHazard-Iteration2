package Controllers;

import java.awt.event.KeyEvent;
import java.io.File;

import ChokingHazard.GameFrame;
import ChokingHazard.GameManager;
import Models.GameModel;
import Models.Actions.Action;
import Models.Actions.DrawPalaceCardAction;
import Models.Actions.IrrigationTileAction;
import Models.Actions.MoveDeveloperAction;
import Models.Actions.PalaceTileAction;
import Models.Actions.PlaceDeveloperOnBoardAction;
import Models.Actions.RiceTileAction;
import Models.Actions.TakeDeveloperOffBoardAction;
import Models.Actions.ThreeTileAction;
import Models.Actions.TwoTileAction;
import Models.Actions.UseActionTokenAction;
import Models.Actions.VillageTileAction;
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
		
		currentGame = new GameModel(numPlayers, playerNames, playerColors);
		board = new BoardController(currentGame.getBoard());
		players = new PlayerController(numPlayers, currentGame.getPlayers()); //change player controller to query the model for the player info
		shared = new SharedComponentController(currentGame.getShared()); //change this to work
		
		//this initializes the dealing of the palace cards
		//the shared dealPalaceCards returns the dealt palace cards
		//and then the player sets those dealt cards as the cards
		players.dealPalaceCards(shared.dealPalaceCards(numPlayers));
		
		currentGamePanel = new GameContainerPanel(board.getBoardPanel(), players.getPlayerPanels(), shared.getSharedComponentPanel());
		gameFrame.setFrameContent(currentGamePanel);
		
		boolean fest = currentGamePanel.askUserIfWouldLikeToHoldAPalaceFestival();
		if(fest){
			currentGamePanel.displayHoldFestivalFrame(players.getPlayerModels(), currentGame.getPlayerIndex(), shared.getCurrentFestivalCard(), 2);
		}
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
		//System.out.println(e.getKeyCode());
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
			System.out.println("(in GameController)Enter was pressed");
			Action action = currentGame.pressEnter();
			if(action != null){
				System.out.println("action != null in GCtrl");
				currentGame.addToActionHistory(action);
				currentGame.doLastActionInHistory();
				currentGame.setSelectedAction(null);
				updateControllersWithAction(action);
			}
			break;	
		case 27:
			//escapes out of a selected action
			//tells the current game about the event so that it makes SelectedAction to null
			//also updates the board panel so that the image is canceled
			//System.out.println("(in GameController)Esc was pressed");
			currentGame.pressEsc();
			board.pressEsc();

			break;
		case 32:
			//released the space bar, rotate
			//tells the current game to tell the selectedAction to do pressSpace()
			//will only tell the board about the change if it was a rotatable tile action
			//System.out.println("(in GameController)Space was pressed");
			if(currentGame.pressSpace()){
				updateBoardControllerWithSelectedAction();
				//System.out.println("(in GameController)Space was valid and attempted to updateBoardController");
			}
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
			if(currentGame.pressDown()){
				//System.out.println("(In GCtrl) pressed down");
				updateBoardControllerWithSelectedAction();
			}
			break;
	// --------------------------------------------------------------------
			
		case 50: //released 2, select two space tile
			//TODO check if the player has enough two tiles and AP to select a two tile action a two tile action
			//player.checkIfSelectionValid(currentGame.getPlayerIndex(), )
			if(players.selectTwoTile(currentGame.getPlayerIndex())){
				if(currentGame.setSelectedAction(new SelectTwoTileAction("twoTile"))){
					updateBoardControllerWithSelectedAction();
				}
			}

			break;
		case 51: //released 3, select three space tile
			//check if player has enough AP and if sharedComponent has any more 3 tiles (I could check game state but we could always change how the game state works...)
			//if(players.checkIfSelectionValid() && shared.checkIfSelectionValid())
			if(players.selectThreeTile(currentGame.getPlayerIndex()) && shared.selectThreeTile()){
				if(currentGame.setSelectedAction(new SelectThreeTileAction("threeTile"))){
					updateBoardControllerWithSelectedAction();
				}
			}

			break;
		case 68: //released D, add new developer onto board
			//currentGame.setSelectedActionDeveloper(new MAction("")); //somehow know the developer hash with the player color
			if(players.selectDeveloper(currentGame.getPlayerIndex())){
				System.out.println("red is the (hard coded) current player color.");
				//currentGame.setSelectedAction(new SelectPlaceDeveloperOnBoardAction("developer_" + players.getColorOfPlayer(currentGame.getPlayerIndex())));
				currentGame.setSelectedAction(new SelectPlaceDeveloperOnBoardAction("red"));
				updateBoardControllerWithSelectedAction();
			}
			break;
		case 73: //released I, add new Irrigation tile
			//check if player has enough AP, and if there is enough in shared
			if(players.selectThreeTile(currentGame.getPlayerIndex()) && shared.selectThreeTile()){
				currentGame.setSelectedAction(new SelectIrrigationTileAction("irrigationTile"));
				updateBoardControllerWithSelectedAction();
			}
			break;
		case 80://released P, new palace tile, need to ask for value of Tile
			
			//is valid due to the view being awesome
			int value = currentGamePanel.promptUserForPalaceValue();
			
			//check player to see if they have enough AP and check shared to see if there are enough
			if( players.selectPalaceTile(currentGame.getPlayerIndex()) && shared.selectPalaceTile(value)){
				if(currentGame.setSelectedAction(new SelectPalaceTileAction("palace" + value + "Tile", value))){
					updateBoardControllerWithSelectedAction();
				}
			}
			break;
		case 82:
			//released R, place rice tile
			//TODO check if the player has enough rice and that they have some AP left to do this
			if(players.selectRiceTile(currentGame.getPlayerIndex())){
				if(currentGame.setSelectedAction(new SelectRiceTileAction("riceTile"))){
					updateBoardControllerWithSelectedAction();
				}
			}
			break;
		case 84:
			//does not become a selected action, just does a regular action!
			//released T, use action token
			if(players.selectActionToken(currentGame.getPlayerIndex())){
				UseActionTokenAction actionTokenAction = new UseActionTokenAction(-1);
				currentGame.addToActionHistory(actionTokenAction);
				updateControllersWithAction(actionTokenAction);
			}

			break;
		case 86:
			//released V, place Village
			//TODO check if the player has enough villages and that they have some AP left to do this
			if(players.selectVillageTile(currentGame.getPlayerIndex())){
				if(currentGame.setSelectedAction(new SelectVillageTileAction("villageTile"))){
					updateBoardControllerWithSelectedAction();
				}
			}
			break;
		case 88:
			//check if the player has placed a land tile so they can get out of their turn
			//released X, end turn
			if(players.selectEndTurn(currentGame.getPlayerIndex())){
				players.setNotCurrentPlayerinPlayerPanel(currentGame.getPlayerIndex()); //need to tell the player panel of the current player to stop outlining their panel
				//if(currentGamePanel.askUserIfWouldLikeToHoldAPalaceFestival()){ //ask if they wanna have a palace festival
					//TODO set up palace festival
					//asks for palace they want to hold a festival at
					//pass the index and the value of the palace and...
				//}
				
				//call this after the prompt to have a palace festival (and after the palace festival if they had one)
				currentGame.endTurn(); //increment the current player in the game model, changes all the stuff in player
				players.setCurrentPlayerinPlayerPanel(currentGame.getPlayerIndex()); //need to tell the new player panel that they are the current player
			}
			break;		
		}
	}
	
	private void updateControllersWithAction(Action action){
		// TODO turn all into permanent actions instead of momentary
		if (action instanceof IrrigationTileAction || action instanceof PalaceTileAction || 
		action instanceof ThreeTileAction || action instanceof DrawPalaceCardAction)
			shared.updateSharedPanel();
		if (action instanceof IrrigationTileAction || action instanceof PalaceTileAction || action instanceof ThreeTileAction
		|| action instanceof PlaceDeveloperOnBoardAction || action instanceof TakeDeveloperOffBoardAction || action instanceof TwoTileAction
		|| action instanceof VillageTileAction || action instanceof RiceTileAction || action instanceof MoveDeveloperAction)
			board.updateBoardPanel(action);
		players.updatePlayerPanel(currentGame.getPlayerIndex());
	}
	
	private void updateBoardControllerWithSelectedAction(){
		if (currentGame.getSelectedAction() instanceof SelectTwoTileAction || currentGame.getSelectedAction() instanceof SelectThreeTileAction){
			//System.out.println("In updateBoardControllerWithSelectedAction() in GameController where instanceof SelectRotatableTileAction");
			//System.out.println(" This is the rotation state: " + ((SelectRotatableTileAction)currentGame.getSelectedAction()).getRotationState());
			board.updateSelectedTileAction(currentGame.getSelectedActionX()*50, currentGame.getSelectedActionY()*50, currentGame.getSelectedActionImageKey(), ((SelectRotatableComponentAction)currentGame.getSelectedAction()).getRotationState());
		}
		else if(currentGame.getSelectedAction() instanceof SelectOneSpaceTileAction){
			//System.out.println("In updateBoardControllerWithSelectedAction() in GameController where instanceof SelectNonRotatableTileAction");
			board.updateSelectedTileAction(currentGame.getSelectedActionX()*50, currentGame.getSelectedActionY()*50, currentGame.getSelectedActionImageKey(), 0);
		}
		else{//developer
			//System.out.println("(In GCtrl) updating Board panel when developer action is selected");
			board.updateSelectedDeveloperAction(currentGame.getSelectedActionX()*50, currentGame.getSelectedActionY()*50,currentGame.getSelectedActionImageKey());
		}
	}
	
}
