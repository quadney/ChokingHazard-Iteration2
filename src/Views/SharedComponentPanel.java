package Views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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
		threePieceTiles = newJLabel("bin/images/layout/layout_threeTile.png"); 
		threePieceTiles.setPreferredSize(new Dimension(70, 90));
        add(threePieceTiles);
        
        irrigationTiles = newJLabel("bin/images/layout/layout_oneTile_irrigation.png"); 
        add(irrigationTiles);
        
        twoPalaceTiles = newJLabel("bin/images/layout/layout_oneTile_2.png"); 
        add(twoPalaceTiles);
        
        fourPalaceTiles = newJLabel("bin/images/layout/layout_oneTile_4.png"); 
        add(fourPalaceTiles);
        
        sixPalaceTiles = newJLabel("bin/images/layout/layout_oneTile_6.png"); 
        add(sixPalaceTiles);
        
        eightPalaceTiles = newJLabel("bin/images/layout/layout_oneTile_8.png");
        add(eightPalaceTiles);
        
        tenPalaceTiles = newJLabel("bin/images/layout/layout_oneTile_10.png"); 
        add(tenPalaceTiles);

        palaceDeck = newJLabel("bin/images/layout/layout_cardDeck.png");
        add(palaceDeck);
        
        festivalCard = newJLabel("bin/images/layout/layout_festivalCard_DRUM.png");
        add(festivalCard);
        

        JLabel actionSummaryCard = new JLabel();
        actionSummaryCard.setIcon(new ImageIcon("bin/images/layout/actionSummaryCard.png"));
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
	private void initHashMap(){
		this.imageSourceHashMap = new HashMap();
		//imageSourceHashMap.put(String )
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
	
}
