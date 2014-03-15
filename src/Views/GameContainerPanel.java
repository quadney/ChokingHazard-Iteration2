package Views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;;


public class GameContainerPanel extends JPanel {
	private final static int WIDTH = 1300;
	private final static int HEIGHT = 840;
	
	public GameContainerPanel(BoardPanel board, PlayerPanel[] players, SharedComponentPanel shared){
		super(new BorderLayout());
		
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
		setBorder(BorderFactory.createEmptyBorder(0, 25, 30, 25));
		
		initPanels(board, players, shared);
	}
	
	private void initPanels(BoardPanel board, PlayerPanel[] players, SharedComponentPanel shared){
		add(shared, BorderLayout.NORTH);
		
		add(board, BorderLayout.CENTER);
		
		JPanel leftColumn = new JPanel();
		leftColumn.setLayout(new BorderLayout());
		leftColumn.setPreferredSize(new Dimension(205, 700));
		leftColumn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
		add(leftColumn, BorderLayout.WEST);
		
		JPanel rightColumn = new JPanel();
		rightColumn.setLayout(new BorderLayout());
		rightColumn.setPreferredSize(new Dimension(205, 700));
		rightColumn.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		add(rightColumn, BorderLayout.EAST);
		
		for(int i = 0; i < players.length; i++){
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
	
	public int promptUserForPalaceValue(){
		String[] palaces = {"2", "4", "6", "8", "10"};
		JComboBox<String> palaceComboBox = new JComboBox<String>(palaces);
		palaceComboBox.setEditable(true);
		JOptionPane.showMessageDialog( null, palaceComboBox, "Select what Palace Value you would like to place", JOptionPane.QUESTION_MESSAGE);
		
		return ((palaceComboBox.getSelectedIndex()+1)*2);
	}
	
	public boolean askUserIfWouldLikeToHoldAPalaceFestival(){
		
		int holdFestival = JOptionPane.showConfirmDialog(null, "Would you like to hold a Palace Festival?", "Let's Party!", JOptionPane.YES_NO_OPTION);
		if(holdFestival == 1)
			return true;
		return false;
	}

}
