package FestivalMiniGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HoldFestivalPanel extends JPanel{
	private HashMap<String, String> imageSourceHashMap;
	private ArrayList<HoldFestivalPlayerPanel> players;
	private JPanel[] playedCardsPanels;
	private JPanel centerPanel;
	private HoldFestivalController controller;
	
	public HoldFestivalPanel(ArrayList<JavaFestivalPlayer> festivalPlayers, int currentPlayerIndex, String festivalHashKey, int palaceValue){
		super(new BorderLayout());
		
		initHashMap();
		setPreferredSize(new Dimension(780, 780));
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setBackground(Color.WHITE);
		
		initPanels(festivalPlayers, currentPlayerIndex, festivalHashKey);
	}
	
	private void initPanels(ArrayList<JavaFestivalPlayer> festivalPlayers, int currentPlayerIndex, String festivalHashKey){
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(560, 560));
		centerPanel.setLayout(new BorderLayout());
		centerPanel.setBackground(new Color(61, 114, 15));
		add(centerPanel, BorderLayout.CENTER);
		
		JLabel festivalCard = palaceLabel(imageSourceHashMap.get("label_"+festivalHashKey));
		festivalCard.setBorder(BorderFactory.createEmptyBorder(0, 125, 0, 125));
		
		centerPanel.add(festivalCard, BorderLayout.CENTER);

		//init player panels and add them to the panel
		int size = festivalPlayers.size();
		players = new ArrayList<HoldFestivalPlayerPanel>(size);
		playedCardsPanels = new JPanel[4];
		for(int i = 0; i < size; ++i){
			players.add(new HoldFestivalPlayerPanel(this, i, festivalPlayers.get(i).getName(), festivalPlayers.get(i).getColor(), festivalPlayers.get(i).getNumPalaceCards(), imageSourceHashMap));
			if(i == 0){
				playedCardsPanels[i] = new JPanel();
				add(players.get(i), BorderLayout.NORTH);
				playedCardsPanels[i].setPreferredSize(new Dimension(500, 120));
				centerPanel.add(playedCardsPanels[i], BorderLayout.NORTH);
				playedCardsPanels[i].setBackground(new Color(79, 148, 19));
			}
			else if (i == 1){
				playedCardsPanels[i] = new JPanel();
				add(players.get(i), BorderLayout.EAST);
				playedCardsPanels[i].setPreferredSize(new Dimension(120, 500));
				centerPanel.add(playedCardsPanels[i], BorderLayout.EAST);
				playedCardsPanels[i].setBackground(new Color(79, 148, 19));
			}
			else if (i == 2){
				playedCardsPanels[i] = new JPanel();
				add(players.get(i), BorderLayout.SOUTH);
				playedCardsPanels[i].setPreferredSize(new Dimension(500, 120));
				centerPanel.add(playedCardsPanels[i], BorderLayout.SOUTH);
				playedCardsPanels[i].setBackground(new Color(79, 148, 19));
			}
			else if(i == 3){
				playedCardsPanels[i] = new JPanel();
				add(players.get(i), BorderLayout.WEST);
				playedCardsPanels[i].setPreferredSize(new Dimension(120, 500));
				centerPanel.add(playedCardsPanels[i], BorderLayout.WEST);
				playedCardsPanels[i].setBackground(new Color(79, 148, 19));
			}
			
		}
		if(size < 4){
			for(int i = 1; i < 4; i++){
				if(playedCardsPanels[i] == null){
					JPanel emptyPlayer = new HoldFestivalPlayerPanel(i);
					playedCardsPanels[i] = new JPanel();
					if(i == 1){
						add(emptyPlayer, BorderLayout.EAST);
						playedCardsPanels[i].setPreferredSize(new Dimension(120, 500));
						centerPanel.add(playedCardsPanels[i], BorderLayout.EAST);
					}
					else if (i == 2){
						add(emptyPlayer, BorderLayout.SOUTH);
						playedCardsPanels[i].setPreferredSize(new Dimension(500, 120));
						centerPanel.add(playedCardsPanels[i], BorderLayout.SOUTH);
					}
					else{
						add(emptyPlayer, BorderLayout.WEST);
						playedCardsPanels[i].setPreferredSize(new Dimension(120, 500));
						centerPanel.add(playedCardsPanels[i], BorderLayout.WEST);
					}
					playedCardsPanels[i].setBackground(new Color(79, 148, 19));
				}
			}
		}
		
		//set currentIndex as the one who is selected
		if(currentPlayerIndex > -1)
			players.get(currentPlayerIndex).setCurrentPlayer();
		else{
			System.out.println("cannot start festival");
		}
	}
	
	public void setFestivalController(HoldFestivalController c){
		this.controller = c;
	}
	
	public void setCurrentPlayerBid(int index, int points){
		players.get(index).setBidAmount(points);
	}
	
	private JLabel palaceLabel(String src){
		JLabel label= new JLabel();
		label.setIcon(new ImageIcon(src));
		label.setPreferredSize(new Dimension(80, 100));
		return label;
	}
	
	public void endPlayerTurn(int index, int points, int numPalaceCards){
		setCurrentPlayerBid(index, points);
		players.get(index).endTurn(false, numPalaceCards);
	}
	
	public void startPlayerTurn(int index){
		players.get(index).setCurrentPlayer();
	}
	
	public void tabThroughPlayerPalaceCards(int indexOfCard, int numCards, int indexOfPlayer, String hashKey){
		players.get(indexOfPlayer).selectCardAtIndex(indexOfCard, numCards, hashKey);
	}
	
	public void playPalaceCardAtIndex(int indexOfPlayer, String hashKey, int newNumCards){
		players.get(indexOfPlayer).clearSelectedCard(newNumCards);
		addCardToCenterPanelWithImage(hashKey, indexOfPlayer);
	}
	
	public void cancelTabbing(int index, int numCards){
		players.get(index).clearSelectedCard(numCards);
	}
	
	public void playerClickedDropButton(){
		controller.dropPlayerFromFestival();
	}
	
	public void dropCurrentPlayer(int index){
		players.get(index).dropPlayer();
	}
	
	public boolean askIfWouldLikeToSpiltWinnings(){
		int split = JOptionPane.showConfirmDialog(null, " There is a tie - would you like to split the winnings?\nIf not, there will be another round", "Replay Mode", JOptionPane.YES_NO_OPTION);
		if(split == 0)
			return true;
		return false;
	}
	
	public void displayWinner(ArrayList<JavaFestivalPlayer> players, int pointsWon){
		String names = players.get(0).getName();
		if(players.size() > 1){
			for(int i = 1; i < players.size(); i++){
				if(i == (players.size()-1)){
					names = names+" and "+players.get(i).getName();
				}
				else{
					names = names +", "+players.get(i).getName();
				}
			}
			JOptionPane.showMessageDialog(null, "Congrats! Players "+names+" have won this festival", "End of Festival", JOptionPane.INFORMATION_MESSAGE);

		}
		else{
			JOptionPane.showMessageDialog(null, "Congrats! Player "+names+" has won this festival", "End of Festival", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void tellUserThatHeHasToPlayAPalaceCardOrDropOut(){
		JOptionPane.showMessageDialog(null, "You need to either play a Palace card or drop out of the festival", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	public void tellUserThatHeHasToDropOut(){
		JOptionPane.showMessageDialog(null, "Just drop out, you're worthless", "Error", JOptionPane.ERROR_MESSAGE);
	}
	
	private void addCardToCenterPanelWithImage(String imageHashKey, int indexOfPlayer){
		JLabel card = palaceLabel(imageSourceHashMap.get("label_"+imageHashKey));
		if(indexOfPlayer % 2 == 1){
			BufferedImage cardToDraw = getImage(imageSourceHashMap.get(imageHashKey));
			BufferedImage cardLabel = new BufferedImage(cardToDraw.getHeight(), cardToDraw.getWidth(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = cardLabel.createGraphics();
			
			if(indexOfPlayer == 1){
				//rotate from horizontal orientation to vertical, in order to match that of the original card
				g2d.rotate((-1)*Math.PI/2);
				
				//translate the g2d to the correct spot of that image
				g2d.translate((-1)*cardToDraw.getWidth(), 0);
				
				//draw the image
				g2d.drawImage(cardToDraw, null, 0, 0);
			}
			else{
				g2d.rotate(Math.PI/2);
				g2d.translate(0, (-1)*cardToDraw.getHeight());
				g2d.drawImage(cardToDraw, null, 0, 0);
				
			}
			//saveImage(cardLabel);
			g2d.dispose();
			card = new JLabel(new ImageIcon(cardLabel));
			card.setPreferredSize(new Dimension(cardLabel.getWidth(), cardLabel.getHeight()));
		}
		playedCardsPanels[indexOfPlayer].add(card);
		updateUI();
	}
	
	private BufferedImage getImage(String source){
		BufferedImage returnImage = null;
		try{
			returnImage = ImageIO.read(this.getClass().getResource(source));
		} catch(IOException e){
			
		}
		return returnImage;
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
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}

}
