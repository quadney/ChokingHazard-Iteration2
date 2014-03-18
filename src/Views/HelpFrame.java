package Views;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class HelpFrame extends JFrame {
	private final int WIDTH, HEIGHT;
	BufferedImage helpImage;
	
	public HelpFrame(){
		WIDTH = 915;
		HEIGHT = 450;
		setTitle("Keyboard Controls");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		loadPanel();
	}
	
	private void loadPanel(){
		try{
			helpImage = ImageIO.read(this.getClass().getResource("/images/helpControls.png"));
			displayBufferedImage(helpImage);
		}catch(IOException e){
				System.out.println(e);
		}
		
	}
	
	private void displayBufferedImage(BufferedImage image){
		this.setContentPane(new JScrollPane(new JLabel(new ImageIcon(image))));
		this.validate();
	}
}
