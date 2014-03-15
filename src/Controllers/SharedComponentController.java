package Controllers;

import Models.SharedComponentModel;
import Views.SharedComponentPanel;

public class SharedComponentController {
	private SharedComponentModel sharedModel;
	private SharedComponentPanel sharedPanel;

	public SharedComponentController(){
		
	}
	
	public SharedComponentPanel getSharedComponentPanel(){
		return this.sharedPanel;
	}
	
	//called if the player has selected a three piece tile.
	//checks to see if there enough three piece tiles to allow this to happen
	//if it does, it changes the value in the mode and tells the panel to update
	public boolean useThreeTile(){
		if(sharedModel.useThreeTile()){
			sharedPanel.updateThreePieceTiles(sharedModel.getThreeSpaceTiles());
			return true;
		}
		return false;
	}
	
	//called if the player has pressed escape while having a three piece tile selected
	//tells the model to change to number of three tiles
	//tells the panel to update
	public void putBackThreeTile(){
		sharedModel.incrementThreeTile();
		sharedPanel.updateThreePieceTiles(sharedModel.getThreeSpaceTiles());
	}
	
	
	
	
	
	
}
