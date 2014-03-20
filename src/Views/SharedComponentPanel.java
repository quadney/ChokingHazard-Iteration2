package Views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import Controllers.GameController;
import Models.PalaceCard;

@SuppressWarnings("serial")
public class SharedComponentPanel extends JPanel{
	private JLabel threeTiles, irrigationTiles, twoPalaceTiles, fourPalaceTiles, sixPalaceTiles, eightPalaceTiles, tenPalaceTiles;
	private JButton palaceDeck, festivalCard;
	private JButton playModeToggleButton, replayButton;
	private boolean playMode = true;
	private HashMap<String, String> imageSourceHashMap;
	
	public SharedComponentPanel(String festivalCardHashKey, GameController gameController){
		super(new FlowLayout());
		setPreferredSize(new Dimension(1170, 110));
		
		initHashMap();
		initPanel(festivalCardHashKey, gameController);
	}
	
	private void initPanel(String festivalCardHashKey, final GameController gameController){
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(140, 90));
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		add(buttonPanel);
		
		playModeToggleButton = new JButton("Planning Mode");
		playModeToggleButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(playMode){
					playModeToggleButton.setText("Play Mode");
					playMode = false;
					gameController.startPlanningMode();
				}
				else{
					playModeToggleButton.setText("Planning Mode");
					playMode = true;
					if(!gameController.askUserIfWouldLikeToSaveChangesFromPlanningMode()) {
						gameController.undoUntilLastPlayingMode();
					}
					gameController.startPlayingMode();
				}
			
			}
		});
		playModeToggleButton.setFocusable(false);
		buttonPanel.add(playModeToggleButton);
		
		replayButton = new JButton("Replay");
		replayButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//temporarily set the button disabled while it replays, then when finished replaying set it to enabled
				if(gameController.askUserIfWouldLikeToEnterReplayMode()) {
					setReplayButtonEnabled(false);
					SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
						@Override
						protected Void doInBackground() throws Exception {
							gameController.startReplay();
							setReplayButtonEnabled(true);
							return null;
						}
					};
					worker.execute();
				}
			}
		});
		replayButton.setFocusable(false);
		buttonPanel.add(replayButton);
		
		threeTiles = newJLabel("", imageSourceHashMap.get("layout_threeTile")); 
		threeTiles.setPreferredSize(new Dimension(70, 90));
        add(threeTiles);
        
        irrigationTiles = newJLabel("", imageSourceHashMap.get("layout_irrigationTile")); 
        add(irrigationTiles);
        
        twoPalaceTiles = newJLabel("", imageSourceHashMap.get("layout_palace2Tile")); 
        add(twoPalaceTiles);
        
        fourPalaceTiles = newJLabel("", imageSourceHashMap.get("layout_palace4Tile")); 
        add(fourPalaceTiles);
        
        sixPalaceTiles = newJLabel("", imageSourceHashMap.get("layout_palace6Tile")); 
        add(sixPalaceTiles);
        
        eightPalaceTiles = newJLabel("", imageSourceHashMap.get("layout_palace8Tile"));
        add(eightPalaceTiles);
        
        tenPalaceTiles = newJLabel("", imageSourceHashMap.get("layout_palace10Tile")); 
        add(tenPalaceTiles);

        palaceDeck = new JButton("", new ImageIcon(imageSourceHashMap.get("layout_palaceDeck")));
        palaceDeck.setFont(new Font("Lucida Grande", 0, 14));
        palaceDeck.setHorizontalTextPosition(SwingConstants.CENTER);
        palaceDeck.setVerticalTextPosition(SwingConstants.BOTTOM);
        palaceDeck.setVerticalAlignment(SwingConstants.BOTTOM);
        palaceDeck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.pickUpPalaceCard();
//				System.out.println("draw from palace deck");
			}
		});
        palaceDeck.setFocusable(false);
        add(palaceDeck);
        //palaceDeck = newJLabel("", imageSourceHashMap.get("layout_palaceDeck"));
        //add(palaceDeck);
        
        festivalCard = new JButton("   ", new ImageIcon(imageSourceHashMap.get("layout_"+festivalCardHashKey)));
        festivalCard.setFont(new Font("Lucida Grande", 0, 14));
        festivalCard.setHorizontalTextPosition(SwingConstants.CENTER);
        festivalCard.setVerticalTextPosition(SwingConstants.BOTTOM);
        festivalCard.setVerticalAlignment(SwingConstants.BOTTOM);
        festivalCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameController.pickUpFestivalCard();
//				System.out.println("draw festival card");
			}
		});
        festivalCard.setFocusable(false);
        add(festivalCard);
//        festivalCard = newJLabel(" ", imageSourceHashMap.get("layout_"+festivalCardHashKey));
//        add(festivalCard);

        JLabel actionSummaryCard = new JLabel();
        actionSummaryCard.setIcon(new ImageIcon(imageSourceHashMap.get("layout_actionSummaryCard")));
        actionSummaryCard.setPreferredSize(new Dimension(533, 78));
        actionSummaryCard.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        add(actionSummaryCard);
		
	}
	
	private JLabel newJLabel(String value, String src){
		JLabel label= new JLabel(value);
		label.setIcon(new ImageIcon(src));
		label.setFont(new Font("Lucida Grande", 0, 14));
		label.setPreferredSize(new Dimension(40, 90));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.BOTTOM);
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		return label;
	}
	
	//------------- ACTIONS ------------------
	
	public void updateThreePieceTiles(int num){
		this.threeTiles.setText(""+num);
	}
	public void updateIrrigationTiles(int num){
		this.irrigationTiles.setText(""+num);
	}
	public void updateTwoPalaceTiles(int num){
		this.twoPalaceTiles.setText(""+num);
	}
	public void updateFourPalaceTiles(int num){
		this.fourPalaceTiles.setText(""+num);
	}
	public void updateSixPalaceTiles(int num){
		this.sixPalaceTiles.setText(""+num);
	}
	public void updateEightPalaceTiles(int num){
		this.eightPalaceTiles.setText(""+num);
	}
	public void updateTenPalaceTiles(int num){
		this.tenPalaceTiles.setText(""+num);
	}
	public void updateNumPalaceCards(int num){
		this.palaceDeck.setText(""+num);
	}
	public void updateFestivalCard(String newFestivalCardHashKey){
		this.festivalCard.setIcon(new ImageIcon(imageSourceHashMap.get("layout_"+newFestivalCardHashKey)));
	}
	
	public void setReplayButtonEnabled(boolean yes){
		replayButton.setEnabled(yes);
	}
	
	public void updatePalaceTiles(int num, int value){
		if(value == 2){
			updateTwoPalaceTiles(num);
		}
		else if(value == 4){
			updateFourPalaceTiles(num);
		}
		else if(value == 6){
			updateSixPalaceTiles(num);
		}
		else if(value == 8){
			updateEightPalaceTiles(num);
		}
		else if(value == 10){
			updateTenPalaceTiles(num);
		}
		
	}
	
	
	public void drawFromPalaceDeck(int numPalaceCards){
		this.palaceDeck.setText(""+numPalaceCards);
	}
	public void drawFestivalCard(int numPalaceCards, String newFestivalCardHashKey){
		drawFromPalaceDeck(numPalaceCards);
		this.festivalCard.setIcon(new ImageIcon(imageSourceHashMap.get("layout_"+newFestivalCardHashKey)));
	}
	
	private void initHashMap(){
		File imageSourceFile = null;
		this.imageSourceHashMap = new HashMap<String, String>();
		try{
			imageSourceFile = new File("bin/files/SharedComponentImageStrings.txt");
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

	public void updateFestivalCard(PalaceCard card) {
		System.out.println(imageSourceHashMap);
		if(card.isFaceUp())
			festivalCard.setIcon(new ImageIcon(imageSourceHashMap.get("layout_" + card.getType())));
		else
			festivalCard.setIcon(new ImageIcon(imageSourceHashMap.get("layout_palaceDeck")));
	}
	
}
