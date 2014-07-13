/* This class provides the pluggable time label component
 *  used in the time panel of the main frame
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */
package in.weighpro.customComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class TimeLabel extends JLabel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6001261414467054496L;

	public TimeLabel(){
		setForeground(new Color(255, 255, 255, 255));//white colored text
		setFont(new Font(Font.DIALOG,Font.PLAIN,60));//setting the font
	}
}
