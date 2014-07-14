/* This class is used for creating an image icon
* that is used for displaying the logo in the
* main frame

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/


package in.weighpro.customImageLabels;

import in.weighpro.dimensionConstants.PanelBounds;
import in.weighpro.dimensionConstants.PanelDimensions;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;


//This class is an extension of a JLabel and contains the logo image
public class LogoLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6649283575935069818L;

//Constructor to initialize an image icon from the resource image present in the same package
	public LogoLabel() {
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("logo.png"));//create image icon from the resource 
											//image
		this.setIcon(imageIcon);						//use the image icon generated as an 
											//icon for this JLabel
		setBorder(new EmptyBorder(0, 0, 0, 0));					//no border
		setBounds(PanelBounds.logoXBound, PanelBounds.logoYBound,		
				PanelDimensions.logoWidth, PanelDimensions.logoHeight);//Setting the bounds using the dimension constants
	}
}
