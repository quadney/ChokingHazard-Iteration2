package Models.Actions;

import Models.JavaCell;

public class IrrigationTileAction extends OneSpaceTileAction {

	public IrrigationTileAction(int actionID, int famePointsEarned, int actionPointsEarned, int x, int y, JavaCell cell) {
		super(actionID, famePointsEarned, actionPointsEarned, x, y, cell);
		this.imageKey = "irrigationTile";
	}

}
