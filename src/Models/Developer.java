package Models;

import Helpers.Json;
import Helpers.JsonObject;

public class Developer implements Serializable<Developer>
{
   private Cell location;
   private JavaPlayer owner;
   
   public Developer(JavaPlayer p)
   {
      //developer not on the board yet, so location is null
      location = null;
      owner = p;
   }
   
   public void setLocation(Cell c)
   {
      location = c;
   }
   
   public void setOwner(JavaPlayer p)
   {
      owner = p;
   }
   
   public Cell getLocation()
   {
      return location;
   }
   
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
		location = ((Cell[][]) json.getObject("map"))[Integer.parseInt(json.getString("x"))][Integer.parseInt(json.getString("y"))];
		// TODO check to make sure player is getting set
		return this;
	}
}