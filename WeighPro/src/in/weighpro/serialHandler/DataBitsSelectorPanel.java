package in.weighpro.serialHandler;

import in.weighpro.customComponents.CustomComboBox;
import in.weighpro.customComponents.CustomStandardLabel;

import java.awt.Rectangle;

import javax.swing.JPanel;

public class DataBitsSelectorPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 594559953655689352L;
	private CustomStandardLabel dataBitsLabel;
	private CustomComboBox dataBitsSelector;
	private Integer[] dataBits = { 5, 6, 7, 8 };

	public DataBitsSelectorPanel() {
		setLayout(null);
		setOpaque(false);
		dataBitsLabel = new CustomStandardLabel("Data Bits");
		Rectangle bounds = new Rectangle(220, 0, 201, 31);
		dataBitsSelector = new CustomComboBox(dataBits, false, bounds);
		dataBitsLabel.setBounds(50, 0, 150,30);
		add(dataBitsLabel);
		setVisible(true);
		add(dataBitsSelector);
	}

	public int getDataBits() {
		return Integer.parseInt(dataBitsSelector.getValue());
	}

	public void setDataBits(int dataBits) {
		dataBitsSelector.setValue(Integer.toString(dataBits));
	}

}
