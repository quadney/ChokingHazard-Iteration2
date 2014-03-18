package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class DrawPalaceCardAction extends Action {

	public DrawPalaceCardAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned, int actionPointsCost) {
		super(actionID, famePointsEarned, actionPointsCost);
		//TODO image key
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
		// TODO Auto-generated method stub
		
	}

}
