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
		return new PlaceDeveloperOnBoardAction(Integer.parseInt(json.getString("actionID")), 
				Integer.parseInt(json.getString("x")), 
				Integer.parseInt(json.getString("y")));
	}

	@Override
	public boolean redo(GameModel game) {
		 return game.placeDeveloperOnBoard(x, y);
	}
}
