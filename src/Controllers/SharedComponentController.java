package Controllers;

import Models.SharedComponentModel;
import Views.SharedComponentPanel;

public class SharedComponentController {
	private SharedComponentModel sharedModel;
	private SharedComponentPanel sharedPanel;

	public SharedComponentController(){
		this.sharedModel = new SharedComponentModel();
		this.sharedPanel = new SharedComponentPanel();
	}
	
	public SharedComponentPanel getSharedComponentPanel(){
		return this.sharedPanel;
	}

	//called if the player has selected a palace tile.
	//checks to see if there enough palace tiles to allow this to happen
	//if it does, tells the main controller true [doesn't change values in the model yet]
	//if else, false
	public boolean selectPalaceTile(int value) {
		return sharedModel.hasPalaceTile( value );
	}
	
	//called if the player has selected a three piece tile.
	//checks to see if there enough three piece tiles to allow this to happen
	//if it does, tells the main controller true [doesn't change values in the model yet]
		//if else, false
	public boolean selectThreeTile(){
		return sharedModel.hasThreeTile();
	}
	
	//called if the player has selected a irrigation tile.
	//checks to see if there enough irrigation tiles to allow this to happen
	//if it does, tells the main controller true [doesn't change values in the model yet]
	//if else, false
	public boolean selectIrrigationTile(){
		return sharedModel.hasIrrigationTile();
	}

	
	
	
}
