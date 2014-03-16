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
	private boolean hasPlacedLandTile;
	//private ArrayList<FestivalCard> festivalCards;
	
	public JavaPlayer(String name, String color){
		super(name, color);
		this.famePoints = 0;
		this.actionPoints = 6;
		this.developersOffBoard = 12;
		this.numOneRiceTile = 3;
		this.numOneVillageTile = 2;
		this.numTwoTile = 5;
		this.numActionTokens = 3;
		this.hasPlacedLandTile = false;
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
	
	public void changeFamePoints(int modifier){
		famePoints += modifier;
	}

	public boolean canUsePalace() {
		return ( ( this.hasPlacedLandTile && this.actionPoints >= 1 ) || this.actionPoints >= 2 );
	}

	public boolean canUseRice() {
		// TODO Auto-generated method stub
		return false;
	}
}
