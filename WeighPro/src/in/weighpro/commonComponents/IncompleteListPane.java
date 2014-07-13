/*
 * This class provides the pluggable incomplete list panel
 *  used in the first entry as well as the second entry pane
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */

package in.weighpro.commonComponents;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import in.weighpro.MainClasses.MainFrame;
import in.weighpro.dataComponents.Entry;
import in.weighpro.dataComponents.IncompleteEntries;
import in.weighpro.listComponents.CustomList;
import in.weighpro.listComponents.CustomScrollBarUI;
import in.weighpro.panels.SecondEntryPanel;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

public class IncompleteListPane extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3233454759378926060L;
	private CustomList list;
	private IncompleteEntries incompEntries;
	private JScrollPane pane;

	// ---- Constructor for generating the list and setting the look and feel
	// ----------
	public IncompleteListPane() throws SQLException {
		setLayout(null);
		setOpaque(false);
		setBounds(500, 85, 345, 435);
		resetList();
	}

	// ----- Method which resets the list after getting the incomplete entries
	// from the database
	public void resetList() throws SQLException {

		if (pane != null) // check whether the pane has been initialized at
							// least once
			remove(pane); // removing the pane if it is present
		incompEntries = new IncompleteEntries(); // instantiating the
													// IncompleteEntries class
		list = new CustomList(incompEntries.getIncompleteEntries()); // initializing
																		// /
																		// re-initializing
																		// the
																		// list

		list.addMouseListener(new MouseAdapter() { // Adding a mouse click
													// listener and depending on
													// which
													// pane is currently visible
													// performing the respective
													// action

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					if (MainFrame.firstEntryPanel.isVisible()) { // if the first
																	// entry
																	// pane is
																	// visible
																	// then make
																	// it
						MainFrame.firstEntryPanel.setVisible(false); // invisible
																		// then
																		// load
																		// the
																		// second
																		// entry
																		// pane
																		// with
																		// the
						if (MainFrame.secondEntryPanel.weight != null) // corresponding
																		// entry
																		// details
							MainFrame.secondEntryPanel.stopThread();
						MainFrame.background.remove(MainFrame.secondEntryPanel); // removing
																					// the
																					// second
																					// entry
																					// pane
																					// and
						MainFrame.secondEntryPanel = new SecondEntryPanel( // creating
																			// a
																			// new
																			// second
																			// entry
																			// pane
																			// using
								(Entry) list.getSelectedValue()); // the
																	// corresponding
																	// entry and
																	// adding it
																	// again

						MainFrame.background.add(MainFrame.secondEntryPanel);
						MainFrame.menuPanel.setAllDisabled();
						MainFrame.secondEntryPanel.setVisible(true);
						MainFrame.menuPanel.secondEntry.setClicked(true);
					} else if (MainFrame.secondEntryPanel.isVisible()) { // if
																			// the
																			// second
																			// entry
																			// pane
																			// is
																			// visible
						MainFrame.background.remove(MainFrame.secondEntryPanel); // make
																					// it
																					// invisible
																					// and
																					// then
																					// remove
																					// it
						MainFrame.secondEntryPanel = new SecondEntryPanel( // re-initializing
																			// using
																			// the
																			// corresponding
																			// entry
								(Entry) list.getSelectedValue()); // and then
																	// adding it
																	// back and
																	// making it
																	// visible
						MainFrame.background.add(MainFrame.secondEntryPanel);
						MainFrame.secondEntryPanel.setVisible(true);

					}
				}
			}

		});
		pane = new JScrollPane(list,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, // These
																// statements
																// use the list
																// as the
																// content
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // of the
																	// scroll
																	// pane and
																	// make sure
																	// the
																	// horizontal
																	// scroll
																	// bar is
																	// never
																	// present
		pane.getViewport().setOpaque(false); // Make the scroll pane view port
												// transparent
		pane.setOpaque(false); // Make the scroll pane transparent
		pane.setBorder(new LineBorder(Color.white, 1)); // Set the border of the
														// scroll pane

		pane.setViewportBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0)); // Padding
																				// for
																				// the
																				// pane
																				// contents

		pane.setBounds(0, 0, 345, 435); // Setting the bounds of the pane
		add(pane); // Adding the pane
		pane.getVerticalScrollBar().setOpaque(false);// making the vertical
														// scroll bar
														// transparent
		pane.getVerticalScrollBar().setUI(new CustomScrollBarUI());// Using the
																	// custom
																	// scroll
																	// bar the vertical
																	// scroll bar
		revalidate(); //revalidating the panel so that the changes take place
		repaint();  // Repainting all the components
	}
}
