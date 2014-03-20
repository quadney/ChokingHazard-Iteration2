package Models.Actions.MActions;

import Models.Actions.Action;

public abstract class SelectOneSpaceTileAction extends SelectNonRotatableComponentAction {

	
	public SelectOneSpaceTileAction(String imageKey) {
		super(imageKey);
		
	}
	
	public boolean pressTab(){
		return false;
	}
	
	public Action pressDelete(){
		return null;
	}
	
	public MAction pressM(){
		return null;
	}
	
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
}
   