/*
* This class provides the
* pluggable parity selection panel
* which is used in the
* settings panel

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.serialHandler;

import in.weighpro.customComponents.CustomComboBox;
import in.weighpro.customComponents.CustomStandardLabel;

import java.awt.Rectangle;

import javax.swing.JPanel;

public class ParitySelectorPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3190293977466939625L;
	private CustomStandardLabel parityLabel;//label to show that this selection is the parity selection
	private CustomComboBox paritySelector;//drop down to list all the available selections
	private String[] parity = { "None", "Odd", "Even", "Mark", "Space" };//all the possible values for parity

	//constructor to initialize the panel and all the components associated with it
	public ParitySelectorPanel() {
		setLayout(null);//custom layout
		setOpaque(false);//setting transparent
		parityLabel = new CustomStandardLabel("Parity");//initializing the label
		Rectangle bounds = new Rectangle(220, 0, 201, 31);//bounds required for the functionality of the drop down menu

		paritySelector = new CustomComboBox(parity, false, bounds);//initializing the drop down selector

		parityLabel.setBounds(50, 0, 150, 30);//setting the bounds of the label
		add(parityLabel);//adding the label to the panel
		setVisible(true);//displaying the panel
		add(paritySelector);//adding the drop down selector to the panel
	}

	//method to get the parity selection from the panel
	//rxtx library uses integer values for different parity selections
	//mapping those integers to the corresponding values
	public int getParity() {
		switch(paritySelector.getValue()){
		case "None":
			return 0;
		case "Odd":
			return 1;
		case "Even":
			return 2;
		case "Mark":
			return 3;
		case "Space":
			return 4;
		}
		return 0;
	}

	
	//method to set the selection of the parity panel
	//this method sets the initial parity of the selector
	//mapping the integers used by the rxtx library to their coressponding values
	public void setParity(int parity) {
		switch (parity) {
		case 0:
			paritySelector.setValue(this.parity[0]);
			break;
		case 1:
			paritySelector.setValue(this.parity[1]);
			break;
		case 2:
			paritySelector.setValue(this.parity[2]);
			break;
		case 3:
			paritySelector.setValue(this.parity[3]);
			break;
		case 4:
			paritySelector.setValue(this.parity[4]);
			break;
		}
	}

}
