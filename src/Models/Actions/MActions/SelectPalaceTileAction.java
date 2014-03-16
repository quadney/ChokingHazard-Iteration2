package Models.Actions.MActions;

import Helpers.JsonObject;

public class SelectPalaceTileAction extends SelectOneSpaceTileAction {

	
	int value;
	
	public SelectPalaceTileAction(String imageKey, int value) {
		super(imageKey);
		this.value = value;
	}

	public int getValue(){
		return value;
	}
	

	public SelectPalaceTileAction loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}
}
