package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class TwoTileAction extends RotatableComponentAction {

	public TwoTileAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned, int x, int y,	int rotationState) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned, x, y, rotationState);
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
