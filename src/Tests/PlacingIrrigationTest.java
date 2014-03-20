package Tests;


import java.awt.AWTException;

import ChokingHazard.RunGame;

public class PlacingIrrigationTest {

		public static void main(String[] args) throws AWTException, InterruptedException {
			test();
		}
		public static void test() throws InterruptedException, AWTException {
			@SuppressWarnings("unused")
			RunGame game = new RunGame();
			
			Simulator.TIME_BETWEEN_MAIN_COMMANDS = 1000;
			Simulator.ALT_N = 1;
			Simulator.newGame("p", "c");
			Simulator.TIME_BETWEEN_MAIN_COMMANDS = 300;
			Simulator.THROTTLE = 50;
			
			Thread.sleep(500);
			GameRobot.pressEscape();
			Simulator.placeIrrigationTile(2, 1);
			Simulator.placeRiceTile(3, 1);
			Simulator.placeVillageTile(4, 1);
			Simulator.placeThreeSpaceTile(5, 0, 2);
			
			Simulator.placeIrrigationTile(0, 12);
			

			Simulator.placeIrrigationTile(0, 13);
			
			Simulator.placeIrrigationTile(0,12);
			
			Simulator.placeIrrigationTile(1, 12);
			
			Simulator.placeIrrigationTile(1, 12);
			
			Simulator.placeThreeSpaceTile(5, 2, 2);
			
			Simulator.placeThreeSpaceTile(4, 1, 0);
			Simulator.placeIrrigationTile(4, 1);
			Simulator.placeIrrigationTile(4, 2);
			Simulator.placeIrrigationTile(5, 1);
			Simulator.placeIrrigationTile(5, 1);
			
			
			//Simulator.placeThreeSpaceTile(5, 0, 2);
			
			Simulator.placeThreeSpaceTile(5, 2, 2);
			
			Simulator.placeTwoSpaceTile(5, 0, 2);
			
			
			
			
			
		}
			
			
			
			
}
