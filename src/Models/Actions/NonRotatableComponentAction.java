package Models.Actions;

public abstract class NonRotatableComponentAction extends Action {

	public NonRotatableComponentAction(int actionID, String beforeImageKey, String afterImageKey, int famePointsEarned) {
		super(actionID, beforeImageKey, afterImageKey, famePointsEarned);
	}

}
