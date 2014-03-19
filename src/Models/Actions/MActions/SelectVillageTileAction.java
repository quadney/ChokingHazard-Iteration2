package Models.Actions.MActions;

import Models.GameModel;
import Models.Actions.Action;
import Models.Actions.VillageTileAction;

public class SelectVillageTileAction extends SelectOneSpaceTileAction {

	public SelectVillageTileAction(String imageKey) {
		super(imageKey);
		// TODO Auto-generated constructor stub
	}
	
	public Action pressEnter(GameModel game) {
		return new VillageTileAction(-1, x, y);
	}
}
