package Models;

import Helpers.Json;
import Helpers.JsonObject;

public class Developer implements Serializable<Developer>
{
   private JavaCell location;
   private JavaPlayer owner;
   
   public Developer(JavaPlayer p)
   {
      //developer not on the board yet, so location is null
      location = null;
      owner = p;
   }
   
   public void setLocation(JavaCell c)
   {
      location = c;
   }
   
   public void setOwner(JavaPlayer p)
   {
      owner = p;
   }
   
   public JavaCell getLocation()
   {
      return location;
   }
   
   //accessor methods needed for SeectTabThroughDeveloperAction------
   
   public int getX(){
	   return location.getX();
   }
   
   public int getY(){
	   return location.getY();
   }
   //------------------------------------------------------------------
   public JavaPlayer getOwner()
   {
      return owner;
   }

	@Override
	public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("x", Json.jsonValue(location.xVal + "")),
			Json.jsonPair("y", Json.jsonValue(location.yVal + "")),
			Json.jsonPair("owner", Json.jsonValue(owner.name + ""))
		));
	}
	
	@Override
	public Developer loadObject(JsonObject json) {
		location = ((JavaCell[][]) json.getObject("map"))[Integer.parseInt(json.getString("x"))][Integer.parseInt(json.getString("y"))];
		// TODO check to make sure player is getting set
		return this;
	}

	public boolean isOnThisXY(int x, int y) {
		return x == this.getX() && y == this.getY();
	}

	public boolean moveDeveloperIfOnThisXY(JavaCell origin, JavaCell newLocation) {
		if(location == origin){
			System.out.println("DevModel: sets new location");
			location = newLocation;
			origin.removeDeveloper();
			location.setDeveloper();
			return true;
		}
		return false;
	}
}