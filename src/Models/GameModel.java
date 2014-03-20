package Models;

import Helpers.*;
import Models.Actions.*;

import java.util.*;

import Models.Actions.MActions.MAction;
import Models.Actions.MActions.SelectMoveDeveloperAroundBoardAction;

// This enum declaration might need to be moved, not sure how accessible it is right now 
// (needs to be accessible by GameModel and the Controller). #JavaTroubles

public class GameModel implements Serializable<GameModel> {
	public enum GameState {
		ReplayMode, PlanningMode, NormalMode
	}
	// VARIABLES
	private BoardModel gameBoard;
	private JavaPlayer[] players;
	private SharedComponentModel shared;
	private int indexOfCurrentPlayer;
	public MAction selectedAction;

	private Stack<Event> actionHistory; // This holds a history of the actions
										// taken up to the currently held
										// state.
	private Stack<Event> actionReplays; // This holds currently undone actions
										// for the purposes of Replay Mode

	private GameState gameState;
	private int actionIDCounter; // Provides unique actionIDs to every action
									// created. To be incremented after each
									// action instantiation.

	public GameModel(int numberPlayers, String[] playerNames,
			String[] playerColors) {
		this.indexOfCurrentPlayer = 0;
		this.gameBoard = new BoardModel();
		this.shared = new SharedComponentModel();
		this.players = new JavaPlayer[numberPlayers];
		for (int i = 0; i < players.length; i++) {
			players[i] = new JavaPlayer(playerNames[i], playerColors[i]);
			System.out.println(players[i].getColor());
		}
		actionHistory = new Stack<Event>();
		actionReplays = new Stack<Event>();
		selectedAction = null;
		this.gameState = GameState.NormalMode;
	}

	// created for loading the game
	public GameModel(int numberPlayers) {
		this.indexOfCurrentPlayer = 0;
		this.gameBoard = new BoardModel();
		this.players = new JavaPlayer[numberPlayers];
		actionHistory = new Stack<Event>();
		actionReplays = new Stack<Event>();
		selectedAction = null;
		this.gameState = GameState.NormalMode;
	}

	// This method is to be used by the controller to determine which buttons
	// are visible/enabled in the view (and other visual components). For
	// example, if the gameState is PlanningMode, the undo button and exit
	// planning mode buttons will be enabled/shown, as well as a Label or
	// something.
	public GameState getGameState() {
		return gameState;
	}

	public void changeFamePoints(int playerIndex, int modifier) {
		players[playerIndex].changeFamePoints(modifier);
	}

	// Used for testing the Actions

	public boolean endTurn() {										//this is called by TriggerSwitchTurn Action and the GameController.
																	//if valid, changes all states accordingly, if not, doesn't do a thing
		JavaPlayer currentPlayer = players[indexOfCurrentPlayer];	//gets the current player out of the container
		if (currentPlayer.endPlayerTurn()){							//if the player can validly end their turn. All the player stuff will have been manipulated
			//System.out.println("player ended turn and index is being incremented");
			indexOfCurrentPlayer = ++indexOfCurrentPlayer % players.length;				//switches the curentPlayer to the next player
			return true;											//tells the caller that everything worked out!
		}
		return false;
	}
	
	public void placeTile(int x, int y, Tile tile, JavaPlayer player){
		gameBoard.placeTile(x, y, tile, player);
	}

	// Returns an array of players in order from highest to lowest of ranks of
	// players
	// valid on a palace/city
	public ArrayList<ArrayList<JavaPlayer>> getPalaceRanks(JavaCell palace) {
		ArrayList<JavaCell> city = gameBoard.getCityFromRootCell(palace);

		HashMap<JavaPlayer, Integer> scores = new HashMap<JavaPlayer, Integer>();

		for (JavaCell c : city) {
			if (getDeveloperOnCell(c) != null) {
				Developer d = getDeveloperOnCell(c);
				JavaPlayer p = d.getOwner();
				int rank = c.getElevation();

				if (!scores.containsKey(p)) {
					scores.put(p, rank);
				} else {
					int newRank = c.getElevation();
					if (newRank > rank)
						scores.put(p, newRank);
				}
			}
		}

		// we now have each player mapped to their rank or not mapped if they
		// don't have a developer
		// on the city.

		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Integer i : scores.values())
			values.add(i);
		Collections.sort(values);

		ArrayList<ArrayList<JavaPlayer>> players = new ArrayList<ArrayList<JavaPlayer>>();
   	  
   	  for(Integer i:values) 
        {
            ArrayList<JavaPlayer> rank = new ArrayList<JavaPlayer>();
            for(JavaPlayer p : scores.keySet()) 
            {
      	      if(scores.get(p) == i) 
                  rank.add(p); 
            }
            players.add(rank); 
        }
   	  
   	  return players;


	}
	
	/*
	 * public ArrayList<JavaPlayer> getIrrigationRanks(JavaCell cell) { int x =
	 * cell.getX(); int y = cell.getY();
	 * 
	 * HashMap<JavaPlayer, Integer> scores = new HashMap<JavaPlayer, Integer>();
	 * 
	 * JavaCell[][] map = gameBoard.getMap();
	 * 
	 * if (y < 13 && getDeveloperOnCell(map[y + 1][x]) != null) { JavaCell c =
	 * map[y + 1][x]; Developer d = getDeveloperOnCell(map[y + 1][x]);
	 * JavaPlayer p = d.getOwner(); int rank = c.getElevation();
	 * if(!scores.containsKey(p)) { scores.put(p, rank); } else { int newRank =
	 * c.getElevation(); if(newRank > rank) scores.put(p, newRank); } } if (y >
	 * 0 && getDeveloperOnCell(map[y - 1][x]) != null) { JavaCell c = map[y -
	 * 1][x]; Developer d = getDeveloperOnCell(map[y - 1][x]); JavaPlayer p =
	 * d.getOwner(); int rank = cell.getElevation(); if(!scores.containsKey(p))
	 * { scores.put(p, rank); } else { int newRank = c.getElevation();
	 * if(newRank > rank) scores.put(p, newRank); } } if (x < 14 &&
	 * getDeveloperOnCell(map[y][x + 1]) != null) { JavaCell c = map[y][x+1];
	 * Developer d = getDeveloperOnCell(map[y][x + 1]); JavaPlayer p =
	 * d.getOwner(); int rank = c.getElevation(); if(!scores.containsKey(p)) {
	 * scores.put(p, rank); } else { int newRank = c.getElevation(); if(newRank
	 * > rank) scores.put(p, newRank); } } if (x > 0 &&
	 * getDeveloperOnCell(map[y][x - 1]) != null) { JavaCell c = map[y][x-1];
	 * Developer d = getDeveloperOnCell(map[y][x - 1]); JavaPlayer p =
	 * d.getOwner(); int rank = cell.getElevation(); if(!scores.containsKey(p))
	 * { scores.put(p, rank); } else { int newRank = c.getElevation();
	 * if(newRank > rank) scores.put(p, newRank); } }
	 * 
	 * ArrayList<Integer> values = new ArrayList<Integer>(); for(Integer
	 * i:scores.values()) values.add(i); Collections.sort(values);
	 * 
	 * ArrayList<JavaPlayer> players = new ArrayList<JavaPlayer>();
	 * 
	 * for(Integer i:values) { for(JavaPlayer p : scores.keySet()) {
	 * if(scores.get(p) == i) players.add(p); } }
	 * 
	 * return players; }
	 */

	public Developer getDeveloperOnCell(JavaCell c) {
		for (int i = 0; i < players.length; i++) {
			for (Developer d : players[i].getDevelopersOnBoard()) {
				if (d.getLocation() == c)
					return d;
			}
		}
		return null;
	}

	// Returns an array of players in order from highest to lowest of ranks of
	// players
	// valid on a palace/city
	
	  /*public ArrayList<ArrayList<Player>> getPalaceRanks(JavaCell palace) {
     
   	  ArrayList<JavaCell> city = gameBoard.getCityFromRootCell(palace);
   	  
   	  HashMap<Player, Integer> scores = new HashMap<Player, Integer>();
   	  
   	  for(JavaCell c : city) 
        { 
           if(getDeveloperOnCell(c) != null) 
           { 
             Developer d = getDeveloperOnCell(c); Player p = d.getOwner(); 
             int rank = c.getElevation();
      	    if(!scores.containsKey(p)) 
             { 
               scores.put(p, rank); 
             } 
             else 
             { 
               int newRank = c.getElevation(); if(newRank > rank) scores.put(p, newRank); 
             } 
           } 
        }
   	  
   	  //we now have each player mapped to their rank or not mapped if they
   	  //don't have a developer on the city.
   	  
   	  ArrayList<Integer> values = new ArrayList<Integer>(); for(Integer
   	  i:scores.values()) values.add(i); Collections.sort(values);
   	  
   	  ArrayList<ArrayList<Player>> players = new ArrayList<Player>();
   	  
   	  for(Integer i:values) 
        {
            ArrayList<Player> rank = new ArrayList<Player>();
            for(Player p : scores.keySet()) 
            {
      	      if(scores.get(p) == i) 
                  rank.add(p); 
            }
            players.add(rank); 
        }
   	  
   	  return players;
	  
	  }
	  
	  public ArrayList<Player> getIrrigationRanks(JavaCell cell) { int x =
	  cell.getX(); int y = cell.getY();
	  
	  HashMap<Player, Integer> scores = new HashMap<Player, Integer>();
	  
	  JavaCell[][] map = gameBoard.getMap();
	  
	  if (y < 13 && getDeveloperOnCell(map[y + 1][x]) != null) { JavaCell c =
	  map[y + 1][x]; Developer d = getDeveloperOnCell(map[y + 1][x]); Player p
	  = d.getOwner(); int rank = c.getElevation(); if(!scores.containsKey(p)) {
	  scores.put(p, rank); } else { int newRank = c.getElevation(); if(newRank
	  > rank) scores.put(p, newRank); } } if (y > 0 && getDeveloperOnCell(map[y
	  - 1][x]) != null) { JavaCell c = map[y - 1][x]; Developer d =
	  getDeveloperOnCell(map[y - 1][x]); Player p = d.getOwner(); int rank =
	  cell.getElevation(); if(!scores.containsKey(p)) { scores.put(p, rank); }
	  else { int newRank = c.getElevation(); if(newRank > rank) scores.put(p,
	  newRank); } } if (x < 14 && getDeveloperOnCell(map[y][x + 1]) != null) {
	  JavaCell c = map[y][x+1]; Developer d = getDeveloperOnCell(map[y][x +
	  1]); Player p = d.getOwner(); int rank = c.getElevation();
	  if(!scores.containsKey(p)) { scores.put(p, rank); } else { int newRank =
	  c.getElevation(); if(newRank > rank) scores.put(p, newRank); } } if (x >
	  0 && getDeveloperOnCell(map[y][x - 1]) != null) { JavaCell c =
	  map[y][x-1]; Developer d = getDeveloperOnCell(map[y][x - 1]); Player p =
	  d.getOwner(); int rank = cell.getElevation(); if(!scores.containsKey(p))
	  { scores.put(p, rank); } else { int newRank = c.getElevation();
	  if(newRank > rank) scores.put(p, newRank); } }
	  
	  ArrayList<Integer> values = new ArrayList<Integer>(); for(Integer
	  i:scores.values()) values.add(i); Collections.sort(values);
	  
	  ArrayList<Player> players = new ArrayList<Player>();
	  
	  for(Integer i:values) { for(Player p : scores.keySet()) {
	  if(scores.get(p) == i) players.add(p); } }
	  
	  return players; }
	 
>>>>>>> 2b6a227283a2a6f9dc324d6ed4eb0995d864d2ad
	/**
	 * Backtracks GameModel state to end of current player's previous turn,
	 * storing all backtracked moves in actionReplays stack. Also changes
	 * gameState to ReplayMode.
	 */
	public void initializeReplayMode() {
		gameState = GameState.ReplayMode;

		while (actionHistory.peek().getPlayerIndex() == indexOfCurrentPlayer) {
			actionReplays.add(actionHistory.peek());
			actionHistory.peek().undo(this);
			actionHistory.pop();
		}

		while (actionHistory.peek().getPlayerIndex() != indexOfCurrentPlayer) {
			actionReplays.add(actionHistory.peek());
			actionHistory.peek().undo(this);
			actionHistory.pop();
		}
	}

	/**
	 * Iterates forward one move during ReplayMode. If the move replayed is the
	 * last move to be replayed, changes gameState to NormalMode.
	 * <p>
	 * This method should only be accessible while the gameState is ReplayMode!
	 * I'm leaving this responsibility to the view and controller.
	 */
	public void replayMove() {
		actionHistory.add(actionReplays.peek());
		actionReplays.peek().redo(this);
		actionReplays.pop();

		if (actionReplays.isEmpty())
			gameState = GameState.NormalMode;
	}

	public int getPlayerIndex() {
		return this.indexOfCurrentPlayer;
	}

	//------ Methods for MAction/selected ------------------------------

	public int getSelectedActionX() {
		return selectedAction.getX();
	}

	public int getSelectedActionY() {
		// TODO Auto-generated method stub
		return selectedAction.getY();
	}

	public String getSelectedActionImageKey() {
		return selectedAction.getImageKey();
	}

	public MAction getSelectedAction() {
		return selectedAction;
	}

	//--------------Key presses to interact with SelectedActions-------------

	public boolean pressLeft() {
		if (selectedAction != null) {
			return selectedAction.pressArrow(0, -1);
		}
		return false;
	}

	public boolean pressUp() {
		if (selectedAction != null) {
			return selectedAction.pressArrow(-1, 0);
		}
		return false;
	}

	public boolean pressRight() {
		if (selectedAction != null) {
			return selectedAction.pressArrow(0, 1);
		}
		return false;
	}

	public boolean pressDown() {
		if (selectedAction != null) {
			return selectedAction.pressArrow(1, 0);
		}
		return false;
	}
	
	public boolean pressSpace() {
		if (selectedAction != null) {
			return selectedAction.pressSpace();
		}
		return false;
	}

	public Action pressEnter() {
		if (selectedAction != null) {
			return selectedAction.pressEnter(this);
		}
		return null;
	}
	
	public boolean pressTab() {
		if (selectedAction != null) {
			return selectedAction.pressTab();
		}
		return false;
	}
	
	public boolean pressM() {
		return false;
	}
	
	public void pressEsc() {
		selectedAction = null;
	}


	public boolean setSelectedAction(MAction selectedAction) {
		// if(this.selectedAction == null){
		// System.out.println("(in GameModel setSelectedACtion()) setSelectedAction set the new MAction");
		this.selectedAction = selectedAction;
		return true;
		// }
		// System.out.println("(in GameModel setSelectedACtion()) setSelectedAction already had an MAction");
		// return false;

	}

	// Method used in Event-----------------------------------------------------

	public void isFinalRound(boolean b) { // used in TriggeredFinalRound
		shared.isFinalRound();
	}

	// ---------------------------------------------------------------------------

	@Override
	public String serialize() {
		Stack<String> playerNames = new Stack<String>();
		Stack<String> playerColors = new Stack<String>();
		for(JavaPlayer player : players) {
			playerNames.push(player.getName());
			playerColors.push(player.getColor());
		}
		String serializedHistory = actionHistory.empty() ? "null" : Json.serializeArray(this.actionHistory);
		String serializedReplays = actionReplays.empty() ? "null" : Json.serializeArray(this.actionReplays);
		return Json.jsonObject(Json.jsonElements(
			Json.jsonPair("playerNames", Json.serializeArray(playerNames)),
			Json.jsonPair("playerColors", Json.serializeArray(playerColors)),
			Json.jsonPair("actionHistory", serializedHistory),
			Json.jsonPair("actionReplays", serializedReplays),
			Json.jsonPair("gameState", this.gameState.toString())
		));
	}

	@Override
	public GameModel loadObject(JsonObject json) {
		this.actionHistory = new Stack<Event>();
		this.actionReplays = new Stack<Event>();
		if(json.getObject("actionHistory") != null)
			for(int x = 0; x < ((Object[])json.getObject("actionHistory")).length; ++x)
				this.actionHistory.push(Action.loadAction((JsonObject)((Object[])json.getObject("actionHistory"))[x]));
		if(json.getObject("actionReplays") != null)
			for(int x = 0; x < ((Object[])json.getObject("actionReplays")).length; ++x)
				this.actionHistory.push(Action.loadAction((JsonObject)((Object[])json.getObject("actionReplays"))[x]));
		String[] names = new String[((Object[])json.getObject("playerNames")).length];
		String[] colors = new String[((Object[])json.getObject("playerColors")).length];
		for(int x = 0; x < names.length; ++x) {
			colors[x] = (String)((Object[])json.getObject("playerColors"))[x];
			names[x] = (String)((Object[])json.getObject("playerNames"))[x];
		}
		System.out.println(((Object[])json.getObject("playerNames")));
		GameModel model = new GameModel(names.length, names, colors);
		model.setActionHistory(actionHistory);
		model.setActionReplays(actionReplays);
		model.setGameState(GameState.valueOf(json.getString("gameState")));
		// TODO set game states
		return model;
	}

	private void setGameState(GameState state) {
		this.gameState = state;
	}

	private void setActionReplays(Stack<Event> actionReplays2) {
		this.actionReplays = actionReplays2;
		
	}

	private void setActionHistory(Stack<Event> actionHistory2) {
		this.actionHistory = actionHistory2;
		
	}

	public void addToActionHistory(Action action) {
		actionHistory.add(action);
	}

	public JavaPlayer getCurrentPlayer() {
		return players[indexOfCurrentPlayer];
	}

	public BoardModel getBoard() {
		return gameBoard;
	}

	public void doLastActionInHistory() {
		System.out.println("in doLactActionInHistory");
		if (actionHistory.size() > 0) {
			actionHistory.peek().redo(this);
		}
	}

	public JavaPlayer[] getPlayers() {
		return players;
	}

	public SharedComponentModel getShared() {
		return shared;
	}

	public int getNextCellId() {
		return gameBoard.getNextCellId();
	}

	public boolean placeDeveloperOnBoard(int x, int y) {
		return gameBoard.placeDeveloper(gameBoard.getCellAtXY(x, y),
				players[indexOfCurrentPlayer]);
	}
	
	public boolean takeDeveloperOffBoard(int x, int y) {
		return gameBoard.removeDatDeveloperOffDaBoard(gameBoard.getCellAtXY(x, y), players[indexOfCurrentPlayer] );
	}
	
	public LinkedList<Developer> getAllPlayerDevelopers() {
		LinkedList<Developer> list = new LinkedList<Developer>();
		for(JavaPlayer player : players)
			list.addAll(Arrays.asList(player.getDevelopersOnBoard()));
		return list;
	}

	public LinkedList<JavaCell> getPath() {
		if(selectedAction instanceof SelectMoveDeveloperAroundBoardAction){
			return ((SelectMoveDeveloperAroundBoardAction)selectedAction).getPath();
		}
		return null;
	}
	
	public int nextActionID() {
		return ++actionIDCounter;
	}
	
	public void undoAction() {
//		if(!gameState.equals(GameState.PlanningMode))
//			return;
		actionHistory.pop();
		System.out.println("UNDONE");
		System.out.println(actionHistory);
	}
	
	public void redoAllActionsUntil(int actionID, boolean slowForReplayMode) {
		Action[] actions = actionHistory.toArray(new Action[1]);
		actionHistory.clear();
		for(int x = 0; x < actions.length; ++x) {
			actions[x].doAction(this);
			if(actions[x].getActionID() == actionID)
				return;
			actionHistory.push(actions[x]);
		}
	}
	
	public int getStartOfRoundActionID() {
		int index = this.indexOfCurrentPlayer;
		Action[] actions = actionHistory.toArray(new Action[1]);
		for(int x = actions.length - 1; x >= 0; --x){
			if(actions[x] instanceof EndTurnAction) {
				--index;
				if(index == 0)
					return actions[x].getActionID();
			}
		}
		return -1;
	}

	public void drawFestivalCard() {
		players[indexOfCurrentPlayer].addPalaceCard(shared.drawFestivalCard());
	}

	public void drawFromDeck() {
		players[indexOfCurrentPlayer].addPalaceCard(shared.drawFromDeck());
	}

	public Action[] getActions() {
		return actionHistory.toArray(new Action[1]);
	}
}
