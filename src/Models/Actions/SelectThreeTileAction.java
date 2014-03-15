package Models.Actions;

public class SelectThreeTileAction extends SelectRotatableTileAction{

	int rotationState = 0;
	
	public SelectThreeTileAction(int x, int y, int rotationState) {
		super(x, y, rotationState);
		// TODO Auto-generated constructor stub
		
	}

	public void pressSpace(){
		rotationState += 1 % 4;
	}
	
	public void pressArrow(int value){
		//move around most of the board, depending on the rotation state
	}


	
	
	
	
}
