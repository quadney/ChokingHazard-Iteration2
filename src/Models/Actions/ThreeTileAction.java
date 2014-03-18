package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;
import Models.JavaCell;
import Models.Tile;
import Models.Tile.TileType;

public class ThreeTileAction extends RotatableComponentAction {

	public boolean isFinalRound;

	public ThreeTileAction(int actionID, int famePointsEarned, int x, int y, int rotationState, boolean isFinalRound, int elevation, JavaCell[] cell) {
		super(actionID, famePointsEarned, x, y, rotationState, elevation,  cell);
		this.isFinalRound = isFinalRound;
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
	public void redo(GameModel game) {
		game.getBoard().placeTile(x, y, new Tile(TileType.threetile, rotationState), game.getCurrentPlayer());
	}
}
