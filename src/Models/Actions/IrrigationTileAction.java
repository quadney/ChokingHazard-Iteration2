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
		if(game.getBoard().placeTile(x, y, new Tile(TileType.irrigation, 0), game.getCurrentPlayer(), game.getAllPlayerDevelopers())) {
			game.getShared().decrementIrrigationTiles();
			return true;
		}
		return false;
	}

	@Override
	public Action loadObject(JsonObject json) {
		return new IrrigationTileAction(Integer.parseInt(json.getString("actionID")), 
				Integer.parseInt(json.getString("x")), 
				Integer.parseInt(json.getString("y")));
	}
}
