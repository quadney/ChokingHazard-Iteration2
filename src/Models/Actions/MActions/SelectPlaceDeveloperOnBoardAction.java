package Models.Actions.MActions;

import Models.Actions.Action;
import Models.Actions.PlaceDeveloperOnBoardAction;

public class SelectPlaceDeveloperOnBoardAction extends SelectOneSpaceComponentAction {

	public SelectPlaceDeveloperOnBoardAction(String imageKey) {
		super(imageKey);
	}
	
	//used for the case that the player presses D and they attempt to place a developer on the board
	//can either show the developer the whole time, or can show a highlighted square and once they press enter
	//the developer will show up (if the move was valid)
	
	@Override
	public boolean isNonRotatableComponentOnBoard(int x, int y) {
		if((y > 1 && y < 12) && (x > 1 && x < 12)){ //check if changes in x are invalid
			System.out.println("if false. (x,y): " + x + "," + y);
			return false;
		}
		else if(x < 0 || x > 13){ //check if changes in x are invalid
			return false;
		}
		else if(y < 0 || y > 13){ //check if changes in y are invalid
			return false;
		}
		else{
			System.out.println("(In SelPlaDev) component on board valid");
			return true;
		}
	}

	@Override
	public Action pressEnter() {
		return new PlaceDeveloperOnBoardAction(x, y);
	}

	@Override
	public boolean pressTab() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pressDelete() {
		// TODO Auto-generated method stub
		return false;
	}

}
