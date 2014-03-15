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
}
