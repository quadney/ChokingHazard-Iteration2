package Models;

import Helpers.*;
import Models.Actions.*;
import java.util.*;
import java.util.Stack;

// This enum declaration might need to be moved, not sure how accessible it is right now 
// (needs to be accessible by GameModel and the Controller). #JavaTroubles
enum GameState {
	ReplayMode, PlanningMode, NormalMode
}

public class GameModel {
	// VARIABLES
	private BoardModel gameBoard;
	private JavaPlayer[] players;
	private int indexOfCurrentPlayer;
	private boolean isFinalRound;

	private Stack<Action> actionHistory; // This holds a history of the actions
											// taken up to the currently held
											// state.
	private Stack<Action> actionReplays; // This holds currently undone actions
											// for the purposes of Replay Mode

	private GameState gameState;
	private int actionIDCounter; // Provides unique actionIDs to every action
									// created. To be incremented after each
									// action instantiation.

	public GameModel(int numberPlayers){
		this.isFinalRound = false;
		this.indexOfCurrentPlayer = 0;
		this.gameBoard = new BoardModel();
		this.players = new JavaPlayer[numberPlayers];
		
		actionHistory = new Stack<Action>();
		actionReplays = new Stack<Action>();
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
   
   public Developer getDeveloperOnCell(Cell c)
   {
      for(int i = 0; i < players.length; i++)
      {
         for(Developer d:players[i].getDevelopersOnBoard())
         {
            if(d.getLocation() == c)
               return d;
         }
      }
      return null;
   }
   
   //Returns an array of players in order from highest to lowest of ranks of players
   //valid on a palace/city
   public ArrayList<Player> getPalaceRanks(JavaCell palace)
   {
      ArrayList<JavaCell> city = gameBoard.getCityFromRootCell(palace);
      
      HashMap<Player, Integer> scores = new HashMap<Player, Integer>();
      
      for(JavaCell c : city)
      {
         if(getDeveloperOnCell(c) != null)
         {
            Developer d = getDeveloperOnCell(c);
            Player p = d.getOwner();
            int rank = c.getElevation();
            
            if(!scores.containsKey(p))
            {
               scores.put(p, rank);
            }
            else
            {
               int newRank = c.getElevation();
               if(newRank > rank)
                  scores.put(p, newRank);
            }
         }
      }
      
      //we now have each player mapped to their rank or not mapped if they don't have a developer 
      //on the city.
      
      ArrayList<Integer> values = new ArrayList<Integer>();
      for(Integer i:scores.values())
         values.add(i);
      Collections.sort(values);
      
      ArrayList<Player> players = new ArrayList<Player>();
      
      for(Integer i:values)
      {
         for(Player p : scores.keySet())
         {
            if(scores.get(p) == i)
               players.add(p);
         }
      }
      
		return players;

   }

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
}
