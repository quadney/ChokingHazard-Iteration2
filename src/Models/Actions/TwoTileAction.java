package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;
import Models.Tile;
import Models.Tile.TileType;

public class TwoTileAction extends RotatableComponentAction {
	

	public TwoTileAction(int actionID, int x, int y, int rotationState) {
		super(actionID, x, y, rotationState);
		System.out.println("Two Tile constructor end");
		this.imageKey = "twoTile";
	}
	
	public TwoTileAction() {

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

	@Override
	public boolean redo(GameModel game) {
		if(game.getBoard().placeTile(x, y, new Tile(TileType.twotile, rotationState), game.getCurrentPlayer())) {
			game.getCurrentPlayer().decrementTwo();
			return true;
		}
		return false;
	}
		
}
