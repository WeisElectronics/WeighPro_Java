/*
* This class provides the
* pluggable list which is 
* used in various panels 

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.listComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class CustomList extends JList<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8292150763793513858L;

	//constructor to initialize the list
	public CustomList(Object[] items) {
		super(items);//populate the list with items passed
		setOpaque(false);//make the list transparent
		setForeground(new Color(255, 255, 255, 255));//set the text color to white
		setFont(new Font(Font.DIALOG, Font.PLAIN, 18));//set the font of the list items
		this.setCellRenderer(new CustomListLabel());//custom cell renderer used to render the contents of the list
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//allow only single selection

	}

}