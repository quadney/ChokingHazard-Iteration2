package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Models.PlayerModel;

public class GamePanel extends JPanel {
	private final static int WIDTH = 1300;
	private final static int HEIGHT = 840;
	private JLabel threePieceTiles, irrigationTiles, twoPalaceTiles, fourPalaceTiles, sixPalaceTiles, eightPalaceTiles, tenPalaceTiles, festivalCard, palaceDeck; 
	BoardPanel board;
	PlayerPanel[] players;
	
	public GamePanel(int numberOfPlayers){
		super(new BorderLayout());
		players = new PlayerPanel[numberOfPlayers];
		
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(0, 25, 30, 25));
		
		initPanels(numberOfPlayers);
	}
	
	private void initPanels(int numberOfPlayers){
		JPanel topContent = new JPanel();
		topContent.setLayout(new FlowLayout());
		topContent.setPreferredSize(new Dimension(1100, 110));
		add(topContent, BorderLayout.NORTH);
		
		threePieceTiles = newJLabel("bin/images/layout/layout_threeTile.png"); 
		threePieceTiles.setPreferredSize(new Dimension(70, 90));
        topContent.add(threePieceTiles);
        
        irrigationTiles = newJLabel("bin/images/layout/layout_oneTile_irrigation.png"); 
        topContent.add(irrigationTiles);
        
        twoPalaceTiles = newJLabel("bin/images/layout/layout_oneTile_2.png"); 
        topContent.add(twoPalaceTiles);
        
        fourPalaceTiles = newJLabel("bin/images/layout/layout_oneTile_4.png"); 
        topContent.add(fourPalaceTiles);
        
        sixPalaceTiles = newJLabel("bin/images/layout/layout_oneTile_6.png"); 
        topContent.add(sixPalaceTiles);
        
        eightPalaceTiles = newJLabel("bin/images/layout/layout_oneTile_8.png");
        topContent.add(eightPalaceTiles);
        
        tenPalaceTiles = newJLabel("bin/images/layout/layout_oneTile_10.png"); 
        topContent.add(tenPalaceTiles);

        palaceDeck = newJLabel("bin/images/layout/layout_cardDeck.png");
        topContent.add(palaceDeck);
        
        festivalCard = newJLabel("bin/images/layout/layout_festivalCard_DRUM.png");
        topContent.add(festivalCard);
        
        JLabel actionSummaryCard = new JLabel();
        actionSummaryCard.setIcon(new ImageIcon("bin/images/layout/actionSummaryCard.png"));
        actionSummaryCard.setPreferredSize(new Dimension(473, 77));
        actionSummaryCard.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        topContent.add(actionSummaryCard);
		
		board = new BoardPanel();
		add(board, BorderLayout.CENTER);
		
		JPanel leftColumn = new JPanel();
		leftColumn.setLayout(new BorderLayout());
		leftColumn.setPreferredSize(new Dimension(175, 700));
		leftColumn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		add(leftColumn, BorderLayout.WEST);
		
		JPanel rightColumn = new JPanel();
		rightColumn.setLayout(new BorderLayout());
		rightColumn.setPreferredSize(new Dimension(175, 700));
		rightColumn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		add(rightColumn, BorderLayout.EAST);
		
		for(int i = 0; i < numberOfPlayers; i++){
			players[i] = new PlayerPanel("Player Name", "red");
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
	
	/* ----- SETTERS FOR THIS CLASS'S COMPONENTS ----- */
//	public void setPlayerPanels(PlayerModel[] playerModels){
//		for(int i = 0; i < players.length; ++i){
//			players[i] = new PlayerPanel(playerModels[i].getName(), playerModels[i].getColor());
//		}
//	}
	public void setThreePieceTiles(int num){
		this.threePieceTiles.setText(""+num);
	}
	public void setIrrigationTiles(int num){
		this.irrigationTiles.setText(""+num);
	}
	public void setTwoPalaceTiles(int num){
		this.twoPalaceTiles.setText(""+num);
	}
	public void setFourPalaceTiles(int num){
		this.fourPalaceTiles.setText(""+num);
	}
	public void setSixPalaceTiles(int num){
		this.sixPalaceTiles.setText(""+num);
	}
	public void setEightPalaceTiles(int num){
		this.eightPalaceTiles.setText(""+num);
	}
	public void setTenPalaceTiles(int num){
		this.tenPalaceTiles.setText(""+num);
	}
	public void setFestivalCard(String imageSource){
		this.festivalCard.setIcon(new ImageIcon(imageSource));
		this.festivalCard.setText(" ");
	}
	public void setPalaceDeck(int num){
		this.palaceDeck.setText(""+num);
	}
	public void setGlobalTileValues(int threeTiles, int irrigationTiles, int[] palaceTiles, String festivalCardImage, int palaceDeck){
		setThreePieceTiles(threeTiles);
		setIrrigationTiles(irrigationTiles);
		setTwoPalaceTiles(palaceTiles[0]);
		setFourPalaceTiles(palaceTiles[1]);
		setSixPalaceTiles(palaceTiles[2]);
		setEightPalaceTiles(palaceTiles[3]);
		setTenPalaceTiles(palaceTiles[4]);
		setFestivalCard(festivalCardImage);
		setPalaceDeck(palaceDeck);
	}

}
