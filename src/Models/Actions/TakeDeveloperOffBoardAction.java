package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class TakeDeveloperOffBoardAction extends NonRotatableComponentAction {

	public TakeDeveloperOffBoardAction(int actionID, int x, int y) {
		super(actionID, x, y);
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
	public boolean redo(GameModel game) {
		// TODO Auto-generated method stub
		return false;
	}

}
