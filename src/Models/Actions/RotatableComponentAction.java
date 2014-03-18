package Models.Actions;

public abstract class RotatableComponentAction extends Action {
	int rotationState = 0;
	protected int x; 
	protected int y;

	public RotatableComponentAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned, int x, int y, int rotationState) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned);
		this.x = x; 
		this.y = y;
		this.rotationState = rotationState;
	}

	public int getRotationState(){
		return rotationState;
	}
}