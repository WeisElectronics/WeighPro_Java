/*
* This class provides the
* pluggable serial port 
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
import java.util.ArrayList;

import javax.swing.JPanel;

public class SerialPortSelectorPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8176596607314867492L;
	private CustomStandardLabel serialPortLabel;//label to show that the current selection is the serial port selection
	private CustomComboBox serialPortSelector;//the drop down menu that displays the available selections
	private ArrayList<String> serialPortsList;//array list which contains the names of the serial ports available for selection

	//constructor to initialize the panel and the components associated with it
	public SerialPortSelectorPanel(ArrayList<String> serialPortsList) {//this constructor takes the array list of the serial ports
																	//available as an argument from the class invoking it
		setLayout(null);//setting custom layout
		setOpaque(false);//setting transparent

		this.serialPortsList = serialPortsList;//using the passed array list to initialize the local instance of the list

		serialPortLabel = new CustomStandardLabel("Serial Port");//initializing the label

		Rectangle bounds = new Rectangle(220, 0, 201, 31);//bounds required for the functionality of the drop down selection panel
		serialPortSelector = new CustomComboBox(serialPortsList.toArray(),
				false, bounds);//initializing the drop down selection panel using the list(converting it to an array)
		serialPortLabel.setBounds(50, 0, 150, 30);//setting the bounds of the drop down selection panel

		add(serialPortLabel);//adding the label to the panel
		setVisible(true);//setting the panel to visible
		add(serialPortSelector);//adding the drop down selector to the panel
	}

	//method to get the current port selection
	public int getPort() {
		return serialPortsList.indexOf(serialPortSelector.getValue());//returns the index of the selected port
	}

	//method to set the current selection
	//this method is used to set the initial selection of the drop down selection
	public void setPort(String portName) {
		serialPortSelector.setValue(portName);
	}

	//this method is called when the last selection of the port does not exist
	public void setPortNull() {
		serialPortSelector.setValue("");
	}

}
