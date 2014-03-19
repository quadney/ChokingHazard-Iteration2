package Models.Actions;

import Models.GameModel;

public class TriggeredSwitchTurn extends Event {
	
	public TriggeredSwitchTurn() {
		
	}

	@Override
	public void redo(GameModel game) {
		game.endTurn();
		
	}

}
