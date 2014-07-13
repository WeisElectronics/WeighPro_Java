/* This class provides the pluggable reset button component
 *  used in the menu panel
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */

package in.weighpro.customComponents;

import in.weighpro.customFonts.MenuFontGenerator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FontMetrics;
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

public class ResetButton extends AbstractButton {

	private static final long serialVersionUID = 4059884845727670365L;

	private int borderStrokeSize = 4;
	private String buttonText;
	private boolean clicked = false;
	private boolean mouseHovered = false;

	// ---Constructor to initialize the button-------------------

	public ResetButton(String buttonText) {
		setOpaque(false);// transparent
		setBackground(new Color(255, 255, 255, 0));// white background
		setBorder(new EmptyBorder(0, 0, 0, 0));// no border
		final ActionEvent actionEvent = new ActionEvent(this,
				ActionEvent.ACTION_PERFORMED, buttonText);// Action listener
															// when this button
															// is clicked

		this.buttonText = buttonText;// setting the text of the button

		// removing any mouse listeners which are present to avoid processing
		// overhead

		for (MouseListener mouseListener : getMouseListeners()) {
			removeMouseListener(mouseListener);
		}

		// removing all action listeners which are present to avoid processing
		// overhead

		for (ActionListener actionListener : getActionListeners()) {
			removeActionListener(actionListener);
		}

		// adding a mouse listener to do custom effects

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				clicked = true;
				mouseHovered = false;
				fireActionPerformed(actionEvent);// using the action listener
													// that was created before
				// if the button is not already clicked

				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!clicked) {
					mouseHovered = true;// flag to track mouse presence over the
										// button
					// when the button is not clicked

					repaint();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!clicked) {
					mouseHovered = false;
					repaint();
				}
			}

		});
	}

	// Method to return the clicked status of the button

	public boolean getClicked() {
		return clicked;
	}

	// ----this button does not use any of the inherited graphics hence super()
	// is not called------
	// has all custom graphics

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// If mouse is clicked the button is highlighted white

		if (clicked) {
			g2.setColor(new Color(240, 240, 240, 240));
		} else {
			g2.setColor(new Color(255, 255, 255, 150));

		}

		g2.fillRect(0, 0, getWidth(), getHeight());// Giving the button a
													// rectangular shape
		g2.setFont(MenuFontGenerator.menuPanelFont);// We are using copper plate
													// Gothic which is
													// initialized in the custom
													// fonts package
		g2.setColor(new Color(50, 50, 50));// Grey color for the button when it
											// is not clicked
		FontMetrics size = g2.getFontMetrics();// For calculating the actual
												// width of the text for the
												// center text alignment
		// Writing the name of the button
		g2.drawString(
				buttonText,
				(getWidth() - size.stringWidth(buttonText)) / 2,
				(getHeight() + size.getLineMetrics(buttonText, g2).getHeight() / 2) / 2);
		// if the mouse is hovered when the button is not in the clicked state
		// then change the background
		// to give it a transition look and feel

		if (mouseHovered) {

			g2.setStroke(new BasicStroke(borderStrokeSize));
			g2.setColor(new Color(100, 100, 100, 200));
			g2.drawRect(1, 1, getWidth() - borderStrokeSize / 2 - 1,
					getHeight() - borderStrokeSize / 2 - 1);// drawing a border
															// rectangle along
															// the edges of the
															// button
		}

	}

	// This method sets the clicked status of the button used to set the default
	// selected option from the menu panel

	public void setClicked(boolean clicked) {
		this.clicked = clicked;
		if (clicked)
			mouseHovered = false;

		repaint();

	}
}
