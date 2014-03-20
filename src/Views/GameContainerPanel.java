package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;

import Controllers.GameController;
import FestivalMiniGame.HoldFestivalFrame;
import Models.JavaPlayer;
import Models.PalaceCard;


@SuppressWarnings("serial")
public class GameContainerPanel extends JPanel {
	private final static int WIDTH = 1300;
	private final static int HEIGHT = 840;
	private HashMap<String, String> imageSourceHashMap; 
	private DisplayPlayersPalaceCardsFrame palaceCardFrame;
	private HoldFestivalFrame festivalFrame;
	
	public GameContainerPanel(BoardPanel board, PlayerPanel[] players, SharedComponentPanel shared){
		super(new BorderLayout());
		
		initHashMap();
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(0, 25, 10, 25));
		
		initPanels(board, players, shared);
	}
	
	private void initPanels(BoardPanel board, PlayerPanel[] players, SharedComponentPanel shared){
		add(shared, BorderLayout.NORTH);
		
		add(board, BorderLayout.CENTER);
		
		JPanel leftColumn = new JPanel();
		leftColumn.setLayout(new BorderLayout());
		leftColumn.setPreferredSize(new Dimension(205, 700));
		leftColumn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		add(leftColumn, BorderLayout.WEST);
		
		JPanel rightColumn = new JPanel();
		rightColumn.setLayout(new BorderLayout());
		rightColumn.setPreferredSize(new Dimension(205, 700));
		rightColumn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		add(rightColumn, BorderLayout.EAST);
		
		for(int i = 0; i < players.length; i++){
			switch(i){
				case 0:
					leftColumn.add(players[i], BorderLayout.NORTH);
					break;
				case 1:
					rightColumn.add(players[i], BorderLayout.NORTH);
					break;
				case 2:
					leftColumn.add(players[i], BorderLayout.SOUTH);
					break;
				case 3:
					rightColumn.add(players[i], BorderLayout.SOUTH);
					break;
			}
		}
	}
	
	private void initHashMap(){
		File imageSourceFile = null;
		this.imageSourceHashMap = new HashMap<String, String>();
		try{
			imageSourceFile = new File("bin/files/FestivalImageStrings.txt");
			BufferedReader fileReader = new BufferedReader(new FileReader(imageSourceFile));
			String line = "";
			while((line = fileReader.readLine()) != null){
				String[] hash = line.split(" ");
				imageSourceHashMap.put(hash[0], hash[1]);
			}
			fileReader.close();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public int promptUserForPalaceValue(){
		String[] palaces = {"2", "4", "6", "8", "10"};
		JComboBox<String> palaceComboBox = new JComboBox<String>(palaces);
		palaceComboBox.setEditable(true);
		JOptionPane.showMessageDialog( null, palaceComboBox, "Select what Palace Value you would like to place", JOptionPane.QUESTION_MESSAGE);
		
		return ((palaceComboBox.getSelectedIndex()+1)*2);
	}
	
	public boolean askUserIfWouldLikeToHoldAPalaceFestival(){
		int holdFestival = JOptionPane.showConfirmDialog(null, "Would you like to hold a Palace Festival?", "Let's Party!", JOptionPane.YES_NO_OPTION);
		if(holdFestival == 0)
			return true;
		return false;
	}
	
	public boolean askUserIfWouldLikeToEnterReplayMode(){
		int replay = JOptionPane.showConfirmDialog(null, "Would you like to enter Replay Mode?", "Replay Mode", JOptionPane.YES_NO_OPTION);
		if(replay == 0)
			return true;
		return false;
	}
	
	public boolean askUserIfWouldLikeToSaveChangesFromPlanningMode(){
		int saveChanges = JOptionPane.showConfirmDialog(null, "Would you like to Play mode to reflect these changes?", "Planning Mode", JOptionPane.YES_NO_OPTION);
		if(saveChanges == 0)
			return true;
		return false;
	}
	
	public String askUserWhatNameToSaveGameAs(){
		String fileName = "";
		boolean okFileName = false;
		while(!okFileName ){
			try{
				fileName = JOptionPane.showInputDialog("Name save file as?");
				String s = ".*[ /\\\\?%*:|\"<>.].*";
				if (fileName.matches(s) || fileName.equals("")){
					JOptionPane.showMessageDialog(null, "Please enter a valid name");
				}
				else {
					okFileName = true;
				}
			}catch(NullPointerException e){
				return null;
			}
		}
		return fileName;
	}
	
	public void displayPalaceCardFrame(JavaPlayer player){
		palaceCardFrame = new DisplayPlayersPalaceCardsFrame(player, imageSourceHashMap);
		palaceCardFrame.setVisible(true);
	}
	
	public void displayHoldFestivalFrame(GameController game, JavaPlayer[] players, int indexOfPlayerHoldingFestival, PalaceCard festivalCard, int selectedPalaceValue){
		festivalFrame = new HoldFestivalFrame(game, players, indexOfPlayerHoldingFestival, festivalCard, selectedPalaceValue);
	}
	
	public void closeFestivalFrame(){
		festivalFrame.dispose();
	}
	
	public void tellPeopleTheyAintPlacedNoLandTile(){
		JOptionPane.showMessageDialog( null, "You haven't placed a land tile :(");
	}

	public void makeErrorSound() {
		//TODO makes sound every time a player presses a button and it wasn't a valid move
		//Called by the GameController
	}

}
