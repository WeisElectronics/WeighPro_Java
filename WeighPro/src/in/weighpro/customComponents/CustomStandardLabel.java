/* This class provides the pluggable label component
 *  used in the various panels
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */

package in.weighpro.customComponents;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class CustomStandardLabel extends JLabel {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = -1661457013310816013L;

	//---constructor to initialize the component
	public CustomStandardLabel(String text) {
		super(text);//Call to the JLabel parent to set the text;
		setForeground(new Color(255, 255, 255, 255));//White text color
		setFont(new Font(Font.DIALOG, Font.PLAIN, 18));//Setting the font

	}
}
