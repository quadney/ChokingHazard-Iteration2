package Models.Actions.MActions;

import Models.Actions.Action;
import Models.Actions.ThreeTileAction;

public class SelectThreeTileAction extends SelectRotatableComponentAction{

	
	public SelectThreeTileAction(String imageKey) {
		super(imageKey);
		
	}
	
	protected boolean isRotatableComponentOnBoard(int x, int y, int rotationState) {
		if(rotationState == 0){
			if((x < 0 || x > 12) || (y < 0 || y > 12)){ //check x changes
				//System.out.println("(in SelectThreeTileAction)New 3 tile center is not on board. State " + rotationState + " center " + x + "," + y);
				return false;
			}
		}
		else if(rotationState == 1){
			if((x < 1 || x > 13) || (y < 0 || y > 12)){ //check x changes
				//System.out.println("(in SelectThreeTileAction)New 3 tile center is not on board. State " + rotationState + " center " + x + "," + y);
				
				return false;
			}
			
		}
		else if(rotationState == 2){
			if((x < 1 || x > 13) || (y < 1 || y > 13)){ //check x changes
				//System.out.println("(in SelectThreeTileAction)New 3 tile center is not on board. State " + rotationState + " center " + x + "," + y);
				
				return false;
			}
		}
		else if(rotationState == 3){
			if((x < 0 || x > 12) || (y < 1 || y > 13)){ //check x changes
				//System.out.println("(in SelectThreeTileAction)New 3 tile center is not on board. State " + rotationState + " center " + x + "," + y);
				
				return false;
			}
		}
		else{
			return false;
		}
		return true;
	}

	@Override
	public Action pressEnter() {
		return new ThreeTileAction(-1, null, null, 0, x, y, rotationState, false);
	}
}
