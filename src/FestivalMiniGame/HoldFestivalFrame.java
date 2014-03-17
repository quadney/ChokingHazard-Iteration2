package FestivalMiniGame;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

import Models.JavaPlayer;
import Models.PalaceCard;

public class HoldFestivalFrame extends JFrame {
	private HoldFestivalController festController;
	
	public HoldFestivalFrame(JavaPlayer[] players, int indexOfPlayer, PalaceCard festivalCard, int selectedPalaceValue){
		setTitle("Let's Party!");
		setSize(800, 500);
		setResizable(false);
		
		//make the model, view and controller
		startFestival(players, indexOfPlayer, festivalCard, selectedPalaceValue);
		
		//set the content pane
		setContentPane(festController.getFestivalPanel());
	}
	
	private void startFestival(JavaPlayer[] players, int indexOfPlayer, PalaceCard festivalCard, int selectedPalaceValue){
		//get the valid players and make their festival cards
		JavaFestivalPlayer[] festivalPlayers = new JavaFestivalPlayer[players.length];
		
		for(int i = 0; i < players.length; ++i){
			//check if null, if null then not in the festival
			if(players[i] == null){
				festivalPlayers[i] = null;
				continue;
			}
			
			//get valid palace cards
			ArrayList<PalaceCard> playerPalaceCards = players[i].getPalaceCards();
			ArrayList<PalaceCard> validPalaceCards = new ArrayList<PalaceCard>();
			
			for (int j = 0; j < validPalaceCards.size(); i++) {
				if(playerPalaceCards.get(j).getNumSymbols() == festivalCard.getNumSymbols()){
					// add a copy of the card
					validPalaceCards.add(playerPalaceCards.get(j).deepCopy());
				}
			}
			festivalPlayers[i] = new JavaFestivalPlayer(players[i], validPalaceCards);
		}
		
		HoldFestivalModel model = new HoldFestivalModel(festivalPlayers, indexOfPlayer, festivalCard, selectedPalaceValue);
		HoldFestivalPanel panel = new HoldFestivalPanel();
		festController = new HoldFestivalController(model, panel);
	}

}
