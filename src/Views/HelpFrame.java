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
	BufferedImage helpImage;
	
	public HelpFrame(){
		setTitle("HELP ME");
		
		loadPanel();
	}
	
	private void loadPanel(){
		try{
			helpImage = ImageIO.read(this.getClass().getResource("/images/helpControls.png"));
			setSize(helpImage.getWidth(), helpImage.getHeight());
			setResizable(false);
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
