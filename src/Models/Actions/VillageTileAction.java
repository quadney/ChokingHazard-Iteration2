package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;
import Models.Tile;
import Models.Tile.TileType;

public class VillageTileAction extends OneSpaceTileAction {

	public VillageTileAction(int actionID, int x, int y) {
		super(actionID, x, y);
		this.imageKey = "villageTile";
	}
	
	public VillageTileAction() {

	}

	@Override
	public boolean redo(GameModel game) {
		if(game.getBoard().placeTile(x, y, new Tile(TileType.village, 0), game.getCurrentPlayer(), game.getAllPlayerDevelopers())) {
			game.getCurrentPlayer().decrementVillage();
			return true;
		}
		return false;
	}

	@Override
	public Action loadObject(JsonObject json) {
		return new VillageTileAction(Integer.parseInt(json.getString("actionID")), 
				Integer.parseInt(json.getString("x")), 
				Integer.parseInt(json.getString("y")));
	}

}
