package Models.Actions;

import Helpers.JsonObject;
import Models.Serializable;

public abstract class Action extends Event implements Serializable<Action> {

	public final int actionID;
	public String imageKey;
	public int famePointsEarned;
	public int actionPointsCost;
	
	public Action(int actionID, int famePointsEarned, int actionPointsCost) {
		this.actionID = actionID;
		this.famePointsEarned = famePointsEarned;
		this.actionPointsCost = actionPointsCost;
	}
	
	public int getActionID() {
		return actionID;
	}
	
	public static Action loadAction(JsonObject json) {
		// TODO this will return the correct Action based on sub actions
		return null;
	}

	public int getEarnedFamePoints() {
		return famePointsEarned; //return earned fame points;
	}
	
	public int getActionPointsCost(){
		return actionPointsCost; 
	}

}
