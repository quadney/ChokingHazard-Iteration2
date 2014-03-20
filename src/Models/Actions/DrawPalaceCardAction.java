package Models.Actions;

import Helpers.Json;
import Helpers.JsonObject;
import Models.GameModel;

public class DrawPalaceCardAction extends Action {

	public DrawPalaceCardAction(int actionID) {
		super(actionID);
		//TODO image key based on value
	}
	
	public DrawPalaceCardAction() {

	}

	
	public String serialize() {
		return Json.jsonObject(Json.jsonElements(
			Json.jsonPair("actionID", this.actionID + ""), 
			Json.jsonPair("imageKey", this.imageKey),
			Json.jsonPair("actionType", this.getClass().getSimpleName())
		));
	}
	
	@Override
	public Action loadObject(JsonObject json) {
		return new DrawPalaceCardAction(Integer.parseInt(json.getString("actionID")));
	}

	@Override
	public boolean redo(GameModel game) {
		// TODO Auto-generated method stub
		return false;
	}

}
