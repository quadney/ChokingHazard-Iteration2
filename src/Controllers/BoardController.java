package Controllers;

import Models.BoardModel;
import Models.Actions.Action;
import Models.Actions.RotatableComponentAction;
import Views.BoardPanel;

public class BoardController {
	BoardModel boardModel;
	BoardPanel boardPanel;
	
	public BoardController(){
		this.boardModel = new BoardModel();
		this.boardPanel = new BoardPanel();
	}
	
	public BoardPanel getBoardPanel(){
		return this.boardPanel;
	}

	public void updateSelectedTileAction(int x, int y, String imageKey, int rotationState) {
		boardPanel.moveTile(x, y, rotationState, imageKey);
	}
	
	public void updateSelectedDeveloperAction(int x, int y, String imageKey){
		boardPanel.selectHighlightedDeveloper(imageKey, x, y);
	}

	public void pressEsc() {
		boardPanel.cancel();
	}

	public void updateBoardPanel(Action action) {
		if(action instanceof RotatableComponentAction){
			System.out.println("in updateBoardPanel RotatableTileComponent");
			boardPanel.placeTile(((RotatableComponentAction)action).getX(), ((RotatableComponentAction)action).getY(), ((RotatableComponentAction)action).getElevation(), action.afterImageKey);
		}
		
	}
}
