package in.weighpro.customImageLabels;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

public class ThumbNailGenerator extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3913420437873008893L;
	
	
	public ThumbNailGenerator(String path,int xBound,int yBound,MouseListener ml) {
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(path));
		this.setIcon(imageIcon);
		setBorder(new LineBorder(Color.white,1));
		setBounds(xBound,yBound,100,56);
		addMouseListener(ml);
		this.setName(path.substring(6,path.length()));
	}
	
}