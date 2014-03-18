package Models;

import Helpers.Json;
import Helpers.JsonObject;

public class Tile implements Serializable<Tile> {
	private String[][] tileCells;
	private int numberCells;


	public Tile(int numberCells) {

		
		this.numberCells = numberCells;

		tileCells = new String[3][3];

		createTile();
	}

	public String[][] getTileCells() {
		return tileCells;
	}

	public void setTileSpaces(String[][] tileCells) {
		this.tileCells = tileCells;
	}
	
	public String getType(){
		int numberOfCell = 0;

		for(int i = 0; i < tileCells.length; i++)
			for(int j = 0; j < tileCells[i].length; i++)
				if(tileCells[i][j] != null)
					numberOfCell++;			
		
				if(numberOfCell == 2){
					return "two";
				}else if(numberOfCell == 3){
					return "three";
				}else{
					return "blank";
				}
	
	}
	private void createTile(){
		if(numberCells == 3){

			tileCells[0][1] = "village";
			tileCells[0][0] = "rice";
			tileCells[1][1] = "rice";
		
		}else if(numberCells == 2){
			tileCells[0][1] = "village";
			tileCells[0][0] = "rice";


		}
	}

	@Override
	public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("numberCells", Json.jsonValue(numberCells + "")),
			Json.jsonPair("tileCells", Json.serializeArray(tileCells))
		));
	}

	@Override
	public Tile loadObject(JsonObject json) {
		tileCells = (String[][]) json.getObject("tileCells");
		numberCells = Integer.parseInt(json.getString("numberCells"));
		return this;
	}
}
