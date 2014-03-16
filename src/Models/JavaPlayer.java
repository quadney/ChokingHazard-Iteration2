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
	private boolean placedLandTile;
	
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
      	this.placedLandTile = false;
        this.developersOnBoard = new Developer[12];
        selectedDeveloperIndex = 0;
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
	
	public int getAvailableActionPoints() {
		if (placedLandTile) {
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
	  
	
	public boolean hasPlacedLandTile() {
		return placedLandTile;
	}
	
	
	public boolean decrementNActionPoints(int n) {
		if (getAvailableActionPoints() >= n) {
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
	public boolean canUsePalace() {
		return ( ( this.hasPlacedLandTile() && this.actionPoints >= 1 ) || this.actionPoints >= 2 );
	}

	public boolean canUseRice() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean canUseThree() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean canUseTwo() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean canUseActionToken() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean canUseIrrigation() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean canUseVillage() {
		// TODO Auto-generated method stub
		return false;
	}
	//---------------------------------------------------------------------------------------------------------

	@Override
	public String serialize() {
		return Json.jsonObject(Json.jsonMembers(
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
				Json.jsonPair("placedLandTile", Json.jsonValue(placedLandTile + ""))
		));
	}

	@Override
	public JavaPlayer loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}
}
