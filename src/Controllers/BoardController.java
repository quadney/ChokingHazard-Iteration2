package Controllers;

import Models.BoardModel;
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
		
	}

	public void pressEsc() {
		boardPanel.cancel();
	}
}
