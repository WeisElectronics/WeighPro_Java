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
	private CustomStandardLabel stopBitsLabel;
	private CustomComboBox stopBitsSelector;
	private String[] stopBits = { "1", "2", "1.5" };

	public StopBitsSelectorPanel() {
		setLayout(null);
		setOpaque(false);
		stopBitsLabel = new CustomStandardLabel("Stop Bits");
		Rectangle bounds = new Rectangle(220,0, 201,31);
		stopBitsSelector = new CustomComboBox(stopBits, false, bounds);
		stopBitsLabel.setBounds(50, 0, 150,30);
		add(stopBitsLabel);
		setVisible(true);
		add(stopBitsSelector);
	}

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
