/* This class provides the pluggable table cell component
 *  used in the transparent tables
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */

package in.weighpro.customComponents;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class InternalTableCellRenderer extends DefaultTableCellRenderer {

	//Constructor for the renderer
	public InternalTableCellRenderer() {
		setOpaque(false);//transparent
	}

	private static final long serialVersionUID = -2341614459632756921L;

	
	
	//This method returns rendered component
	//Basically a JLabel with custom appearance settings
	public Component getTableCellRendererComponent(JTable table, Object arg1,
			boolean isSelected, boolean hasFocus, int row, int col) {
		JLabel label = (JLabel) super.getTableCellRendererComponent(table,
				arg1, isSelected, hasFocus, row, col);//casting the default cell renderer to a JLabel
		label.setHorizontalAlignment(JLabel.CENTER);//center horizontal alignment

		
		//-----Settings for the table header row
		if (row == -1) {
			label.setForeground(Color.white);//white colored text
			label.setFont(new Font(Font.DIALOG, Font.BOLD, 14));//setting the font
			label.setHorizontalAlignment(JLabel.CENTER);//setting center horizontal alignment
			label.setBorder(new EmptyBorder(10, 0, 8, 0));//setting padding and en empty border
			return label;
		}

		//for other rows of the table
		label.setForeground(Color.white);//white colored text
		if (isSelected) {
			label.setFont(new Font(Font.DIALOG, Font.BOLD, 18));//Increase the font size and 
																//font is set to Bold 

		} 

		return label;
	}

}