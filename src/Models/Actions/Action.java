package Models.Actions;

import Helpers.JsonObject;
import Models.Serializable;

/**
 * The abstract "action" class, to be defined by various subclasses, each of
 * which handle the representation of different actions taken by players. Every
 * action holds the index of the player who enacted it, its immutable actionID,
 * and and must be able to undo/redo itself.
 * 
 * @author Cameron Morrow
 * 
 */
public abstract class Action extends Event implements Serializable<Action> {

	protected final int actionID;
	protected int playerIndex;

	public Action(int actionID, int playerIndex) {
		this.actionID = actionID;
		this.playerIndex = playerIndex;
	}
	
	//Accessors-------------------------------------------------------------------
	/**
	 * Returns the immutable actionID of the action, obtained at the time of
	 * instantiation.
	 */
	public int getActionID() {
		return actionID;
	}

	/**
	 * Returns the int which holds the index of the player who created this
	 * action.
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	//Loading----------------------------------------------------------------------
	
	public static Action loadAction(JsonObject json) {
		// TODO this will return the correct Action based on sub actions
		return null;
	}

}
