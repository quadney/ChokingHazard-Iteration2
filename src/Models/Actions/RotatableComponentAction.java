package Models.Actions;

public abstract class RotatableComponentAction extends Action {
	int rotationState = 0;
	protected int x; 
	protected int y;
	
	public RotatableComponentAction() {

	}

	public RotatableComponentAction(int actionID, int x, int y, int rotationState) {

		super(actionID);
		this.x = x; 
		this.y = y;
		this.rotationState = rotationState;
	}

	public int getRotationState(){
		return rotationState;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
}