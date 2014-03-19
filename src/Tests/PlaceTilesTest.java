package Tests;

import java.awt.AWTException;

import ChokingHazard.RunGame;

public class PlaceTilesTest {
	public static void main(String[] args) throws AWTException, InterruptedException {
		test();
	}
	public static void test() throws InterruptedException, AWTException {
		@SuppressWarnings("unused")
		RunGame game = new RunGame();
		
		Simulator.newGame("p", "c");
		Thread.sleep(500);
		GameRobot.pressEscape();
		Simulator.placeIrrigationTile(2, 1);
		Simulator.placeRiceTile(3, 1);
		Simulator.placeVillageTile(4, 1);
		Simulator.placeThreeSpaceTile(5, 0, 2);
	}
	
}
