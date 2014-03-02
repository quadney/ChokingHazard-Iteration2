package ChokingHazard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Views.HelpFrame;
import Views.NewGameFrame;

public class GameFrame extends JFrame {
	private final int WIDTH, HEIGHT;
	private GameManager gm;

	public GameFrame(int width, int height, GameManager gameManager){
		this.WIDTH = width;
		this.HEIGHT = height;
		this.gm = gameManager;
		gm.giveGameFrame(this);
		
		setTitle("Java - by Choking Hazard");
		setSize(WIDTH, HEIGHT);
		setResizable(true);
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(gm != null)
					gm.keyReleased(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(gm != null)
					gm.keyPressed(e);
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
				NewGameFrame frame = new NewGameFrame();
        		frame.setVisible(true);
				//when it returns, do this:
				new Thread(new Runnable(){
					public void run(){
						
						gm.createNewGame();
//						setContentPane(newGamePanel);
//						pack();
//						validate();
					}
				}).start();
			}
		});
		file.add(newGame);
		
		JMenuItem loadGame = new JMenuItem("Open Game");
		loadGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		loadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final File file = getFile();
				if(file != null){
					//starts a new thread
					new Thread(new Runnable(){
						public void run(){
							gm.loadGame(file);
							
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
            	if(gm.saveGame()){
            		
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
		return true;
	}
	
	public void setFrameContent(JPanel panel){
		this.setContentPane(panel);
		this.pack();
		this.validate();
	}
	

}
