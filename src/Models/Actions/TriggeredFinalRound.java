package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class TriggeredFinalRound extends Event {

	public TriggeredFinalRound(GameModel game) {
		redo(game);
	}

	public boolean redo(GameModel game) {
		game.setIsFinalRound(true);
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
