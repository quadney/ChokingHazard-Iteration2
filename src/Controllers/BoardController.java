package Controllers;

import java.util.Stack;

import Models.BoardModel;
import Models.Developer;
import Models.GameModel;
import Models.Actions.Action;
import Models.Actions.OneSpaceTileAction;
import Models.Actions.PlaceDeveloperOnBoardAction;
import Models.Actions.RotatableComponentAction;
import Views.BoardPanel;

public class BoardController {
	BoardModel boardModel;
	BoardPanel boardPanel;
	
	public BoardController(BoardModel boardModel){
		this.boardModel = boardModel;
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

	public void updateBoardPanel(Action action, GameModel game) {
		if(action instanceof RotatableComponentAction){
			System.out.println("in updateBoardPanel RotatableTileComponent");
			boardPanel.placeTile(((RotatableComponentAction)action).getY()*50, ((RotatableComponentAction)action).getX()*50, ((RotatableComponentAction)action).getRotationState(), boardModel.getElevationAtCellXY(((RotatableComponentAction)action).getX(), ((RotatableComponentAction)action).getY()), action.imageKey);
		}
		
		else if(action instanceof OneSpaceTileAction){
			System.out.println("in updateBoardPanel OneSpaceTileAction");
			boardPanel.placeTile(((OneSpaceTileAction)action).getY()*50, ((OneSpaceTileAction)action).getX()*50, 0, boardModel.getElevationAtCellXY(((OneSpaceTileAction)action).getX(), ((OneSpaceTileAction)action).getY()), action.imageKey);
		}
		else if(action instanceof PlaceDeveloperOnBoardAction) {
			System.out.println("in updateBoardPanel NonRotatableTileComponent");
<<<<<<< HEAD
			Stack<Integer> xs = new Stack<Integer>();
			Stack<Integer> ys = new Stack<Integer>(); 
			Stack<String> images = new Stack<String>(); 
			for(Developer developer : game.getAllPlayerDevelopers()){
				xs.push(developer.getX());
				ys.push(developer.getY());
				images.push(developer.getOwner().getColor());
			}
			System.out.println(xs + " " + ys + " " + images);
			
			boardPanel.placeDeveloper(xs, ys, images);
			//game.getCurrentPlayer().getColor(), ((NonRotatableComponentAction)action).getY()*50, ((NonRotatableComponentAction)action).getX()*50);
			//boardPanel.placeDeveloper(game.getCurrentPlayer().getColor(), ((NonRotatableComponentAction)action).getY()*50, ((NonRotatableComponentAction)action).getX()*50);
		}
	}
}
