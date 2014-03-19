package Models.Actions;

import Models.GameModel;

public abstract class Event {

	public Event() {
		// TODO Auto-generated constructor stub
	}
	
	//Undo and Redo methods---------------------------------------------------------
	
	public void undo(GameModel game) { 
		// TODO redo actions until the last action
	}

	public abstract void redo(GameModel game);
	
	public void doAction(GameModel game){
		redo(game);
	}

	public int getPlayerIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	//------------------------------------------------------------------------------
}
