package Models;

public class testing {

	public static void main(String[] args) {
		BoardModel test = new BoardModel();
		
		JavaPlayer jose = new JavaPlayer("jose", "blue");
		
		System.out.println("village: " + test.placeTile(1, 1, new Tile(Tile.TileType.threetile, 3), jose));
	//	System.out.println("village2: " + test.placeTile(1, 2, new Tile(Tile.TileType.village, 1), jose));
		
		System.out.println("Palace: " + test.placeTile(1, 1, new Tile(Tile.TileType.threetile, 3), jose));

	}

}
