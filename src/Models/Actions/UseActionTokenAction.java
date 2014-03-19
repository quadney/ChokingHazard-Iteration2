package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class UseActionTokenAction extends Action {

	public UseActionTokenAction(int actionID) {
		super(actionID);
	}
	
	public UseActionTokenAction() {

	}

	@Override
	public Action loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean redo(GameModel game) {
		game.getCurrentPlayer().useActionToken();
		return true;
	}
}
