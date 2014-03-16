package Controllers;

import Models.JavaPlayer;
import Views.PlayerPanel;

public class PlayerController {
	private JavaPlayer[] playerModels;
	private PlayerPanel[] playerPanels;
	
	public PlayerController(int numPlayers, String[] playerNames, String[] playerColors){
		this.playerModels = new JavaPlayer[numPlayers];
		this.playerPanels = new PlayerPanel[numPlayers];
		for(int i = 0; i < playerModels.length; ++i){
			playerModels[i] = new JavaPlayer(playerNames[i], playerColors[i]);
			playerPanels[i] = new PlayerPanel(playerNames[i], playerColors[i]);
		}
	}
	
	public PlayerPanel[] getPlayerPanels(){
		return this.playerPanels;
	}
	
	public boolean selectPalaceTile(int value, int playerIndex) {
		return playerModels[playerIndex].canUsePalace();
	}
	
	public boolean selectRiceTile(int playerIndex){
		return playerModels[playerIndex].canUseRice();
	}

	public boolean checkIfRiceTileSelectionValid(int i) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean selectThreeTile(int playerIndex) {
		// TODO Auto-generated method stub
		return false;
	}
}
