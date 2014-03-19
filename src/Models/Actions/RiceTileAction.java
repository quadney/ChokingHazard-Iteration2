package Models.Actions;

import Models.GameModel;
import Models.Tile;
import Models.Tile.TileType;

public class RiceTileAction extends OneSpaceTileAction {

	public RiceTileAction(int actionID, int x, int y) {
		super(actionID, x, y);
		this.imageKey = "riceTile";
	}

	@Override
	public void redo(GameModel game) {
		game.getBoard().placeTile(x, y, new Tile(TileType.rice, 0), game.getCurrentPlayer());
		game.getCurrentPlayer().decrementRice();
	}
}
