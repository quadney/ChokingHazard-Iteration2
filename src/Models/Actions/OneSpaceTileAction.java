package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;
import Models.JavaCell;

public abstract class OneSpaceTileAction extends NonRotatableComponentAction {

	public JavaCell cell;

	public OneSpaceTileAction(int actionID, int x, int y) {
		super(actionID, x, y);
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
   