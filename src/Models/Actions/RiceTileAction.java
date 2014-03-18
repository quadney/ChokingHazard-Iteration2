package Models.Actions;

import Models.JavaCell;

public class RiceTileAction extends OneSpaceTileAction {

	public RiceTileAction(int actionID, int famePointsEarned, int actionPointsEarned, int x, int y, JavaCell cell) {
		super(actionID, famePointsEarned, actionPointsEarned, x, y, cell);
		this.imageKey = "riceTile";
	}
}
