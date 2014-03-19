package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;
import Models.Tile;
import Models.Tile.TileType;

public class RiceTileAction extends OneSpaceTileAction {

	public RiceTileAction(int actionID, int x, int y) {
		super(actionID, x, y);
		this.imageKey = "riceTile";
	}
	
	public RiceTileAction() {

	}

	@Override
	public boolean redo(GameModel game) {
		if(game.getBoard().placeTile(x, y, new Tile(TileType.rice, 0), game.getCurrentPlayer())) {
			game.getCurrentPlayer().decrementRice();			
			return true;
		}
		return false;
	}

	@Override
	public Action loadObject(JsonObject json) {
		return new RiceTileAction(Integer.parseInt(json.getString("actionID")), 
				Integer.parseInt(json.getString("x")), 
				Integer.parseInt(json.getString("y")));
	}
}
