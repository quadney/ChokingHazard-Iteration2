package Models;

public class Tile {
	private String[][] tileCells;
	private int numberCells;


	public Tile(int numberCells) {

		
		this.numberCells = numberCells;

		tileCells = new String[3][3];

		createTile();
	}

	public String[][] getTileCells() {
		return tileCells;
	}

	public void setTileSpaces(String[][] tileCells) {
		this.tileCells = tileCells;
	}
	
	public String getType(){
		int numberOfCell = 0;

		for(int i = 0; i < tileCells.length; i++)
			for(int j = 0; j < tileCells[i].length; i++)
				if(tileCells[i][j] != null)
					numberOfCell++;			
		
				if(numberOfCell == 2){
					return "two";
				}else if(numberOfCell == 3){
					return "three";
					
				}else if(numberOfCell == 1){
					return "one";
				}else{
					return "blank";
				}
	
	}
	private void createTile(){
		if(numberCells == 3){

			tileCells[1][1] = "village";
			tileCells[1][2] = "rice";
			tileCells[2][1] = "rice";
		
		}else if(numberCells == 2){
			tileCells[1][1] = "village";
			tileCells[1][2] = "rice";


		}
	}
}
