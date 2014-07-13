/* This class provides the pluggable heading label component
 *  used on the top of incomplete list in various panels
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */


package in.weighpro.customComponents;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;


public class HeadingPanel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3260008149317704637L;
	boolean clicked = false;

	
//Constructor to initialize the component
public HeadingPanel(String text){
	super(text);//Call to the constructor of JLabel class
	setName(text);//Setting the name of the component
	setOpaque(false);//translucent effect
	setFont(new Font(Font.DIALOG,Font.PLAIN,18));//setting the font 
	setBackground(new Color(255,255,255,80));//setting the background color to an off white color
	setBorder(new EmptyBorder(0,0,0,0));//Empty border 
	setForeground(new Color(0,0,0,100));//gray colored text
	setHorizontalAlignment(JLabel.CENTER);//center alignment for the text
	
	//remove all mouse listeners to avoid processing overhead
	for(MouseListener mouseListener : getMouseListeners()){
		removeMouseListener(mouseListener);
	}
	
	
}

//This is necessary for getting the translucent effect
protected void paintComponent(Graphics g) {
	g.setColor(getBackground());
	g.fillRect(0, 0, getWidth(), getHeight());
	super.paintComponent(g);

}


}
