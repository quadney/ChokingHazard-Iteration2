package FestivalMiniGame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import Models.JavaPlayer;
import Models.PalaceCard;

public class HoldFestivalFrame extends JFrame {
	private HoldFestivalController festController;
	
	public HoldFestivalFrame(JavaPlayer[] players, int indexOfPlayerHoldingFestival, PalaceCard festivalCard, int selectedPalaceValue){
		setTitle("Let's Party!");
		setSize(800, 800);
		setResizable(false);
		
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
	
	private void startFestival(JavaPlayer[] players, int indexOfPlayer, PalaceCard festivalCard, int selectedPalaceValue){
		System.out.println("festival card type: "+festivalCard.getType());
		//get the valid players and make their festival cards
		JavaFestivalPlayer[] festivalPlayers = new JavaFestivalPlayer[players.length];
		
		for(int i = 0; i < players.length; ++i){
			//check if null, if null then not in the festival
			if(players[i] == null){
				System.out.println("players null "+i);
				festivalPlayers[i] = null;
				continue;
			}
			
			//get valid palace cards
			ArrayList<PalaceCard> playerPalaceCards = players[i].getPalaceCards();
			ArrayList<PalaceCard> validPalaceCards = new ArrayList<PalaceCard>();
			
			for(int j = 0; j < playerPalaceCards.size(); j++) {
				if(playerPalaceCards.get(j).getNumSymbols() <= festivalCard.getNumSymbols()){
					// add a copy of the card
					validPalaceCards.add(playerPalaceCards.get(j).deepCopy());
				}
			}
			festivalPlayers[i] = new JavaFestivalPlayer(players[i], validPalaceCards);
		}
		
		HoldFestivalModel model = new HoldFestivalModel(festivalPlayers, indexOfPlayer, festivalCard, selectedPalaceValue);
		HoldFestivalPanel panel = new HoldFestivalPanel(festivalPlayers, indexOfPlayer, festivalCard.getType(), selectedPalaceValue);
		festController = new HoldFestivalController(model, panel);
	}

}
