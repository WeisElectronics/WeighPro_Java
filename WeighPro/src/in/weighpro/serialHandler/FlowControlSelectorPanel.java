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
	private CustomStandardLabel flowControlLabel;
	private CustomComboBox flowControlSelector;
	private String[] flowControl = { "None", "RTS/CTS IN", "RTS/CTS OUT",
			"X-ON/X-OFF IN", "X-ON/X-OFF OUT" };

	public FlowControlSelectorPanel() {
		setLayout(null);
		setOpaque(false);
		flowControlLabel = new CustomStandardLabel("Flow Control");
		Rectangle bounds = new Rectangle(220, 0, 201, 31);
		flowControlSelector = new CustomComboBox(flowControl, false, bounds);
		flowControlLabel.setBounds(50, 0, 150, 30);
		add(flowControlLabel);
		setVisible(true);
		add(flowControlSelector);

	}

	public int getFlowControl() {
		switch (flowControlSelector.getValue()) {
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
