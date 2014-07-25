/*
* This class provides the
* pluggable data bits selector panel
* which is used in the settings panel

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.serialHandler;

import in.weighpro.customComponents.CustomComboBox;
import in.weighpro.customComponents.CustomStandardLabel;

import java.awt.Rectangle;

import javax.swing.JPanel;

//This class is a child of the JPanel class and inherits the functionalities of a JPanel
public class DataBitsSelectorPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 594559953655689352L;
	private CustomStandardLabel dataBitsLabel;//label which shows that this selection is data bits selection
	private CustomComboBox dataBitsSelector;//This mimics a drop down list containing all the possible baud rates
	private Integer[] dataBits = { 5, 6, 7, 8 };//All the data bit values that can be used

	
	//constructor to initialize the panel and add the functionality
	public DataBitsSelectorPanel() {
		setLayout(null);//custom layout
		setOpaque(false);//set transparent
		dataBitsLabel = new CustomStandardLabel("Data Bits");//initializing the label
		Rectangle bounds = new Rectangle(220, 0, 201, 31);//the bounds that are required for the drop down functionality
		dataBitsSelector = new CustomComboBox(dataBits, false, bounds);//initializing the drop down menu
		dataBitsLabel.setBounds(50, 0, 150,30);//setting the bounds of the label
		add(dataBitsLabel);//adding the label to the panel
		setVisible(true);//displaying the panel
		add(dataBitsSelector);//adding the drop down  menu to the panel
	}

	//method to get the data bits selection from the panel
	public int getDataBits() {
		return Integer.parseInt(dataBitsSelector.getValue());//the selection is a string so converting it to an integer
	}

	//method to set the selection of the drop down list
	//this method is called to set the initial values of the drop down list
	public void setDataBits(int dataBits) {
		dataBitsSelector.setValue(Integer.toString(dataBits));//since the passed value is an integer converting it to a string and 
															//setting the initial selection to that value
	}

}
