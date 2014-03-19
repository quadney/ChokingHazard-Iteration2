package Models.Actions.MActions;

import Models.GameModel;
import Models.Actions.Action;

public class SelectMoveDeveloperAroundBoardAction extends SelectNonRotatableComponentAction {
	
	int originalX;
	int originalY;

	public SelectMoveDeveloperAroundBoardAction(String imageKey, int originalX, int originalY) {
		super(imageKey);
		this.x = originalX;
		this.y = originalY;
		this.originalX = originalX;
		this.originalY = originalY;
		
	}

	@Override
	public boolean isNonRotatableComponentOnBoard(int x, int y) {
		if(x < 0 || x > 13){ //check if changes in x are invalid
			return false;
		}
		else if(y < 0 || y > 13){ //check if changes in y are invalid
			return false;
		}
		else
			return true;
	}

	@Override
	public boolean pressTab() {
		return false;
	}
	
	public MAction pressM(){
		return null;
	}

	@Override
	public Action pressDelete() {
		return null;
	}

	@Override
	public Action pressEnter(GameModel game) {
		// TODO return move action
		return null;
	}

}
