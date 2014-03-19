package Models;

import Helpers.Json;

import java.util.*;

import Helpers.JsonObject;
import Views.GameContainerPanel;

public class JavaPlayer implements Serializable<JavaPlayer> {
	public String name;
	private String color;
	private int famePoints;
	private int actionPoints;
	private int numOneRiceTile;
	private int numOneVillageTile;
	private int numTwoTile;
	private int numActionTokens;
	private ArrayList<PalaceCard> palaceCards;
	private int selectedDeveloperIndex;
	// private int[][] palacesInteractedWith;
	private Developer[] developersArray;
	private boolean hasPlacedLandTile;
	private boolean hasUsedActionToken;

	public JavaPlayer(String name, String color) {
		this.name = name;
		this.color = color;
		this.famePoints = 0;
		this.actionPoints = 6;
		this.numOneRiceTile = 3;
		this.numOneVillageTile = 2;
		this.numTwoTile = 5;
		this.numActionTokens = 3;
		this.palaceCards = new ArrayList<PalaceCard>();
		this.hasPlacedLandTile = false;
		this.selectedDeveloperIndex = 0;
		this.hasUsedActionToken = false;
		this.developersArray = new Developer[12];
		// this accounts for the fact that a player can only interact with a
		// palace once per turn. Can either build or upgrade a palace
		// int[][] palacesInteractedWith = new int[40][2];
	}

	public String getName() {
		return this.name;
	}

	public String getColor() {
		return this.color;
	}

	public int getFamePoints() {
		return famePoints;
	}

	public int getActionPoints() {
		return actionPoints;
	}
	
	public boolean hasPlaceLandTile(){
		return hasPlacedLandTile;
	}
	
	public void placedLandTile(){
		hasPlacedLandTile = true;
	}
	
	public void resetPlacedLandTile(){
		hasPlacedLandTile = false;
	}

	public int getDevelopersOffBoard() {
		int developersOnBoard = 0;
		for (int i = 0; i < developersArray.length; i++) {
			if (developersArray[i] != null) {
				developersOnBoard++;
			}
		}

		return developersArray.length - developersOnBoard;
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

	public ArrayList<PalaceCard> getPalaceCards() {
		return palaceCards;
	}

	public int getAvailableActionPoints(boolean isLandTile) {
		if (hasPlacedLandTile || isLandTile) {
			return actionPoints;
		}

		return actionPoints - 1;
	}

	public void changeFamePoints(int modifier) {
		famePoints += modifier;
	}

	public Developer[] getDevelopersOnBoard() {
		ArrayList<Developer> temp = new ArrayList<Developer>();
		for (int i = 0; i < developersArray.length; i++) {
			if (developersArray[i] != null) {
				temp.add(developersArray[i]);
			}
		}

		return temp.toArray(new Developer[1]);
	}

	// Returns the selected developer if there is a valid option (Developers on
	// the board)
	public Developer getSelectedDeveloper() {
		return developersArray[selectedDeveloperIndex];
	}

	// Tabs through the collection of developers on the board. If the index is
	// greater than the
	// number of developers, the first developer in the list becomes selected
	public void tabThroughDevelopers() {
		int count = 0;
		while (count < developersArray.length) {
			selectedDeveloperIndex++;
			if (selectedDeveloperIndex >= developersArray.length) {
				selectedDeveloperIndex = 0;
			}

			if (developersArray[selectedDeveloperIndex] != null) {
				break;
			}

			count++;
		}
	}

	public void associateDeveloperWithCell(JavaCell jc) {
	
		developersArray[selectedDeveloperIndex].setLocation(jc);
		jc.setDeveloper();
	}

	public boolean decrementNActionPoints(int n, boolean isLandTile) {
		if (getAvailableActionPoints(isLandTile) >= n) {
			actionPoints -= n;
			return true;
		}

		return false;
	}

	public void removeDeveloperFromArray() {
		developersArray[selectedDeveloperIndex] = null;
	}

	public void addPalaceCard(PalaceCard card) {
		this.palaceCards.add(card);
	}
	
	public void removePalaceCard(PalaceCard cardToBeRemoved){
		this.palaceCards.remove(cardToBeRemoved);
	}

	// Methods needed from Player controller to validate action
	// selections-------------------------------
	// checks if the player has the AP
	public boolean canUsePalace() {
		return getAvailableActionPoints(false) > 0;
	}

	// TODO Make sure this works
	public boolean endTurn() {
		if (!hasPlacedLandTile) {
			GameContainerPanel.tellPeopleTheyAintPlacedNoLandTile();
			return false;
		}

		// Otherwise, typical end of turn activities
		changeFamePoints(1); // TODO: determine correct amount, method?
		return true;
	}

	// checks if the player has enough plus has the AP
	public boolean canUseRice() {
		return numOneRiceTile > 0 && getAvailableActionPoints(true) > 0;
	}

	// checks if the player has the AP
	public boolean canUseThree() {
		return getAvailableActionPoints(false) > 0;
	}

	// checks if the player has enough plus has the AP
	public boolean canUseTwo() {
		return numTwoTile > 0 && getAvailableActionPoints(true) > 0;
	}

	public boolean canUseActionToken() { //checks if the player has not used an action token yet
		return !hasUsedActionToken && numActionTokens > 0;
	}

	// checks if the player has the AP
	public boolean canUseIrrigation() {
		return getAvailableActionPoints(false) > 0;
	}

	// checks if the player has enough plus has the AP
	public boolean canUseVillage() {
		return numOneVillageTile > 0 && getAvailableActionPoints(true) > 0;
	}

	// checks if the player has placed a land tile
	public boolean canEndTurn() {
		return hasPlacedLandTile;
	}
	
	public boolean canPlaceDeveloperOnBoard() {
		int index = getNextAvailable();
		return index != -1;
		
		
	}
	
	public boolean placeDevOnBoard( JavaCell location){
		
		
			developersArray[selectedDeveloperIndex] = new Developer(this);
			associateDeveloperWithCell(location); 
		
		return false;
			
	}
	
	private int getNextAvailable(){
	
		for(int i = 0; i < developersArray.length; i++){
			if(developersArray[i] == null){
				selectedDeveloperIndex = i;
				return selectedDeveloperIndex;
			}
		}
		
		return -1;
	}
	
	//--------used for doAction in PlayerController----------------------------------------------------------------------
	
	public void incrementRice(){
		numOneRiceTile++;
	}
	
	public void incrementVillage(){
		numOneVillageTile++;
	}
	
	public void incrememntTwo(){
		numTwoTile++;
	}
	
	public void useActionToken(){
		hasUsedActionToken = true;
		numActionTokens--;
		actionPoints++;
	}
	
	public void decrementRice(){
		numOneRiceTile--;
	}
	
	public void decrementVillage(){
		numOneVillageTile--;
	}
	
	public void decrementTwo(){
		numTwoTile--;
	}
	
	public void unuseActionToken(){
		hasUsedActionToken = false;
		numActionTokens++;
		actionPoints--;
	}
	
	//----------------------------------------

	@Override
	public String serialize() {
		return Json.jsonObject(Json.jsonMembers(Json.jsonPair("name",
				Json.jsonValue(name + "")), Json.jsonPair("color",
				Json.jsonValue(color + "")), Json.jsonPair("famePoints",
				Json.jsonValue(famePoints + "")), Json.jsonPair("actionPoints",
				Json.jsonValue(actionPoints + "")), Json.jsonPair(
				"numOneRiceTile", Json.jsonValue(numOneRiceTile + "")), Json
				.jsonPair("numOneVillageTile",
						Json.jsonValue(numOneVillageTile + "")), Json.jsonPair(
				"numTwoTile", Json.jsonValue(numTwoTile + "")), Json.jsonPair(
				"numActionTokens", Json.jsonValue(numActionTokens + "")), Json
				.jsonPair("palaceCards", Json.serializeArray(palaceCards)),
				Json.jsonPair("developerArray",
						Json.serializeArray(developersArray)), Json.jsonPair(
						"selectedDeveloperIndex",
						Json.jsonValue(selectedDeveloperIndex + "")), Json
						.jsonPair("hasPlacedLandTile",
								Json.jsonValue(hasPlacedLandTile + "")), Json
						.jsonPair("hasUsedActionToken",
								Json.jsonValue(hasUsedActionToken + ""))));
	}

	@Override
	public JavaPlayer loadObject(JsonObject json) {
		this.name = json.getString("name");
		this.color = json.getString("color");
		this.famePoints = Integer.parseInt(json.getString("famePoints"));
		this.actionPoints = Integer.parseInt(json.getString("actionPoints"));
		this.numOneRiceTile = Integer
				.parseInt(json.getString("numOneRiceTile"));
		this.numOneVillageTile = Integer.parseInt(json
				.getString("numOneVillageTile"));
		this.numTwoTile = Integer.parseInt(json.getString("numTwoTile"));
		this.numActionTokens = Integer.parseInt(json
				.getString("numActionTokens"));
		this.selectedDeveloperIndex = Integer.parseInt(json
				.getString("selectedDeveloperIndex"));
		this.hasPlacedLandTile = Boolean.parseBoolean(json
				.getString("hasPlacedLandTile"));
		this.hasUsedActionToken = Boolean.parseBoolean(json
				.getString("hasUsedActionToken"));

		this.palaceCards = new ArrayList<PalaceCard>();
		for (JsonObject obj : json.getJsonObjectArray("palaceCards"))
			this.palaceCards.add((new PalaceCard(-1)).loadObject(obj));

		// TODO check if these are distinct or from developersOnBoard (or
		// vice-versa)
		this.developersArray = new Developer[json
				.getJsonObjectArray("developerArray").length];
		for (int x = 0; x < this.developersArray.length; ++x)
			this.developersArray[x] = (new Developer(this)).loadObject(json
					.getJsonObjectArray("developerArray")[x]);

		return this;
	}

	public int getNumPalaceCards() {
		return palaceCards.size();
	}

}
