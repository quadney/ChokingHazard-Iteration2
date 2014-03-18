package Models.Actions;

public abstract class NonRotatableComponentAction extends Action {

	public int x;
	public int y;
	public NonRotatableComponentAction(int actionID, int famePointsEarned, int actionPointsCost, int x, int y) {
		super(actionID, famePointsEarned, actionPointsCost);
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}

}
