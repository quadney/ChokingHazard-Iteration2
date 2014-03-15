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
	
	public int placePalaceTile() {
		return boardModel.placePalaceTile();
	}

	public void selectPalace(int value) {
		boardPanel.placeTile(xLoc, yLoc, elevation, hashMapKey);
	}
}
