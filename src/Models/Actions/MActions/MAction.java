package Models.Actions.MActions;
import Helpers.Json;
import Models.*;

/**
 * MAction, which is a 'momentary' action only used for selection
 * 
 * MAction and it's children are created by Meghan and Mauricio
 * 
 */
public abstract class MAction implements Serializable<MAction>{

	String imageKey;
	int x = 1;
	int y = 1;
	
	public MAction(String imageKey){
		this.imageKey = imageKey;
	}
	
	//Abstract Methods ---------------------------------------------//
	public abstract boolean pressSpace();
	
	public abstract boolean pressArrow(int xChange, int yChange);
	
	
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
	
	@Override
	public String serialize() {
		return Json.jsonPair("MAction", Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("x", Json.jsonValue(x + "")),
			Json.jsonPair("y", Json.jsonValue(y + "")),
			Json.jsonPair("imageKey", Json.jsonValue(imageKey))
		)));
	}
}
