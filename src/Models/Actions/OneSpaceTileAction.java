package Models.Actions;

import Helpers.JsonObject;
import Models.BoardModel;
import Models.GameModel;
import Models.JavaCell;
import Models.Tile;

public class OneSpaceTileAction extends OneSpaceComponentAction {

	public int x;
	public int y;
	public JavaCell cell;

	public OneSpaceTileAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned, int x, int y, JavaCell cell) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned);
		this.x = x; 
		this.y = y;
		this.cell = cell;
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
		
	}
	
}
   