package Models.Actions.MActions;

import Models.GameModel;
import Models.Actions.Action;
import Models.Actions.IrrigationTileAction;

public class SelectIrrigationTileAction extends SelectOneSpaceTileAction {

	public SelectIrrigationTileAction(String imageKey) {
		super(imageKey);
		// TODO Auto-generated constructor stub
	}
	
	public Action pressEnter(GameModel game) {
		IrrigationTileAction action = new IrrigationTileAction(game.nextActionID(), x, y);
		if(action.doAction(game))
			return action; 
		return null;
	}
}
