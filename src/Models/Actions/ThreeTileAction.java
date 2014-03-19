package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;
import Models.Tile;
import Models.Tile.TileType;

public class ThreeTileAction extends RotatableComponentAction {

	public boolean isFinalRound;

	public ThreeTileAction(int actionID, int x, int y, int rotationState) {
		super(actionID, x, y, rotationState);
		this.imageKey = "threeTile";
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
		if(game.getBoard().placeTile(x, y, new Tile(TileType.threetile, rotationState), game.getCurrentPlayer())) {
			game.getShared().decrementThreeSpaceTiles();
			return true;
		}
		return false;
	}
}
