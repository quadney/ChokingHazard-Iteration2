package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class PlaceDeveloperOnBoardAction extends NonRotatableComponentAction {


	public PlaceDeveloperOnBoardAction(int actionID, int x, int y) {
		super(actionID, x, y);
	}
	
	public PlaceDeveloperOnBoardAction() {

	}

	@Override
	public Action loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean redo(GameModel game) {
		 game.placeDeveloperOnBoard(x, y);
		 return false;
	}
}
