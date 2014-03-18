package Models.Actions;

public class PalaceTileAction extends OneSpaceTileAction {

	int value;
	
	public PalaceTileAction(int actionID, int playerIndex, int value) {
		super(actionID, playerIndex);
		this.value = value;
		// TODO Auto-generated constructor stub
	}

	public int getValueOfPalace() {
		return value;
	}
}
