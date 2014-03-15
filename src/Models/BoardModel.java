package Models;

import java.util.Stack;

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
	
	public boolean placeDeveloper(Point location, JavaPlayer player)
	{
		// Set the point equal to the cell on the board
		JavaCell locationCell = map[location.getX()][location.getY()];
		
		// Check with validity method first
		if (!canPlaceDeveloper(locationCell, player))
			return false;
		
		 //Set developer on board
		 return true; //TODO
		
	}
	
	public boolean canPlaceDeveloper(JavaCell locationCell, JavaPlayer player)
	{	
		// Can only place on village or rice space
		if ( !(isTileOrLand(locationCell.getX(), locationCell.getY())))
			return false;
		
		// Can only place if no developer is already occupying space
		if (locationCell.hasDeveloper())
			return false;
		
		// Space must be on or inside border into central java
		if (locationCell.isBorder())
		{
			// If it's on the border, must see that there is a land space tile there
			// But we already checked that above, so by this point we are good
		}
		// If it's not on the border, have to check that at least on adjacent space is open in outer java
		else if (!hasAdjacentEmptyTile(locationCell))
			return false;
		
		// Check that player has available AP for this
		// First determine type of move/cost
		if (!player.decrementNActionPoints(1))	//TODO: Check lowlands or mountains
			return false;
			
		
		return true;
	}
	
	public boolean hasAdjacentEmptyTile(JavaCell cell)
	   {
		   int x = cell.getX();
		   int y = cell.getY();
		   
		   // Check if the cell is adjacent to any empty border cells
		   if (x + 1 == 13)
		   {
			   if (map[13][y].getCellType().equals("Blank"))
				   return true;
		   }
		   
		   if (x- 1 == 0)
		   {
			   if (map[0][y].getCellType().equals("Blank"))
				   return true;
		   }
		   if (y + 1 == 13)
		   {
			   if (map[x][13].getCellType().equals("Blank"))
				   return true;
		   }
		   if (y - 1 == 0)
		   {
			   if (map[x][0].getCellType().equals("Blank"))
				   return true;
		   }
		   
		   return false;
			   
	   }
	
	public boolean isTileOrLand( int x, int y)
	{
		if (!(map[x][y].getCellType().equals("Village") || map[x][y].getCellType().equals("Rice")))
			return false;
		return true;
	}
	
	public void removeDeveloper(Point point, JavaPlayer player)
	{
		JavaCell pointCell = map[point.getX()][point.getY()];
		
		//Turn off hasDeveloper
		pointCell.removeDeveloper();
		// Remove currently selected developer from dev array
		player.removeDeveloperFromArray(); //TODO: correct indices
		//Decrement actions points
		player.decrementNActionPoints(1); 
	}
	
	/**************************
	 * Currently useless code that might be useful later
	 * ************************/
	
	/* 
	public boolean hasAdjacentLandSpaceTile(JavaCell cell)
	   {
		   int x = cell.getX();
		   int y = cell.getY();
		   
		   // Check if the cell is adjacent to any border cells
		   if (x + 1 == 13)
		   {
			   if (isTileOrLand(x+1,y))
				   return true;
		   }
		   
		   if (x- 1 == 0)
		   {
			   if (isTileOrLand(x-1,y))
				   return true;
		   }
		   if (y + 1 == 13)
		   {
			   if (isTileOrLand(x,y+1))
				   return true;
		   }
		   if (y - 1 == 0)
		   {
			   if (isTileOrLand(x,y-1))
				   return true;
		   }
		   
		   return false;
			   
	   }
	*/ 
	 

}
