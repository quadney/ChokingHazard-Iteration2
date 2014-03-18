package Models.Actions.MActions;

public abstract class SelectOneSpaceComponentAction extends SelectNonRotatableComponentAction {

	public SelectOneSpaceComponentAction(String imageKey) {
		super(imageKey);
	}
	
	public boolean pressArrow(int xChange, int yChange){
		//assuming the size of the board is 14x14
		//OneSpace tiles have the ability to hovered over any space on the board
		//(does not mean that it is valid)
		System.out.println("(In Sel1CompAction) in pressArrow()");
		int newX = x + xChange;
		int newY = y + yChange;
		if(isNonRotatableComponentOnBoard(newX, newY)){
			System.out.println("(In Sel1CompAction) changes (x,y) " + x + "," +y + " to " + newX + "," + newY);
			x = newX;
			y = newY;
			return true;
			
		}
		return false;
	}


}
