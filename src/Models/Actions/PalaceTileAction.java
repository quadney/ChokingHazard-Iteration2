package Models.Actions;

import Models.JavaCell;

public class PalaceTileAction extends OneSpaceTileAction {
	
	int value;

	public PalaceTileAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned, int x, int y, int value, JavaCell cell) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned, x, y, cell);
		this.value = value;
	}

	public int getValueOfPalace() {
		return value;
	}
}
