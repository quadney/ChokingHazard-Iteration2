package Models.Actions;

public abstract class RotatableComponentAction extends Action {

	int rotationState = 0;
	
	public RotatableComponentAction(int actionID, int playerIndex, int rotationState) {
		super(actionID, playerIndex);
		this.rotationState = rotationState;
	}

	public int getRotationState(){
		return rotationState;
	}
}