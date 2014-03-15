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

public class PlayerPanel extends JPanel{
	private String color;
	private String name;
	private Color playerColor;
	private JLabel playerName, famePoints, actionPointsLeft, numDevelopers, numOneTileRice, numOneTileVillage, numTwoTile, numActionTokens;
	private HashMap<String, String> imageSourceHashMap;
	
	public PlayerPanel(String name, String color){
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
        setPreferredSize(new Dimension(270, 335));
        setMinimumSize(new Dimension(270, 335));
        setMaximumSize(new Dimension(270, 335));
        setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255)));
        
        initHashMap();
        initLayout();
        setCurrentPlayer();
	}
	
	private void initLayout() {
		JPanel leftPlayerInfo = new JPanel();
		leftPlayerInfo.setPreferredSize(new Dimension(86, 60));
		leftPlayerInfo.setBackground(Color.WHITE);
		this.add(leftPlayerInfo);
		
		playerName = new JLabel(name);
        playerName.setFont(new Font("Lucida Grande", 0, 18)); 
        playerName.setPreferredSize(new Dimension(80, 22));
        playerName.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
        leftPlayerInfo.add(playerName);
        
        actionPointsLeft = new JLabel();
        actionPointsLeft.setFont(new Font("Lucida Grande", 0, 48)); 
        actionPointsLeft.setHorizontalAlignment(SwingConstants.CENTER);
        actionPointsLeft.setPreferredSize(new Dimension(60, 60));
        actionPointsLeft.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        this.add(actionPointsLeft);
        
        famePoints = new JLabel();
        famePoints.setFont(new Font("Lucida Grande", 1, 36));
        famePoints.setHorizontalAlignment(SwingConstants.LEFT);
        famePoints.setPreferredSize(new Dimension(80, 32));
        famePoints.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
        leftPlayerInfo.add(famePoints);

        JSeparator jSeparator1 = new JSeparator();
        jSeparator1.setForeground(new Color(102, 102, 102));
        jSeparator1.setPreferredSize(new Dimension(158, 15));
        this.add(jSeparator1);
        
        numDevelopers = newJLabel(imageSourceHashMap.get("layout_player_"+color)); 
        this.add(numDevelopers);

        numOneTileRice = newJLabel(imageSourceHashMap.get("layout_riceTile")); 
        this.add(numOneTileRice);
        
        numOneTileVillage = newJLabel(imageSourceHashMap.get("layout_villageTile")); 
        this.add(numOneTileVillage);

        numTwoTile = newJLabel(imageSourceHashMap.get("layout_twoTile")); 
        this.add(numTwoTile);
        
        numActionTokens = newJLabel(imageSourceHashMap.get("layout_actionToken")); 
        this.add(numActionTokens);
		
	}
	
	private JLabel newJLabel(String src){
		JLabel label= new JLabel();
		label.setIcon(new ImageIcon(src));
		label.setFont(new Font("Lucida Grande", 0, 14));
		label.setPreferredSize(new Dimension(150, 41));
		label.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		return label;
	}
	
	public void setPlayerName(String playersName){
		this.playerName.setText(playersName);
	}
	
	public void setPlayerColor(String color){
		this.color = color;
		this.playerColor = Color.getColor(color);
	}

	public void setFamePoints(int famePoints) {
		this.famePoints.setText(""+famePoints);
	}

	public void setActionPointsLeft(int actionPointsLeft) {
		this.actionPointsLeft.setText(""+actionPointsLeft);
	}

	public void setNumDevelopers(int numDevelopers) {
		this.numDevelopers.setText(""+numDevelopers);
	}

	public void setNumOneTileRice(int numOneTileRice) {
		this.numOneTileRice.setText(""+numOneTileRice);
	}

	public void setNumOneTileVillage(int numOneTileVillage) {
		this.numOneTileVillage.setText(""+numOneTileVillage);
	}

	public void setNumTwoTile(int numTwoTile) {
		this.numTwoTile.setText(""+numTwoTile);
	}

	public void setNumActionTokens(int numActionTokens) {
		this.numActionTokens.setText(""+numActionTokens);
	}
	
	public void setCurrentPlayer(){
		this.setBorder(BorderFactory.createLineBorder(playerColor));
	}
	
	public void setNotCurrentPlayer(){
		this.setBorder(BorderFactory.createLineBorder(Color.WHITE));
	}
	
	private void initHashMap(){
		// TODO test this
		File imageSourceFile = null;
		this.imageSourceHashMap = new HashMap<String, String>();
		try{
			imageSourceFile = new File("/files/PlayerImageStrings.txt");
			BufferedReader fileReader = new BufferedReader(new FileReader(imageSourceFile));
			String line = fileReader.readLine();
			String[] hash = line.split(" ");
			imageSourceHashMap.put(hash[0], hash[1]);
			System.out.println("Hash 0: "+hash[0]);
			System.out.println("Hash 1: "+hash[1]);
		} catch(Exception e){
			
		}
		System.out.println("File Name: "+imageSourceFile.getName());
	}
	
	
	// ---------- Actions -----------
	public void updateFamePoints(){
		
	}
	public void updateActionPoints(){
		
	}
	public void useDeveloper(){
		
	}
	public void removeDeveloperFromBoard(){
		
	}
	public void useOneRiceTile(){
		
	}
	public void useOneVillageTile(){
		
	}
	public void useTwoTile(){
		
	}
	public void useActionToken(){
		
	}
	public void displayPLayerFestivalCards(){
		
	}
	public void hidePlayerFestivalCards(){
		
	}
	public void useFestivalCard(){
		
	}
	public void removeFestivalCard(){
		
	}
}
