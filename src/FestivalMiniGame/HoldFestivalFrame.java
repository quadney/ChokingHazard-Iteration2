package FestivalMiniGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Controllers.GameController;
import Models.JavaPlayer;
import Models.PalaceCard;

public class HoldFestivalFrame extends JFrame {
	private HoldFestivalController festController;
	private GameController gameController;
	
	public HoldFestivalFrame(GameController gc, JavaPlayer[] players, int indexOfPlayerHoldingFestival, PalaceCard festivalCard, int selectedPalaceValue){
		setTitle("Let's Party!");
		setSize(800, 800);
		setResizable(false);
		
		this.gameController = gc;
		
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				festController.keyPressed(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		setFocusTraversalKeysEnabled(false);
		
		//make the model, view and controller
		startFestival(players, indexOfPlayerHoldingFestival, festivalCard, selectedPalaceValue);
		
		//set the content pane
		setContentPane(festController.getFestivalPanel());
	}
	
	private void startFestival(JavaPlayer[] javaPlayers, int indexOfPlayer, PalaceCard festivalCard, int selectedPalaceValue){
		JavaPlayer[] players = javaPlayers;
		//get the valid players and make their festival cards
		ArrayList<JavaFestivalPlayer> festivalPlayers = new ArrayList<JavaFestivalPlayer>();
		
		for(int i = 0; i < players.length; ++i){
			//check if null, if null then not in the festival
			if(players[i] == null){
				System.out.println("players null "+i);
				continue;
			}
			else{
				//get valid palace cards
				ArrayList<PalaceCard> playerPalaceCards = players[i].getPalaceCards();
				ArrayList<PalaceCard> validPalaceCards = new ArrayList<PalaceCard>();
				
				for(int j = 0; j < playerPalaceCards.size(); j++) {
					if(playerPalaceCards.get(j).compare(festivalCard)){
						// add a copy of the card
						validPalaceCards.add(playerPalaceCards.get(j).deepCopy());
					}
				}
				if(validPalaceCards.isEmpty()){
					//player is not in the festival
					continue;
				}
				//only adds players who are eligible to be in the festival
				if(i == indexOfPlayer)
					festivalPlayers.add(new JavaFestivalPlayer(players[i], validPalaceCards, true));
				else
					festivalPlayers.add(new JavaFestivalPlayer(players[i], validPalaceCards, false));
			}
		}
		if(festivalPlayers.size() == 0){
			JOptionPane.showMessageDialog(null, "No players to have festival with :(");
			this.dispose();
		}
		else{
			System.out.println(festivalPlayers.size());
			festController = new HoldFestivalController(this, festivalPlayers, festivalCard, selectedPalaceValue);
		}
	}
	
	public void festivalDidReturn(HashMap<String, ArrayList<PalaceCard>> cardsToDiscardPerPlayer, int[] famePointsWonPerPlayer){
		this.gameController.updatePlayersAfterFestival(cardsToDiscardPerPlayer, famePointsWonPerPlayer);
	}

}
