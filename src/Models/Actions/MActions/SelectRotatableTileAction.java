package Models.Actions.MActions;

import Helpers.Json;


public abstract class SelectRotatableTileAction extends MAction {

	int rotationState = 0;
	
	public SelectRotatableTileAction(String imageKey) {
		super(imageKey);
		// TODO Auto-generated constructor stub
		
	}

	public boolean pressSpace() {
		int newRotationState = (rotationState + 1)  % 4;
		if (isRotatableComponentOnBoard(x,y,newRotationState)){
			rotationState = newRotationState;
			return true;
		}
		return false;
	}
	
	public boolean pressArrow(int xChange, int yChange) {
		int newX = x + xChange;
		int newY = y + yChange;
		if(isRotatableComponentOnBoard(newX, newY, rotationState)){
			x = newX;
			y = newY;
			return true;
		}
		else{
			return false;
		}
	}
	
	protected abstract boolean isRotatableComponentOnBoard(int x, int y, int rotationState);
	
	public int getRotationState(){
		return rotationState;
	}
	
	@Override
	public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("x", Json.jsonValue(x + "")),
			Json.jsonPair("y", Json.jsonValue(y + "")),
			Json.jsonPair("rotationState", Json.jsonValue(rotationState + "")),
			Json.jsonPair("imageKey", Json.jsonValue(imageKey))
		));
	}
}