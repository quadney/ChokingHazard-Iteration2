package Tests;

import java.awt.AWTException;

import ChokingHazard.RunGame;

public class SelectTilesTest {

	public SelectTilesTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) throws AWTException, InterruptedException {
		test();
	}
	public static void test() throws InterruptedException, AWTException {
		@SuppressWarnings("unused")
		RunGame game = new RunGame();
		
		Simulator.newGame("p", "c");
		Thread.sleep(3000);
		GameRobot.pressEscape();
//		Simulator.placeIrrigationTile(4, 3);
//		//GameRobot.pressEscape();
//		Simulator.placeVillageTile(2, 2);
//		//GameRobot.pressEscape();
//		Simulator.placeVillageTile(2, 3);
//		GameRobot.pressEscape();
//		Simulator.placeRiceTile(0, 9);
//		//GameRobot.pressEscape();
//		Simulator.placeRiceTile(7, 7);
//		GameRobot.pressEscape();
//		Simulator.placeRiceTile(9, 0);
//		//GameRobot.pressEscape();
//		Simulator.placeTwoSpaceTile(6, 8, 0);
//		GameRobot.pressSpace();
//		GameRobot.pressSpace();
//		GameRobot.pressSpace();
//		GameRobot.pressSpace();
//		//GameRobot.pressEscape();
//		Simulator.placeThreeSpaceTile(0, 8, 0);
//		GameRobot.pressEscape();
//		Simulator.placeThreeSpaceTile(6, 6, 0);
//		GameRobot.pressSpace();
//		GameRobot.pressSpace();
//		GameRobot.pressSpace();
//		GameRobot.pressSpace();
//		//GameRobot.pressEscape();
//		Simulator.placeThreeSpaceTile(8, 6, 1);
//		//GameRobot.pressEscape();
//		Simulator.placeThreeSpaceTile(8, 8, 2);
//		//GameRobot.pressEscape();
//		Simulator.placeThreeSpaceTile(6, 8, 3);
//		GameRobot.pressEscape();
//		
		
	}

}
