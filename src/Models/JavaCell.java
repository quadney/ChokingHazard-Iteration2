package Models;

import Helpers.Json;
import Helpers.JsonObject;
import Models.Tile.TileType;

public class JavaCell implements Serializable<JavaCell> {
   private int elevation;
   private String cellType;
   private boolean hasDeveLoper;
   private int cellId;
   public int xVal;
   public int yVal;
   public int numOriginalSpaces;
   
   public JavaCell(int x, int y, int cellId) {
	  this.xVal = x;
	  this.yVal = y;
	  elevation = 0;
	  cellType = "blank";
	  this.cellId = cellId;
	  this.numOriginalSpaces = 0;
   }

	public int getX() {
		return xVal;
	}

	public int getY() {
		return yVal;
	}
	
	public int getNumOriginalSpaces(){
		return numOriginalSpaces;
	}
   public int getCellId() {
      return cellId;
   }
   
   public void setCellId(int cellId) {
	   this.cellId = cellId;
   }
   
   public int getElevation() {
      return elevation;
   }
   
   public String getCellType() {
      return cellType;
   }

   
   public void setElevation(int e) {
      elevation = e;
   }
   
   public void setCellType(TileType tileCells) {
      cellType = tileCells.toString();
   }
   
   public void setDeveloper() {
	   this.hasDeveLoper = true;
   }
   
   public void removeDeveloper() {
	   this.hasDeveLoper = false;
   }
   
   public boolean hasDeveloper() {
	   return hasDeveLoper;
   }
   
   public boolean isBorder() {
	   
	   if (this.xVal == 13 || this.yVal == 13 || this.xVal == 0 || this.yVal == 0 )
		   return true;
	   return false;
   }
   
   public boolean isNextToBorder()
   {
	   if (this.xVal == 12 || this.yVal == 12 || this.xVal == 1 || this.yVal == 1 )
		   return true;
	   return false;
   }
   
   public int getActionPointsFromDeveloperMove()
   {
	   if (this.yVal > 6) //From lowlands
		   return 1;
	   else
		   return 2; //From mountains
   }
   
   //for testing purpose
   public String toString(){
	   
	   String s = "x: " + getX() + " y: " + getY();
	   
	   return s;
   }
   
	@Override
	public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("elevation", Json.jsonValue(elevation + "")),
			Json.jsonPair("cellType", Json.jsonValue(cellType + "")),
			Json.jsonPair("hasDeveLoper", Json.jsonValue(hasDeveLoper + "")),
			Json.jsonPair("cellId", Json.jsonValue(cellId + "")),
			Json.jsonPair("xVal", Json.jsonValue(xVal + "")),
			Json.jsonPair("yVal", Json.jsonValue(yVal + ""))
		));
	}
	
	@Override
	public JavaCell loadObject(JsonObject json) {
		elevation = Integer.parseInt(json.getString("elevation"));
		cellType = json.getString("cellType");
		hasDeveLoper = Boolean.parseBoolean(json.getString("hasDeveLoper"));
		cellId = Integer.parseInt(json.getString("cellId"));
		xVal = Integer.parseInt(json.getString("xVal"));
		yVal = Integer.parseInt(json.getString("yVal"));
		return this;
	}


	public void setNumOriginalSpaces(int numOfSpaces) {
		this.numOriginalSpaces = numOfSpaces;
	}
}