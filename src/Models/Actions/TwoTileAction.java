package Models.Actions;

import Helpers.JsonObject;
import Models.GameModel;
import Models.JavaCell;
import Models.JavaPlayer;
import Models.Tile;
import Models.Tile.TileType;

public class TwoTileAction extends RotatableComponentAction {
	

	public TwoTileAction(int actionID, int x, int y, int rotationState) {
		super(actionID, x, y, rotationState);
		System.out.println("Two Tile constructor end");
		this.imageKey = "twoTile";
	}

	@Override
	public String serialize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Action loadObject(JsonObject json) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void redo(GameModel game) {
		game.placeTileOnBoard(y, x, new Tile(TileType.twotile, rotationState));
		System.out.println(x + "," + y +"Center " + game.getBoard().getMap()[x][y].getCellType());
		
		System.out.println(x + "," + y +"Center " + game.getBoard().getMap()[x][y].getCellType());
		System.out.println("Down " + game.getBoard().getMap()[x + 1][y].getCellType());
		System.out.println("Up " + game.getBoard().getMap()[x - 1][y].getCellType());
		System.out.println("Right " + game.getBoard().getMap()[x][y + 1].getCellType());
		System.out.println("Left " + game.getBoard().getMap()[x][y - 1].getCellType());
	}
		
}
