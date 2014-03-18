package Models.Actions;

import Models.JavaCell;

public abstract class RotatableComponentAction extends Action {
	int rotationState = 0;
	protected int x; 
	protected int y;
	JavaCell cell[];
	protected int elevation; //Should be in a subclass called RotatableTileAction but whatever

	public RotatableComponentAction(int actionID, int famePointsEarned, int x, int y, int rotationState, int elevation, JavaCell[] cell) {

		super(actionID, famePointsEarned);
		this.x = x; 
		this.y = y;
		this.rotationState = rotationState;
		this.cell = cell;
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