/* This class provides the pluggable weight label component
 *  used in the first entry and seond entry panel of the main frame
 
 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */

package in.weighpro.customComponents;

import in.weighpro.customFonts.DigitalFontGenerator;

import java.awt.Color;

import javax.swing.JLabel;

public class WeightLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6481689105851702395L;

	//Constructor to initialize the component
	public WeightLabel(String text) {
		super(text);//call to the constructor of the JLabel to set the initial text
		setForeground(new Color(255, 255, 255, 255));//White colored text
		setFont(DigitalFontGenerator.digitalFont);//Using the digital font generator class
		//Look into the fonts package for details

	}
}
