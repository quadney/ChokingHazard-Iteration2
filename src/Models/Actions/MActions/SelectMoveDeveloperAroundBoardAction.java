package Models.Actions.MActions;

import java.util.LinkedList;

import Models.BoardModel;
import Models.GameModel;
import Models.JavaCell;
import Models.JavaPlayer;
import Models.Actions.Action;

public class SelectMoveDeveloperAroundBoardAction extends SelectNonRotatableComponentAction {
	
	int originalX;
	int originalY;
	private LinkedList<JavaCell> path;
	BoardModel board;

	public SelectMoveDeveloperAroundBoardAction(String imageKey, int originalX, int originalY, BoardModel board) {
		super(imageKey);
		this.x = originalX;
		this.y = originalY;
		this.originalX = originalX;
		this.originalY = originalY;
		this.path = new LinkedList<JavaCell>();
		this.board = board;
		
		
	}
	
	public boolean pressArrow(int xChange, int yChange) {
		int newX = x + xChange;
		int newY = y + yChange;
		if(isNonRotatableComponentOnBoard(newX, newY) ){
			x = newX;
			y = newY;
			return addJavaCellToPath(board.getCellAtXY(x, y));
		}
		else{
			return false;
		}
	}

	@Override
	public boolean isNonRotatableComponentOnBoard(int x, int y) {
		if(x < 0 || x > 13){ //check if changes in x are invalid
			return false;
		}
		else if(y < 0 || y > 13){ //check if changes in y are invalid
			return false;
		}
		else
			return true;
	}
	
	public boolean fromVillageToRice(JavaCell jc1, JavaCell jc2) {
		if (jc1.getCellType().equals("village")
				&& jc2.getCellType().equals("rice")
				|| jc1.getCellType().equals("rice")
				&& jc2.getCellType().equals("village")) {
			return true;
		}

		return false;
	}
	
	public boolean addJavaCellToPath(JavaCell javaCell) {
		int pathSize = path.size();
		LinkedList<JavaCell> temp = new LinkedList<JavaCell>();

		for (int i = 0; i < pathSize; i++) {
			temp.push(path.pop());
		}

		JavaCell currentCell = temp.pop();
		int count = 0;

		while ((currentCell != javaCell) && count < pathSize - 1) {
			path.push(currentCell);
			currentCell = temp.pop();
			count++;
		}
		
		path.push(currentCell);
		return true;
	}

	@Override
	public boolean pressTab() {
		return false;
	}
	
	public MAction pressM(){
		return null;
	}

	@Override
	public Action pressDelete() {
		return null;
	}

	@Override
	public Action pressEnter(GameModel game) {
		return null;
	}
	
	private int costOfDeveloperPath(){
		int pathSize = path.size();
		int actionPoints = 0;
		JavaCell currentCell = path.removeLast();
		JavaCell nextCell = path.removeLast();
		for (int i = 0; i < pathSize - 2; i++) {
			if (fromVillageToRice(currentCell, nextCell)) {
				actionPoints++;
			}

			currentCell = nextCell;
			nextCell = path.removeLast();
		}

		if (fromVillageToRice(currentCell, nextCell)) {
			actionPoints++;
		}

		return actionPoints;
	}

}
