package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class TakeDeveloperOffBoardAction extends NonRotatableComponentAction {

	public TakeDeveloperOffBoardAction(int actionID, int famePointsEarned, int actionPointsEarned, int x, int y) {
		super(actionID, famePointsEarned, actionPointsEarned, x, y);
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
	public void redo(GameModel game) {
		// TODO Auto-generated method stub
		
	}

}
