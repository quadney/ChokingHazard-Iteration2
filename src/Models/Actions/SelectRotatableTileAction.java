package Models.Actions;

public abstract class SelectRotatableTileAction extends MAction {

	int rotationState;
	int x = 1;
	int y = 1;
	
	public SelectRotatableTileAction(int x, int y, int rotationState) {
		super();
		// TODO Auto-generated constructor stub
		
	}

	public void pressSpace(){
		rotationState += 1 % 4;
	}
	
	
}