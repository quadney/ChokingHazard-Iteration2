package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class UseActionTokenAction extends Action {

	public UseActionTokenAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned);
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
		game.getCurrentPlayer().unuseActionToken();
	}

	@Override
	public void redo(GameModel game) {
		game.getCurrentPlayer().useActionToken();
	}
}
