package Controllers;

import Models.Player;
import Views.PlayerPanel;

public class PlayerController {
	private Player[] playerModels;
	private PlayerPanel[] playerPanels;
	
	public PlayerController(int numPlayers, String[] playerNames, String[] playerColors){
		
	}
	
	public PlayerPanel[] getPlayerPanels(){
		return this.playerPanels;
	}
}
