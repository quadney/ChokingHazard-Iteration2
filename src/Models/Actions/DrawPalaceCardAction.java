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
	public Action loadObject(JsonObject json) {
		return new DrawPalaceCardAction(Integer.parseInt(json.getString("actionID")));
	}

	@Override
	public boolean redo(GameModel game) {
		// TODO Auto-generated method stub
		return false;
	}

}
