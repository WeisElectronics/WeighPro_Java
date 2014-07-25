/*
* This class provides the
* pluggable stop bits 
* selector panel
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

public class StopBitsSelectorPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomStandardLabel stopBitsLabel;//label which shows that the current selection is stop bits selection
	private CustomComboBox stopBitsSelector;//drop down selector for displaying the available values
	private String[] stopBits = { "1", "2", "1.5" };//the possible selections for the stop bits

	public StopBitsSelectorPanel() {
		setLayout(null);//custom layout
		setOpaque(false);//set transparent
		stopBitsLabel = new CustomStandardLabel("Stop Bits");//initializing the label
		Rectangle bounds = new Rectangle(220,0, 201,31);//bounds required for the functionality of the drop down selector
		stopBitsSelector = new CustomComboBox(stopBits, false, bounds);//initializing the drop down selector
		stopBitsLabel.setBounds(50, 0, 150,30);//setting the bounds of the label
		add(stopBitsLabel);//adding the label to the panel
		setVisible(true);//displaying the panel
		add(stopBitsSelector);//adding the selector to the panel
	}

	//method to get the current stop bits selection from the selector
	//mapping from the constants used by rxtx library to the values 
	public int getStopBits() {
		switch(stopBitsSelector.getValue()){
		case "1":
			return 0;
		case "2":
			return 1;
		case "1.5":
			return 2;
		}
		return 0;
	}

	//method to set the current selection of the stop bits selector
	//used to set the initial selection of the selector
	//mapping the constants from the rxtx library to their corresponding values
	public void setStopBits(int stopBits) {
		switch (stopBits) {
		case 1:
			stopBitsSelector.setValue("1");
			break;
		case 2:
			stopBitsSelector.setValue("2");
			break;
		case 3:
			stopBitsSelector.setValue("1.5");
			break;

		}
	}

}
