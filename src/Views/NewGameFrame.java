package Views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NewGameFrame extends JFrame {
	JPanel newGamePanel;
	JButton startGame;
	JPanel[] newPlayers;
	JTextField[] playerNames;
	JComboBox[] colorSelection;
	JComboBox playerSelectionBox;
	int width = 500;
	int height = 500;
	
	public NewGameFrame() {
		newGamePanel = new JPanel();
		newGamePanel.setPreferredSize(new Dimension(width, height));
		newGamePanel.setLayout(new FlowLayout());
		newGamePanel.setBackground(Color.WHITE);
		newGamePanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
		
		newPlayers = new JPanel[4];
		playerNames = new JTextField[4];
		colorSelection = new JComboBox[4];
		
		startGame = new JButton("Let's Play!");
		startGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//does this button need to have this action listenr in the game frame class?
				
				//need to parse through the information
				//also check if any of the colors are the same, because they can't be
				int numPlayers = playerSelectionBox.getSelectedIndex();
				if(numPlayers == 0){
					//show option pane that the user needs to select players
				}
				else{
					numPlayers += 1;
				}
				//need to return that information the the GameFrame or something
			}
		});
		
		setTitle("Setup New Game");
		setSize(width, height);
		setResizable(false);
		
		setUpView();
		displayPanel(newGamePanel);
	}

	private void setUpView(){
		JPanel numPlayerPanel = new JPanel();
		numPlayerPanel.setPreferredSize(new Dimension(480, 70));
		JLabel numPlayers = new JLabel("Number of Players: ");
		numPlayerPanel.add(numPlayers);
		
		String[] players = {"Select number of Players", "2", "3", "4"};
		playerSelectionBox = new JComboBox(players);
		playerSelectionBox.setSelectedIndex(0);
		playerSelectionBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = playerSelectionBox.getSelectedIndex();
				if(selectedIndex > 0){
					displayPlayerNamePanels(selectedIndex+1);
				}
				
			}
		});
		numPlayerPanel.add(playerSelectionBox);
		
		newGamePanel.add(numPlayerPanel);
		
		String[] colors = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple"}; 
		
		for(int i = 0; i < newPlayers.length; ++i){
			newPlayers[i] = new JPanel();
			newPlayers[i].setPreferredSize(new Dimension(480, 70));
			newPlayers[i].add(new JLabel("Player "+(i+1)+" name: "));
			playerNames[i] = new JTextField();
			playerNames[i].setPreferredSize(new Dimension(100, 25));
			newPlayers[i].add(playerNames[i]);
			colorSelection[i] = new JComboBox(colors);
			colorSelection[i].setSelectedIndex(i);
			newPlayers[i].add(colorSelection[i]);
			newGamePanel.add(newPlayers[i]);
			newPlayers[i].setVisible(false);
		}
		
		newGamePanel.add(startGame);
		
	}
	
	private void displayPlayerNamePanels(int players){
		for(int i = 0; i < newPlayers.length; ++i){
			newPlayers[i].setVisible(true);
			if(i >= players){
				newPlayers[i].setVisible(false);
			}
		}
	}
	
	private void displayPanel(JPanel panel){
		this.setContentPane(panel);
		this.validate();
	}

}
