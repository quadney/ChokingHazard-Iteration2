package Models.Actions;

import Models.GameModel;
import Models.JavaCell;

public class VillageTileAction extends OneSpaceTileAction {

	public VillageTileAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned, int x, int y, JavaCell cell) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned, x, y, cell);
	}

	@Override
	public void undo(GameModel game) {
		game.getCurrentPlayer().incrementVillage();
		game.getBoard().getMap();
	}

	@Override
	public void redo(GameModel game) {
		game.getCurrentPlayer().decrementVillage();
	}

}
