package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;
import Models.Tile;
import Models.Tile.TileType;

public class IrrigationTileAction extends OneSpaceTileAction {

	public IrrigationTileAction(int actionID, int x, int y) {
		super(actionID, x, y);
		this.imageKey = "irrigationTile";
	}
	
	public IrrigationTileAction() {

	}

	@Override
	public boolean redo(GameModel game) {
		if(game.getBoard().placeTile(x, y, new Tile(TileType.irrigation, 0), game.getCurrentPlayer())) {
			game.getShared().decrementIrrigationTiles();
			return true;
		}
		return false;
	}

	@Override
	public String serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}
}
