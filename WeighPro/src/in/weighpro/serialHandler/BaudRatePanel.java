/*
* This class provides the
* pluggable baud rate panel
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


//This class is a child of the JPanel class and inherits the functionalities of a JPanel
public class BaudRatePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5221074325682030816L;
	private CustomComboBox baudRateSelector;//This mimics a drop down list containing all the possible baud rates
	private Integer[] baudRates = { 110, 150, 300, 1200, 2400, 4800, 9600,
			19200, 38400, 57600, 115200, 230400, 460800, 921600 };//All the possible baud rates that can be used
	private CustomStandardLabel baudRateLabel;//label which shows that this selection is baud rate selection

	
	//constructor to initialize all the components and set the previous selected values
	public BaudRatePanel() {
		setLayout(null);//custom layout
		setOpaque(false);//set transparent
		baudRateLabel = new CustomStandardLabel("Baud Rate");//initializing the label
		Rectangle bounds = new Rectangle(220,0, 201,31);//bounds that are passed to the drop down panel
		baudRateSelector = new CustomComboBox(baudRates, false, bounds);//initializing the drop down panel
		baudRateLabel.setBounds(50, 0, 150,30);//setting the bounds of the drop down panel
		add(baudRateLabel);//adding the label
		setVisible(true);//displaying the panel
		add(baudRateSelector);//adding the drop down panel
	}

	
	//method to get the baud rate
	public int getBaudRate() {
		return Integer.parseInt(baudRateSelector.getValue());
	}

	//method to set the selection of the drop down panel
	//used to set the initial value of the drop down panel
	public void setBaudRate(int baudRate) {
		baudRateSelector.setValue(Integer.toString(baudRate));
	}
	

}
