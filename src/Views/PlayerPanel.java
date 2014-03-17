package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel{
	private String color;
	private String name;
	private Color playerColor;
	private JLabel playerName, famePoints, actionPointsLeft, developers, oneTileRice, oneTileVillage, twoTile, actionTokens;
	private JLabel palaceCards;
	private HashMap<String, String> imageSourceHashMap;
	
	public PlayerPanel(String name, String color, int numFamePoints, int numActionPoints, int numDevelopers, int numRiceTile, int numVillageTile, int numTwoTile, int numActionTokens, int numPalaceCards){
		setLayout(new FlowLayout());
		this.name = name;
		this.color = color;
		
		try {
			Field field = Color.class.getField(color);
			this.playerColor = (Color)field.get(null);
		} catch (Exception e) {
			System.out.println("There was an image parseing the color string to a color");
			this.playerColor = Color.black;
		} 
		
		setBackground(Color.WHITE);
        setPreferredSize(new Dimension(200, 335));
        setMinimumSize(new Dimension(200, 335));
        setMaximumSize(new Dimension(200, 335));
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        
        initHashMap();
        initLayout(numFamePoints, numActionPoints, numDevelopers, numRiceTile, numVillageTile, numTwoTile, numActionTokens, numPalaceCards);
	}
	
	private void initLayout(int numFamePoints, int numActionPoints, int numDevelopers, int numRiceTile, int numVillageTile, int numTwoTile, int numActionTokens, int numPalaceCards) {
		JPanel leftPlayerInfo = new JPanel();
		leftPlayerInfo.setPreferredSize(new Dimension(115, 60));
		leftPlayerInfo.setBackground(Color.WHITE);
		this.add(leftPlayerInfo);
		
		playerName = new JLabel(name);
        playerName.setFont(new Font("Lucida Grande", 0, 18)); 
        playerName.setPreferredSize(new Dimension(110, 22));
        playerName.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        leftPlayerInfo.add(playerName);
        
        actionPointsLeft = new JLabel(numActionPoints+"");
        actionPointsLeft.setFont(new Font("Lucida Grande", 0, 48)); 
        actionPointsLeft.setHorizontalAlignment(SwingConstants.CENTER);
        actionPointsLeft.setPreferredSize(new Dimension(60, 60));
        actionPointsLeft.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.add(actionPointsLeft);
        
        famePoints = new JLabel(numFamePoints+"");
        famePoints.setFont(new Font("Lucida Grande", 1, 36));
        famePoints.setHorizontalAlignment(SwingConstants.LEFT);
        famePoints.setPreferredSize(new Dimension(110, 32));
        famePoints.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        leftPlayerInfo.add(famePoints);

        JSeparator jSeparator1 = new JSeparator();
        jSeparator1.setForeground(new Color(102, 102, 102));
        jSeparator1.setPreferredSize(new Dimension(180, 15));
        this.add(jSeparator1);
        
        developers = newJLabel(numDevelopers+"", imageSourceHashMap.get("layout_player_"+color), 80, 45); 
        this.add(developers);
        
        actionTokens = newJLabel(numActionTokens+"", imageSourceHashMap.get("layout_actionToken"), 80, 45); 
        this.add(actionTokens);

        oneTileRice = newJLabel(numRiceTile+"", imageSourceHashMap.get("layout_riceTile"), 80, 45); 
        this.add(oneTileRice);
        
        oneTileVillage = newJLabel(numVillageTile+"", imageSourceHashMap.get("layout_villageTile"), 80, 45); 
        this.add(oneTileVillage);

        twoTile = newJLabel(numTwoTile+"", imageSourceHashMap.get("layout_twoTile"), 160, 45); 
        this.add(twoTile);
        
        palaceCards = newJLabel(numPalaceCards+"", imageSourceHashMap.get("layout_palaceDeck"), 160, 75);
        this.add(palaceCards);
		
	}
	
	private JLabel newJLabel(String value, String src, int width, int height){
		JLabel label= new JLabel(value);
		label.setIcon(new ImageIcon(src));
		label.setFont(new Font("Lucida Grande", 0, 14));
		label.setPreferredSize(new Dimension(width, height));
		label.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		return label;
	}
	
	public void setCurrentPlayer(){
		this.setBorder(BorderFactory.createLineBorder(playerColor, 3));
	}
	
	public void setNotCurrentPlayer(){
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}
	
	private void initHashMap(){
		File imageSourceFile = null;
		this.imageSourceHashMap = new HashMap<String, String>();
		try{
			imageSourceFile = new File("bin/files/PlayerImageStrings.txt");
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
	
	
	// ---------- Actions -----------
	public void updateFamePoints(int newFamePoints){
		this.famePoints.setText(""+newFamePoints);
	}
	public void updateActionPoints(int actionPoints){
		this.actionPointsLeft.setText(""+actionPoints);
	}
	public void updateDevelopersOffBoard(int developersOffBoard){
		this.developers.setText(""+developersOffBoard);
	}
	public void useOneRiceTile(int numRiceTiles){
		this.oneTileRice.setText(""+numRiceTiles);
	}
	public void useOneVillageTile(int numVillageTiles){
		this.oneTileVillage.setText(""+numVillageTiles);
	}
	public void useTwoTile(int numTwoTiles){
		this.twoTile.setText(""+numTwoTiles);
	}
	public void useActionToken(int numActionTokens, int newNumActionPoints){
		this.actionTokens.setText(""+numActionTokens);
		updateActionPoints(newNumActionPoints);
	}
	public void updateNumPalaceCards(int numPalaceCards){
		this.palaceCards.setText(""+numPalaceCards);
	}
}
