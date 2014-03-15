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
	private JLabel threePieceTiles, irrigationTiles, twoPalaceTiles, fourPalaceTiles, sixPalaceTiles, eightPalaceTiles, tenPalaceTiles, festivalCard, palaceDeck;
	private HashMap<String, String> imageSourceHashMap;
	
	public SharedComponentPanel(){
		super(new FlowLayout());
		setPreferredSize(new Dimension(1100, 110));
		
		initHashMap();
		initPanel();
	}
	
	private void initPanel(){
		threePieceTiles = newJLabel(imageSourceHashMap.get("layout_threeTile")); 
		threePieceTiles.setPreferredSize(new Dimension(70, 90));
        add(threePieceTiles);
        
        irrigationTiles = newJLabel(imageSourceHashMap.get("layout_irrigationTile")); 
        add(irrigationTiles);
        
        twoPalaceTiles = newJLabel(imageSourceHashMap.get("layout_palace2Tile")); 
        add(twoPalaceTiles);
        
        fourPalaceTiles = newJLabel(imageSourceHashMap.get("layout_palace4Tile")); 
        add(fourPalaceTiles);
        
        sixPalaceTiles = newJLabel(imageSourceHashMap.get("layout_palace6Tile")); 
        add(sixPalaceTiles);
        
        eightPalaceTiles = newJLabel(imageSourceHashMap.get("layout_palace8Tile"));
        add(eightPalaceTiles);
        
        tenPalaceTiles = newJLabel(imageSourceHashMap.get("layout_palace10Tile")); 
        add(tenPalaceTiles);

        palaceDeck = newJLabel(imageSourceHashMap.get("layout_palaceDeck"));
        add(palaceDeck);
        
        //TODO
        festivalCard = newJLabel(imageSourceHashMap.get("layout_festival_DRUM"));
        add(festivalCard);
        

        JLabel actionSummaryCard = new JLabel();
        actionSummaryCard.setIcon(new ImageIcon(imageSourceHashMap.get("layout_actionSummaryCard")));
        actionSummaryCard.setPreferredSize(new Dimension(473, 77));
        actionSummaryCard.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        add(actionSummaryCard);
		
	}
	
	private JLabel newJLabel(String src){
		JLabel label= new JLabel();
		label.setIcon(new ImageIcon(src));
		label.setFont(new Font("Lucida Grande", 0, 14));
		label.setPreferredSize(new Dimension(40, 90));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setVerticalTextPosition(SwingConstants.BOTTOM);
		label.setVerticalAlignment(SwingConstants.BOTTOM);
		label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
		return label;
	}
	
	public void updateThreePieceTiles(int num){
		this.threePieceTiles.setText(""+num);
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
	public void updateFestivalCard(String imageSource){
		this.festivalCard.setIcon(new ImageIcon(imageSource));
		this.festivalCard.setText(" ");
	}
	public void updatePalaceDeck(int num){
		this.palaceDeck.setText(""+num);
	}
	
	public void setGlobalTileValues(int threeTiles, int irrigationTiles, int[] palaceTiles, String festivalCardImage, int palaceDeck){
		updateThreePieceTiles(threeTiles);
		updateIrrigationTiles(irrigationTiles);
		updateTwoPalaceTiles(palaceTiles[0]);
		updateFourPalaceTiles(palaceTiles[1]);
		updateSixPalaceTiles(palaceTiles[2]);
		updateEightPalaceTiles(palaceTiles[3]);
		updateTenPalaceTiles(palaceTiles[4]);
		updateFestivalCard(festivalCardImage);
		updatePalaceDeck(palaceDeck);
	}
	
	private void initHashMap(){
		// TODO test this
		File imageSourceFile = null;
		this.imageSourceHashMap = new HashMap<String, String>();
		try{
			imageSourceFile = new File("/files/SharedComponentImageStrings.txt");
			BufferedReader fileReader = new BufferedReader(new FileReader(imageSourceFile));
			String line = fileReader.readLine();
			String[] hash = line.split(" ");
			imageSourceHashMap.put(hash[0], hash[1]);
			System.out.println("Hash 0: "+hash[0]);
			System.out.println("Hash 1: "+hash[1]);
		} catch(Exception e){
			
		}
		System.out.println("File Name: "+imageSourceFile.getName());
	}
	
}
