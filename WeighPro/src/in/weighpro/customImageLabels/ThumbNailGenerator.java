/* This class generayes the
* thumbnails which show the wallpapers
* available for use in the settings panel

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.customImageLabels;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

//This class is an extension of the JLabel class and uses the image icon generated from the resource image to show the thumbnails
//in the wallpaper selection section of the settings panel
public class ThumbNailGenerator extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3913420437873008893L;
	
	//Constructor to initialize the image icon from the resource image file
	//Common mouse listener for the thumbnails is taken as an argument
	public ThumbNailGenerator(String path,int xBound,int yBound,MouseListener ml) {
		ImageIcon imageIcon = new ImageIcon(getClass().getResource(path));//creating an image icon from the resource image file
		this.setIcon(imageIcon);//using the generated image icon as the icon of the JLabel
		setBorder(new LineBorder(Color.white,1));//white border of thickness 1
		setBounds(xBound,yBound,100,56);//setting the bounds of the image
		addMouseListener(ml);//adding the common mouse listener
		this.setName(path.substring(6,path.length()));//setting the name so it can be used during the handling by the
							//common mouse listener
	}
	
}
