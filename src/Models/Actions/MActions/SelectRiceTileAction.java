package Models.Actions.MActions;

import Models.GameModel;
import Models.Actions.Action;
import Models.Actions.RiceTileAction;

public class SelectRiceTileAction extends SelectOneSpaceTileAction {

	public SelectRiceTileAction(String imageKey) {
		super(imageKey);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Action pressEnter(GameModel game) {
		return new RiceTileAction(-1, x, y);
	}
}
