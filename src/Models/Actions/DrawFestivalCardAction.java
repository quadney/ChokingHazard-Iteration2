package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;

public class DrawFestivalCardAction extends Action {

	int value;
	public DrawFestivalCardAction(int actionID) {
		super(actionID);
		//TODO image key 
	}
	
	public DrawFestivalCardAction() {

	}
	
	@Override
	public Action loadObject(JsonObject json) {
		return new DrawFestivalCardAction(Integer.parseInt(json.getString("actionID")));
	}

	@Override
	public boolean redo(GameModel game) {
		game.drawFestivalCard();
		return true;
	}

}
