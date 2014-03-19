package FestivalMiniGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
	boolean isEvenLayout;
	BufferedImage cardBack;
	JLabel cards;
	JLabel currentBid;
	
	public HoldFestivalPlayerPanel(int index, String name, String color, int numFestivalCards, HashMap<String, String> imageHash){
		super(new FlowLayout());
		
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
	
	private void initPanel(int indexOnPanel, String playerName, int numFestivalCards){
		cards = new JLabel();
		JPanel extraPanel = new JPanel();
		
		currentBid = new JLabel("Bid: 0");
		if(indexOnPanel % 2 == 0){
			//if its even
			isEvenLayout = true;
			setPreferredSize(new Dimension(780, 110));
			setBorder(BorderFactory.createEmptyBorder(5, 115, 5, 115));
			cards.setPreferredSize(new Dimension(470, 100));
			extraPanel.setPreferredSize(new Dimension(70, 100));
		}
		else{
			setPreferredSize(new Dimension(110, 560));
			setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			cards.setPreferredSize(new Dimension(100, 470));
			extraPanel.setPreferredSize(new Dimension(100, 70));
			
		}
		extraPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		add(cards);
		add(extraPanel);
		extraPanel.add(new JLabel(playerName));
		//extraPanel.add(new JButton("Drop"));
		extraPanel.add(currentBid);
		
		clearSelectedCard(numFestivalCards);
	}
	
	private int getPreferredWidth(){
		if(isEvenLayout) return 470;
		else return 100;
	}
	
	private int getPreferredHeight(){
		if(isEvenLayout) return 100;
		else return 470;
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

	public void setCurrentPlayer(boolean isTurn){
		if(isTurn){
			setBorder(BorderFactory.createLineBorder(playerColor, 2));
		}
		else
			setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}
	
	public void selectCardAtIndex(int indexOfCard, int numCards, String hashKey){
		System.out.println(indexOfCard);
		BufferedImage card = drawPalaceCardBacks(numCards);
		Graphics2D g2d = card.createGraphics();
		g2d.drawImage(getBufferedImageFromSource(imageSourceHashMap.get(hashKey)), null, indexOfCard*getCardSpacing(numCards), 0);
		cards.setIcon(new ImageIcon(card));
	}
	
	public void clearSelectedCard(int numCards){
		cards.setIcon(new ImageIcon(drawPalaceCardBacks(numCards)));
	}
	
	public void dropPlayer(){
		JPanel clearPanel = new JPanel();
		clearPanel.setBackground(Color.BLACK);
		setBorder(BorderFactory.createEmptyBorder(20,20, 20, 20));
		clearPanel.setPreferredSize(new Dimension(getPreferredWidth(), getPreferredHeight()));
		add(clearPanel);
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
	
	private void saveImage(BufferedImage image){
		System.out.println("saving image");
		try{
			File outputfile = new File("image.png");
		    ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
		    System.out.println(e);
		}
	}
}
