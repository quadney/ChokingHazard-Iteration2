package Models;

import Helpers.Json;

import java.util.*;

import Helpers.JsonObject;

public class JavaPlayer implements Serializable<JavaPlayer> {
	
	static int testingActionPoints = 6;
	
	public String name;	 
	private String color;
	private int famePoints;
	private int actionPoints;
	private int numOneRiceTile;
	private int numOneVillageTile;
	private int numTwoTile;
	private int numActionTokens;
	private ArrayList<PalaceCard> palaceCards;
	private ArrayList<JavaCell> palacesInteractedWith;
	private Developer[] developersArray;
	private boolean hasPlacedLandTile;
	private boolean hasUsedActionToken;

	public JavaPlayer(String name, String color) {
		this.name = name;
		this.color = color;
		this.actionPoints = testingActionPoints;
		this.famePoints = 0;
		this.numOneRiceTile = 3;
		this.numOneVillageTile = 2;
		this.numTwoTile = 5;
		this.numActionTokens = 3;
		this.palaceCards = new ArrayList<PalaceCard>();
		this.hasPlacedLandTile = false;
		this.hasUsedActionToken = false;
		this.developersArray = new Developer[12];
		this.palacesInteractedWith = new ArrayList<JavaCell>();
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

	// Tabs through the collection of developers on the board. If the index is
	// greater than the
	// number of developers, the first developer in the list becomes selected

	public boolean associateDeveloperWithCell(JavaCell jc) {
	
		int index = indexWithNoDeveloper();
		if(index > -1){
			developersArray[indexWithNoDeveloper()].setLocation(jc);
			jc.setDeveloper();
			return true;
		}
		return false;
	}
	
	private int indexWithNoDeveloper(){
		for(int i = 0; i < developersArray.length; i++){
			if(developersArray[i] == null)
				return i;
		}
		return -1;
	}

	public boolean decrementNActionPoints(int n, boolean isLandTile) {
		if (getAvailableActionPoints(isLandTile) >= n) {
			actionPoints -= n;
			return true;
		}

		return false;
	}

	public void removeDeveloperAtXY(int x, int y) {
		for(int i = 0; i < developersArray.length; i++){
			if(developersArray[i] != null){
				if(developersArray[i].getX() == x && developersArray[i].getY() == y){
					developersArray[i] = null;
				}
			}
		}
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

	// 
	public boolean endPlayerTurn() {
		if(hasPlacedLandTile){
			System.out.println("Player has placed land tile and is now resetting their state");
			hasPlacedLandTile = false;
			hasUsedActionToken = false;
			palacesInteractedWith.clear();
			actionPoints = testingActionPoints;
			return true;
		}
		return false;
		
	}
	
	public boolean canPlaceDeveloperOnBoard() {
		int index = getNextAvailableDeveloperSpot();
		return index != -1;
	}
	
	public boolean canEndTurn() {
		return hasPlacedLandTile;
	}
	
	public boolean cellInPalacesInteractedWith(JavaCell cell) {
		return palacesInteractedWith.contains(cell);
	}
	
	public boolean placeDevOnBoard(JavaCell location){
		int num = getNextAvailableDeveloperSpot();
		developersArray[num] = new Developer(this);
		developersArray[num].setLocation(location);
		return true;
	}
	
	public boolean moveDeveloperAroundBoard(JavaCell origin, JavaCell newLocation, int actionPointsCost) {
		
		for(Developer d: developersArray){
			if(d.moveDeveloperIfOnThisXY(origin, newLocation)){
				return decrementNActionPoints(actionPointsCost, false);
			}
		}
		return false;
	}
	
	private int getNextAvailableDeveloperSpot(){
		for(int i = 0; i < developersArray.length; i++){
			if(developersArray[i] == null){
				return i;
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
						Json.serializeArray(developersArray)),  Json
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

	public boolean hasDeveloperOnXY(int x, int y) {
		for(int i = 0; i < developersArray.length; i++){
			if(developersArray[i] != null && developersArray[i].isOnThisXY(x,y)){
				return true;
			}
		}
		return false;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
