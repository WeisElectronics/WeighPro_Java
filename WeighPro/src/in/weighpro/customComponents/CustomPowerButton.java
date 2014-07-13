/* This class provides the pluggable power off button component
 *  used in the settings panel of the main frame
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */


package in.weighpro.customComponents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.border.EmptyBorder;

//----This class is a child of the abstract button class but does not incorporate any
//of the graphics related with it just the functions associated with it


public class CustomPowerButton extends AbstractButton {

	private static final long serialVersionUID = -8586713983715081526L;
	private boolean mouseHovered = false;

	//---Constructor to initialize the button-------------------
	public CustomPowerButton() {
		setOpaque(false);//transparent 
		setBorder(new EmptyBorder(0, 0, 0, 0));//no border
		final ActionEvent actionEvent = new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, "Shutdown");//Action listener when this button is clicked
		
		//removing any mouse listeners which are present to avoid processing overhead
		
		for (MouseListener mouseListener : getMouseListeners()) {
			removeMouseListener(mouseListener);
		}
		//removing all action listeners which are present to avoid processing overhead
		for (ActionListener actionListener : getActionListeners()) {
			removeActionListener(actionListener);
		}
		
		
		//adding a mouse listener to do custom effects
		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				fireActionPerformed(actionEvent);//using the action listener that was created before
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				mouseHovered = true;//flag to track mouse presence over the button
				repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				mouseHovered = false;
				repaint();
			}

		});
	}

	//----this button does not use any of the inherited graphics hence super() is not called------
		//has all custom graphics 
		
	public void paintComponent(Graphics g) {
		//If mouse is hovered the button is highlighted white
		
		if (mouseHovered) {
			g.setColor(Color.white);
			g.drawString("Shutdown", 0, 10);//A string that appears above the button when the mouse is hovered
		} else {
			g.setColor(new Color(255, 255, 255, 100));//else an off-white color is used for painting

		}
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(8f));
		g2.drawArc(10, 20, 55, 55, 120, 300);//drawing an arc
		g2.drawLine(37, 15, 37, 33);//with a line in the middle
	}
}
