package ChokingHazard;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import Controllers.GameController;


public class RunGame {
	private final static int WIDTH = 1300;
	private final static int HEIGHT = 850;

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		RunGame game = new RunGame();
	}
	
	public RunGame() {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI(){
		GameFrame frame = new GameFrame(WIDTH, HEIGHT);
		@SuppressWarnings("unused")
		GameController controller = new GameController(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
