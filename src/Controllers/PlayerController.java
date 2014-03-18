package Controllers;

import Models.JavaPlayer;
import Models.PalaceCard;
import Models.Actions.Action;
import Models.Actions.RiceTileAction;
import Models.Actions.TwoTileAction;
import Models.Actions.UseActionTokenAction;
import Models.Actions.VillageTileAction;
import Views.PlayerPanel;

public class PlayerController {
	private JavaPlayer[] playerModels;
	private PlayerPanel[] playerPanels;
	
	public PlayerController(int numPlayers, String[] playerNames, String[] playerColors){
		// create new game constructor
		this.playerModels = new JavaPlayer[numPlayers];
		this.playerPanels = new PlayerPanel[numPlayers];
		for(int i = 0; i < playerModels.length; ++i){
			playerModels[i] = new JavaPlayer(playerNames[i], playerColors[i]);
			playerPanels[i] = new PlayerPanel(playerNames[i], playerColors[i], playerModels[i].getFamePoints(), 
					playerModels[i].getActionPoints(), playerModels[i].getDevelopersOffBoard(), 
					playerModels[i].getNumOneRiceTile(), playerModels[i].getNumOneVillageTile(), 
					playerModels[i].getNumTwoTile(), playerModels[i].getNumActionTokens(), 0);
		}
	}
	
	public PlayerPanel[] getPlayerPanels(){
		return this.playerPanels;
	}
	
	public JavaPlayer[] getPlayerModels(){
		return playerModels;
	}
	
	public JavaPlayer getPlayerAtIndex(int i){
		return playerModels[i];
	}
	
	public void dealPalaceCards(PalaceCard[][] cards){
		for(int i = 0; i < cards.length; i++){
			int j = 0;
			while(j < cards[i].length){
				playerModels[i].addPalaceCard(cards[i][j]);
				j++;
			}
			playerPanels[i].updateNumPalaceCards(cards[i].length);
		}
	}
	
	//Methods to validate select actions/key presses--------------------------------
	
	public boolean selectPalaceTile(int playerIndex) {
		return playerModels[playerIndex].canUsePalace();
	}
	
	public boolean selectRiceTile(int playerIndex){ 
		return playerModels[playerIndex].canUseRice();
	}

	public boolean selectThreeTile(int playerIndex) {
		return playerModels[playerIndex].canUseThree();
	}
	
	public boolean selectTwoTile(int playerIndex) {
		return playerModels[playerIndex].canUseTwo(); 
	}
	
	public boolean selectActionToken(int playerIndex) {
		return playerModels[playerIndex].canUseActionToken();
	}
	
	public boolean selectIrrigationTile(int playerIndex) {
		return playerModels[playerIndex].canUseIrrigation();
	}
	
	public boolean selectVillageTile(int playerIndex) {
		return playerModels[playerIndex].canUseVillage();
	}

	public boolean selectEndTurn(int playerIndex) {
		return playerModels[playerIndex].canEndTurn();
	}
	//----------------------------------------------------------------------------
	//Select Final turn method - only occurs if this is valid to do
	public void setNotCurrentPlayerinPlayerPanel(int playerIndex) {
		playerPanels[playerIndex].setNotCurrentPlayer();
		
	}

	public void setCurrentPlayerinPlayerPanel(int playerIndex) {
		playerPanels[playerIndex].setCurrentPlayer();
	}

	public boolean selectDeveloper(int playerIndex) {
		return playerModels[playerIndex].canPlaceDeveloperOnBoard();
	}

	public String getColorOfPlayer(int playerIndex) {
		playerModels[playerIndex].getColor();
		return null;
	}

	
	//---------------------------------------------------------------------------

	
	public void doAction(Action action, int playerIndex) {
		
		//changes the fame points of the player, applicable (for now) for every action
		playerModels[playerIndex].changeFamePoints(action.getEarnedFamePoints());
		//updated the playerPanel of this change
		playerPanels[playerIndex].updateFamePoints(playerModels[playerIndex].getFamePoints());
		
		//
		if(action instanceof RiceTileAction){
			playerModels[playerIndex].decrementRice();
			playerPanels[playerIndex].useOneRiceTile(playerModels[playerIndex].getNumOneRiceTile());
		}
		else if(action instanceof VillageTileAction){
			playerModels[playerIndex].decrementVillage();
			playerPanels[playerIndex].useOneVillageTile(playerModels[playerIndex].getNumOneVillageTile());
		}
		else if(action instanceof TwoTileAction){
			playerModels[playerIndex].decrementTwo();
			playerPanels[playerIndex].useTwoTile(playerModels[playerIndex].getNumTwoTile());
		}
		else if(action instanceof UseActionTokenAction){
			playerModels[playerIndex].useActionToken();
			playerPanels[playerIndex].useActionToken(playerModels[playerIndex].getNumActionTokens(), playerModels[playerIndex].getActionPoints());
		}
		//else if() //use for palace cards shit....
		//else if() //use for palace festival shit...
						
		
		
	}
	
	
}
