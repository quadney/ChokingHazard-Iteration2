package Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Models.JavaPlayer;

public class GameContainerPanel extends JPanel {
	private final static int WIDTH = 1300;
	private final static int HEIGHT = 840;
	SharedComponentPanel sharedComponents; 
	BoardPanel board;
	PlayerPanel[] players;
	
	public GameContainerPanel(int numberOfPlayers){
		super(new BorderLayout());
		players = new PlayerPanel[numberOfPlayers];
		
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(0, 25, 30, 25));
		
		initPanels(numberOfPlayers);
	}
	
	private void initPanels(int numberOfPlayers){
		sharedComponents = new SharedComponentPanel();
		add(sharedComponents, BorderLayout.NORTH);
		
		board = new BoardPanel();
		add(board, BorderLayout.CENTER);
		
		JPanel leftColumn = new JPanel();
		leftColumn.setLayout(new BorderLayout());
		leftColumn.setPreferredSize(new Dimension(175, 700));
		leftColumn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		add(leftColumn, BorderLayout.WEST);
		
		JPanel rightColumn = new JPanel();
		rightColumn.setLayout(new BorderLayout());
		rightColumn.setPreferredSize(new Dimension(175, 700));
		rightColumn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		add(rightColumn, BorderLayout.EAST);
		
		for(int i = 0; i < numberOfPlayers; i++){
			players[i] = new PlayerPanel("Player Name", "red");
			switch(i){
				case 0:
					leftColumn.add(players[i], BorderLayout.NORTH);
					break;
				case 1:
					rightColumn.add(players[i], BorderLayout.NORTH);
					break;
				case 2:
					leftColumn.add(players[i], BorderLayout.SOUTH);
					break;
				case 3:
					rightColumn.add(players[i], BorderLayout.SOUTH);
					break;
			}
		}
	}
	
	/* ----- SETTERS FOR THIS CLASS'S COMPONENTS ----- */
//	public void setPlayerPanels(PlayerModel[] playerModels){
//		for(int i = 0; i < players.length; ++i){
//			players[i] = new PlayerPanel(playerModels[i].getName(), playerModels[i].getColor());
//		}
//	}


}
