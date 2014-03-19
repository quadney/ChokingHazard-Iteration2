package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class MoveDeveloperAction extends NonRotatableComponentAction {

	public MoveDeveloperAction(int actionID, int x, int y) {
		super(actionID, x, y);
	}
	
	public MoveDeveloperAction() {

	}

	@Override
	public Action loadObject(JsonObject json) {
		return new MoveDeveloperAction(Integer.parseInt(json.getString("actionID")), 
				Integer.parseInt(json.getString("x")), 
				Integer.parseInt(json.getString("y")));
	}

	@Override
	public boolean redo(GameModel game) {
		
		return false;
	}

}
