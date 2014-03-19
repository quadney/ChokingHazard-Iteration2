package Models;

import Helpers.*;
import Models.Actions.*;

import java.util.*;

import Models.Actions.MActions.MAction;

// This enum declaration might need to be moved, not sure how accessible it is right now 
// (needs to be accessible by GameModel and the Controller). #JavaTroubles
enum GameState {
	ReplayMode, PlanningMode, NormalMode
}

public class GameModel implements Serializable<GameModel> {
	// VARIABLES
	private BoardModel gameBoard;
	private JavaPlayer[] players;
	private SharedComponentModel shared;
	private int indexOfCurrentPlayer;
	private boolean isFinalRound;
	public MAction selectedAction;

	private Stack<Event> actionHistory; // This holds a history of the actions
										// taken up to the currently held
										// state.
	private Stack<Event> actionReplays; // This holds currently undone actions
										// for the purposes of Replay Mode

	private GameState gameState;
	private int actionIDCounter; // Provides unique actionIDs to every action
									// created. To be incremented after each
									// action instantiation.

	public GameModel(int numberPlayers, String[] playerNames,
			String[] playerColors) {
		this.isFinalRound = false;
		this.indexOfCurrentPlayer = 0;
		this.gameBoard = new BoardModel();
		this.shared = new SharedComponentModel();
		this.players = new JavaPlayer[numberPlayers];
		for (int i = 0; i < players.length; i++) {
			players[i] = new JavaPlayer(playerNames[i], playerColors[i]);
		}
		actionHistory = new Stack<Event>();
		actionReplays = new Stack<Event>();
		selectedAction = null;
	}

	// created for loading the game
	public GameModel(int numberPlayers) {
		this.isFinalRound = false;
		this.indexOfCurrentPlayer = 0;
		this.gameBoard = new BoardModel();
		this.players = new JavaPlayer[numberPlayers];
		actionHistory = new Stack<Event>();
		actionReplays = new Stack<Event>();
		selectedAction = null;
	}

	// This method is to be used by the controller to determine which buttons
	// are visible/enabled in the view (and other visual components). For
	// example, if the gameState is PlanningMode, the undo button and exit
	// planning mode buttons will be enabled/shown, as well as a Label or
	// something.
	public GameState getGameState() {
		return gameState;
	}

	public void changeFamePoints(int playerIndex, int modifier) {
		players[playerIndex].changeFamePoints(modifier);
	}

	// Used for testing the Actions

	public boolean endTurn() {										//this is called by TriggerSwitchTurn Action and the GameController.
																	//if valid, changes all states accordingly, if not, doesn't do a thing
		JavaPlayer currentPlayer = players[indexOfCurrentPlayer];	//gets the current player out of the container
		if (currentPlayer.endPlayerTurn()){							//if the player can validly end their turn. All the player stuff will have been manipulated
			System.out.println("player ended turn and index is being incremented");
			indexOfCurrentPlayer = ++indexOfCurrentPlayer % players.length;				//switches the curentPlayer to the next player
			return true;											//tells the caller that everything worked out!
		}
		return false;
	}

	public void placeTile(int x, int y, Tile tile, JavaPlayer player) {
		gameBoard.placeTile(x, y, tile, player);
	}

	public Developer getDeveloperOnCell(JavaCell c) {
		for (int i = 0; i < players.length; i++) {
			for (Developer d : players[i].getDevelopersOnBoard()) {
				if (d.getLocation() == c)
					return d;
			}
		}
		return null;
	}

	/**
	 * Backtracks GameModel state to end of current player's previous turn,
	 * storing all backtracked moves in actionReplays stack. Also changes
	 * gameState to ReplayMode.
	 */
	public void initializeReplayMode() {
		gameState = GameState.ReplayMode;

		while (actionHistory.peek().getPlayerIndex() == indexOfCurrentPlayer) {
			actionReplays.add(actionHistory.peek());
			actionHistory.peek().undo(this);
			actionHistory.pop();
		}

		while (actionHistory.peek().getPlayerIndex() != indexOfCurrentPlayer) {
			actionReplays.add(actionHistory.peek());
			actionHistory.peek().undo(this);
			actionHistory.pop();
		}
	}

	/**
	 * Iterates forward one move during ReplayMode. If the move replayed is the
	 * last move to be replayed, changes gameState to NormalMode.
	 * <p>
	 * This method should only be accessible while the gameState is ReplayMode!
	 * I'm leaving this responsibility to the view and controller.
	 */
	public void replayMove() {
		actionHistory.add(actionReplays.peek());
		actionReplays.peek().redo(this);
		actionReplays.pop();

		if (actionReplays.isEmpty())
			gameState = GameState.NormalMode;
	}

	public int getPlayerIndex() {
		return this.indexOfCurrentPlayer;
	}

	public boolean pressSpace() {
		if (selectedAction != null) {
			return selectedAction.pressSpace();
		}
		return false;
	}

	public Action pressEnter() {
		if (selectedAction != null) {
			return selectedAction.pressEnter(this);
		}
		return null;
	}

	// Methods for MAction/selected action traversal that is needed by the
	// controller

	public int getSelectedActionX() {
		return selectedAction.getX();
	}

	public int getSelectedActionY() {
		// TODO Auto-generated method stub
		return selectedAction.getY();
	}

	public String getSelectedActionImageKey() {
		return selectedAction.getImageKey();
	}

	public MAction getSelectedAction() {
		return selectedAction;
	}

	public void pressEsc() {
		selectedAction = null;
	}

	public boolean pressLeft() {
		if (selectedAction != null) {
			return selectedAction.pressArrow(0, -1);
		}
		return false;
	}

	public boolean pressUp() {
		if (selectedAction != null) {
			return selectedAction.pressArrow(-1, 0);
		}
		return false;
	}

	public boolean pressRight() {
		if (selectedAction != null) {
			return selectedAction.pressArrow(0, 1);
		}
		return false;
	}

	public boolean pressDown() {
		if (selectedAction != null) {
			return selectedAction.pressArrow(1, 0);
		}
		return false;
	}

	public boolean setSelectedAction(MAction selectedAction) {
		// if(this.selectedAction == null){
		// System.out.println("(in GameModel setSelectedACtion()) setSelectedAction set the new MAction");
		this.selectedAction = selectedAction;
		return true;
		// }
		// System.out.println("(in GameModel setSelectedACtion()) setSelectedAction already had an MAction");
		// return false;

	}

	// Method used in Event-----------------------------------------------------

	public void setIsFinalRound(boolean b) { // used in TriggeredFinalRound
		this.isFinalRound = b;
	}

	// ---------------------------------------------------------------------------

	@Override
	public String serialize() {
		return Json.jsonObject(Json.jsonMembers(Json.jsonPair("gameBoard",
				gameBoard.serialize()), Json.jsonPair("gameState",
				gameState.toString()), Json.jsonPair("actionHistory",
				Json.serializeArray(actionHistory)), Json.jsonPair(
				"actionReplays", Json.serializeArray(actionReplays)), Json
				.jsonPair("players", Json.serializeArray(players)), Json
				.jsonPair("indexOfCurrentPlayer",
						Json.jsonValue(indexOfCurrentPlayer + "")), Json
				.jsonPair("isFinalRound", Json.jsonValue(isFinalRound + "")),
				Json.jsonPair("actionIDCounter",
						Json.jsonValue(actionIDCounter + ""))));
	}

	@Override
	public GameModel loadObject(JsonObject json) {
		gameState = GameState.valueOf(json.getString("gameState"));
		gameBoard = (new BoardModel()).loadObject(json
				.getJsonObject("gameBoard"));
		indexOfCurrentPlayer = Integer.parseInt(json
				.getString("indexOfCurrentPlayer"));
		isFinalRound = Boolean.parseBoolean(json.getString("isFinalRound"));
		actionIDCounter = Integer.parseInt(json.getString("actionIDCounter"));

		this.players = new JavaPlayer[json.getJsonObjectArray("players").length];
		for (int x = 0; x < players.length; ++x) {
			json.getJsonObjectArray("players")[x].addKeyManually("map",
					gameBoard.getMap());
			players[x] = (new JavaPlayer("temp", "temp")).loadObject(json
					.getJsonObjectArray("players")[x]);
		}

		this.actionHistory = new Stack<Event>();
		for (JsonObject obj : json.getJsonObjectArray("actionHistory"))
			actionHistory.push(Action.loadAction(obj));

		this.actionReplays = new Stack<Event>();
		for (JsonObject obj : json.getJsonObjectArray("actionReplays"))
			actionReplays.push(Action.loadAction(obj));

		return this;
	}

	public void addToActionHistory(Action action) {
		actionHistory.add(action);
	}

	public JavaPlayer getCurrentPlayer() {
		return players[indexOfCurrentPlayer];
	}

	public BoardModel getBoard() {
		return gameBoard;
	}

	public void doLastActionInHistory() {
		System.out.println("in doLactActionInHistory");
		if (actionHistory.size() > 0) {
			actionHistory.peek().redo(this);
		}
	}

	public JavaPlayer[] getPlayers() {
		return players;
	}

	public SharedComponentModel getShared() {
		return shared;
	}

	public int getNextCellId() {
		return gameBoard.getNextCellId();
	}

	public void placeDeveloperOnBoard(int x, int y) {
		gameBoard.placeDeveloper(gameBoard.getCellAtXY(x, y),
				players[indexOfCurrentPlayer]);
	}
}
