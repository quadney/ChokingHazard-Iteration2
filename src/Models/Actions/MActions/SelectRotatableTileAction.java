package Models.Actions.MActions;


public abstract class SelectRotatableTileAction extends MAction {

	int rotationState = 0;
	
	public SelectRotatableTileAction(String imageKey) {
		super(imageKey);
		//for testing only
		System.out.println("New SelectRotatableTileAction created. This message is in that class.");
		
	}

	public boolean pressSpace() {
		int newRotationState = (rotationState + 1)  % 4;
		if (isRotatableComponentOnBoard(x,y,newRotationState)){
			System.out.println("(in SelectRotatableTileAction)New Rotation changed from " + rotationState + " to " + newRotationState );
			rotationState = newRotationState;
			return true;
		}
		System.out.println("(in SelectRotatableTileAction)Rotation illegal. Did not switch from  " + rotationState +" to "+ newRotationState );
		return false;
	}
	
	public boolean pressArrow(int xChange, int yChange) {
		int newX = x + xChange;
		int newY = y + yChange;
		if(isRotatableComponentOnBoard(newX, newY, rotationState)){
			System.out.println("(in SelectRotatableTileAction)New location changed from" + x +"," + y+ " to " + newX +"," + newY );
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