package Tests;

import java.awt.AWTException;

import ChokingHazard.RunGame;

public class MovingDeveloperTest {
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
		
		Simulator.placeThreeSpaceTile(1, 1, 0);
		Simulator.placeThreeSpaceTile(3, 2, 2);
		Simulator.placeThreeSpaceTile(3, 3, 1);
		Simulator.placeThreeSpaceTile(4, 3, 0);
		Simulator.placeThreeSpaceTile(4, 2, 3);
		Simulator.placeDeveloper(1, 1);
		Simulator.placeDeveloper(1, 2);
		GameRobot.pressTab();
		GameRobot.type("x");
		Simulator.placeThreeSpaceTile(7, 1, 3);
		Simulator.placeDeveloper(1,3);
		
		
	}
}
