package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class EndTurnAction extends Action {

	public EndTurnAction(int actionID) {
		super(actionID);
		// TODO Auto-generated constructor stub
	}
	
	public EndTurnAction() {

	}

	@Override
	public Action loadObject(JsonObject json) {
		return new EndTurnAction(Integer.parseInt(json.getString("actionID")));
	}

	@Override
	public boolean redo(GameModel game) {
		return game.endTurn();
	}

}
