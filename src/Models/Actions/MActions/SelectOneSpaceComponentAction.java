package Models.Actions.MActions;

public abstract class SelectOneSpaceComponentAction extends SelectNonRotatableComponentAction {

	public SelectOneSpaceComponentAction(String imageKey) {
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
}
