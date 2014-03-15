package Models.Actions;


import Models.*;

/**
 * The abstract "action" class, to be defined by various subclasses, each of
 * which handle the representation of different actions taken by players. Every
 * action holds the index of the player who enacted it, its immutable actionID,
 * and and must be able to undo/redo itself.
 * 
 * @author Cameron Morrow
 * Reused Cameron Morrow's Action class to create MAction, which is a momentary action only used for selection
 * 
 */
public abstract class MAction {

	/**
	 * Returns the int which holds the index of the player who created this
	 * action.
	 */
	
	public abstract void pressSpace();
	
	public abstract void pressArrow(int value);
	
}
