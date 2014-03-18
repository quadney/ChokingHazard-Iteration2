package Models.Actions.MActions;

import Models.Developer;
import Models.Actions.Action;

public abstract class SelectTabThroughDevelopersAction extends MAction {

	int tabCount = 0;
	int numOfDevelopers;
	Developer[] developersArray;

	public SelectTabThroughDevelopersAction(String imageKey, Developer[] developersArray) {
		super(imageKey);
		this.numOfDevelopers = developersArray.length;
		this.developersArray = developersArray;
	}

	
	public boolean pressSpace(){
		return false;
	}
	
	public boolean pressArrow(int xChange, int yChange){
		return false;
	}
	
	public boolean pressTab(){
		if(numOfDevelopers > 0){
			tabCount += 1 % numOfDevelopers;
			this.x = getXOfDeveloperAtIndexTabCount();
			this.y = getYOfDeveloperAtIndexTabCount();
			return true;
		}
		return false;
	}
	
	public MAction pressDelete(){
		//this creates a new select action 
		return null;
	}
	
	public Action pressEnter(){
		return null;
	}
	
	private int getXOfDeveloperAtIndexTabCount(){
		return developersArray[tabCount].getX();
	}
	
	private int getYOfDeveloperAtIndexTabCount(){
		return developersArray[tabCount].getY();
	}
	
	
	
	
}
