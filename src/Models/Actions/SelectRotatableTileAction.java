package Models.Actions;

public abstract class SelectRotatableTileAction extends MAction {

	int rotationState = 0;
	
	public SelectRotatableTileAction(String imageKey) {
		super(imageKey);
		// TODO Auto-generated constructor stub
		
	}

	public void pressSpace(){
		rotationState += 1 % 4;
	}
	
	public int getRotationState(){
		return rotationState;
	}
	
	
}