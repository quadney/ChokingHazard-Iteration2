package Models;

public class Tile {

	private String[][] tileSpaces;
	private int tileId;
	private int numberCells;


	public Tile(int numberCells, int tileId) {

		this.tileSpaces = tileSpaces;
		this.tileId = tileId;
		this.numberCells = numberCells;
		tileSpaces = new String[2][2];
		createTile();
	}

	public String[][] getTileSpaces() {
		return tileSpaces;
	}

	public void settileSpaces(String[][] tileSpaces) {
		this.tileSpaces = tileSpaces;
	}
	
	public String getType(){
		int numberOfCell = 0;
		for(int i = 0; i < tileSpaces.length; i++)
			for(int j = 0; j < tileSpaces[i].length; i++){
				if(tileSpaces[i][j] == null)
					break;
				numberOfCell++;
			}
		
				if(numberOfCell == 2){
					return "two";
				}else if(numberOfCell == 3){
					return "three";
				}else{
					return "Blank";
				}
	
	}
	public void createTile(){
		if(numberCells == 3){
			tileSpaces[0][1] = "Village";
			tileSpaces[0][0] = "rice";
			tileSpaces[1][1] = "rice";
			tileSpaces[1][0] = "";
		}else if(numberCells == 2){
			tileSpaces[0][1] = "Village";
			tileSpaces[0][0] = "rice";
			tileSpaces[1][1] = "";
			tileSpaces[1][0] = "";
		}
	}

}
