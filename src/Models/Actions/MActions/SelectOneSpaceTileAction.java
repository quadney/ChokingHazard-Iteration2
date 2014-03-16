package Models.Actions.MActions;

import Helpers.JsonObject;

public class SelectOneSpaceTileAction extends SelectNonRotatableTileAction {
	
	public SelectOneSpaceTileAction(String imageKey) {
		super(imageKey);
		
	}
	
	public boolean pressArrow(int xChange, int yChange){
		//assuming the size of the board is 14x14
		//OneSpace tiles have the ability to hovered over any space on the board
		//(does not mean that it is valid)
		int newX = x + xChange;
		int newY = y + yChange;
		if(isNonRotatableComponentOnBoard(newX, newY)){
			x = newX;
			y = newY;
			return true;
		}
		return false;
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

	public SelectOneSpaceTileAction loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	
}