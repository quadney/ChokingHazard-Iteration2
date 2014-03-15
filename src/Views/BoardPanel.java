package Views;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	private BufferedImage board;
	private BufferedImage tileImage;
	private BufferedImage developers;
	private BufferedImage tempImage;
	private BufferedImage planningMode;
	private Graphics2D g2d;
	private HashMap<String, String> imageSourceHashMap;

	public BoardPanel(HashMap<String, String> imageSources){
		getBackgroundImage();
		Dimension size = new Dimension(board.getWidth(), board.getHeight());
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
		
		this.imageSourceHashMap = imageSources;
		this.tileImage = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.developers = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
		this.tempImage = new BufferedImage(board.getWidth(), board.getHeight(), BufferedImage.TYPE_INT_ARGB);
	}
	
	private void getBackgroundImage(){
		board = getImage(imageSourceHashMap.get("board"));
		repaint();
	}
	
	private BufferedImage getImage(String source){
		BufferedImage returnImage = null;
		try{
			returnImage = ImageIO.read(this.getClass().getResource(source));
		} catch(IOException e){
			
		}
		return returnImage;
	}
	
	public void placeTile(int xLoc, int yLoc, int elevation, String hashMapKey){
		clearImage(tempImage);
		g2d = tileImage.createGraphics();
		//g2d.rotate(rotationState*Math.PI/2, xLoc+50, yLoc+50);
		g2d.scale(-0.1*elevation, -0.1*elevation);
		g2d.drawImage(getImage(imageSourceHashMap.get(hashMapKey)), null, xLoc, yLoc);
		g2d.setColor(Color.YELLOW);
		g2d.drawString(""+elevation, xLoc+35, yLoc+35);
		g2d.dispose();
		repaint();
	}
	
	public void moveTile(int xLoc, int yLoc, int rotationState, String hashMapKey){
		clearImage(tempImage);
		g2d = tempImage.createGraphics();
		g2d.rotate(rotationState*Math.PI/2);
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
	
	public void placeDeveloper(String playerColor, int xLoc, int yLoc){
		clearImage(tempImage);
		g2d = developers.createGraphics();
		g2d.drawImage(getImage(imageSourceHashMap.get("player_"+playerColor)), null, xLoc, yLoc);
		g2d.dispose();
		repaint();
	}
	
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
		g.drawImage(board, 0, 0, null);
		g.drawImage(tileImage, 0, 0, null);
		g.drawImage(tempImage, 0, 0, null);
		g.drawImage(developers, 0, 0, null);
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
}
