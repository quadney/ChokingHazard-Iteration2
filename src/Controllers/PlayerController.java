package Controllers;

import Models.JavaPlayer;
import Models.PalaceCard;
import Views.PlayerPanel;

public class PlayerController {
	private JavaPlayer[] playerModels;
	private PlayerPanel[] playerPanels;
	
	public PlayerController(int numPlayers, JavaPlayer[] players) {
		this.playerModels = players;
		this.playerPanels = new PlayerPanel[numPlayers];
		for(int i = 0; i < numPlayers ; i++){
			playerPanels[i] = new PlayerPanel(players[i].getName(), players[i].getColor());
			updatePlayerPanel(i);
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
	
	public boolean selectDeveloper(int playerIndex) {
		return playerModels[playerIndex].canPlaceDeveloperOnBoard();
	}
	
//	public boolean canDeleteDeveloperPlayerCheck(int playerIndex, int actionPointCost){
//		return playerModels[playerIndex].canDeleteDeveloperOnBoardPlayerCheck(actionPointCost);
//	}
	
	
	//----------------------------------------------------------------------------
	//Select Final turn method - only occurs if this is valid to do
	public void setNoCurrentPlayerinPlayerPanels() {
	
		for(PlayerPanel player : playerPanels){
			player.setNotCurrentPlayer();
		}
	}

	public void setCurrentPlayerinPlayerPanel(int playerIndex) {
		playerPanels[playerIndex].setCurrentPlayer();
	}

	public String getColorOfPlayer(int playerIndex) {
		playerModels[playerIndex].getColor();
		return null;
	}

	
	//---------------------------------------------------------------------------
	
	//--------------- Update Players after Palace Festival ----------------------
	public void updatePlayersAfterFestival(){
		for(int i = 0; i < playerPanels.length; i++){
			playerPanels[i].updateFamePoints(playerModels[i].getFamePoints());
			playerPanels[i].updateNumPalaceCards(playerModels[i].getPalaceCards().size());
		}

	}

	public void updatePlayerPanel(int playerIndex) {
		//given a player index, the corresponding player panel will update with all the information.
		
		//tells the player their fame points
		playerPanels[playerIndex].updateFamePoints(playerModels[playerIndex].getFamePoints());
		
		//tells the player panel the number of palace cards
		playerPanels[playerIndex].updateNumPalaceCards(playerModels[playerIndex].getNumPalaceCards());
		
		// tells the player panel the number of rice tiles
		playerPanels[playerIndex].useOneRiceTile(playerModels[playerIndex].getNumOneRiceTile());
		
		// tells the player panel the number of village tiles
		playerPanels[playerIndex].useOneVillageTile(playerModels[playerIndex].getNumOneVillageTile());
		
		// tells the player panel the number of two tiles
		playerPanels[playerIndex].useTwoTile(playerModels[playerIndex].getNumTwoTile());
		
		// tells the player panel the number of action tokens
		playerPanels[playerIndex].useActionToken(playerModels[playerIndex].getNumActionTokens(),playerModels[playerIndex].getActionPoints());
		
		// tells the player their action points
		playerPanels[playerIndex].updateActionPoints(playerModels[playerIndex].getActionPoints());
		
		// tells the panel the number of developers off the board
		playerPanels[playerIndex].updateDevelopersOffBoard(playerModels[playerIndex].getDevelopersOffBoard());
	}
	
}
