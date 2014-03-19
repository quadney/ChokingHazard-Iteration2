package Tests;

import java.awt.AWTException;

public class AllTests {
	public static void main(String args[]) throws InterruptedException, AWTException {
//		NewGameTest.test();
//		SelectTilesTest.test();
//		GameRobot.wait(2000);
//		PlaceTilesTest.test();
//		GameRobot.wait(2000);
		PlacingTilesValidityTest.test();
		GameRobot.wait(2000);
		Simulator.quitGame();
	}
}
