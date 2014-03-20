package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class TakeDeveloperOffBoardAction extends NonRotatableComponentAction {

	public TakeDeveloperOffBoardAction(int actionID, int x, int y) {
		super(actionID, x, y);
	}
	
	public TakeDeveloperOffBoardAction(){
		
	}


	@Override
	public Action loadObject(JsonObject json) {
		return new TakeDeveloperOffBoardAction(Integer.parseInt(json.getString("actionID")), 
				Integer.parseInt(json.getString("x")), 
				Integer.parseInt(json.getString("y")));
	}

	@Override
	public boolean redo(GameModel game) {
		return game.takeDeveloperOffBoard(x, y);
	}

}
