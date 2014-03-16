package Models.Actions;
import Models.*;

/**
 * 
 * MAction, which is a momentary action only used for selection
 * 
 */
public abstract class MAction {

	String imageKey;
	int x = 1;
	int y = 1;
	
	public MAction(String imageKey){
		this.imageKey = imageKey;
	}
	

	public abstract void pressSpace();
	
	public abstract boolean pressArrow(int xChange, int yChange);
	
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
