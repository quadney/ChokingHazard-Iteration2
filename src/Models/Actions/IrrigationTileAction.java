package Models.Actions;

import Models.GameModel;
import Models.Tile;
import Models.Tile.TileType;

public class IrrigationTileAction extends OneSpaceTileAction {

	public IrrigationTileAction(int actionID, int x, int y) {
		super(actionID, x, y);
		this.imageKey = "irrigationTile";
	}

	@Override
	public void redo(GameModel game) {
		game.getBoard().placeTile(x, y, new Tile(TileType.irrigation, 0), game.getCurrentPlayer());
		game.getShared().decrementIrrigationTiles();
	}
}
