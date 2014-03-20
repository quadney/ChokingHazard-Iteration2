package Controllers;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import ChokingHazard.GameFrame;
import ChokingHazard.GameManager;
import Models.GameModel;
import Models.GameModel.GameState;
import Models.PalaceCard;
import Models.Actions.Action;
import Models.Actions.DrawFestivalCardAction;
import Models.Actions.DrawPalaceCardAction;
import Models.Actions.EndTurnAction;
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

	public GameController(GameFrame frame) {
		// should we just create them here instead of passing it in?
		this.gameFrame = frame;
		gameFrame.giveGameController(this);
		this.gameManager = new GameManager(this);
	}

	public void createNewGame(int numPlayers, String[] playerNames,
			String[] playerColors) {
		// create controllers
		currentGame = new GameModel(numPlayers, playerNames, playerColors);
		board = new BoardController(currentGame.getBoard());
		players = new PlayerController(numPlayers, currentGame.getPlayers()); // change
																				// player
																				// controller
																				// to
																				// query
																				// the
																				// model
																				// for
																				// the
																				// player
																				// info
		shared = new SharedComponentController(currentGame.getShared(), this); // change
																				// this
																				// to
																				// work

		// this initializes the dealing of the palace cards
		// and then the player sets those dealt cards as the cards
		players.dealPalaceCards(shared.dealPalaceCards(numPlayers));
		players.setCurrentPlayerinPlayerPanel(currentGame.getPlayerIndex());
		currentGamePanel = new GameContainerPanel(board.getBoardPanel(),
				players.getPlayerPanels(), shared.getSharedComponentPanel());
		gameFrame.setFrameContent(currentGamePanel);

		//seeIfPlayerCanHoldAFestival();
	}

	public boolean loadGame(File file) {
		// create controllers

				currentGame = (new GameModel(0, null, null)).loadObject(gameManager.loadGame(file));
				int numPlayers = currentGame.getPlayers().length;
				board = new BoardController(currentGame.getBoard());
				players = new PlayerController(numPlayers, currentGame.getPlayers()); 
				shared = new SharedComponentController(currentGame.getShared(), this); 

				// this initializes the dealing of the palace cards
				// and then the player sets those dealt cards as the cards
				players.dealPalaceCards(shared.dealPalaceCards(numPlayers));
				players.setCurrentPlayerinPlayerPanel(currentGame.getPlayerIndex());
				currentGamePanel = new GameContainerPanel(board.getBoardPanel(),
						players.getPlayerPanels(), shared.getSharedComponentPanel());
				gameFrame.setFrameContent(currentGamePanel);
				loadActions();
		
		
		
		return true;
	}

	public boolean saveGame() {
		if (currentGame == null) {
			JOptionPane.showMessageDialog(null, "No game to save!");
			return false;
		}

		String filename = currentGamePanel.askUserWhatNameToSaveGameAs();
		if (filename == null) {
			JOptionPane.showMessageDialog(null, "Invalid Filename");
			return false;
		}

		// everything is okay, save the game
		gameManager.saveGame(filename, currentGame.serialize());

		return true;
	}

	public boolean getCurrentGameExists() {
		if (currentGame == null) {
			return false;
		}
		return true;
	}

	public void keyPressed(KeyEvent e) {
		// this is used only for when users want to show their festival cards
		// when the key is released, then the festival cards will be hidden once
		// again
		if (currentGame != null) {
			userPressedKey(e);
		}
	}

	public void keyReleased(KeyEvent e) {
		// key released is when a user lifts their finger from a key
		if (currentGame != null) {
			userReleasedKey(e);
		}
	}

	public void userPressedKey(KeyEvent e) {
		// check if the key that is pressed is the button to show the user's
		// festival card.
		if (e.getKeyCode() == 70) {
			// the user is pressing (and holding) the F button
			// TODO this can only be called if in Play Mode
			currentGamePanel.playDrawCardSound();
			currentGamePanel.displayPalaceCardFrame(this.players
					.getPlayerAtIndex(this.currentGame.getPlayerIndex()));
		}
	}

	private void userReleasedKey(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 8:
		case 127:
			//released delete, delete a developer from the board
			//need all the type checks and where they are to delete a developer
			
			System.out.println("GCtrl Delete pressed");
			Action deleteAction = currentGame.pressDelete();
			if(deleteAction != null){
				currentGame.addToActionHistory(deleteAction);
				currentGame.setSelectedAction(null);
				currentGamePanel.playPlaceTileSound();
				board.updateBoardPanel(deleteAction, currentGame);
				players.updatePlayerPanel(currentGame.getPlayerIndex());
			}
			else{
				currentGamePanel.playErrorSound();
			}
			break;
		case 9:
			//released tab, tab through developers
			if (currentGame.pressTab()){
				//System.out.println("Gctrl tab for not first time");
				updateBoardControllerWithSelectedAction();
			}
			else if(players.getNumDevelopersOffBoard(currentGame.getPlayerIndex()) < 12) {
				//System.out.println("Gctrl tab for first time");
				currentGame.setSelectedAction(new SelectTabThroughDevelopersAction("player_" + players.getColorOfPlayer(currentGame.getPlayerIndex()), players.getDevelopersOnBoard(currentGame.getPlayerIndex())));
				updateBoardControllerWithSelectedAction();
			} else {
				//System.out.println("Gctrl not tab blargh");
				currentGamePanel.playErrorSound();
			}

			break;
		case 10:

			// released enter, place tile/developer onto board.
			// System.out.println("(in GameController)Enter was pressed");
			Action action = currentGame.pressEnter();
			//currentGamePanel.playSelectDeveloperSound();  ?
			if(action != null){
				currentGame.addToActionHistory(action);
				currentGame.setSelectedAction(null);
				currentGamePanel.playPlaceTileSound();
				updateControllersWithAction(action);
			} else {
				currentGamePanel.playErrorSound();
			}
			break;
		case 27:
			// escapes out of a selected action
			// tells the current game about the event so that it makes
			// SelectedAction to null
			// also updates the board panel so that the image is canceled
			currentGame.pressEsc();
			board.pressEsc();

			break;
		case 32:
			// released the space bar, rotate
			// tells the current game to tell the selectedAction to do
			// pressSpace()
			// will only tell the board about the change if it was a rotatable
			// tile action
			if (currentGame.pressSpace()) {
				currentGamePanel.playMoveComponentSound();
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}
			break;

		// using these arrow keys for movement of developers and tiles
		case 37:
			if (currentGame.pressLeft()) {
				currentGamePanel.playMoveComponentSound();
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}
			break;
		case 38:
			if (currentGame.pressUp()) {
				currentGamePanel.playMoveComponentSound();
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}
			break;
		case 39:
			if (currentGame.pressRight()) {
				currentGamePanel.playMoveComponentSound();
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}
			break;
		case 40:
			if (currentGame.pressDown()) {
				currentGamePanel.playMoveComponentSound();
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}
			break;
		// --------------------------------------------------------------------

		case 50: // released 2, select two space tile
			if (players.selectTwoTile(currentGame.getPlayerIndex())) {
				currentGamePanel.playMoveComponentSound();
				currentGame
						.setSelectedAction(new SelectTwoTileAction("twoTile"));
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}

			break;
		case 51: // released 3, select three space tile
			// check if player has enough AP and if sharedComponent has any more
			// 3 tiles (I could check game state but we could always change how
			// the game state works...)
			// if(players.checkIfSelectionValid() &&
			// shared.checkIfSelectionValid())
			if (players.selectThreeTile(currentGame.getPlayerIndex())
					&& shared.selectThreeTile()) {
				currentGamePanel.playMoveComponentSound();
				currentGame.setSelectedAction(new SelectThreeTileAction(
						"threeTile"));
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}

			break;
		case 68: // released D, add new developer onto board
			// currentGame.setSelectedActionDeveloper(new MAction(""));
			// //somehow know the developer hash with the player color
			if (players.selectDeveloper(currentGame.getPlayerIndex())) {
				currentGamePanel.playSelectDeveloperSound();
				currentGame
						.setSelectedAction(new SelectPlaceDeveloperOnBoardAction(
								"player_"
										+ players.getColorOfPlayer(currentGame
												.getPlayerIndex())));
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}
			break;
		case 73: // released I, add new Irrigation tile
			// check if player has enough AP, and if there is enough in shared
			if (players.selectThreeTile(currentGame.getPlayerIndex())
					&& shared.selectThreeTile()) {
				currentGamePanel.playMoveComponentSound();
				currentGame.setSelectedAction(new SelectIrrigationTileAction(
						"irrigationTile"));
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}
			break;
		case 77:
			if (currentGame.pressM()) {
				currentGamePanel.playSelectDeveloperSound();
				updateBoardControllerWithSelectedAction();
			}
			else{
				currentGamePanel.playErrorSound();
			}

			break;
		case 80:// released P, new palace tile, need to ask for value of Tile

			// is valid due to the view being awesome
			int value = currentGamePanel.promptUserForPalaceValue();

			// check player to see if they have enough AP and check shared to
			// see if there are enough
			if (players.selectPalaceTile(currentGame.getPlayerIndex())
					&& shared.selectPalaceTile(value)) {
				currentGame.setSelectedAction(new SelectPalaceTileAction(
						"palace" + value + "Tile", value));
				currentGamePanel.playMoveComponentSound();
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}
			break;
		case 82:
			// released R, place rice tile
			if (players.selectRiceTile(currentGame.getPlayerIndex())) {
				currentGame.setSelectedAction(new SelectRiceTileAction(
						"riceTile"));
				currentGamePanel.playMoveComponentSound();
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}
			break;
		case 84:
			// released T, use action token
			if (players.selectActionToken(currentGame.getPlayerIndex())) {
				UseActionTokenAction actionTokenAction = new UseActionTokenAction(
						-1);
				currentGamePanel.playMoveComponentSound();
				currentGame.addToActionHistory(actionTokenAction);
				currentGame.doLastActionInHistory();
				updateControllersWithAction(actionTokenAction);
			} else {
				currentGamePanel.playErrorSound();
			}

			break;
		case 85:
			// released U, undo
			if (currentGame.getGameState().equals(GameState.PlanningMode)) {
				undo();
				System.out.println("UNDO");
			} else {
				currentGamePanel.playErrorSound();
			}

			break;
		case 86:
			// released V, place Village
			if (players.selectVillageTile(currentGame.getPlayerIndex())) {
				currentGame.setSelectedAction(new SelectVillageTileAction(
						"villageTile"));
				currentGamePanel.playMoveComponentSound();
				updateBoardControllerWithSelectedAction();
			} else {
				currentGamePanel.playErrorSound();
			}
			break;
		case 88:
			// check if the player has placed a land tile so they can get out of
			// their turn
			// released X, end turn
			if (currentGame.getGameState().equals(GameState.NormalMode) && currentGame.endTurn()) {
				EndTurnAction endTurn = new EndTurnAction(currentGame.nextActionID());
				currentGame.setSelectedAction(null);
				currentGame.addToActionHistory(endTurn);
				board.pressEsc();
				players.setNoCurrentPlayerinPlayerPanels(); // need to tell the
															// player panel of
															// the current
															// player to stop
															// outlining their
															// panel
				players.setCurrentPlayerinPlayerPanel(currentGame
						.getPlayerIndex()); // need to tell the new player panel
											// that they are the current player
			} else {
				currentGamePanel.playErrorSound();
				currentGamePanel.tellPeopleTheyAintPlacedNoLandTile();
			}
			break;
		}
	}

	private void updateControllersWithAction(Action action) {
		if (action instanceof IrrigationTileAction
				|| action instanceof PalaceTileAction
				|| action instanceof ThreeTileAction
				|| action instanceof DrawPalaceCardAction
				|| action instanceof DrawFestivalCardAction)
			shared.updateSharedPanel();
		if (action instanceof IrrigationTileAction
				|| action instanceof PalaceTileAction
				|| action instanceof ThreeTileAction
				|| action instanceof PlaceDeveloperOnBoardAction
				|| action instanceof TakeDeveloperOffBoardAction
				|| action instanceof TwoTileAction
				|| action instanceof VillageTileAction
				|| action instanceof RiceTileAction
				|| action instanceof MoveDeveloperAction)
			board.updateBoardPanel(action, currentGame);
		players.updatePlayerPanel(currentGame.getPlayerIndex());
	}

	private void updateBoardControllerWithSelectedAction() {
		if (currentGame.getSelectedAction() instanceof SelectTwoTileAction
				|| currentGame.getSelectedAction() instanceof SelectThreeTileAction) {
			board.updateSelectedTileAction(
					currentGame.getSelectedActionY() * 50, currentGame
							.getSelectedActionX() * 50, currentGame
							.getSelectedActionImageKey(),
					((SelectRotatableComponentAction) currentGame
							.getSelectedAction()).getRotationState());
		} else if (currentGame.getSelectedAction() instanceof SelectOneSpaceTileAction) {
			board.updateSelectedTileAction(
					currentGame.getSelectedActionY() * 50,
					currentGame.getSelectedActionX() * 50,
					currentGame.getSelectedActionImageKey(), 0);
		} else if (currentGame.getSelectedAction() instanceof SelectPlaceDeveloperOnBoardAction
				|| currentGame.getSelectedAction() instanceof SelectTabThroughDevelopersAction) {// developer
			board.updateSelectedHighlightDeveloperAction(
					currentGame.getSelectedActionY() * 50,
					currentGame.getSelectedActionX() * 50,
					currentGame.getSelectedActionImageKey());
		} else if (currentGame.getSelectedAction() instanceof SelectMoveDeveloperAroundBoardAction) {
			board.updateSelectedPathDeveloperAction(
					currentGame.getSelectedActionImageKey(),
					currentGame.getPath());
		}
	}

	private void seeIfPlayerCanHoldAFestival() {
		// TODO need to first make sure that the user can in fact hold a
		// festival
		// TODO need to get the palace and palace vaule that the player wants it
		// to be on
		// TODO put the festival image on the palace to let the user know that
		// there was a festival on it
		int palaceValue = 10;
		// TODO need to also reflect that in the board model ?
		// TODO
		boolean fest = currentGamePanel
				.askUserIfWouldLikeToHoldAPalaceFestival();
		if (fest)
			startFestival(palaceValue);
	}

	private void startFestival(int palaceValue) {
		// TODO testing
		currentGamePanel.playFestivalSound();
		// currentGamePanel.displayHoldFestivalFrame(this,
		// players.getPlayerModels(), currentGame.getPlayerIndex(),
		// shared.getCurrentFestivalCard(), palaceValue);
	}

	public void updatePlayersAfterFestival(ArrayList<PalaceCard> cardsToDiscard) {
		// the player's fame points and festival cards have already been taken
		// care of.
		// this updates the views and discards of the cards that were played in
		// the festival
		this.players.updatePlayersAfterFestival();
		this.shared.updateAfterFestival(cardsToDiscard);
		this.currentGamePanel.closeFestivalFrame();
	}

	public void startPlanningMode() {
		currentGame.setGameState(GameState.PlanningMode);
		currentGame.setLastPlanningModeActionID();
	}
	
	public void startPlayingMode() {
		currentGame.setGameState(GameState.NormalMode);
		currentGame.flipAllCards();
	}
	
	public void undo() {
		currentGame.clearForReplay();
		currentGame.undoAction();
		board.clearBoard(false);
		Action[] actions = currentGame.getActions();
		doActions(actions, 0, actions.length-1, false, false);
		doActions(actions, actions.length-1, actions.length, false, true);
		System.out.println(currentGame.getActionHistory());
	}

	public void startReplay() {
		System.out.println("START OF ROUND ACTION ID: " + currentGame.getStartOfRoundActionID());
		currentGame.clearForReplay();
		board.clearBoard(false);
		Action[] actions = currentGame.getActions();
		int startOfRoundIndex = 0;
		for(int x = 0; x < actions.length; ++x)
			if(actions[x].getActionID() == currentGame.getStartOfRoundActionID())
				startOfRoundIndex = x;
		System.out.println(Arrays.toString(actions));
		System.out.println("START OF ROUND" + startOfRoundIndex);
		doActions(actions, 0, startOfRoundIndex-1, false, false);
		doActions(actions, startOfRoundIndex-1, actions.length, true, true);
	}

	public void undoUntilLastPlayingMode() {
		currentGame.clearForReplay();
		board.clearBoard(false);
		Action[] actions = currentGame.getActions();
		int startOfPlanningModeIndex = 0;
		for(int x = 0; x < actions.length; ++x)
			if(actions[x].getActionID() == currentGame.getLastPlanningModeActionID())
				startOfPlanningModeIndex = x;
		if(startOfPlanningModeIndex == 0)
			return;
		System.out.println("Last Planning Mode Index" + startOfPlanningModeIndex);
		doActions(actions, 0, startOfPlanningModeIndex, false, false);
		doActions(actions, startOfPlanningModeIndex, startOfPlanningModeIndex+1, false, true);
	}
	
	private void doActions(Action[] actions, int startIndex, int endIndex, boolean wait, boolean draw) {
		for(int x = startIndex; x < endIndex; ++x) {
			if(wait) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {	}
			}	  
			actions[x].doAction(currentGame);
			if(draw) {
				shared.updateSharedPanel();
				players.updatePlayerPanel(currentGame.getPlayerIndex());
			}
			else {
				board.setRedraw(false);
			}
			board.updateBoardPanel(actions[x], currentGame);
			board.setRedraw(true);
		}
	}

	private void loadActions() {
		Action[] actions = currentGame.getActions();
		doActions(actions, 0, actions.length-1, false, false);
		doActions(actions, actions.length-1, actions.length, false, true);
	}

	public void pickUpPalaceCard() {
		Action action = new DrawPalaceCardAction(currentGame.nextActionID());
		action.doAction(currentGame);
		currentGame.addToActionHistory(action);
		updateControllersWithAction(action);
	}

	public void pickUpFestivalCard() {
		Action action = new DrawFestivalCardAction(currentGame.nextActionID());
		action.doAction(currentGame);
		currentGame.addToActionHistory(action);
		updateControllersWithAction(action);
	}

	public Component getGameFrame() {
		return gameFrame;
	}

	public boolean askUserIfWouldLikeToSaveChangesFromPlanningMode() {
		return currentGamePanel.askUserIfWouldLikeToSaveChangesFromPlanningMode();
	}
	
	public boolean askUserIfWouldLikeToEnterReplayMode() {
		return currentGamePanel.askUserIfWouldLikeToEnterReplayMode();
	}
}
