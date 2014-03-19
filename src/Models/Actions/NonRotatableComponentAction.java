package Models.Actions;

public abstract class NonRotatableComponentAction extends Action {

	public int x;
	public int y;
	public NonRotatableComponentAction(int actionID, int x, int y) {
		super(actionID);
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
