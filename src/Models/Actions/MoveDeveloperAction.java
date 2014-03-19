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
		
		return false;
	}

}
