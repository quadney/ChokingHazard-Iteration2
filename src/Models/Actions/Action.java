package Models.Actions;

import Helpers.Json;
import Helpers.JsonObject;
import Models.Serializable;

public abstract class Action extends Event implements Serializable<Action> {

	public final int actionID;
	public String imageKey;
	

	public Action() {
		this.actionID = -1;
	}
	
	public Action(int actionID) {
		this.actionID = actionID;
	}
	
	public int getActionID() {
		return actionID;
	}
	
	public String serialize() {
		return Json.jsonObject(Json.jsonElements(
			Json.jsonPair("actionID", this.actionID + ""), 
			Json.jsonPair("imageKey", this.imageKey),
			Json.jsonPair("actionType", this.getClass().getSimpleName())
		));
	}
	
	public static Action loadAction(JsonObject json) {
		String actionType = json.getString("actionType");
		Action action = null;
		if(actionType.equals("TwoTileAction"))
			action = (new TwoTileAction()).loadObject(json);
		if(actionType.equals("ThreeTileAction"))
			action = (new ThreeTileAction()).loadObject(json);
		if(actionType.equals("RiceTileAction"))
			action = (new RiceTileAction()).loadObject(json);
		if(actionType.equals("IrrigationTileAction"))
			action = (new IrrigationTileAction()).loadObject(json);
		if(actionType.equals("PalaceTileAction"))
			action = (new PalaceTileAction()).loadObject(json);
		if(actionType.equals("VillageTileAction"))
			action = (new VillageTileAction()).loadObject(json);
		if(actionType.equals("MoveDeveloperAction"))
			action = (new MoveDeveloperAction()).loadObject(json);
		if(actionType.equals("PlaceDeveloperOnBoardAction"))
			action = (new PlaceDeveloperOnBoardAction()).loadObject(json);
		if(actionType.equals("TakeDeveloperOffBoardAction"))
			action = (new TakeDeveloperOffBoardAction()).loadObject(json);
		if(actionType.equals("UseActionTokenAction"))
			action = (new UseActionTokenAction()).loadObject(json);
		if(actionType.equals("EndTurnAction"))
			action = (new EndTurnAction()).loadObject(json);
		if(actionType.equals("DrawPalaceCardAction"))
			action = (new DrawPalaceCardAction()).loadObject(json);
		if(actionType.equals("DrawFestivalCardAction"))
			action = (new DrawPalaceCardAction()).loadObject(json);
		return action;
	}
}
