package Models.Actions;

import Helpers.Json;
import Helpers.JsonObject;
import Models.GameModel;
import Models.Tile;
import Models.Tile.TileType;

public class PalaceTileAction extends OneSpaceTileAction {
	
	int value;

	public PalaceTileAction(int actionID, int x, int y, int value) {
		super(actionID, x, y);
		this.value = value;
		this.imageKey = "palace" + value + "Tile";
	}
	
	public PalaceTileAction() {

	}

	public int getValueOfPalace() {
		return value;
	}
	
	@Override
	public boolean redo(GameModel game) {
		if(game.getBoard().placeTile(x, y, new Tile(TileType.valueOf("palace"+value), 0), game.getCurrentPlayer())) {
			game.getShared().decrementPalaceTiles(value);
			return true;
		}
		return false;
	}
	
	public String serialize() {
		return Json.jsonObject(Json.jsonElements(
			Json.jsonPair("actionID", this.actionID + ""), 
			Json.jsonPair("imageKey", this.imageKey),
			Json.jsonPair("x", this.x + ""),
			Json.jsonPair("y", this.y + ""),
			Json.jsonPair("value", this.value + ""),
			Json.jsonPair("actionType", this.getClass().getSimpleName())
		));
	}

	@Override
	public Action loadObject(JsonObject json) {
		return new PalaceTileAction(Integer.parseInt(json.getString("actionID")), 
				Integer.parseInt(json.getString("x")), 
				Integer.parseInt(json.getString("y")), 
				Integer.parseInt(json.getString("value")));
	}
}
