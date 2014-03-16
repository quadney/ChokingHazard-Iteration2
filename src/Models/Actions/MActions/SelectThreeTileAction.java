package Models.Actions.MActions;


public class SelectThreeTileAction extends SelectRotatableTileAction{

	
	public SelectThreeTileAction(String imageKey) {
		super(imageKey);
		
	}
	
	protected boolean isRotatableComponentOnBoard(int x, int y, int rotationState) {
		if(rotationState == 0){
			if((x < 0 || x > 12) && (y < 0 || y > 12)){ //check x changes
				return false;
			}
		}
		else if(rotationState == 1){
			if((x < 0 || x > 12) && (y < 0 || y > 12)){ //check x changes
				return false;
			}
			
		}
		else if(rotationState == 2){
			if((x < 1 || x > 13) && (y < 1 || y > 13)){ //check x changes
				return false;
			}
		}
		else if(rotationState == 3){
			if((x < 1 || x > 13) && (y < 0 || y > 12)){ //check x changes
				return false;
			}
		}
		else{
			return false;
		}
		return true;
	}


	
	
	
	
}