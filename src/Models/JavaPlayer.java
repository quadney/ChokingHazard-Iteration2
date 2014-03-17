package Models;
import Helpers.Json;

import java.util.*;

import Helpers.JsonObject;

public class JavaPlayer extends Player implements Serializable<JavaPlayer>{
	private int famePoints;
	private int actionPoints;
	private int developersOffBoard;
	//private int developersOnBoard;
	private int numOneRiceTile;
	private int numOneVillageTile;
	private int numTwoTile;
	private int numActionTokens;
	private ArrayList<PalaceCard> palaceCards;
    private Developer[] developersOnBoard;
    private int selectedDeveloperIndex;
	private Developer[] developerArray;
	public int currentlySelectedDeveloper;
	private boolean hasPlacedLandTile;
	private boolean hasUsedActionToken;
	
	public JavaPlayer(String name, String color){
		super(name, color);
		this.famePoints = 0;
		this.actionPoints = 6;
		this.developersOffBoard = 12;
		this.numOneRiceTile = 3;
		this.numOneVillageTile = 2;
		this.numTwoTile = 5;
		this.numActionTokens = 3;
      	this.palaceCards = new ArrayList<PalaceCard>();
      	this.hasPlacedLandTile = false;
        this.developersOnBoard = new Developer[12];
        this.selectedDeveloperIndex = 0;
        this.hasUsedActionToken = false;
	}
   
	public int getFamePoints() {
		return famePoints;
	}
	public int getActionPoints() {
		return actionPoints;
	}
	public int getDevelopersOffBoard() {
		return developersOffBoard;
	}
	public int getNumOneRiceTile() {
		return numOneRiceTile;
	}
	public int getNumOneVillageTile() {
		return numOneVillageTile;
	}
	public int getNumTwoTile() {
		return numTwoTile;
	}
	public int getNumActionTokens() {
		return numActionTokens;
	}
	public ArrayList<PalaceCard> getPalaceCards(){
		return palaceCards;
	}
	
	public int getAvailableActionPoints(boolean isLandTile) {
		if (hasPlacedLandTile || isLandTile) {
			return actionPoints;
		}
		
		return actionPoints - 1;
	}
	
	public void changeFamePoints(int modifier){
		famePoints += modifier;
	}
   
   public Developer[] getDevelopersOnBoard()
   {
      return developersOnBoard;
   }
   
   //Returns the selected developer if there is a valid option (Developers on the board)
   public Developer getSelectedDeveloper()
   {
      return developersOnBoard[selectedDeveloperIndex];
   }
   
   //Tabs through the collection of developers on the board. If the index is greater than the
   //number of developers, the first developer in the list becomes selected
   public void tabThroughDevelopers()
   {
	  int count = 0;
	  while (count < developersOnBoard.length) {
		  selectedDeveloperIndex++;
		  if(selectedDeveloperIndex >= developersOnBoard.length) {
		     selectedDeveloperIndex = 0;
	  	  }
		  
		  if (developersOnBoard[selectedDeveloperIndex] != null) {
			  break;
		  }
		  count++;
	  }
   }
	
	public boolean decrementNActionPoints(int n, boolean isLandTile) {
		if (getAvailableActionPoints(isLandTile) >= n) {
			actionPoints -= n;
			return true;
		}
		
		return false;
	}
	
	public void removeDeveloperFromArray()
	{
		developerArray[currentlySelectedDeveloper] = null;
	}
	
	public void addPalaceCard(PalaceCard card){
		this.palaceCards.add(card);
	}

	//Methods needed from Player controller to validate action selections-----------------------------------
	public boolean canUsePalace() { //checks if the player has the AP
		return getAvailableActionPoints(false) > 0;
	}

	public boolean canUseRice() { //checks if the player has enough plus has the AP
		return numOneRiceTile > 0 && getAvailableActionPoints(true) > 0;
	}

	public boolean canUseThree() { //checks if the player has the AP
		return getAvailableActionPoints(false) > 0;
	}

	public boolean canUseTwo() { //checks if the player has enough plus has the AP
		return numTwoTile > 0 && getAvailableActionPoints(true) > 0;
	}

	public boolean canUseActionToken() { //checks if the player has not used an action token yet
		return !hasUsedActionToken;
	}

	public boolean canUseIrrigation() { //checks if the player has the AP
		return getAvailableActionPoints(false) > 0;
	}

	public boolean canUseVillage() { //checks if the player has enough plus has the AP
		return numOneVillageTile > 0 && getAvailableActionPoints(true) > 0;
	}

	public boolean canEndTurn() { //checks if the player has placed a land tile
		return hasPlacedLandTile;
	}
	
	//---------------------------------------------------------------------------------------------------------

	@Override
	public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
			Json.jsonPair("name", Json.jsonValue(name + "")),
			Json.jsonPair("color", Json.jsonValue(color + "")),
			Json.jsonPair("famePoints", Json.jsonValue(famePoints + "")),
			Json.jsonPair("actionPoints", Json.jsonValue(actionPoints + "")),
			Json.jsonPair("numOneRiceTile", Json.jsonValue(numOneRiceTile + "")),
			Json.jsonPair("numOneVillageTile", Json.jsonValue(numOneVillageTile + "")),
			Json.jsonPair("numTwoTile", Json.jsonValue(numTwoTile + "")),
			Json.jsonPair("numActionTokens", Json.jsonValue(numActionTokens + "")),
			Json.jsonPair("developersOffBoard", Json.jsonValue(developersOffBoard + "")),
			Json.jsonPair("palaceCards", Json.serializeArray(palaceCards)),
			Json.jsonPair("developersOnBoard", Json.serializeArray(developersOnBoard)),
			Json.jsonPair("developerArray", Json.serializeArray(developerArray)),
			Json.jsonPair("selectedDeveloperIndex", Json.jsonValue(selectedDeveloperIndex + "")),
			Json.jsonPair("currentlySelectedDeveloper", Json.jsonValue(currentlySelectedDeveloper + "")),
			Json.jsonPair("hasPlacedLandTile", Json.jsonValue(hasPlacedLandTile + "")),
			Json.jsonPair("hasUsedActionToken", Json.jsonValue(hasUsedActionToken + ""))
		));
	}

	@Override
	public JavaPlayer loadObject(JsonObject json) {
		this.name = json.getString("name");
		this.color = json.getString("color");
		this.famePoints = Integer.parseInt(json.getString("famePoints"));
		this.actionPoints = Integer.parseInt(json.getString("actionPoints"));
		this.numOneRiceTile = Integer.parseInt(json.getString("numOneRiceTile"));
		this.numOneVillageTile = Integer.parseInt(json.getString("numOneVillageTile"));
		this.numTwoTile = Integer.parseInt(json.getString("numTwoTile"));
		this.numActionTokens = Integer.parseInt(json.getString("numActionTokens"));
		this.developersOffBoard = Integer.parseInt(json.getString("developersOffBoard"));
		this.selectedDeveloperIndex = Integer.parseInt(json.getString("selectedDeveloperIndex"));
		this.currentlySelectedDeveloper = Integer.parseInt(json.getString("currentlySelectedDeveloper"));
		this.hasPlacedLandTile = Boolean.parseBoolean(json.getString("hasPlacedLandTile"));
		this.hasUsedActionToken = Boolean.parseBoolean(json.getString("hasUsedActionToken"));
		
		this.palaceCards = new ArrayList<PalaceCard>();
		for(JsonObject obj : json.getJsonObjectArray("palaceCards"))
			this.palaceCards.add((new PalaceCard(-1)).loadObject(obj));

		this.developersOnBoard = new Developer[json.getJsonObjectArray("developersOnBoard").length];
		for(int x = 0; x < this.developersOnBoard.length; ++x) 
			this.developersOnBoard[x] = (new Developer(this)).loadObject(json.getJsonObjectArray("developersOnBoard")[x]);
		
		this.developerArray = new Developer[json.getJsonObjectArray("developerArray").length];
		for(int x = 0; x < this.developerArray.length; ++x) 
			this.developerArray[x] = (new Developer(this)).loadObject(json.getJsonObjectArray("developerArray")[x]);

		return this;
	}
}
