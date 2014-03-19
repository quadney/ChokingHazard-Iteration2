package Models.Actions;

import Models.GameModel;

public class TriggeredFinalRound extends Event {

	public TriggeredFinalRound(GameModel game) {
		redo(game);
	}

	public boolean redo(GameModel game) {
		game.setIsFinalRound(true);
		return true;
	}
	
}
