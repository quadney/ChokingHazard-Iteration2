package Models.Actions;

import Helpers.Json;
import Helpers.JsonObject;
import Models.GameModel;

public class UseActionTokenAction extends Action {

	public UseActionTokenAction(int actionID, int famePointsEarned) {
		super(actionID, famePointsEarned);
	}

	@Override
	public String serialize() {
		return Json.jsonObject(Json.jsonElements(
			Json.jsonPair("actionID", actionID + ""),
			Json.jsonPair("famePointsEarned", famePointsEarned + "")
		));
	}

	@Override
	public Action loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void redo(GameModel game) {
		game.getCurrentPlayer().useActionToken();
	}
}
