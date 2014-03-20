package Tests;

import java.awt.AWTException;

import ChokingHazard.RunGame;

public class SaveGameTest {
	public static void main(String[] args) throws AWTException, InterruptedException {
		test();
	}
	public static void test() throws InterruptedException, AWTException {
		@SuppressWarnings("unused")
		RunGame game = new RunGame();
		
		Simulator.newGame("p", "c");
		//Simulator.placeThreeSpaceTile(5, 5, 1);
		//Simulator.saveGame("mau");
		
		
		
		

//		Simulator.placeThreeSpaceTile(3, 3, 0);
//		
//		Simulator.placeTwoSpaceTile(5, 5, 0);
//		Simulator.placeTwoSpaceTile(5, 5, 0);
//		
//		Simulator.placeVillageTile(2, 8);
//		Simulator.placeVillageTile(2, 8);
//		
//		Simulator.placeRiceTile(8, 2);
//		Simulator.placeRiceTile(8, 2);
//		
//		Simulator.placeIrrigationTile(6, 9);
//		Simulator.placeIrrigationTile(6, 9);
//		
//		
//		Simulator.placeTwoSpaceTile(7, 7, 0);
//		Simulator.placeRiceTile(7, 7);
//		Simulator.placeRiceTile(8, 7);
//		Simulator.placeTwoSpaceTile(7, 7, 0);
//	
//		Simulator.placeThreeSpaceTile(0, 0, 0);
//		Simulator.placeThreeSpaceTile(13, 0, 1);
//		Simulator.placeThreeSpaceTile(13, 13, 2);
//		Simulator.placeThreeSpaceTile(0, 13, 3);
//		
//		Simulator.placeThreeSpaceTile(0, 8, 0);
//		Simulator.placeThreeSpaceTile(8, 0, 0);
//		Simulator.placeThreeSpaceTile(8, 13, 2);
//		Simulator.placeThreeSpaceTile(13, 8, 2);
//		
//		Simulator.placeThreeSpaceTile(10, 10, 0);
		/*Simulator.placeDeveloper(3, 3);
		Simulator.placeDeveloper(4, 4);
		Simulator.placeDeveloper(1, 5);
		Simulator.placeDeveloper(1, 6);
		Simulator.moveDeveloper(0, 2, 0);
		Simulator.moveDeveloper(1, 5, 5);
		Simulator.placeIrrigationTile(4, 3);
		Simulator.placeVillageTile(2, 2);
		Simulator.placeVillageTile(2, 3);
		Simulator.placeRiceTile(0, 9);
		Simulator.placeRiceTile(7, 7);
		Simulator.placeRiceTile(9, 0);
		Simulator.placeTwoSpaceTile(0, 8, 0);
		Simulator.placeThreeSpaceTile(0, 8, 0);
		Simulator.placeThreeSpaceTile(6, 6, 0);
		Simulator.placeThreeSpaceTile(8, 6, 1);
		Simulator.placeThreeSpaceTile(8, 8, 2);
		Simulator.placeThreeSpaceTile(6, 8, 3);
		Simulator.deleteDeveloper(0);
		Simulator.useExtraActionToken();
		Simulator.placePalaceTile(0, 0, 2);
		Simulator.placePalaceTile(1, 0, 4);
		Simulator.placePalaceTile(2, 0, 6);
		Simulator.placePalaceTile(3, 0, 8);
		Simulator.placePalaceTile(4, 0, 10);
		Simulator.endTurn(10);
		
		Simulator.placeDeveloper(0, 0);
		Simulator.placePalaceTile(4, 9, 2);
		Simulator.placeThreeSpaceTile(9, 9, 2);
		Simulator.endTurn(9);

//		Simulator.saveGame("jkl");
//		Simulator.quitGame(null);
		Simulator.saveAndCompareGames("jkl");*/
	}
	
}
