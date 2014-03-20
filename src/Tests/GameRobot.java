package Tests;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class GameRobot {
	public static Robot robot;
	static {
	     Robot tmp = null;
	     try {
	       tmp = new Robot();
	     } catch (AWTException awe) {
	        System.err.println("Couldn't create a robot for some reason. Weird.");
	     }
	     robot = tmp;
	   }

	/**
	 * clicks on pixel x, y. Does this n times if specified.
	 */
	public static void click(int x, int y, int n) {
		robot.mouseMove(x, y);
		throttle();
		for(int i = 0; i < n; ++i) {
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
	}
	
	/**
	 * clicks on pixel x, y once.
	 */
	public static void click(int x, int y) {
		click(x, y, 1);
	}


	/**
	 * "drags" the mouse from spot startX, startY to endX, endY; in pixels.
	 * Clicks on start coordinates, releases at end coordinates.
	 */
	public static void drag(int startX, int startY, int endX, int endY) {
		robot.mouseMove(startX, startY);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseMove(endX, endY);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
	/**
	 * moves the mouse to pixel x, y. Doesn't click.
	 */
	public static void navigateCursor(int x, int y) {
		int horizontal = x < 0 ? KeyEvent.VK_LEFT : KeyEvent.VK_RIGHT;
		int vertical = y < 0 ? KeyEvent.VK_UP : KeyEvent.VK_DOWN;
		for(int i = 0; i < Math.abs(x); ++i) {
			press(horizontal);
			release(horizontal);
		}
		for(int i = 0; i < Math.abs(y); ++i) {
			press(vertical);
			release(vertical);
		}
	}
	
	/**
	 * mimics the press and release of the spacebar.
	 */
	public static void pressSpace() {
		type(" ");
	}
	
	/**
	 * mimics keyboard combination alt+N. 
	 */
	public static void altN() {
		press(KeyEvent.VK_ALT);
		for(int x = 0; x < Simulator.ALT_N; ++x) 
			type("n");
		release(KeyEvent.VK_ALT);
	}

	/**
	 * mimics keyboard combination alt+O. 
	 */
	public static void altO() {
		press(KeyEvent.VK_ALT);
		for(int x = 0; x < 1; ++x) 
			type("o");
		release(KeyEvent.VK_ALT);
	}

	/**
	 * mimics keyboard combination alt+Q. 
	 */
	public static void altQ() {
		press(KeyEvent.VK_ALT);
		for(int x = 0; x < 1; ++x) 
			type("q");
		release(KeyEvent.VK_ALT);
	}
	
	/**
	 * mimics press and release of enter key
	 */
	public static void pressEnter() {
		press(KeyEvent.VK_ENTER);
		release(KeyEvent.VK_ENTER);
	}

	/**
	 * mimics press and release of escape key
	 */
	public static void pressEscape() {
		press(KeyEvent.VK_ESCAPE);
		release(KeyEvent.VK_ESCAPE);
	}

	/**
	 * mimics press and release of tab key
	 */
	public static void pressTab() {
		press(KeyEvent.VK_TAB);
		release(KeyEvent.VK_TAB);
	}

	/**
	 * mimics press and release of delete key
	 */
	public static void pressDelete() {
		press(KeyEvent.VK_DELETE);
		release(KeyEvent.VK_DELETE);
	}

	/**
	 * mimics press and release of backspace key
	 */
	public static void pressBackspace() {
		press(KeyEvent.VK_BACK_SPACE);
		release(KeyEvent.VK_BACK_SPACE);
	}

	/**
	 * mimics press and release of the tab key n times
	 */
	public static void pressTab(int n) {
		for(int x = 0; x < n; ++x)
			pressTab();
	}
	
	/*
	 * Known unsupported characters: '?'
	 * TODO implement those.
	 */
	public static void type(String input) {
		for(char c : input.toCharArray()) {
			if(c >= 'A' && c <= 'Z') {
				press(KeyEvent.VK_SHIFT);
				press((int)c);
				release((int)c);
				release(KeyEvent.VK_SHIFT);
			}
			else if(c >= 'a' && c <= 'z') {
				press((int)Character.toUpperCase(c));
				release((int)Character.toUpperCase(c));
			}
			else {
				press((int)c);
				release((int)c);
			}
		}
	}
	
	/**
	 * pauses simulator for an amount of milliseconds
	 * @param time, milliseconds that the simulator Thread pauses for.
	 */
	public static void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * private method to pause for a constant amount of time between key presses.
	 */
	private static void throttle() {
		try {
			Thread.sleep(Simulator.THROTTLE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * private method to press a keycode and then throttle
	 * @param keycode
	 */
	private static void press(int keycode) {
		robot.keyPress(keycode);
		throttle();
	}

	/**
	 * private method to release a keycode and then throttle
	 * @param keycode
	 */
	private static void release(int keycode) {
		robot.keyRelease(keycode);
		throttle();
	}
}
