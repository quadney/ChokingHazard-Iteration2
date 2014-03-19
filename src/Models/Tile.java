package Models;

import Helpers.JsonObject;

public class Tile implements Serializable<Tile> {
	public static enum TileType {
		palace2, palace4, palace6, palace8, palace10, village, rice, irrigation, twotile, threetile;
	}
	private TileType[][] tileCells;

	public Tile(TileType type, int rotationState) {
		createTile(type);
	}

	public TileType[][] getTileCells() {
		return tileCells;
	}

	public void setTileSpaces(TileType[][] tileCells) {
		this.tileCells = tileCells;
	}

	public String getType() {
		int numberOfCell = 0;

		for (int i = 0; i < tileCells.length; i++) {
			for (int j = 0; j < tileCells.length; j++) {
				if (tileCells[i][j] != null) {
					numberOfCell++;
				}
			}
		}

		if (numberOfCell == 2) {
			return "two";
		}

		else if (numberOfCell == 3) {
			return "three";
		}

		else if (numberOfCell == 1) {
			return "one";
		}

		else {
			return "blank";
		}
	}

	private void createTile(TileType type) {
		tileCells = new TileType[3][3];
		if (type == TileType.threetile) {
			tileCells[1][1] = TileType.village;
			tileCells[1][2] = TileType.rice;
			tileCells[2][1] = TileType.rice;
		} else if (type == TileType.twotile) {
			tileCells[1][1] = TileType.village;
			tileCells[1][2] = TileType.rice;
		} else {
			tileCells[1][1] = type;
		}
	}
	
	public void rotationToRotationState(int rotationState) { 
		int rotations = (getRotationState() - rotationState + 4) % 4;
		for(int x = 0; x < rotations; ++x)
			rotate();
	}
	
	private int getRotationState() {
		int[] xx = {0, 1, 2, 1};
		int[] yy = {1, 2, 1, 0};
		for(int i = 0; i < xx.length-1; ++i)
			if(tileCells[xx[i]][yy[i]] != null && tileCells[xx[(i+1)%xx.length]][yy[(i+1)%yy.length]] != null)
				return i;
		for(int i = 0; i < xx.length-1; ++i)
			if(tileCells[xx[i]][yy[i]] != null)
				return i;
		return 0;
	}
	
	public void rotate() {
		int[] xx = {0, 1, 2, 1};
		int[] yy = {1, 2, 1, 0};
		TileType temp = tileCells[xx[0]][yy[0]];
		for(int i = 0; i < xx.length-1; ++i)
			tileCells[xx[i]][yy[i]] = tileCells[xx[i+1]][yy[i+1]];
		tileCells[xx.length-1][yy.length-1] = temp;
	}

	@Override
	public String serialize() {
		return null;
	}

	@Override
	public Tile loadObject(JsonObject json) {
		return this;
	}
}
