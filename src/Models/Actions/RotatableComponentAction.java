package Models.Actions;

public abstract class RotatableComponentAction extends Action {
	int rotationState = 0;
	protected int x; 
	protected int y;
	protected int elevation; //Should be in a subclass called RotatableTileAction but whatever

	public RotatableComponentAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned, int x, int y, int rotationState, int elevation) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned);
		this.x = x; 
		this.y = y;
		this.rotationState = rotationState;
		this.elevation = elevation;
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
	
	public int getElevation(){
		return elevation;
	}
}