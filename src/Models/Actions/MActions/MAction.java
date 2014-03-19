package Models.Actions.MActions; 

import Models.GameModel;
import Models.Actions.Action;

/**
 * MAction, which is a 'momentary' action only used for selection
 * 
 * MAction and it's children are created by Meghan and Mauricio
 * 
 */
public abstract class MAction {

	String imageKey;
	int x = 1;
	int y = 1;
	
	public MAction(String imageKey){
		this.imageKey = imageKey;
	}
	
	//Abstract Methods ---------------------------------------------//
	public abstract boolean pressSpace();
	
	public abstract boolean pressArrow(int xChange, int yChange);
	
	public abstract boolean pressTab();
	
	public abstract Action pressDelete();
	
	public abstract Action pressEnter(GameModel game);
	
	public abstract MAction pressM();
	
	//Accessor Methods ---------------------------------------------//
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String getImageKey(){
		return imageKey;
	}
}
