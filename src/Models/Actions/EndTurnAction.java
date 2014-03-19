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
	public String serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean redo(GameModel game) {
		return game.endTurn();
	}

}
