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
}
