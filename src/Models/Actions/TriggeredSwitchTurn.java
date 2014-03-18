package Models.Actions;

import Models.GameModel;

public class TriggeredSwitchTurn extends Event {

	int oldPlayerIndex;
	int newPlayerIndex;
	
	public TriggeredSwitchTurn(GameModel game) {
		oldPlayerIndex = game.getPlayerIndex();
		newPlayerIndex = oldPlayerIndex;
	}

	@Override
	public void redo(GameModel game) {
		
	}

}
