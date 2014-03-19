package Models.Actions;

import Helpers.JsonObject;
import Models.Serializable;

public abstract class Action extends Event implements Serializable<Action> {

	public final int actionID;
	public String imageKey;
	
	public Action(int actionID) {
		this.actionID = actionID;
	}
	
	public int getActionID() {
		return actionID;
	}
	
	public static Action loadAction(JsonObject json) {
		// TODO this will return the correct Action based on sub actions
		return null;
	}
}
