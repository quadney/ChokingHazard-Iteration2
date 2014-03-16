package Tests;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Simulator {
	public static int FILE_POS_X = 20;
	public static int FILE_POS_Y = 50;
	public static int QUIT_POS_X = 50;
	public static int QUIT_POS_Y = 140;
	public static int PLACE_START_X = 1;
	public static int PLACE_START_Y = 1;
	public static int THROTTLE = 20;
	public static int TIME_BETWEEN_MAIN_PLAYERS = 150;
	public static int TIME_BETWEEN_MAIN_COMMANDS = 400;
	public static int GAME_LOAD_WAIT_TIME = 2000;
	
	public static void newGame(String... players) {
		GameRobot.wait(GAME_LOAD_WAIT_TIME);
		GameRobot.altN();
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
		GameRobot.type(players.length + "");
		GameRobot.pressTab();
		for(String player : players) { 
			GameRobot.type(player);
			GameRobot.pressTab();
			GameRobot.pressTab();
		}
		GameRobot.pressSpace();
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
	}
	
	public static void quitGame(String filename) {
		GameRobot.click(FILE_POS_X, FILE_POS_Y, 1);
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
		GameRobot.click(QUIT_POS_X, QUIT_POS_Y, 1);
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS*2);
		//if(filename == null) {
			GameRobot.pressTab();
			GameRobot.pressSpace();
		//}
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS*2);
		GameRobot.pressSpace();
	}
	
	public static void saveGame(String filename) {
		GameRobot.altQ();
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
		GameRobot.pressEnter();
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
		GameRobot.type(filename);
		GameRobot.pressEnter();
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
	}
	
	public static boolean saveAndCompareGames(String filename) {
		saveGame(filename + "compare");
		String s1 = readFile(filename+"compare");
		String s2 = readFile(filename);
		if(s1.equals(s2))
			System.out.println("TEST PASSED");
		else
			System.out.println("TEST FAILED");
		return s1.equals(s2);
	}
	
	static String readFile(String path) {
	    Charset encoding = Charset.defaultCharset();
		byte[] encoded;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		    return encoding.decode(ByteBuffer.wrap(encoded)).toString();
		} catch (IOException e) {
			System.err.println("COULDN'T READ FILES, PICK NEW FILE OR REPORT BUG");
		}
		return null;
	}
	
	public static void cancelAction() {
		GameRobot.pressEscape();
	}
	
	public static void endTurn(int fame) {
		GameRobot.type("X");
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
		GameRobot.type("" + fame);
		GameRobot.pressEnter();
	}

	public static void useExtraActionToken() {
		GameRobot.type("T");
	}
	
	public static void placeRiceTile(int posX, int posY) {
		GameRobot.type("R");
		placeTile(posX, posY, 0);
	}
	
	public static void placePalaceTile(int posX, int posY, int value) {
		GameRobot.type("P");
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
		GameRobot.type("" + value);
		GameRobot.pressEnter();
		placeTile(posX, posY, 0);
	}
	
	public static void placeIrrigationTile(int posX, int posY) {
		GameRobot.type("I");
		placeTile(posX, posY, 0);
	}
	
	public static void placeVillageTile(int posX, int posY) {
		GameRobot.type("V");
		placeTile(posX, posY, 0);
	}
	
	public static void placeTwoSpaceTile(int posX, int posY, int rotations) {
		//GameRobot.type("2");
		GameRobot.type(""+((char)(50)));
		placeTile(posX, posY, rotations);
	}
	
	public static void placeThreeSpaceTile(int posX, int posY, int rotations) {
		//GameRobot.type("3");
		GameRobot.type(""+((char)(51)));
		placeTile(posX, posY, rotations);
	}
	
	private static void placeTile(int posX, int posY, int rotations) {
		GameRobot.navigateCursor(posX - PLACE_START_X, posY - PLACE_START_Y);
		for(int x = 0; x < rotations; ++x)
			GameRobot.pressSpace();
		GameRobot.pressEnter();
	}

	public static void moveDeveloper(int developer, int displaceX, int displaceY) {
		GameRobot.pressTab(developer+1);
		GameRobot.pressEnter();
		GameRobot.navigateCursor(displaceX, displaceY);
		GameRobot.pressEnter();
	}
	
	public static void placeDeveloper(int posX, int posY)  {
		GameRobot.type("D");
		GameRobot.navigateCursor(posX - PLACE_START_X, posY - PLACE_START_Y);
		GameRobot.pressEnter();
	}
	
	public static void deleteDeveloper(int tabs) {
		GameRobot.pressTab(tabs+1); 
		GameRobot.pressBackspace();
	}
}
