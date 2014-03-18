package Models.Actions;

public class PalaceTileAction extends OneSpaceTileAction {
	
	int value;

	public PalaceTileAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned, int x, int y, int value) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned, x, y);
		this.value = value;
	}

	public int getValueOfPalace() {
		return value;
	}
}
