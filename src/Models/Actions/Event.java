package Models.Actions;

import Models.GameModel;
import Models.Serializable;

public abstract class Event implements Serializable<Action> {

	public Event() {
		// TODO Auto-generated constructor stub
	}
	
	//Undo and Redo methods---------------------------------------------------------
	
	public void undo(GameModel game) { 
		// TODO redo actions until the last action
	}

	public abstract boolean redo(GameModel game);
	
	public boolean doAction(GameModel game){
		return redo(game);
	}

	public int getPlayerIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	//------------------------------------------------------------------------------
}
