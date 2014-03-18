package Models.Actions;

public class PalaceTileAction extends OneSpaceTileAction {
	
	int value;

	public PalaceTileAction(int actionID, int x, int y, int value) {
		super(actionID, x, y);
		this.value = value;
		this.imageKey = "palace" + value + "Tile";
	}

	public int getValueOfPalace() {
		return value;
	}
}
