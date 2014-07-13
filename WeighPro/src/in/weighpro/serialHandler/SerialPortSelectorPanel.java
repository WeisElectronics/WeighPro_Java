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
	private CustomStandardLabel serialPortLabel;
	private CustomComboBox serialPortSelector;
	private ArrayList<String> serialPortsList;

	public SerialPortSelectorPanel(ArrayList<String> serialPortsList) {
		setLayout(null);
		setOpaque(false);

		this.serialPortsList = serialPortsList;

		serialPortLabel = new CustomStandardLabel("Serial Port");

		Rectangle bounds = new Rectangle(220, 0, 201, 31);
		serialPortSelector = new CustomComboBox(serialPortsList.toArray(),
				false, bounds);
		serialPortLabel.setBounds(50, 0, 150, 30);

		add(serialPortLabel);
		setVisible(true);
		add(serialPortSelector);
	}

	public int getPort() {
		return serialPortsList.indexOf(serialPortSelector.getValue());
	}

	public void setPort(String portName) {
		serialPortSelector.setValue(portName);
	}

	public void setPortNull() {
		serialPortSelector.setValue("");
	}

}
