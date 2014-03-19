package Models.Actions;

import Helpers.Json;

public abstract class NonRotatableComponentAction extends Action {

	public int x;
	public int y;
	public NonRotatableComponentAction(int actionID, int x, int y) {
		super(actionID);
		this.x = x;
		this.y = y;
	}
	
	public NonRotatableComponentAction() {

	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

	public String serialize() {
		return Json.jsonObject(Json.jsonElements(
			Json.jsonPair("actionID", this.actionID + ""), 
			Json.jsonPair("imageKey", this.imageKey),
			Json.jsonPair("x", this.x + ""),
			Json.jsonPair("y", this.y + ""),
			Json.jsonPair("actionType", this.getClass().getSimpleName())
		));
	}
}
