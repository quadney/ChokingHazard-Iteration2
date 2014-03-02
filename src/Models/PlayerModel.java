package Models;

public class PlayerModel {
	private String name;
	private String color;
	private int famePoints;
	private int actionPoints;
	private int developersOffBoard;
	//private int developersOnBoard;
	private int numOneRiceTile;
	private int numOneVillageTile;
	private int numTwoTile;
	private int numActionTokens;
	//private ArrayList<FestivalCard> festivalCards;
	
	public PlayerModel(String name, String color){
		this.name = name;
		this.color = color;
		this.famePoints = 0;
		this.actionPoints = 6;
		this.developersOffBoard = 12;
		this.numOneRiceTile = 3;
		this.numOneVillageTile = 2;
		this.numTwoTile = 5;
		this.numActionTokens = 3;
		//this.festivalCards = new ArrayList<FestivalCard>;
	}

	public String getName() {
		return name;
	}
	public String getColor() {
		return color;
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
}
