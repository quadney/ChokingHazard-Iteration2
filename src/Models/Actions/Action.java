package Models.Actions;

import Helpers.JsonObject;
import Models.Serializable;

public abstract class Action extends Event implements Serializable<Action> {

	public final int actionID;
	public String imageKey;
	public int famePointsEarned;
	
	public Action(int actionID, int famePointsEarned) {
		this.actionID = actionID;
		this.famePointsEarned = famePointsEarned;
	}
	
	public int getActionID() {
		return actionID;
	}
	
	public static Action loadAction(JsonObject json) {
		// TODO this will return the correct Action based on sub actions
		return null;
	}

	public int getEarnedFamePoints() {
		return 0; //return earned fame points;
	}

}
