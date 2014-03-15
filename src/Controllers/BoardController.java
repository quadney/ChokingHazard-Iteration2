package Controllers;

import Models.BoardModel;
import Views.BoardPanel;

public class BoardController {
	BoardModel boardModel;
	BoardPanel boardPanel;
	
	public BoardController(){
		
	}
	
	public BoardPanel getBoardPanel(){
		return this.boardPanel;
	}
}
