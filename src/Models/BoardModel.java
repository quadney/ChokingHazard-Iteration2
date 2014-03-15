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

	public int placePalaceTile() {
		/* 
		 * can only be placed if: 
			 	- on top of a village tile
				- there is no developer on the space underneath
				- must be the highest ranked player in that city
				- refer to Highest Ranking Player under Developer
				- count the number of villages connected to the village you are placing the palace on top of (include the village you are placing on top of)
					* if this number is less that n
						- not allowed
					* if this number is greater than or equal to n
						- allowed
				- all the villages that are connected to the village you are placing the palace are on top of are not connected to another palace (this would be attempting to have two palaces in the same city)
					* not allowed


			return : Player scores (n/2) fame points, -1 if not allowed
		 */
		// TODO Auto-generated method stub
		return -1;
	}
}
