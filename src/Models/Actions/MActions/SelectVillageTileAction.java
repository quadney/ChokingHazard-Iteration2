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
		VillageTileAction action = new VillageTileAction(-1, x, y);
		if(action.doAction(game))
			return action; 
		return null;
	}
}
