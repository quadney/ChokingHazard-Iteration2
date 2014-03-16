package Models.Actions.MActions;

import Helpers.Json;

public class SelectPalaceTileAction extends SelectOneSpaceTileAction {

	
	int value;
	
	public SelectPalaceTileAction(String imageKey, int value) {
		super(imageKey);
		this.value = value;
	}

	public int getValue(){
		return value;
	}

	@Override
	public String serialize() {
		return Json.jsonPair("SelectTwoTileAction", Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("x", Json.jsonValue(x + "")),
			Json.jsonPair("y", Json.jsonValue(y + "")),
			Json.jsonPair("value", Json.jsonValue(value + "")),
			Json.jsonPair("imageKey", Json.jsonValue(imageKey))
		)));
	}

}
