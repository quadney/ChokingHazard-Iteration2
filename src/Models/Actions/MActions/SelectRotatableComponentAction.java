package Models.Actions.MActions;

public abstract class SelectRotatableComponentAction extends MAction {

	int rotationState = 0;
	
	public SelectRotatableComponentAction(String imageKey) {
		super(imageKey);
	}
	
	public boolean pressTab(){
		return false;
	}
	
	public MAction pressDelete(){
		return null;
	}

	public boolean pressSpace() {
		int newRotationState = (rotationState + 1)  % 4;
		if (isRotatableComponentOnBoard(x,y,newRotationState)){
			rotationState = newRotationState;
			return true;
		}

		
		return false;
	}
	
	public boolean pressArrow(int xChange, int yChange) {
		int newX = x + xChange;
		int newY = y + yChange;
		if(isRotatableComponentOnBoard(newX, newY, rotationState)){
			x = newX;
			y = newY;
			return true;
		}
		else{
			return false;
		}
	}
	
	protected abstract boolean isRotatableComponentOnBoard(int x, int y, int rotationState);
	
	public int getRotationState(){
		return rotationState;
	}
}