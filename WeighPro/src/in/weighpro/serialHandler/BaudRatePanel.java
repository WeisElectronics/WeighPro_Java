package in.weighpro.serialHandler;

import in.weighpro.customComponents.CustomComboBox;
import in.weighpro.customComponents.CustomStandardLabel;

import java.awt.Rectangle;

import javax.swing.JPanel;

public class BaudRatePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5221074325682030816L;
	private CustomComboBox baudRateSelector;
	private Integer[] baudRates = { 110, 150, 300, 1200, 2400, 4800, 9600,
			19200, 38400, 57600, 115200, 230400, 460800, 921600 };
	private CustomStandardLabel baudRateLabel;

	public BaudRatePanel() {
		setLayout(null);
		setOpaque(false);
		baudRateLabel = new CustomStandardLabel("Baud Rate");
		Rectangle bounds = new Rectangle(220,0, 201,31);
		baudRateSelector = new CustomComboBox(baudRates, false, bounds);
		baudRateLabel.setBounds(50, 0, 150,30);
		add(baudRateLabel);
		setVisible(true);
		add(baudRateSelector);
	}

	public int getBaudRate() {
		return Integer.parseInt(baudRateSelector.getValue());
	}

	public void setBaudRate(int baudRate) {
		baudRateSelector.setValue(Integer.toString(baudRate));
	}
	

}
