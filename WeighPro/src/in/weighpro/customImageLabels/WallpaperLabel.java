package in.weighpro.customImageLabels;

import java.sql.SQLException;

import in.weighpro.databaseQueryHandler.CurrentWallpaperRetriever;
import in.weighpro.dimensionConstants.ScreenDimensions;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class WallpaperLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2892125922971304730L;
	private static ImageIcon imageIcon;

	public WallpaperLabel() throws SQLException {
		CurrentWallpaperRetriever cwr = new CurrentWallpaperRetriever();
		imageIcon = new ImageIcon(getClass().getResource(cwr.getWallpaper()));
		this.setIcon(imageIcon);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(null);
		setBounds(0, 0, ScreenDimensions.width, ScreenDimensions.height);
		setName("Wallpaper");
	}
	
	
	public void setWallpaper(String path){
		imageIcon = new ImageIcon(getClass().getResource(path));
		this.setIcon(imageIcon);
	}
}
