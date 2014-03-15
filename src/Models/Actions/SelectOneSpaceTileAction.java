package Models.Actions;

public class SelectOneSpaceTileAction extends MAction {

	int x = 1;
	int y = 1;
	
	public SelectOneSpaceTileAction(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		// TODO Auto-generated constructor stub
		
	}

	public void pressSpace(){
		//does nothing
	}
	
	public void pressArrow(int value){
		//moves around the whole entire board
	}
	
}