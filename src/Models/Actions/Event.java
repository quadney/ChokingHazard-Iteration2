package Models.Actions;

import Models.GameModel;

public abstract class Event {

	public Event() {
		// TODO Auto-generated constructor stub
	}
	
	//Undo and Redo methods---------------------------------------------------------
	
	public abstract void undo(GameModel game);

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
