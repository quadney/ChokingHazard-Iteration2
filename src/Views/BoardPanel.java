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
		System.out.println("Board Panel is created.");
		
		initHashMap();
		getBackgroundImage();
		Dimension size = new Dimension(board.getWidth(), board.getHeight());
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
		
		this.tileImage = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.developers = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.tempImage = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
	}
	
	private void getBackgroundImage(){
		board = getImage(imageSourceHashMap.get("board"));
		repaint();
	}
	
//	public void repaint() {
//		if(!replaying)
//			super.repaint();
//	}
	
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
		g2d.setColor(Color.RED);
		g2d.drawString(""+elevation, xLoc+35, yLoc+35);
		g2d.dispose();
		//drawElevationLabel(xLoc, yLoc, rotationState, elevation, hashMapKey);
		
		repaint();
		System.out.println("placed tile");
	}
	
	//use this when the user is moving a tile around the board, and has not made the decision to place it yet
	public void moveTile(int xLoc, int yLoc, int rotationState, String hashMapKey){
		clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.rotate(rotationState*Math.PI/2, xLoc+25, yLoc+25);
		g2d.drawImage(getImage(imageSourceHashMap.get(hashMapKey)), null, xLoc, yLoc);
//		g2d.setColor(Color.YELLOW);
//		g2d.setStroke(new BasicStroke(2.0f));
//		g2d.drawRect(xLoc, yLoc, 50, 50);
		g2d.dispose();
		repaint();
	}
	
	public void rotate(int xLoc, int yLoc, int rotationState, String hashMapKey){
		clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.rotate(rotationState*Math.PI/2);
		g2d.drawImage(getImage(imageSourceHashMap.get(hashMapKey)), null, xLoc, yLoc);
		g2d.dispose();
		repaint();
	}
	
	public void trackDeveloperPath(int playerIndex, int xLoc, int yLoc){
		//clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.setColor(Color.YELLOW);
		g2d.setStroke(new BasicStroke());
		g2d.drawRect(xLoc, yLoc, 50, 50);
		g2d.dispose();
		repaint();
	}
	
//	public void drawPlayerPath(int playerIndex, int[] x, int[] y){
//		clearImage(tempImage);
//		g2d = tempImage.createGraphics();
//		g2d.setColor(Color.YELLOW);
//		g2d.setStroke(new BasicStroke());
//		System.out.println("X Length: "+x.length);
//		for(int i = 0; i < x.length; ++i){
//			g2d.drawRect(x[i], y[i], 50, 50);
//		}
//		g2d.drawImage(getImage("images/player_"+playerIndex+".png"), null, x[x.length-1], y[y.length-1]);
//		g2d.dispose();
//		repaint();
//	}
	
	//use this when the user places a developer onto the board, aka presses enter
	public void placeDeveloper(String playerColor, int xLoc, int yLoc){
		clearImage(tempImage);
		g2d = developers.createGraphics();
		g2d.drawImage(getImage(imageSourceHashMap.get("player_"+playerColor)), null, xLoc, yLoc);
		g2d.dispose();
		repaint();
	}
	
	//tabbing through developers
	public void highlightDeveloper(int xLoc, int yLoc){
		clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.setColor(Color.RED);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.drawRect(xLoc, yLoc, 50, 50);
		g2d.dispose();
		repaint();
	}
	
	public void removeDeveloper(int xLoc, int yLoc){
		//removes developer from the image
		clearDeveloperSpace(xLoc, yLoc, developers);
		clearImage(tempImage);
		repaint();
	}
	
	public void selectHighlightedDeveloper(String playerColor, int xLoc, int yLoc){
		//this method removes the developer from the buffered image, and places it on the temp image
		clearDeveloperSpace(xLoc, yLoc, developers);
		clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.drawImage(getImage(imageSourceHashMap.get("player_"+playerColor)), null, xLoc, yLoc);
		g2d.setColor(Color.YELLOW);
		g2d.setStroke(new BasicStroke(2.0f));
		g2d.drawRect(xLoc, yLoc, 50, 50);
		g2d.dispose();
		repaint();
	}
	
	public void cancel(){
		clearImage(tempImage);
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("calling paint component");
		g.drawImage(board, 0, 0, null);
		g.drawImage(tileImage, 0, 0, null);
		g.drawImage(tempImage, 0, 0, null);
		g.drawImage(developers, 0, 0, null);
	}
	
	private void drawElevationLabel(int xLoc, int yLoc, int rotationState, int elevation, String hashMapKey){
		System.out.println("drawing elevation label");
		int imageWidth = 50;
		int imageHeight = imageWidth;
		g2d = tileImage.createGraphics();
		if(hashMapKey.equals("threeTile")){
			imageWidth *= 2;
			imageHeight = imageWidth;
		}
		else if(hashMapKey.equals("twoTile")){
			imageWidth *= 2;
		}
		else{
			//draw the elevation with no restrictions
			System.out.println("drawing the elevation");
			//g2d.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

			
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
