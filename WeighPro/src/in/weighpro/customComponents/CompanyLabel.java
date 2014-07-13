/* This class provides the company name label component
 *  used in the main panel
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */


package in.weighpro.customComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class CompanyLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2107460883040021366L;

	//-----------Constructor for initializing all the fields------
	public CompanyLabel(String text) {
		super(text);//calling the constructor of the JLabel
		setForeground(new Color(255, 255, 255, 255));//white color text
		setFont(new Font(Font.DIALOG, Font.PLAIN, 27));//Setting the font for the text
		setHorizontalAlignment(JLabel.CENTER);//Center alignment 
	}
}
