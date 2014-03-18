package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Models.JavaPlayer;
import Models.PalaceCard;

@SuppressWarnings("serial")
public class DisplayPlayersPalaceCardsFrame extends JFrame {
	JLabel playerNameLabel;
	private HashMap<String, String> imageSourceHashMap;
	int width = 800;
	int height = 500;

	public DisplayPlayersPalaceCardsFrame(JavaPlayer player, HashMap<String, String> imageHash){
		this.imageSourceHashMap = imageHash;
		setTitle("Your Secret Palace Cards");
		setSize(width, height);
		setResizable(false);
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == 70){
					dispose();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		setFocusTraversalKeysEnabled(false);
		
		initPanels(player);
		
	}
	
	private void initPanels(JavaPlayer player){
		JPanel palaceCardPanel = new JPanel();
		palaceCardPanel.setPreferredSize(new Dimension(width, height));
		palaceCardPanel.setLayout(new BorderLayout());
		palaceCardPanel.setBackground(Color.white);
		palaceCardPanel.setBorder(BorderFactory.createEmptyBorder(50, 60, 50, 60));
		add(palaceCardPanel);
		
		playerNameLabel = new JLabel(player.getName()+"'s Palace Cards");
		palaceCardPanel.add(playerNameLabel, BorderLayout.NORTH);
		
		JPanel cardsPanel = new JPanel();
		cardsPanel.setPreferredSize(new Dimension(600, 330));
		cardsPanel.setMaximumSize(new Dimension(600, 330));
		cardsPanel.setBackground(Color.WHITE);
		cardsPanel.setLayout(new FlowLayout());
		palaceCardPanel.add(cardsPanel, BorderLayout.CENTER);
		
		ArrayList<PalaceCard> cards = player.getPalaceCards();
		for(int i = 0; i < cards.size(); i++){
			JLabel cardLabel = createPalaceCardJLabel("label_"+cards.get(i).getType());
			System.out.println(cards.get(i).getType());
			cardsPanel.add(cardLabel);
		}
	}
	
	private JLabel createPalaceCardJLabel(String hashKey){
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(imageSourceHashMap.get(hashKey)));
		label.setPreferredSize(new Dimension(80, 110));
		label.setBorder(BorderFactory.createEmptyBorder(0, 5, 10, 5));
		return label;
	}
}
