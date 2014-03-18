package FestivalMiniGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

public class HoldFestivalPanel extends JPanel{
	private HashMap<String, String> imageSourceHashMap;
	private HoldFestivalPlayerPanel[] players;
	private JPanel[] playedCardsPanels;
	private JPanel centerPanel;
	private HoldFestivalController controller;
	private JButton dropFromFestival;
	
	public HoldFestivalPanel(JavaFestivalPlayer[] festivalPlayers, int currentPlayerIndex, String festivalHashKey, int palaceValue){
		super(new BorderLayout());
		
		initHashMap();
		setPreferredSize(new Dimension(750, 750));
		setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		setBackground(Color.WHITE);
		
		dropFromFestival = new JButton("Drop Out of Festival");
		dropFromFestival.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.dropPlayerFromFestival();
			}
		});
		
		initPanels(festivalPlayers, currentPlayerIndex, festivalHashKey);
	}
	
	private void initPanels(JavaFestivalPlayer[] festivalPlayers, int currentPlayerIndex, String festivalHashKey){
		centerPanel = new JPanel();
		centerPanel.setPreferredSize(new Dimension(550, 550));
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new BorderLayout());
		add(centerPanel, BorderLayout.CENTER);
		
		centerPanel.add(palaceLabel(imageSourceHashMap.get("label_"+festivalHashKey)), BorderLayout.CENTER);
		
//		add(dropFromFestival);
//		dropFromFestival.setVisible(false);

		//init player panels and add them to the panel
		players = new HoldFestivalPlayerPanel[festivalPlayers.length];
		playedCardsPanels = new JPanel[festivalPlayers.length];
		for(int i = 0; i < players.length; ++i){
			if(!festivalPlayers[i].checkIfInFestival()){
				players[i] = null;
				playedCardsPanels[i] = null;
			}
			else{
				players[i] = new HoldFestivalPlayerPanel(i, festivalPlayers[i].getName(), festivalPlayers[i].getColor(), festivalPlayers[i].getNumPalaceCards(), imageSourceHashMap);
				playedCardsPanels[i] = new JPanel();
				if(i == 0){
					add(players[i], BorderLayout.NORTH);
					playedCardsPanels[i].setPreferredSize(new Dimension(500, 100));
					centerPanel.add(playedCardsPanels[i], BorderLayout.NORTH);
				}
				else if (i == 1){
					add(players[i], BorderLayout.EAST);
					playedCardsPanels[i].setPreferredSize(new Dimension(100, 500));
					centerPanel.add(playedCardsPanels[i], BorderLayout.EAST);
				}
				else if (i == 2){
					add(players[i], BorderLayout.SOUTH);
					playedCardsPanels[i].setPreferredSize(new Dimension(500, 100));
					centerPanel.add(playedCardsPanels[i], BorderLayout.SOUTH);
				}
				else{
					add(players[i], BorderLayout.WEST);
					playedCardsPanels[i].setPreferredSize(new Dimension(100, 500));
					centerPanel.add(playedCardsPanels[i], BorderLayout.WEST);
				}
			}
		}
		
		//set currentIndex as the one who is selected
		players[currentPlayerIndex].setCurrentPlayer(true);
	}
	
	public void setFestivalController(HoldFestivalController c){
		this.controller = c;
	}
	
	private JLabel palaceLabel(String src){
		JLabel label= new JLabel();
		label.setIcon(new ImageIcon(src));
		label.setPreferredSize(new Dimension(60, 100));
		return label;
	}
	
	public void endPlayerTurn(int index){
		players[index].setCurrentPlayer(false);
	}
	
	public void startPlayerTurn(int index){
		players[index].setCurrentPlayer(true);
		dropFromFestival.setVisible(true);
	}
	
	public void tabThroughPlayerPalaceCards(int indexOfCard, int numCards, int indexOfPlayer, String hashKey){
		players[indexOfPlayer].selectCardAtIndex(indexOfCard, numCards, hashKey);
	}
	
	public void playPalaceCardAtIndex(int indexOfPlayer, String hashKey, int newNumCards){
		players[indexOfPlayer].clearSelectedCard(newNumCards);
		addCardToCenterPanelWithImage(hashKey, indexOfPlayer);
	}
	
	public void cancelTabbing(int index, int numCards){
		players[index].clearSelectedCard(numCards);
	}
	
	public void dropCurrentPlayer(int index){
		players[index].setVisible(false);
	}
	
	private void addCardToCenterPanelWithImage(String imageHashKey, int indexOfPlayer){
		playedCardsPanels[indexOfPlayer].add(palaceLabel(imageSourceHashMap.get(imageHashKey)));
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
