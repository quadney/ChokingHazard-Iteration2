package Models.Actions;

public class SelectOneSpaceTileAction extends MAction {
	
	public SelectOneSpaceTileAction(String imageKey) {
		super(imageKey);

		// TODO Auto-generated constructor stub
		
	}

	public void pressSpace(){
		//does nothing
	}
	
	public boolean pressArrow(int xChange, int yChange){
		//moves around the whole entire board
		return false;
	}
	
}