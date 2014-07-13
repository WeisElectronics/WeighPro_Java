/* This class provides the pluggable date day label component
 *  used in the time panel of the main frame
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */

package in.weighpro.customComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class DateDayLabel extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6915404544932387485L;

	//Constructor to initialize the component
	public DateDayLabel(){
		setForeground(new Color(255, 255, 255, 255));//White colored text
		setFont(new Font(Font.DIALOG,Font.PLAIN,15));//setting the font
		setHorizontalAlignment(JLabel.CENTER);//center alignment
	}
}
