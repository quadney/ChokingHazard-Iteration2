package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class ThreeTileAction extends RotatableComponentAction{

	public ThreeTileAction(int actionID, int playerIndex, int rotationState) {
		super(actionID, playerIndex, rotationState);
		// TODO Auto-generated constructor stub
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
