package Models;

import java.util.Stack;

public class BoardModel {
	private Cell[][] map;
	private Stack<Cell> path;
	
	public BoardModel() {
		this.map = new Cell [14][14];
		this.path = new Stack<Cell>();
	
		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				if (x == 0 || x == 1) {
					map[x][y] = new JavaCell (x, y,"BLANK");
				}

				else if (x <= 6 && (y <= 1 || y >= 12)) {
					map[x][y] = new JavaCell (x, y, "BLANK");
				}

				else if (x >= 7 && (y <= 1 || y >= 12)) {
					map[x][y] = new JavaCell (x, y, "BLANK");
				}

				else if (x == 12 || x == 13) {
					map[x][y] = new JavaCell (x, y, "BLANK");
				}
				
				else {
					map[x][y] = new JavaCell (x,y, "BLANK");	//this creates Cell objects for the rest of central Java
				}
			}
		}
		
	}
	
	public boolean placeTile(int xC, int yC, Tile tile, JavaPlayer player) {
		checkValidTilePlacement(xC, yC, tile, player);
		return true;
	}
	
	private boolean checkValidTilePlacement(int xC, int yC, Tile tile, JavaPlayer player) {
		JavaCell[][] javaCells = createTestMap(xC, yC);
		int neededActionPoints = checkNeededActionPoints(javaCells, tile);
		
		if (checkPalacePlacement(javaCells, tile) && 
			checkTilesBelow(javaCells, tile) && 
			checkElevation(javaCells, tile) && 
			checkIrrigationPlacement(javaCells, tile) &&
			checkDeveloperOnCell(javaCells, tile) && 
			checkCityConnection(javaCells, tile) && 
			checkEdgePlacement(javaCells, tile) &&
			player.decrementNActionPoints(neededActionPoints)) {
				return true;
		}

		return false;
	}
	
	private int checkNeededActionPoints(JavaCell[][] javaCells, Tile tile) {
		return 0;
	}
	
	private boolean checkPalacePlacement(JavaCell[][] javaCells, Tile tile) {
		return true;
	}
	
	private boolean checkTilesBelow(JavaCell[][] javaCells, Tile tile) {
		return true;
	}
	
	private boolean checkElevation(JavaCell[][] javaCells, Tile tile) {
		return true;
	}
	
	private boolean checkIrrigationPlacement(JavaCell[][] javaCells, Tile tile) {
		String[][] tileCells = tile.getTileCells();

		for (int i = 0; i < tileCells.length; i++) {
			for (int j = 0; j < tileCells[i].length; j++) {
				if (tileCells[i][j] != null) {
					if (javaCells[i][j] != null &&
					    javaCells[i][j].getCellType() != null &&
					    javaCells[i][j].getCellType() == "irrigation") {
		
							return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean checkDeveloperOnCell(JavaCell[][] javaCells, Tile tile) {
		String[][] tileCells = tile.getTileCells();
		
		for (int i = 0; i < tileCells.length; i++) {
			for (int j = 0; j < tileCells[i].length; j++) {
				if (tileCells[i][j] != null) {
					if (javaCells[i][j] != null &&
					    javaCells[i][j].hasDeveloper()) {
		
							return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean checkCityConnection(JavaCell[][] javaCells, Tile tile) {
		return true;
	}
	
	private boolean checkEdgePlacement(JavaCell[][] javaCells, Tile tile) {
		return true;
	}
}
