package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;
import Models.JavaCell;
import Models.Tile;

public class TwoTileAction extends RotatableComponentAction {
	

	public TwoTileAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned, int x, int y,	int rotationState, int elevation, JavaCell[] cell) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned, x, y, rotationState, elevation, cell);
		System.out.println("Two Tile constructor end");
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
	public void undo(GameModel game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo(GameModel game) {
		game.placeTileOnBoard(x, y, new Tile(2));
	}
		
}
