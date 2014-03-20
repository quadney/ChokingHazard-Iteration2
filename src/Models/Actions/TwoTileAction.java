package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;
import Models.Tile;
import Models.Tile.TileType;

public class TwoTileAction extends RotatableComponentAction {
	

	public TwoTileAction(int actionID, int x, int y, int rotationState) {
		super(actionID, x, y, rotationState);
//		System.out.println("Two Tile constructor end");
		this.imageKey = "twoTile";
	}
	
	public TwoTileAction() {

	}

	@Override
	public Action loadObject(JsonObject json) {
		return new TwoTileAction(Integer.parseInt(json.getString("actionID")), 
				Integer.parseInt(json.getString("x")), 
				Integer.parseInt(json.getString("y")), 
				Integer.parseInt(json.getString("rotationState")));
	}

	@Override
	public boolean redo(GameModel game) {
		if(game.getBoard().placeTile(x, y, new Tile(TileType.twotile, rotationState), game.getCurrentPlayer(), game.getAllPlayerDevelopers())) {
			game.getCurrentPlayer().decrementTwo();
			return true;
		}
		return false;
	}
		
}
