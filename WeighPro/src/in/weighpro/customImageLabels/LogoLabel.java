package in.weighpro.customImageLabels;

import in.weighpro.dimensionConstants.PanelBounds;
import in.weighpro.dimensionConstants.PanelDimensions;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class LogoLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6649283575935069818L;

	public LogoLabel() {
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("logo.png"));
		this.setIcon(imageIcon);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBounds(PanelBounds.logoXBound, PanelBounds.logoYBound,
				PanelDimensions.logoWidth, PanelDimensions.logoHeight);
	}
}
