package Models.Actions;

import Models.JavaCell;

public abstract class RotatableComponentAction extends Action {
	int rotationState = 0;
	protected int x; 
	protected int y;
	JavaCell cell[];
	
	public RotatableComponentAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned, int x, int y, int rotationState, JavaCell[] cell) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned);
		this.x = x; 
		this.y = y;
		this.rotationState = rotationState;
		this.cell = cell;
	}

	public int getRotationState(){
		return rotationState;
	}
}