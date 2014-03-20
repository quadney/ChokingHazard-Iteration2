package Models.Actions;

import Helpers.Json;
import Helpers.JsonObject;
import Models.GameModel;

public class DrawPalaceCardAction extends Action {

	int value;
	public DrawPalaceCardAction(int actionID, int value) {
		super(actionID);
		this.value = value;
		//TODO image key based on value
	}
	
	public DrawPalaceCardAction() {

	}

	
	public String serialize() {
		return Json.jsonObject(Json.jsonElements(
			Json.jsonPair("actionID", this.actionID + ""), 
			Json.jsonPair("imageKey", this.imageKey),
			Json.jsonPair("value", this.value + ""),
			Json.jsonPair("actionType", this.getClass().getSimpleName())
		));
	}
	
	@Override
	public Action loadObject(JsonObject json) {
		return new DrawPalaceCardAction(Integer.parseInt(json.getString("actionID")), Integer.parseInt(json.getString("value")));
	}

	@Override
	public boolean redo(GameModel game) {
		// TODO Auto-generated method stub
		return false;
	}

}
