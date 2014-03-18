package Models.Actions.MActions;

import Models.Actions.Action;
import Models.Actions.RiceTileAction;

public class SelectRiceTileAction extends SelectOneSpaceTileAction {

	public SelectRiceTileAction(String imageKey) {
		super(imageKey);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Action pressEnter() {
		return new RiceTileAction(-1, null, null, x, y, 0);
	}
}
