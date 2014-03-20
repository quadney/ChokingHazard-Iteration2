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
	public static int THROTTLE = 30;
	public static int TIME_BETWEEN_MAIN_PLAYERS = 150;
	public static int TIME_BETWEEN_MAIN_COMMANDS = 300;
	public static int GAME_LOAD_WAIT_TIME = 2000;
	public static int GAME_EXIT_WAIT_TIME = 2000;
	public static int ALT_N = 2;
	
	/**
	 * starts a new game with new players. The colors are set to default.
	 * This will pause for GAME_LOAD_WAIT_TIME and then start setting the new game.
	 * The time is set to wait while the game is loaded.
	 * @param players a java vararg. Can take any number of Strings as parameters.
	 * Game meant to be 2-4 players, but not restricted to that.
	 */
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
		GameRobot.wait(GAME_LOAD_WAIT_TIME/2);
		GameRobot.pressEscape();
	}
	
	/**
	 * Not fully implemented yet.
	 * Quits the game through they menu with the mouse. 
	 * @param filename name of the file to save. Set to null to quit without saving.
	 */
	public static void quitGame(String filename) {
		GameRobot.click(FILE_POS_X, FILE_POS_Y, 1);
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
		GameRobot.click(QUIT_POS_X, QUIT_POS_Y, 1);
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS*2);
		if(filename == null) {
			GameRobot.pressTab();
			GameRobot.pressSpace();
		}
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS*2);
		GameRobot.pressSpace();
	}

	/**
	 * Not fully implemented yet.
	 * quits through System.exit(0)
	 */
	public static void quitGame() {
		GameRobot.wait(GAME_EXIT_WAIT_TIME);
		System.exit(0);
	}
	
	/** 
	 * Not fully implemented yet.
	 * Saves the game through the keyboard shortcut: alt+Q.
	 * @param filename name of the file to save.
	 */
	public static void saveGame(String filename) {
		GameRobot.altQ();
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
		GameRobot.pressEnter();
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
		GameRobot.type(filename);
		GameRobot.pressEnter();
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
		GameRobot.pressEnter();
	}

	/** 
	 * Not fully implemented yet.
	 * Saves the game through the keyboard shortcut: alt+Q, then compares it to
	 * a preset file *filename*. Appends "compare" to the end of the filename and 
	 * saves that for later comparison.
	 * @param filename name of the file to use as a comparison.
	 */
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

	/**
	 * Reads a saved game file with name *path* and returns all the contents as a String.
	 * @param path the name of the file
	 * @return the String in the file.
	 */
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
	
	/**
	 * cancels the Action by pressing Escape
	 */
	public static void cancelAction() {
		GameRobot.pressEscape();
	}
	
	/**
	 * Ends the turn of a player.
	 * @param fame the fame the player earned that round.
	 */
	public static void endTurn(int fame) {
		GameRobot.type("X");
//		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
//		GameRobot.type("" + fame);
//		GameRobot.pressEnter();
	}

	/**
	 * use an extra action token
	 */
	public static void useExtraActionToken() {
		GameRobot.type("T");
	}
	
	/**
	 * Place a Rice Tile at position posX, posY on the board. Starts from 0,0; not 1,1.
	 * @param posX the x position from 0 as the leftmost cell.
	 * @param posY the y position from 0 as the topmost cell.
	 */
	public static void placeRiceTile(int posX, int posY) {
		GameRobot.type("R");
		placeTile(posX, posY, 0);
	}

	/**
	 * Place a Palace Tile at position posX, posY on the board with value *value*. Starts from 0,0; not 1,1.
	 * @param posX the x position from 0 as the leftmost cell.
	 * @param posY the y position from 0 as the topmost cell.
	 * @param value the value of the palace being placed.
	 */
	public static void placePalaceTile(int posX, int posY, int value) {
		GameRobot.type("P");
		GameRobot.wait(TIME_BETWEEN_MAIN_COMMANDS);
		GameRobot.type("" + value);
		GameRobot.pressEnter();
		placeTile(posX, posY, 0);
	}

	/**
	 * Place a Irrigation Tile at position posX, posY on the board. Starts from 0,0; not 1,1.
	 * @param posX the x position from 0 as the leftmost cell.
	 * @param posY the y position from 0 as the topmost cell.
	 */
	public static void placeIrrigationTile(int posX, int posY) {
		GameRobot.type("I");
		placeTile(posX, posY, 0);
	}

	/**
	 * Place a Village Tile at position posX, posY on the board. Starts from 0,0; not 1,1.
	 * @param posX the x position from 0 as the leftmost cell.
	 * @param posY the y position from 0 as the topmost cell.
	 */
	public static void placeVillageTile(int posX, int posY) {
		GameRobot.type("V");
		placeTile(posX, posY, 0);
	}
	
	/**
	 * Place a Two Space Tile at position posX, posY on the board with rotation *rotations*. 
	 * Starts from 0,0; not 1,1.
	 * @param posX the x position from 0 as the leftmost cell.
	 * @param posY the y position from 0 as the topmost cell.
	 * @param rotation the number of times the tile is rotated before being placed.
	 */
	public static void placeTwoSpaceTile(int posX, int posY, int rotations) {
		//GameRobot.type("2");
		GameRobot.type(""+((char)(50)));
		placeTile(posX, posY, rotations);
	}
	
	/**
	 * Place a Three Space Tile at position posX, posY on the board with rotation *rotations*. 
	 * Starts from 0,0; not 1,1.
	 * @param posX the x position from 0 as the leftmost cell.
	 * @param posY the y position from 0 as the topmost cell.
	 * @param rotation the number of times the tile is rotated before being placed.
	 */
	public static void placeThreeSpaceTile(int posX, int posY, int rotations) {
		//GameRobot.type("3");
		GameRobot.type(""+((char)(51)));
		placeTile(posX, posY, rotations);
	}

	/**
	 * Place a Tile at position posX, posY on the board with rotation *rotations*. 
	 * Starts from 0,0; not 1,1.
	 * @param posX the x position from 0 as the leftmost cell.
	 * @param posY the y position from 0 as the topmost cell.
	 * @param rotation the number of times the tile is rotated before being placed.
	 */
	private static void placeTile(int posX, int posY, int rotations) {
		for(int x = 0; x < rotations; ++x)
			GameRobot.pressSpace();
		GameRobot.navigateCursor(posX - PLACE_START_X, posY - PLACE_START_Y);
		GameRobot.pressEnter();
	}

	/**
	 * Selects a developer with the amount of tabs *developer* and moves it displaceX, displaceY
	 * @param developer the amount of times tab is pressed
	 * @param displaceX the amount of times right/left is pressed. Negative for left.
	 * @param displaceY the amount of times up/down is pressed. Negative for up.
	 */
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
