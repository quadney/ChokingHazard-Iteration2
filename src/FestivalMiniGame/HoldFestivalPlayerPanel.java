package FestivalMiniGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HoldFestivalPlayerPanel extends JPanel {
	private HashMap<String, String> imageSourceHashMap;
	private Color playerColor;
	private boolean isEvenLayout;
	private BufferedImage cardBack;
	private HoldFestivalPanel parentPanel;
	private JLabel cards, currentBid, playerLabel;
	private JPanel extraPanel;
	private JButton drop;
	
	public HoldFestivalPlayerPanel(HoldFestivalPanel parent, int index, String name, String color, int numFestivalCards, HashMap<String, String> imageHash){
		super(new FlowLayout());
		
		this.parentPanel = parent;
		//convert the color string to color
		try {
			Field field = Color.class.getField(color);
			this.playerColor = (Color)field.get(null);
		} catch (Exception e) {
			this.playerColor = Color.black;
		} 
		
		this.imageSourceHashMap = imageHash;
		this.cardBack = getBufferedImageFromSource(imageSourceHashMap.get("palaceCard_back"));
		
		initPanel(index, name, numFestivalCards);
	}
	
	public HoldFestivalPlayerPanel(int index){
		super(new FlowLayout());
		//constructor for an empty panel, place holder for the Festival frame
		cards = new JLabel();
		extraPanel = new JPanel();
		currentBid = new JLabel();
		playerLabel = new JLabel();
		if(index % 2 == 0){
			//if its even
			isEvenLayout = true;
			setPreferredSize(new Dimension(780, 110));
			setBorder(BorderFactory.createEmptyBorder(5, 115, 5, 115));
			cards.setPreferredSize(new Dimension(470, 100));
			extraPanel.setPreferredSize(new Dimension(70, 100));
		}
		else{
			setPreferredSize(new Dimension(110, 560));
			setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
			cards.setPreferredSize(new Dimension(100, 470));
			extraPanel.setPreferredSize(new Dimension(100, 70));
		}
		setBackground(Color.WHITE);
		extraPanel.setBackground(Color.WHITE);
		
		add(cards);
		add(extraPanel);
		dropPlayer();
	}
	
	private void initPanel(int indexOnPanel, String playerName, int numFestivalCards){
		cards = new JLabel();
		extraPanel = new JPanel();
		
		currentBid = new JLabel("Bid: 0");
		currentBid.setFont(new Font("Lucida Grande", 0, 16));
		
		if(indexOnPanel % 2 == 0){
			//if its even
			isEvenLayout = true;
			setPreferredSize(new Dimension( 780, getPreferredHeight()+10 ));
			setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			cards.setPreferredSize(new Dimension(getPreferredWidth(), getPreferredHeight()));
			extraPanel.setPreferredSize(new Dimension(85, getPreferredHeight()));
		}
		else{
			setPreferredSize(new Dimension( getPreferredWidth()+10 , 560));
			setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			cards.setPreferredSize(new Dimension( getPreferredWidth(), getPreferredHeight() ));
			extraPanel.setPreferredSize(new Dimension( getPreferredWidth(), 85));			
		}
		setBackground(Color.WHITE);
		extraPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		extraPanel.setBackground(Color.WHITE);
		
		add(cards);
		add(extraPanel);
		playerLabel = new JLabel(playerName);
		
		extraPanel.add(playerLabel);
		
		drop = new JButton("Drop");
		extraPanel.add(drop);
		drop.setFocusable(false);
		drop.setEnabled(false);
		drop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicked on the drop button");
				parentPanel.playerClickedDropButton();
			}
		});
		
		extraPanel.add(currentBid);
		
		clearSelectedCard(numFestivalCards);
	}
	
	private int getPreferredWidth(){
		if(isEvenLayout) return 470;
		else return 100;
	}
	
	private int getPreferredHeight(){
		if(isEvenLayout) return 100;
		else return 435;
	}
	
	private int getCardSpacing(int numCards){
		if(numCards == 0) return 0;
		if(isEvenLayout){
			if(numCards < 8){
				return 100;
			}
			return getPreferredWidth()/numCards;
		}
		else{
			if(numCards < 6){
				return 105;
			}
			return getPreferredHeight()/numCards;
		}
	}
	
	public void setBidAmount(int amount){
		this.currentBid.setText("Bid: "+amount);
	}
	
	public void endTurn(boolean isTurn, int numCards){
		setBorder(BorderFactory.createLineBorder(Color.WHITE));
		drop.setEnabled(false);
		clearSelectedCard(numCards);
	}

	public void setCurrentPlayer(){
		setBorder(BorderFactory.createLineBorder(playerColor, 2));
		drop.setEnabled(true);
		System.out.println(drop.isEnabled());		
	}
	
	public void selectCardAtIndex(int indexOfCard, int numCards, String hashKey){
		BufferedImage card = drawPalaceCardBacks(numCards);
		Graphics2D g2d = card.createGraphics();
		if(isEvenLayout)
			g2d.drawImage(getBufferedImageFromSource(imageSourceHashMap.get(hashKey)), null, indexOfCard*getCardSpacing(numCards), 0);
		else{
			//need to rotate the card
			g2d.drawImage(getBufferedImageFromSource(imageSourceHashMap.get(hashKey)), null, 0, indexOfCard*getCardSpacing(numCards));
		}
		
		cards.setIcon(new ImageIcon(card));
	}
	
	public void clearSelectedCard(int numCards){
		cards.setIcon(new ImageIcon(drawPalaceCardBacks(numCards)));
	}
	
	public void dropPlayer(){
		ImageIcon icon = new ImageIcon();
		cards.setIcon(icon);
		currentBid.setText("");
		extraPanel.setBorder(BorderFactory.createEmptyBorder());
		playerLabel.setText("");
		drop.setVisible(false);
		updateUI();
	}
	
	private BufferedImage drawPalaceCardBacks(int numPalaceCards){
		int spacing = getCardSpacing(numPalaceCards);
		BufferedImage cards = new BufferedImage(getPreferredWidth(), getPreferredHeight(), BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g2d = cards.createGraphics();
		for(int i = 0; i <numPalaceCards; ++i){
			if(isEvenLayout)
				g2d.drawImage(cardBack, null, i*spacing, 0);
			else{
				g2d.drawImage(cardBack, null, 0, i*spacing);
			}
		}
		return cards;
	}
	
	private BufferedImage getBufferedImageFromSource(String src){
		try{
			BufferedImage image = ImageIO.read(this.getClass().getResource(src));
			return image;
		}catch(IOException e){
			System.out.println("there was an error");
			System.out.println(e);
		}
		return null;
	}
}
