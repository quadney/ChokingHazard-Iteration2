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
		int neededActionPoints = checkNeededActionPoints(xC, yC, tile);
		
		if (checkPalacePlacement(xC, yC, tile) && 
			checkTilesBelow(xC, yC, tile) && 
			checkElevation(xC, yC, tile) && 
			checkIrrigationPlacement(xC, yC, tile) &&
			checkDeveloperOnCell(xC, yC, tile) && 
			checkCityConnection(xC, yC, tile) && 
			checkEdgePlacement(xC, yC, tile) &&
			player.decrementNActionPoints(neededActionPoints)) {
				return true;
		}

		return false;
	}
	
	private int checkNeededActionPoints(int xC, int yC, Tile tile) {
		return 0;
	}
	
	private boolean checkPalacePlacement(int xC, int yC, Tile tile) {
		return true;
	}
	
	private boolean checkTilesBelow(int xC, int yC, Tile tile) {
		return true;
	}
	
	private boolean checkElevation(int xC, int yC, Tile tile) {
		return true;
	}
	
	private boolean checkIrrigationPlacement(int xC, int yC, Tile tile) {
		return true;
	}
	
	private boolean checkDeveloperOnCell(int xC, int yC, Tile tile) {
		return true;
	}
	
	private boolean checkCityConnection(int xC, int yC, Tile tile) {
		return true;
	}
	
	private boolean checkEdgePlacement(int xC, int yC, Tile tile) {
		return true;
	}
}
