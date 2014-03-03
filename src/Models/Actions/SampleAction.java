package Models.Actions;

import Models.GameModel;

/**
 * This is a SAMPLE action class. As you can see, it represents an action which
 * added fame points to a player. Its undo method subtracts those points,
 * effectively undoing itself. This class is for reference only, and may be
 * deleted once it is no longer needed.
 * 
 * @author Cameron Morrow
 * 
 */
public class SampleAction extends Action {

	private int famePointsEarned;

	public SampleAction(int actionID, int playerIndex, int famePointsEarned) {
		super(actionID, playerIndex);
		this.famePointsEarned = famePointsEarned;
	}

	@Override
	public void undo(GameModel game) {
		game.changeFamePoints(playerIndex, famePointsEarned * -1);

	}

}
