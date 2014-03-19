package Models.Actions;

import Helpers.Json;

public abstract class RotatableComponentAction extends Action {
	int rotationState = 0;
	protected int x; 
	protected int y;
	
	public RotatableComponentAction() {

	}

	public RotatableComponentAction(int actionID, int x, int y, int rotationState) {

		super(actionID);
		this.x = x; 
		this.y = y;
		this.rotationState = rotationState;
	}

	public int getRotationState(){
		return rotationState;
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
			Json.jsonPair("rotationState", this.rotationState + ""),
			Json.jsonPair("actionType", this.getClass().getSimpleName())
		));
	}
	
}