package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class ThreeTileAction extends RotatableComponentAction {

	public boolean isFinalRound;

	public ThreeTileAction(int actionID, String beforeImageKey,	String afterImageKey, int famePointsEarned, int x, int y, int rotationState, boolean isFinalRound) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned, x, y, rotationState, 0); //TODO hard coded elevation value
		this.isFinalRound = isFinalRound;
	}
	
	@Override
	public String serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo(GameModel game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo(GameModel game) {
		// TODO Auto-generated method stub
		
	}
}
