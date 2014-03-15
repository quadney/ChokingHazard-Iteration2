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

	public void selectPalaceTile(int value) {
		boardPanel.moveTile(1, 1, 0, "Palace 2");
	}

	public void updateSelectedAction(int x, int y, String imageKey, int rotationState) {
		boardPanel.moveTile(x, y, rotationState, imageKey);
	}
}
