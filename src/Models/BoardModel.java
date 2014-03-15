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
}
