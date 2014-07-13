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
	private CustomStandardLabel parityLabel;
	private CustomComboBox paritySelector;
	private String[] parity = { "None", "Odd", "Even", "Mark", "Space" };

	public ParitySelectorPanel() {
		setLayout(null);
		setOpaque(false);
		parityLabel = new CustomStandardLabel("Parity");
		Rectangle bounds = new Rectangle(220, 0, 201, 31);

		paritySelector = new CustomComboBox(parity, false, bounds);

		parityLabel.setBounds(50, 0, 150, 30);
		add(parityLabel);
		setVisible(true);
		add(paritySelector);
	}

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
