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

	public boolean selectPalaceTile(int value) {
		return sharedModel.hasPalaceTile( value );
	}

	public boolean hasPalaceTileValue(int value) {
		return sharedModel.hasPalaceTile( value );
	}
	
	//called if the player has placed a three piece tile.
	//checks to see if there enough three piece tiles to allow this to happen
	//if it does, it changes the value in the mode and tells the panel to update
	public boolean useThreeTile(){
		if(sharedModel.useThreeTile()){
			sharedPanel.updateThreePieceTiles(sharedModel.getThreeSpaceTiles());
			return true;
		}
		return false;
	}
	
}
