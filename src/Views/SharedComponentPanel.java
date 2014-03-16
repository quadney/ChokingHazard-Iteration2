package Views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SharedComponentPanel extends JPanel{
	private JLabel threeTiles, irrigationTiles, twoPalaceTiles, fourPalaceTiles, sixPalaceTiles, eightPalaceTiles, tenPalaceTiles, festivalCard, palaceDeck;
	private HashMap<String, String> imageSourceHashMap;
	
	public SharedComponentPanel(int numThreeTiles, int numIrrigationTiles, int[] palaceTiles, int numCardsInPalaceDeck){
		//constructor for when creating a new game
		super(new FlowLayout());
		setPreferredSize(new Dimension(1100, 110));
		
		initHashMap();
		initPanel(numThreeTiles, numIrrigationTiles, palaceTiles, numCardsInPalaceDeck, "palaceCard_X");
	}
	
	public SharedComponentPanel(int numThreeTiles, int numIrrigationTiles, int[] palaceTiles, int numCardsInPalaceDeck, String festivalCardHashKey){
		//constructor for when loading
		super(new FlowLayout());
		setPreferredSize(new Dimension(1100, 110));
		
		initHashMap();
		initPanel(numThreeTiles, numIrrigationTiles, palaceTiles, numCardsInPalaceDeck, festivalCardHashKey);
	}
	
	private void initPanel(int numThreeTiles, int numIrrigationTiles, int[] palaceTiles, int numCardsInPalaceDeck, String festivalCardHashKey){
		threeTiles = newJLabel(numThreeTiles+"", imageSourceHashMap.get("layout_threeTile")); 
		threeTiles.setPreferredSize(new Dimension(70, 90));
        add(threeTiles);
        
        irrigationTiles = newJLabel(numIrrigationTiles+"", imageSourceHashMap.get("layout_irrigationTile")); 
        add(irrigationTiles);
        
        twoPalaceTiles = newJLabel(palaceTiles[0]+"", imageSourceHashMap.get("layout_palace2Tile")); 
        add(twoPalaceTiles);
        
        fourPalaceTiles = newJLabel(palaceTiles[1]+"", imageSourceHashMap.get("layout_palace4Tile")); 
        add(fourPalaceTiles);
        
        sixPalaceTiles = newJLabel(palaceTiles[2]+"", imageSourceHashMap.get("layout_palace6Tile")); 
        add(sixPalaceTiles);
        
        eightPalaceTiles = newJLabel(palaceTiles[3]+"", imageSourceHashMap.get("layout_palace8Tile"));
        add(eightPalaceTiles);
        
        tenPalaceTiles = newJLabel(palaceTiles[4]+"", imageSourceHashMap.get("layout_palace10Tile")); 
        add(tenPalaceTiles);

        palaceDeck = newJLabel(numCardsInPalaceDeck+"", imageSourceHashMap.get("layout_palaceDeck"));
        add(palaceDeck);
        
        festivalCard = newJLabel(" ", imageSourceHashMap.get("layout_"+festivalCardHashKey));
        add(festivalCard);

        JLabel actionSummaryCard = new JLabel();
        actionSummaryCard.setIcon(new ImageIcon(imageSourceHashMap.get("layout_actionSummaryCard")));
        actionSummaryCard.setPreferredSize(new Dimension(473, 77));
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
	public void drawFromPalaceDeck(int numPalaceCards){
		this.palaceDeck.setText(""+numPalaceCards);
	}
	public void drawFestivalCard(int numPalaceCards, String newFestivalCardHashKey){
		drawFromPalaceDeck(numPalaceCards);
		this.festivalCard.setIcon(new ImageIcon(imageSourceHashMap.get("layout_"+newFestivalCardHashKey)));
		System.out.println("done");
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
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
}
