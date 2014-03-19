package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class DrawPalaceCardAction extends Action {

	public DrawPalaceCardAction(int actionID) {
		super(actionID);
		//TODO image key
	}
	
	public DrawPalaceCardAction() {

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
		// TODO Auto-generated method stub
		return false;
	}

}
