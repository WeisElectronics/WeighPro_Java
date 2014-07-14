/* This class initializes the
*wallpaper component of the main frame

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.customImageLabels;

import java.sql.SQLException;

import in.weighpro.databaseQueryHandler.CurrentWallpaperRetriever;
import in.weighpro.dimensionConstants.ScreenDimensions;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;


//This class is an extension of the JLabel class which uses the image icon generated from the image files in the same package
//as its icon
public class WallpaperLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2892125922971304730L;
	private static ImageIcon imageIcon;

//Constructor to get an image icon from the image resource file and setting the image icon as the icon of the JLabel
	public WallpaperLabel() throws SQLException {//Accesses database to get the current wallpaper hence throws the SQL Exception
		CurrentWallpaperRetriever cwr = new CurrentWallpaperRetriever();//This class retrieves the current wallpaer and may throw a SQL Exception
		imageIcon = new ImageIcon(getClass().getResource(cwr.getWallpaper()));//Creating an image icon from the image resource file in the same package
		this.setIcon(imageIcon);//Setting the icon of the JLabel using the generated ImageIcon 
		setBorder(new EmptyBorder(0, 0, 0, 0));//no border
		setLayout(null);//custom layout
		setBounds(0, 0, ScreenDimensions.width, ScreenDimensions.height);//setting the bounds 
		setName("Wallpaper");//setting the name of the component 
	}
	
	
	//Method to set the wallpaper 
	//Used when the user changes the current wallpaper from the settings panel
	public void setWallpaper(String path){//the path and image file name are same since the images are present in the same package
		imageIcon = new ImageIcon(getClass().getResource(path));//get an image icon object from the resource image file
		this.setIcon(imageIcon);//set the icon of the JLabel using the generated image icon
	}
}
