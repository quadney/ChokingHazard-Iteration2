package Views;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	private BufferedImage board;
	private BufferedImage tileImage;
	private BufferedImage developers;
	private BufferedImage tempImage;
	private Graphics2D g2d;
	private HashMap<String, String> imageSourceHashMap;
	public boolean replaying = false;

	public BoardPanel(){		
		initHashMap();
		getBackgroundImage();
		setPreferredSize(new Dimension(board.getWidth(), board.getHeight()));
		
		this.tileImage = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.developers = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.tempImage = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
	}
	
	private void getBackgroundImage(){
		board = getImage(imageSourceHashMap.get("board"));
		drawLines(board);
		repaint();
	}
	
	public void repaint() {
		if(!replaying)
			super.repaint();
	}
	
	private BufferedImage getImage(String source){
		BufferedImage returnImage = null;
		try{
			returnImage = ImageIO.read(this.getClass().getResource(source));
		} catch(IOException e){
			
		}
		return returnImage;
	}
	// call this when the user is for sure placing a tile on the board
	// aka when the Enter button is pressed
	public void placeTile(int xLoc, int yLoc, int rotationState, int elevation, String hashMapKey){
		clearImage(tempImage);
		g2d = tileImage.createGraphics();
		g2d.rotate(rotationState*Math.PI/2, xLoc+25, yLoc+25);
		//g2d.scale(-0.1*elevation, -0.1*elevation);
		g2d.drawImage(getImage(imageSourceHashMap.get(hashMapKey)), null, xLoc, yLoc);
		g2d.dispose();
		drawElevationLabel(xLoc, yLoc, rotationState, elevation, hashMapKey);
		
		repaint();
	}
	
	//use this when the user is moving a tile around the board, and has not made the decision to place it yet
	public void moveTile(int xLoc, int yLoc, int rotationState, String hashMapKey){
		clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.rotate(rotationState*Math.PI/2, xLoc+25, yLoc+25);
		g2d.drawImage(getImage(imageSourceHashMap.get(hashMapKey)), null, xLoc, yLoc);
		g2d.dispose();
		repaint();
	}
	
	public void drawDeveloperPath(Stack<Integer> x, Stack<Integer> y){
		clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.setColor(Color.YELLOW);
		g2d.setStroke(new BasicStroke());
		while(!x.isEmpty()){
			g2d.drawRect(x.pop(), y.pop(), 50, 50);
		}
		g2d.dispose();
		repaint();
	}
	
	//use this when the user places a developer onto the board,
	public void placeDeveloper(Stack<Integer> x, Stack<Integer> y, Stack<String> hash){
		redrawAllDevelopers(x, y, hash);
		repaint();
	}
	
	//tabbing through developers
	public void highlightDeveloper(int xLoc, int yLoc){
		clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.setColor(Color.YELLOW);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.drawRect(xLoc, yLoc, 50, 50);
		g2d.dispose();
		repaint();
	}
	
	public void selectHighlightedDeveloper(int xLoc, int yLoc){
		clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.drawRect(xLoc, yLoc, 50, 50);
		g2d.dispose();
		repaint();
	}
	
	public void removeDeveloper(Stack<Integer> x, Stack<Integer> y, Stack<String> hash){
		redrawAllDevelopers(x, y, hash);
		repaint();
	}
	
	private void redrawAllDevelopers(Stack<Integer> x, Stack<Integer> y, Stack<String> hash){
		clearImage(tempImage);
		clearImage(developers);
		g2d = developers.createGraphics();
		
		while(!x.isEmpty()){
			g2d.drawImage(getImage(imageSourceHashMap.get(hash.pop())), null, x.pop(), y.pop());
		}
		g2d.dispose();
	}
	
	public void cancel(){
		clearImage(tempImage);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(board, 0, 0, null);
		g.drawImage(tileImage, 0, 0, null);
		g.drawImage(tempImage, 0, 0, null);
		g.drawImage(developers, 0, 0, null);
	}
	
	private void drawElevationLabel(int xLoc, int yLoc, int rotationState, int elevation, String hashMapKey){
		int xStringLoc = xLoc+35;
		int yStringLoc = yLoc+35;
		g2d = tileImage.createGraphics();
		g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g2d.setColor(Color.WHITE);
		g2d.fillOval(xStringLoc-5, yStringLoc-5, 10, 10);
		g2d.setColor(Color.RED);
		g2d.drawString(""+elevation, xStringLoc, yStringLoc);
		
		if(hashMapKey.equals("twoTile") || hashMapKey.equals("threeTile")){
			boolean ifThree = false;
			if(hashMapKey.equals("threeTile")) ifThree = true;
			for(int i = 0; i < 2; i++){
				if(rotationState == 0){
					//draw right
//					g2d.setColor(Color.WHITE);
//					g2d.fillOval(xStringLoc+45, yStringLoc-5, 10, 10);
					g2d.setColor(Color.RED);
					g2d.drawString(""+elevation, xStringLoc+50, yStringLoc);
				}
				else if(rotationState == 1){
					//draw bottom
//					g2d.fillOval(xStringLoc-5, yStringLoc+45, 10, 10);
					g2d.setColor(Color.RED);
					g2d.drawString(""+elevation, xStringLoc, yStringLoc+50);
				}
				else if(rotationState == 2){
					//draw left
					g2d.setColor(Color.RED);
					g2d.drawString(""+elevation, xStringLoc-50, yStringLoc);
				}
				else if(rotationState == 3){
					//draw top
					g2d.setColor(Color.RED);
					g2d.drawString(""+elevation, xStringLoc, yStringLoc-50);
				}
				
				if(ifThree){
					//do again but increase the rotation state
					rotationState = (rotationState + 1) % 4;
				}
				else
					break;
			}
		}
		g2d.dispose();
	}
	
	private void clearImage(BufferedImage image){
		g2d = image.createGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR)); 
		g2d.fillRect(0, 0, image.getWidth(), image.getHeight()); 
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		g2d.dispose();
	}
	
	private void clearDeveloperSpace(int x, int y, BufferedImage image){
		g2d = image.createGraphics();
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
		g2d.fillRect(x, y, 50, 50);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		g2d.dispose();
	}
	
	private void drawLines(BufferedImage image){
		g2d = image.createGraphics();
		g2d.setColor(Color.black);
		for(int i = 50; i < image.getWidth(); i+=50){
			//columns
			g2d.drawLine(i, 0, i, image.getWidth());
			//rows
			g2d.drawLine(0, i, image.getHeight(), i);
		}
	}
	
	private void initHashMap(){
		File imageSourceFile = null;
		this.imageSourceHashMap = new HashMap<String, String>();
		try{
			imageSourceFile = new File("bin/files/BoardImageStrings.txt");
			BufferedReader fileReader = new BufferedReader(new FileReader(imageSourceFile));
			String line = "";
			while((line = fileReader.readLine()) != null){
				String[] hash = line.split(" ");
				imageSourceHashMap.put(hash[0], hash[1]);
			}
			fileReader.close();
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
