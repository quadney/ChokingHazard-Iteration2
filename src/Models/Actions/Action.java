package Models.Actions;

import Helpers.JsonObject;
import Models.Serializable;

public abstract class Action extends Event implements Serializable<Action> {

	public final int actionID;
	public String beforeImageKey;
	public String afterImageKey;
	public int famePointsEarned;
	
	public Action(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned) {
		this.actionID = actionID;
		this.beforeImageKey = beforeImageKey; 
		this.afterImageKey = afterImageKey;
		this.famePointsEarned = famePointsEarned;
	}
	
	public int getActionID() {
		return actionID;
	}
	
	public static Action loadAction(JsonObject json) {
		// TODO this will return the correct Action based on sub actions
		return null;
	}

}
