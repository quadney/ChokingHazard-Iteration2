package ChokingHazard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import Controllers.GameController;
import Views.HelpFrame;
import Views.NewGameFrame;

public class GameFrame extends JFrame {
	private final int WIDTH, HEIGHT;
	NewGameFrame frame;
	GameController gameController;

	public GameFrame(int width, int height){
		this.WIDTH = width;
		this.HEIGHT = height;
		
		setTitle("Java - by Choking Hazard");
		setSize(WIDTH, HEIGHT);
		setResizable(true);
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				gameController.keyReleased(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				gameController.keyPressed(e);
			}
		});
		setFocusTraversalKeysEnabled(false);
		
		addMenu();
	}
	
	private void addMenu(){
		final JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_A);
		
		JMenuItem newGame = new JMenuItem("New Game");
		newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.ALT_MASK));
		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//display the content pane that will allow the user to add players in a nice interface
				//rather than what it looks like now
				frame = new NewGameFrame();
        		frame.setVisible(true);
        		frame.addStartNewGameListener(new StartGameListener());
        		//when it returns, it will do the method to start the new game
			}
		});
		file.add(newGame);
		
		JMenuItem loadGame = new JMenuItem("Open Game");
		loadGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		loadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final File file = getFile();
				System.out.println(file);
				if(file != null){
					//starts a new thread
					new Thread(new Runnable(){
						public void run(){
							gameController.loadGame(file);
						}
					}).start();
				}
			}
		});
		
		file.add(loadGame);
		
		JMenuItem saveGame = new JMenuItem("Save Game");
		saveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
        saveGame.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	if(gameController.saveGame()){
            		
            	}
            	else{
            		
            	}
            }
        }); 
        file.add(saveGame);
        
        JMenuItem exit = new JMenuItem("Exit");
        saveGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	System.exit(0);
            }
        });
        file.add(exit);
 
        JMenu help = new JMenu("Help");
        
        JMenuItem controls = new JMenuItem("Game Controls");
        controls.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.ALT_MASK));
        controls.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
            	//display a frame that shows a keyboard mapping of the game controls
            	HelpFrame frame = new HelpFrame();
        		frame.setVisible(true);
            }
        });
        help.add(controls);
        
        
        //add the menu bar to the JFrame
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(file);
        menuBar.add(help);
        this.setJMenuBar(menuBar);
	}
	
	private File getFile(){
		//this method opens a JFileChooser Dialog so the user
		//may select which file they would like to load
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		File file = null;
		if(chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			file = chooser.getSelectedFile();
		}
		return file;
	}
	
	private boolean askUserIfWouldLikeToSaveGame(){
		//JOptionPane.showOptionDialog(this, "Would you like to save this game?", "Save Game", JOptionPane.YES_NO_CANCEL_OPTION);
		return true;
	}
	
	private void startNewGame(final int numPlayers, final String[] players, final String[] playerColors){
		new Thread(new Runnable(){
			public void run(){
				gameController.createNewGame(numPlayers, players, playerColors);
			}
		}).start();
	}
	
	public void setFrameContent(JPanel panel){
		this.setContentPane(panel);
		this.pack();
		this.validate();
	}
	
	public void giveGameController(GameController gc){
		this.gameController = gc;
	}
	
	class StartGameListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//this action listener is in the gameFrame class
		
			//also check if any of the colors are the same, because they can't be
			int numPlayers = frame.getPlayerSelectionComboBox().getSelectedIndex();
			if(numPlayers == 0){
				//show option pane that the user needs to select players
				frame.displayErrorMessage("Please Select a Number of Players");
			}
			else{
				//now need to parse through the information that the user submitted
				numPlayers += 1;
				JTextField[] playerNames = frame.getPlayerNames();
				JComboBox[] playerColors = frame.getColorSelection();
				String[] players = new String[numPlayers];
				String[] colors = new String[numPlayers];
				for(int i = 0; i < numPlayers; ++i){
					players[i] = playerNames[i].getText();
					colors[i] = playerColors[i].getSelectedItem().toString().toLowerCase();
				}
				
				frame.dispose();
				startNewGame(numPlayers, players, colors);
			}
			
		}
	}

	public static int getPalaceValueFromUser() {
		// TODO Auto-generated method stub
		// This pop up a window and ask for the palace value
		return 0;
	}
	

}
