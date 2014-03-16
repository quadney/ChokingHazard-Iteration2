package Models.Actions;

public class SelectTwoTileAction extends SelectRotatableTileAction {

	public SelectTwoTileAction(String imageKey) {
		super(imageKey);
	}

	@Override
	public boolean pressArrow(int xChange, int yChange) {
		//moves around according to contraints.
		return false;
	}
	
}
