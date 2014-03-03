package Models.Actions;

import Models.*;

/**
 * The abstract "action" class, to be defined by various subclasses, each of
 * which handle the representation of different actions taken by players. Every
 * action holds the index of the player who enacted it, its immutable actionID,
 * and and must be able to undo itself.
 * 
 * @author Cameron Morrow
 * 
 */
public abstract class Action {

	protected final int actionID;

	/**
	 * Returns the immutable actionID of the action, obtained at the time of
	 * instantiation.
	 */
	public int getActionID() {
		return actionID;
	}

	protected int playerIndex;

	/**
	 * Returns the int which holds the index of the player who created this
	 * action.
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	public Action(int actionID, int playerIndex) {
		this.actionID = actionID;
		this.playerIndex = playerIndex;
	}

	/**
	 * Undoes the action's effects on the GameModel.
	 * 
	 * @param game
	 *            The game upon which the action took place.
	 */
	public abstract void undo(GameModel game);

}
