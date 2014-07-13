/* This class provides the pluggable radio button component
 *  used in the first entry panel
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */

package in.weighpro.customComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;

import javax.swing.JRadioButton;


//Creating this class as a child of the radio button to get all the functionality but
//not the graphics associated with it
public class CustomRadioButton extends JRadioButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3269501967903691292L;

	private String name;
	private Color color = Color.white;

	private boolean selected = false;

	//This is a constructor which takes a string as an argument and initializes a radio button
	//with that text
	public CustomRadioButton(String name) {
		super(name);//Calling the constructor of the Radio Button class  with the text
		setOpaque(false);//Transparent background
		this.name = name;
	}

	
	//Custom graphics method to draw the button and the text 
	public void paintComponent(Graphics g) {
		g.setColor(color);

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(1.2f));
		drawCircle(g2, 10, 10, 5); // center (10,10) r=5//draw the selector circle
		g2.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));
		g2.drawString(name, 25, 17);//Drawing the text beside the selector
		if (selected) {
			drawFilledCircle(g2, 10, 10, 2);//draw a selected circle inside the selector circle
		}
	}

	//Method to draw the circle for the selection component
	public void drawCircle(Graphics2D cg, int xCenter, int yCenter, int r) {
		cg.drawOval(xCenter - r, yCenter - r, 2 * r, 2 * r);
	}

	//Method to draw the filled circle for the selection component
	public void drawFilledCircle(Graphics2D cg, int xCenter, int yCenter, int r) {
		Ellipse2D.Double circle = new Ellipse2D.Double(xCenter - r,
				yCenter - r, 2 * r, 2 * r);
		cg.fill(circle);
		cg.draw(circle);
	}

	
	//-----Method to set the button in the selected status
	public void setSelected(boolean selected) {
		this.selected = selected;
		repaint();
	}

	//----Method to get the selected status of the button
	public boolean getSelected() {
		return selected;
	}
	
	//----Method to set the color to red to show that the none of the buttons have been selected
	//See usage in the first entry panel
	public void setNullSelection(){
		color = new Color(150,0,0);
		repaint();
	}
	
	//----Method to reset the color to white when a selection has been done after a previous null selection
	//See usage in the first entry panel
	public void setValidSelection(){
		color = Color.white;
		repaint();
	}

}