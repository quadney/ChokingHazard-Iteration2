package Models.Actions;

import Models.GameModel;

public class TriggeredFinalRound extends Event {

	public TriggeredFinalRound(GameModel game) {
		redo(game);
	}

	
	public void undo(GameModel game) {
		game.setIsFinalRound(false);
	}

	
	public void redo(GameModel game) {
		game.setIsFinalRound(true);
	}
	
}
