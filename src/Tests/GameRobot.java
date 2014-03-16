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

	public static void click(int x, int y, int times) {
		robot.mouseMove(x, y);
		throttle();
		for(int i = 0; i < times; ++i) {
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		}
	}

	public static void drag(int startX, int startY, int endX, int endY) {
		robot.mouseMove(startX, startY);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.mouseMove(endX, endY);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	}
	
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
	
	public static void pressSpace() {
		type(" ");
	}
	
	public static void altN() {
		press(KeyEvent.VK_ALT);
		for(int x = 0; x < 2; ++x) 
			type("n");
		release(KeyEvent.VK_ALT);
	}
	
	public static void altO() {
		press(KeyEvent.VK_ALT);
		for(int x = 0; x < 2; ++x) 
			type("o");
		release(KeyEvent.VK_ALT);
	}
	
	public static void altQ() {
		press(KeyEvent.VK_ALT);
		for(int x = 0; x < 2; ++x) 
			type("q");
		release(KeyEvent.VK_ALT);
	}
	
	public static void pressEnter() {
		press(KeyEvent.VK_ENTER);
		release(KeyEvent.VK_ENTER);
	}

	public static void pressEscape() {
		press(KeyEvent.VK_ESCAPE);
		release(KeyEvent.VK_ESCAPE);
	}
	
	public static void pressTab() {
		press(KeyEvent.VK_TAB);
		release(KeyEvent.VK_TAB);
	}
	
	public static void pressDelete() {
		press(KeyEvent.VK_DELETE);
		release(KeyEvent.VK_DELETE);
	}
	
	public static void pressBackspace() {
		press(KeyEvent.VK_BACK_SPACE);
		release(KeyEvent.VK_BACK_SPACE);
	}
	
	public static void pressTab(int times) {
		for(int x = 0; x < times; ++x)
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
	
	public static void wait(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void throttle() {
		try {
			Thread.sleep(Simulator.THROTTLE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void press(int keycode) {
		robot.keyPress(keycode);
		throttle();
	}
	
	private static void release(int keycode) {
		robot.keyRelease(keycode);
		throttle();
	}
}
