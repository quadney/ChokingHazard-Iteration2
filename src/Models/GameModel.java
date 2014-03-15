package Models;

import Helpers.*;
import Models.Actions.*;

// This enum declaration might need to be moved, not sure how accessible it is right now 
// (needs to be accessible by GameModel and the Controller). #JavaTroubles
enum GameState {
	ReplayMode, PlanningMode, NormalMode
}

public class GameModel {
	// VARIABLES
	private BoardModel gameBoard;
	private JavaPlayer[] players;
	private int indexOfCurrentPlayer;
	private boolean isFinalRound;
	public MAction selectedAction;

	private Stack<Action> actionHistory; // This holds a history of the actions
											// taken up to the currently held
											// state.
	private Stack<Action> actionReplays; // This holds currently undone actions
											// for the purposes of Replay Mode

	private GameState gameState;
	private int actionIDCounter; // Provides unique actionIDs to every action
									// created. To be incremented after each
									// action instantiation.

	public GameModel(int numberPlayers){
		this.isFinalRound = false;
		this.indexOfCurrentPlayer = 0;
		this.gameBoard = new BoardModel();
		this.players = new JavaPlayer[numberPlayers];
		
		actionHistory = new Stack<Action>();
		actionReplays = new Stack<Action>();
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

	/**
	 * Backtracks GameModel state to end of current player's previous turn,
	 * storing all backtracked moves in actionReplays stack. Also changes
	 * gameState to ReplayMode.
	 */
	public void initializeReplayMode() {
		gameState = GameState.ReplayMode;

		while (actionHistory.top().getPlayerIndex() == indexOfCurrentPlayer) {
			actionReplays.add(actionHistory.top());
			actionHistory.top().undo(this);
			actionHistory.pop();
		}

		while (actionHistory.top().getPlayerIndex() != indexOfCurrentPlayer) {
			actionReplays.add(actionHistory.top());
			actionHistory.top().undo(this);
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
		actionHistory.add(actionReplays.top());
		actionReplays.top().redo(this);
		actionReplays.pop();

		if (actionReplays.isEmpty())
			gameState = GameState.NormalMode;
	}

	public int getPlayerIndex() {
		return this.indexOfCurrentPlayer;
	}

	public void pressSpace() {
		selectedAction.pressSpace();
	}
	
	//Methods for MAction/selected action traversal that is needed by the controller

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
}
