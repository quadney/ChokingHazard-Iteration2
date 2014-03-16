package Models.Actions;

public class SelectThreeTileAction extends SelectRotatableTileAction{

	
	public SelectThreeTileAction(String imageKey) {
		super(imageKey);
		
	}
	
	public boolean pressArrow(int xChange, int yChange){
		//move around most of the board, depending on the rotation state
		return false;
	}


	
	
	
	
}
