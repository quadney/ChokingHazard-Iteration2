package Models.Actions;

import Models.GameModel;
import Models.Tile;
import Models.Tile.TileType;

public class VillageTileAction extends OneSpaceTileAction {

	public VillageTileAction(int actionID, int x, int y) {
		super(actionID, x, y);
		this.imageKey = "villageTile";
	}

	@Override
	public void redo(GameModel game) {
		game.getBoard().placeTile(x, y, new Tile(TileType.village, 0), game.getCurrentPlayer());
		game.getCurrentPlayer().decrementVillage();
	}

}
