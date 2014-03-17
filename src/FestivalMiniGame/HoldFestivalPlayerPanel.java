package FestivalMiniGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
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
	JLabel cards;
	boolean isEvenLayout;
	
	public HoldFestivalPlayerPanel(int index, String name, String color, int numFestivalCards, HashMap<String, String> imageHash){
		super(new FlowLayout());
		
		//convert the color string to color
		try {
			Field field = Color.class.getField(color);
			this.playerColor = (Color)field.get(null);
		} catch (Exception e) {
			System.out.println("There was an image parseing the color string to a color");
			this.playerColor = Color.black;
		} 
		
		this.imageSourceHashMap = imageHash;
		
		initPanel(index, name, numFestivalCards);
	}
	
	private void initPanel(int indexOnPanel, String playerName, int numFestivalCards){
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		cards = new JLabel();
		if(indexOnPanel % 2 == 0){
			//if its even
			isEvenLayout = true;
		}
		int width = getPreferredWidth();
		int height = getPreferredHeight();
		setPreferredSize(new Dimension(width, height));
		
		clearSelectedCard(numFestivalCards);
	}
	
	private int getPreferredWidth(){
		if(isEvenLayout)
			return 550;
		return 125;
	}
	
	private int getPreferredHeight(){
		if(isEvenLayout){
			return 125;
		}
		return 650;
	}
	
	private int getCardSpacing(int numCards){
		if(isEvenLayout){
			return (getPreferredWidth() - (60*numCards))/numCards;
		}
		else
			return (getPreferredHeight() - (100*numCards)/numCards);
	}

	public void setCurrentPlayer(boolean isTurn){
		if(isTurn){
			setBorder(BorderFactory.createLineBorder(playerColor, 3));
		}
		else
			setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}
	
	public void selectCardAtIndex(int indexOfCard, int numCards, String hashKey){
		BufferedImage card = drawPalaceCardBacks(numCards);
		Graphics2D g2d = card.createGraphics();
		g2d.drawImage(getBufferedImageFromSource(imageSourceHashMap.get(hashKey)), null, indexOfCard*getCardSpacing(numCards), 0);
		cards.setIcon(new ImageIcon(card));
	}
	
	public void clearSelectedCard(int numCards){
		this.cards.setIcon(new ImageIcon(drawPalaceCardBacks(numCards)));
	}
	
	private BufferedImage drawPalaceCardBacks(int numPalaceCards){
		int spacing = getCardSpacing(numPalaceCards);
		BufferedImage cards = new BufferedImage(getPreferredWidth(), getPreferredHeight(), BufferedImage.TYPE_INT_ARGB);
		BufferedImage cardBack = getBufferedImageFromSource(imageSourceHashMap.get("palaceCard_back"));
		
		Graphics2D g2d = cards.createGraphics();
		for(int i = 0; i <numPalaceCards; ++i){
			if(isEvenLayout)
				g2d.drawImage(cardBack, null, i*spacing, 0);
			else{
				if(i % 2 == 0){
					g2d.drawImage(cardBack, null, 0, i*spacing);
				}
				else{
					g2d.drawImage(cardBack, null, 50, i*spacing);
				}
			}
		}
		return cards;
	}
	
	private BufferedImage getBufferedImageFromSource(String src){
		try{
			BufferedImage image = ImageIO.read(this.getClass().getResource(src));
			return image;
		}catch(IOException e){
				System.out.println(e);
		}
		System.out.println("there was an error");
		return null;
	}
}
