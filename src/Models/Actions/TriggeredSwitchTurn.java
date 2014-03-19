package Models.Actions;

import Models.GameModel;

public class TriggeredSwitchTurn extends Event {
	
	public TriggeredSwitchTurn() {
		
	}

	@Override
	public boolean redo(GameModel game) {
		game.endTurn();
		return true;
	}

}
