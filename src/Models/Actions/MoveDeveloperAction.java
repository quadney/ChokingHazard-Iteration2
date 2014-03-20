package Models.Actions;

import java.util.LinkedList;

import Helpers.JsonObject;
import Models.GameModel;
import Models.JavaCell;

public class MoveDeveloperAction extends NonRotatableComponentAction {
	
	int originX;
	int originY;
	int actionPointsCost;

	public MoveDeveloperAction(int actionID, int x, int y, int originX, int originY, int actionPointsCost) {
		super(actionID, x, y);
		this.originX = originX;
		this.originY = originY;
		this.actionPointsCost = actionPointsCost;
	}
	
	public MoveDeveloperAction() {

	}

	@Override
	public Action loadObject(JsonObject json) {
		
		
		return null; //new MoveDeveloperAction(Integer.parseInt(json.getString("actionID")), 
//				Integer.parseInt(json.getString("x")), 
//				Integer.parseInt(json.getString("y")), path)));
		
	}

	@Override
	public boolean redo(GameModel game) {
		return game.moveDeveloperAroundBoard(originX, originY, x, y, actionPointsCost);
	}

}
