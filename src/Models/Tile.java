package Models;

public class Tile {

	private Cell[][] tileSpaces;
	private int tileId;
	private int tileNumber;

	// String tileType;

	Tile(Cell[][] tileSpaces, int tileId) {

		this.tileSpaces = tileSpaces;
		this.tileId = tileId;
	}

	public Cell[][] getTileSpaces() {
		return tileSpaces;
	}

	public void settileSpaces(Cell[][] tileSpaces) {
		this.tileSpaces = tileSpaces;
	}

}
