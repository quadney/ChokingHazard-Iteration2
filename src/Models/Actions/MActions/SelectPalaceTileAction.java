package Models.Actions.MActions;

import Models.Actions.Action;
import Models.Actions.PalaceTileAction;

public class SelectPalaceTileAction extends SelectOneSpaceTileAction {

	
	int value;
	
	public SelectPalaceTileAction(String imageKey, int value) {
		super(imageKey);
		this.value = value;
	}

	public int getValue(){
		return value;
	}
	
	public Action pressEnter() {
		return new PalaceTileAction(-1, null, null, 0, x, y, value);
	}
}
