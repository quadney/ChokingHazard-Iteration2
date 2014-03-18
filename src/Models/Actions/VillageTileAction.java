package Models.Actions;

import Models.GameModel;
import Models.JavaCell;

public class VillageTileAction extends OneSpaceTileAction {

	public VillageTileAction(int actionID, int x, int y) {
		super(actionID, x, y);
		this.imageKey = "villageTile";
	}

	@Override
	public void redo(GameModel game) {
		game.getCurrentPlayer().decrementVillage();
		// TODO deal with cellID
		game.getBoard().getMap()[x][y] = new JavaCell(x, y, "VILLAGE", -1);
	}

}
