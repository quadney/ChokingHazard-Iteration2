package ChokingHazard;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class RunGame {
	private final static int WIDTH = 1100;
	private final static int HEIGHT = 850;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});

	}
	
	private static void createAndShowGUI(){
		GameFrame frame = new GameFrame(WIDTH, HEIGHT, new GameManager());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
