package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class OneSpaceTileAction extends OneSpaceComponentAction {

	public OneSpaceTileAction(int actionID, int playerIndex) {
		super(actionID, playerIndex);
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
		// TODO Auto-generated method stub
		
	}
	
}
   