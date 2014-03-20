package Controllers;

import java.util.LinkedList;
import java.util.Stack;

import Models.BoardModel;
import Models.Developer;
import Models.GameModel;
import Models.JavaCell;
import Models.Actions.Action;
import Models.Actions.OneSpaceTileAction;
import Models.Actions.PlaceDeveloperOnBoardAction;
import Models.Actions.RotatableComponentAction;
import Models.Actions.TakeDeveloperOffBoardAction;
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
	
	public void updateSelectedHighlightDeveloperAction(int x, int y, String imageKey){
		boardPanel.selectHighlightedDeveloper(imageKey, x, y);
	}
	
//	public void updateSelectedTabDeveloperAction(int x, int y){
//		boardPanel.selectHighlightedDeveloper(imageKey, x, y);
//	}
	
	public void updateSelectedPathDeveloperAction(String imageKey, LinkedList<JavaCell> path){
		Stack<Integer> x = new Stack<Integer>();
		Stack<Integer> y = new Stack<Integer>();
		
		//System.out.println(path.peekFirst().getY() + " " + path.peekFirst().getX() );
		
		//draws the developer on the cell the were originally on
		
		//boardPanel.selectHighlightedDeveloper(imageKey, path.peekFirst().getY()*50, path.peekFirst().getX()*50);
		
		//puts the path 
		for(JavaCell c: path){
			x.add(c.getX()*50);
			y.add(c.getY()*50);
		}
		
		//remember to flip x and y
		boardPanel.drawDeveloperPath(y, x);
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
		else if(action instanceof PlaceDeveloperOnBoardAction || action instanceof TakeDeveloperOffBoardAction) {
			System.out.println("in updateBoardPanel NonRotatableTileComponent");
			Stack<Integer> xs = new Stack<Integer>();
			Stack<Integer> ys = new Stack<Integer>(); 
			Stack<String> images = new Stack<String>(); 
			for(Developer developer : game.getAllPlayerDevelopers()){
				if(developer != null){
					xs.push(developer.getX()*50);
					ys.push(developer.getY()*50);
					//TODO LOD violation
					images.push("player_" + developer.getOwner().getColor());
				}
			}
			System.out.println(xs + " " + ys + " " + images);
			
			boardPanel.placeDeveloper(ys, xs, images);
			//game.getCurrentPlayer().getColor(), ((NonRotatableComponentAction)action).getY()*50, ((NonRotatableComponentAction)action).getX()*50);
		}
	}
}
