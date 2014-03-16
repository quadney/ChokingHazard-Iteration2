package Views;

import javax.swing.JButton;
import javax.swing.JFrame;

import Models.JavaPlayer;

public class HoldFestivalFrame extends JFrame {
	//players who are in the city are in the festival
	//players can drop out of the festival
	//players play festival cards
	JButton[] dropPlayerFromFestivalButtons;
	int indexOfPlayerToPlayCard;
	
	public HoldFestivalFrame(JavaPlayer[] playersInFestival, int indexOfPlayer){
		this.indexOfPlayerToPlayCard = indexOfPlayer;
		dropPlayerFromFestivalButtons = new JButton[playersInFestival.length];
	}

}
