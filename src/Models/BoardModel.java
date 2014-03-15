package Models;
import java.util.Stack;
import java.util.*;

public class BoardModel {
	private JavaCell[][] map;

	private Stack<JavaCell> path;
	
	public BoardModel() {
		this.map = new JavaCell [14][14];
		this.path = new Stack<JavaCell>();

		for (int x = 0; x < map.length; x++) {
			for (int y = 0; y < map[0].length; y++) {
				if (x == 0 || x == 1) {
					map[x][y] = new JavaCell (x, y);
				}

				else if (x <= 6 && (y <= 1 || y >= 12)) {
					map[x][y] = new JavaCell (x, y);
				}

				else if (x >= 7 && (y <= 1 || y >= 12)) {
					map[x][y] = new JavaCell (x, y);
				}
				else if (x == 12 || x == 13) {
					map[x][y] = new JavaCell (x, y);
				}
				else {
					map[x][y] = new JavaCell (x,y);	//this creates Cell objects for the rest of central Java
				}
			}
		}
		
	}
	
	public boolean placeTile(Cell[][] cells, Tile tile) {
		
		return true;
	}
	
	private boolean checkValidTilePlacement(int xC, int yC, Tile tile, JavaPlayer player) {

		JavaCell[][] miniMap = createTestMap(xC, yC); //creating a small map with the cells we need to compare
		
		int neededActionPoints = checkNeededActionPoints(miniMap, tile);

		if (checkPalacePlacement(miniMap, tile) && 
			checkTilesBelow(miniMap, tile) && 
			checkElevation(miniMap, tile) && 
			checkIrrigationPlacement(miniMap, tile) &&
			checkDeveloperOnCell(miniMap, tile) && 
			checkCityConnection(miniMap, tile) && 
			checkEdgePlacement(miniMap, tile) &&
			player.decrementNActionPoints(neededActionPoints)) {
				return true;
		}

		return false;
	}

	private JavaCell[][] createTestMap(int xC, int yC){
		
		JavaCell[][] testingMap = new JavaCell[3][3];
		
		for(int i = 0, x = xC-1; i < 3; i++, x++)
			for(int j = 0, y = yC-1; j < 3; j++, y++)
				testingMap[i][j] = map[x][y];
				
		
		return testingMap;
	}
	

	private int checkNeededActionPoints(JavaCell[][] miniMap, Tile tile) {

		return 0;
	}
	
	private boolean checkPalacePlacement(JavaCell[][] miniMap, Tile tile) {
		String[][] tileCells = tile.getTileCells();

		for (int i = 0; i < tileCells.length; i++) {
			for (int j = 0; j < tileCells[i].length; j++) {
				if (tileCells[i][j] != null) {
					if (miniMap[i][j] != null &&
						miniMap[i][j].getCellType() != null &&
						miniMap[i][j].getCellType() == "irrigation") {
		
							return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean checkTilesBelow(JavaCell[][] miniMap, Tile tile) {
		return true;
	}
	
	private boolean checkElevation(JavaCell[][] miniMap, Tile tile) {
		return true;
	}
	
	private boolean checkIrrigationPlacement(JavaCell[][] miniMap, Tile tile) {
		String[][] tileCells = tile.getTileCells();

		for (int i = 0; i < tileCells.length; i++) {
			for (int j = 0; j < tileCells[i].length; j++) {
				if (tileCells[i][j] != null) {
					if (miniMap[i][j] != null &&
						miniMap[i][j].getCellType() != null &&
						miniMap[i][j].getCellType() == "irrigation") {
		
							return false;
					}
				}
			}
		}
		
		return true;
	}
	

	private boolean checkDeveloperOnCell(JavaCell[][] miniMap, Tile tile) {
		String[][] tileCells = tile.getTileCells();
		
		for (int i = 0; i < tileCells.length; i++) {
			for (int j = 0; j < tileCells[i].length; j++) {
				if (tileCells[i][j] != null) {
					if (miniMap[i][j] != null &&
						miniMap[i][j].hasDeveloper()) {
		
							return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean checkCityConnection(JavaCell[][] miniMap, Tile tile) {
		return true;
	}
	
	private boolean checkEdgePlacement(JavaCell[][] miniMap, Tile tile) {
		return true;
	}

   public JavaCell[][] getMap()
   {
      return map;
   }
   
   //Given a root cell, finds and adds to a list all adjacent cells with the same type
   //This can be used to retrieve the cells that make up a city.
   public ArrayList<JavaCell> getCityFromRootCell(JavaCell root)
   {
      ArrayList<JavaCell> connected = new ArrayList<JavaCell>();
      int x = root.getX();
      int y = root.getY();
      
		connected.add(root);

		int i = 0;
		while (i < connected.size()) {
			Cell temp = connected.get(i);
			HashSet<JavaCell> adjacent = new HashSet<JavaCell>();
			if (y < 14 && map[y + 1][x].getCellType().equals("village") || map[y + 1][x].getCellType().equals("palace"))
				adjacent.add(map[y + 1][x]);
			if (y > 0 && map[y - 1][x].getCellType().equals("village") || map[y - 1][x].getCellType().equals("palace"))
				adjacent.add(map[y - 1][x]);
			if (x < 14 && map[y][x + 1].getCellType().equals("village") || map[y][x + 1].getCellType().equals("palace"))
				adjacent.add(map[y][x + 1]);
			if (x > 0 && map[y][x - 1].getCellType().equals("village") || map[y][x - 1].getCellType().equals("palace"))
				adjacent.add(map[y][x - 1]);

			Iterator<JavaCell> it = adjacent.iterator();
			while (it.hasNext()) {
				JavaCell next = (JavaCell) it.next();
				if (!connected.contains(next))
					connected.add(next);
			}
			i++;
		}

		return connected;
   }
}
