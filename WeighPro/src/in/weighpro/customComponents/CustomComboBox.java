/* This class provides the pluggable and highly 
 * customizable transparent combo box
 *  used in various panels
 * Basically a panel with a label and a visible/invisible list
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */

package in.weighpro.customComponents;

import in.weighpro.listComponents.CustomList;
import in.weighpro.listComponents.CustomScrollBarUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CustomComboBox extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2966628888906466557L;
	private JLabel label;
	private boolean mouseHovered;
	private boolean selected;
	private JScrollPane pane;
	private CustomList list;

	// ---------Constructor to initialize all the indiviual components---------
	// ---------Takes an array of items,a boolean value which decides whether
	// an empty value is allowed and the bounds for the combo box which controls
	// the pop up visibility

	public CustomComboBox(Object[] items, boolean nullValue,
			final Rectangle bounds) {
		setBounds(bounds);// set bounds for the combo box
		// This block initializes the case where the combo box can accept an
		// empty value
		// Basically creates a new array object with an empty value at the
		// beginning
		if (nullValue) {
			Arrays.sort(items);
			Object[] modItems = new Object[items.length + 1];
			modItems[0] = " ";
			int i = 1;
			for (Object item : items) {
				modItems[i] = item;
				i++;
			}
			items = modItems;
			modItems = null;
		}
		setLayout(null);// custom layout
		label = new JLabel();// initialize a label
		label.setBounds(0, 0, 170, 30);// set bounds for the label
		setOpaque(false);// make this panel transparent
		label.setHorizontalAlignment(JLabel.CENTER);// center alignment for the
													// label
		label.setFont(new Font(Font.DIALOG, Font.PLAIN, 18));// Set font for the
																// label
		label.setForeground(new Color(255, 255, 255, 100));// white color text
		add(label);// add label to the panel
		setBorder(new EmptyBorder(0, 0, 0, 0));// no border

		list = new CustomList(items);// list for drop down

		pane = new JScrollPane(list,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);// Scroll pane
																// to hold the
																// list
																// Scroll pane
																// always has
																// the vertical
																// scroll bar
																// no horizontal
																// scroll bar
		pane.getViewport().setOpaque(false);// transparent scroll pane view port
		pane.setOpaque(false);// transparent scroll pane
		pane.setBorder(new LineBorder(Color.white, 1));// white border of
														// thickness 1

		pane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));// Empty
																				// border
																				// with
																				// padding

		pane.setBounds(0, 30, 200, 130);// set bounds for the panel
		pane.getVerticalScrollBar().setOpaque(false);// transparent vertical
														// scroll bar
		pane.getVerticalScrollBar().setUI(new CustomScrollBarUI());// custom
																	// vertical
																	// scroll
																	// bar

		// ----adding a mouse listener to the custom combo box
		// when the mouse is clicked set the clicked view for panel
		// See the paint Components method for the more details
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				selected = !selected;// this flag tracks the selected status
				if (selected)
					add(pane);// if combo box is selected show the pane, make
								// the pane visible
				else
					remove(pane);// if not selected make the pane invisible
				revalidate();// re-validate and repaint the panel
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {

				mouseHovered = true;// this flag tracks the presence of the
									// mouse in the panel
				repaint();// repaint the panel
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!selected)// if the panel is not selected set the mouse
								// hovered flag to false
					mouseHovered = false;
				repaint();
			}

		});

		// Adding a mouse listener to the list so that when an item is clicked
		// select that value on a double click
		list.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {
				// TODO find exactly why the next two lines are required
				@SuppressWarnings("rawtypes")
				JList list = (JList) evt.getSource();
				if (evt.getClickCount() == 2) {
					label.setText(list.getSelectedValue().toString());// set
																		// label
																		// text
																		// with
																		// the
																		// value
																		// of
																		// the
																		// selected
																		// item
					mouseHovered = false;// change the hovered status
					selected = !selected;// change the selected status
					remove(pane);// remove pane when an item is selected
					revalidate();// re-validate and repaint the panel
					repaint();
				}
			}
		});

		// Performs the same function as the above but when enter key is pressed
		// instead of a mouse click
		list.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					label.setText(list.getSelectedValue().toString());
					mouseHovered = false;
					selected = !selected;
					remove(pane);
					revalidate();
					repaint();
				}
			}

		});

		// This container listener is necessary for showing a pop up like effect
		// when the combo box is selected
		// changes the bounds of the panel when the combo box is selected
		// and also when an item is clicked

		addContainerListener(new ContainerListener() {
			// When the pane is added to the panel
			@Override
			public void componentAdded(ContainerEvent e) {
				setBounds(bounds.x, bounds.y, bounds.width, bounds.height + 130);

			}

			// When the pane is removed from the panel
			@Override
			public void componentRemoved(ContainerEvent e) {
				setBounds(bounds);

			}

		});

	}

	
	//-------The paint method to draw a custom drop down button and also mouse hovered and panel selected effects
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		//if mouse is hovered then set the hovered effects (The button and the borders have a white highlight)
		//the border of the panel is also drawn using lines
		if (!mouseHovered) {
			g2.setColor(new Color(255, 255, 255, 100));
			label.setForeground(new Color(255, 255, 255, 100));//off white color when mouse not hovering
			g2.drawLine(0, 0, 200, 0);
			g2.drawLine(0, 30, 200, 30);
			g2.drawLine(0, 0, 0, 30);
			g2.drawLine(200, 0, 200, 30);

		} else {
			g2.setColor(Color.white);
			label.setForeground(Color.white);//white color when mouse is hovered over the panel
			g2.drawLine(0, 0, 200, 0);
			g2.drawLine(0, 30, 200, 30);
			g2.drawLine(0, 0, 0, 30);
			g2.drawLine(200, 0, 200, 30);

		}
		int[] x = { 170, 190, 180 };
		int[] y = { 10, 10, 20 };
		g2.fillPolygon(x, y, 3);//this draws the triangle which acts as an indicator for the drop down list

	}

	//----Method to get the selected value---------------------
	public String getValue() {
		return label.getText().trim();
	}

	//-----Method to set the selected value (Used at times to initialize the initial selection)
	public void setValue(String value) {
		label.setText(value);
	}

}
