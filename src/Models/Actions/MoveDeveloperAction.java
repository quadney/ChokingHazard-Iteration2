package Models.Actions;

import java.util.LinkedList;

import Helpers.JsonObject;
import Models.GameModel;
import Models.JavaCell;

public class MoveDeveloperAction extends NonRotatableComponentAction {
	
	LinkedList<JavaCell> path;

	public MoveDeveloperAction(int actionID, int x, int y, LinkedList<JavaCell> path) {
		super(actionID, path.peekFirst().getX(), path.peekFirst().getY());
		this.path = path;
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
		
		return false;
	}

}
