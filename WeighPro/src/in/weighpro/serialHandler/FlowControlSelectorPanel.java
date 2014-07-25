/*
* This class provides the
* pluggable flow control selector
*  panel which is used in the
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

public class FlowControlSelectorPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4690564352308859552L;
	private CustomStandardLabel flowControlLabel;//label which shows that the current selection is flow control selection
	private CustomComboBox flowControlSelector;//drop down menu to select the flow control
	private String[] flowControl = { "None", "RTS/CTS IN", "RTS/CTS OUT",
			"X-ON/X-OFF IN", "X-ON/X-OFF OUT" };//the possible values that can be possible with the flow control

	
	//constructor to initialize the panel and all the components associated with it
	public FlowControlSelectorPanel() {
		setLayout(null);//setting a custom layout
		setOpaque(false);//setting transparent
		flowControlLabel = new CustomStandardLabel("Flow Control");//initializing the label
		Rectangle bounds = new Rectangle(220, 0, 201, 31);//bounds that are required for the functionality of the drop down menu
		flowControlSelector = new CustomComboBox(flowControl, false, bounds);//initializing the drop down menu
		flowControlLabel.setBounds(50, 0, 150, 30);//setting the bounds of the label
		add(flowControlLabel);//adding the label to the panel
		setVisible(true);//displaying the panel
		add(flowControlSelector);//add the drop down menu

	}

	//method to get the flow control from the selection panel
	//the rxtx library used in this project uses specific integers corresponding to the various strings used
	public int getFlowControl() {
		switch (flowControlSelector.getValue()) {//returning specific integer values corresponding to the strings using a switch case
		case "None":
			return 0;
		case "RTS/CTS IN":
			return 1;
		case "RTS/CTS OUT":
			return 2;
		case "X-ON/X-OFF IN":
			return 3;
		case "X-ON/X-OFF OUT":
			return 4;

		}
		return 0;
	}

	//method to set the value of the drop down menu selection
	//this method is used to set the initial value of the drop down selection
	//mapping the rxtx library's integer values to specific strings
	public void setFlowControl(int flowControl) {
		switch (flowControl) {
		case 0:
			flowControlSelector.setValue(this.flowControl[0]);
			break;
		case 1:
			flowControlSelector.setValue(this.flowControl[1]);
			break;
		case 2:
			flowControlSelector.setValue(this.flowControl[2]);
			break;
		case 4:
			flowControlSelector.setValue(this.flowControl[3]);
			break;
		case 8:
			flowControlSelector.setValue(this.flowControl[4]);
			break;
		}
	}
}
