package Models.Actions.MActions;

import java.util.LinkedList;

import Models.BoardModel;
import Models.GameModel;
import Models.JavaCell;
import Models.JavaPlayer;
import Models.Actions.Action;
import Models.Actions.MoveDeveloperAction;

public class SelectMoveDeveloperAroundBoardAction extends SelectNonRotatableComponentAction {
	
	int originalX;
	int originalY;
	private LinkedList<JavaCell> path;
	BoardModel board;
	JavaPlayer player;

	public SelectMoveDeveloperAroundBoardAction(String imageKey, int originalX, int originalY, BoardModel board, JavaPlayer player) {
		super(imageKey);
		this.x = originalX;
		this.y = originalY;
		this.originalX = originalX;
		this.originalY = originalY;
		this.path = new LinkedList<JavaCell>();
		this.board = board;
		this.player = player;
	}
	
	public boolean pressArrow(int xChange, int yChange) {
		int newX = x + xChange;
		int newY = y + yChange;
		if(isNonRotatableComponentOnBoard(newX, newY) ){
			x = newX;
			y = newY;
			return addJavaCellToPath(board.getCellAtXY(x, y), player);
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
	
	public boolean addJavaCellToPath(JavaCell javaCell, JavaPlayer player) {
		
		if(javaCell.hasDeveloper() && !player.hasDeveloperOnXY(x,y)){
			return false;
		}
		
		int pathSize = path.size();
		LinkedList<JavaCell> temp = new LinkedList<JavaCell>();
		LinkedList<JavaCell> temp2 = new LinkedList<JavaCell>();

		for (int i = 0; i < pathSize; i++) {
			temp2.push(path.peek());
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
		if(costOfDeveloperPath(path) >= player.getAvailableActionPoints(false)){
			path = temp2;
			return false;
		}
		return true;
	}

	@Override
	public Action pressEnter(GameModel game) {
		if (!doesLastCellAlreadyHaveDeveloper()) {
			Action action = new MoveDeveloperAction(game.getNextCellId(), x, y, path);
			if (action.doAction(game)) {
				return action;
			}
		}
		return null;
	}
	
	private int costOfDeveloperPath(LinkedList<JavaCell> path){
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
	
	private boolean doesLastCellAlreadyHaveDeveloper(){
		return path.peekLast().hasDeveloper();
	}
	
	public LinkedList<JavaCell> getPath(){
		return path;
	}
	
	//-------Methods that should do nothing---------------------
	public boolean pressTab() {
		return false;
	}
	
	public MAction pressM(BoardModel board, JavaPlayer player){
		return null;
	}

	@Override
	public Action pressDelete(GameModel game) {
		return null;
	}

}
