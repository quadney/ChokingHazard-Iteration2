package Models;

public class JavaPlayer extends Player {
	private int famePoints;
	private int actionPoints;
	private int developersOffBoard;
	//private int developersOnBoard;
	private int numOneRiceTile;
	private int numOneVillageTile;
	private int numTwoTile;
	private int numActionTokens;
	private boolean placedThreeTile;
	//private ArrayList<FestivalCard> festivalCards;
	private Developer[] developerArray;
	public int currentlySelectedDeveloper;
	
	public JavaPlayer(String name, String color){
		super(name, color);
		this.famePoints = 0;
		this.actionPoints = 6;
		this.developersOffBoard = 12;
		this.numOneRiceTile = 3;
		this.numOneVillageTile = 2;
		this.numTwoTile = 5;
		this.numActionTokens = 3;
		//this.festivalCards = new ArrayList<FestivalCard>;
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
	
	public int getAvailableActionPoints() {
		if (placedThreeTile) {
			return actionPoints;
		}
		
		return actionPoints - 1;
	}
	
	public void changeFamePoints(int modifier){
		famePoints += modifier;
	}
	
	public boolean hasPlacedThreeTile() {
		return placedThreeTile;
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
}
