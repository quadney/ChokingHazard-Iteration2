package Models.Actions;

import Models.GameModel;
import Models.Tile;
import Models.Tile.TileType;

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
	
	@Override
	public void redo(GameModel game) {
		game.getBoard().placeTile(x, y, new Tile(TileType.valueOf("palace"+value), 0), game.getCurrentPlayer());
		game.getShared().decrementPalaceTiles(value);
	}
}
