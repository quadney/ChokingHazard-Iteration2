package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;
import Models.JavaCell;

public class OneSpaceTileAction extends OneSpaceComponentAction {

	public int x;
	public int y;
	public JavaCell cell;

	public OneSpaceTileAction(int actionID, int x, int y) {
		super(actionID);
		this.x = x; 
		this.y = y;
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
		
	}
	
}
   