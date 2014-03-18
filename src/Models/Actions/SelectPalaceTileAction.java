package Models.Actions;

public class PalaceTileAction extends OneSpaceTileAction {

	
	int value;
	
	public PalaceTileAction(String imageKey, int value) {
		super(imageKey);
		this.value = value;
	}

	public int getValue(){
		return value;
	}
}
