/*
* This class provides the
* pluggable cell renderer
* which is used to give the 
* look and fell to the contents 
* of the list

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.listComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

//this class renders the indviual cells of the list
//it uses the functionality and the methods of the JLabel
//it implements the ListCellRenderer which provides the functionality to act as a list cell
public class CustomListLabel extends JLabel implements ListCellRenderer<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7559320301298288702L;

	//constructor to initialize the renderer
	CustomListLabel() {
		setOpaque(true);//set transparent
		setFont(new Font(Font.DIALOG, Font.PLAIN, 18));//set font of the renderer
		this.setBorder(new EmptyBorder(5, 10, 5, 0));//set an empty border
		setForeground(new Color(255, 255, 255));//set the text color to white
	}

	// this method is invoked whenever a list item is being rendered
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		setText(value.toString().trim());//set text using the toString method of the passed object
										// the entry object's toString method returns vehicle number for this reason
		if (isSelected) {//if the current item is selected
			setBackground(new Color(255, 255, 255));//set the background to white color
			setForeground(new Color(100, 100, 100));//text color is set to a grey shade
		} else {
			setBackground(new Color(255, 255, 255, 0));//if not selected make it transparent
			setForeground(new Color(255, 255, 255));//text color becomes white
		}
		if (!cellHasFocus) {//if the cell has lost focus
			setBackground(new Color(255, 255, 255, 0));//make it transparent
			setForeground(new Color(255, 255, 255));//set the text color to white
		}

		return this;
	}

}