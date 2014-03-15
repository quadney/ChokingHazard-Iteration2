package Models;
import java.util.Stack;
import java.util.*;

public class BoardModel {
	private JavaCell[][] map;
	private Stack<Cell> path;
	
	public BoardModel() {
		this.map = new JavaCell [14][14];
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
