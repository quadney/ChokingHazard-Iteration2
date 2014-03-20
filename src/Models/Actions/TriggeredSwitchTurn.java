package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class TriggeredSwitchTurn extends Event {
	
	public TriggeredSwitchTurn() {
		
	}

	@Override
	public boolean redo(GameModel game) {
		game.endTurn();
		return true;
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

}
