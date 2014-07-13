package in.weighpro.settingsComponents;

import in.weighpro.customImageLabels.ThumbNailGenerator;
import in.weighpro.databaseQueryHandler.CurrentWallpaperRetriever;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class WallpaperHandler extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2093935457815220624L;
	ThumbNailGenerator thumb1;
	ThumbNailGenerator thumb2;
	ThumbNailGenerator thumb3;
	ThumbNailGenerator thumb4;
	ThumbNailGenerator thumb5;
	ThumbNailGenerator thumb6;
	private String selectedWall;
	
	public WallpaperHandler() throws SQLException{
		
		setLayout(null);
		setOpaque(false);
		setBorder(new LineBorder(Color.white, 1));
		setBounds(500, 85, 345, 145);
		
		CurrentWallpaperRetriever cwr= new CurrentWallpaperRetriever();
		selectedWall = cwr.getWallpaper();
		MouseListener ml = new MouseAdapter(){

			@Override
			public void mouseClicked(MouseEvent e) {
				resetAll();
				((JLabel) e.getComponent()).setBorder(new LineBorder(Color.white,4));
				selectedWall = ((JLabel)e.getComponent()).getName();
			}
			
		};
		
		
		thumb1 = new ThumbNailGenerator("thumb_bg1.jpg",11,11,ml);
		add(thumb1);
		thumb2 = new ThumbNailGenerator("thumb_bg2.jpg",122,11,ml);
		add(thumb2);
		thumb3 = new ThumbNailGenerator("thumb_bg3.jpg",233,11,ml);
		add(thumb3);
		
		thumb4 = new ThumbNailGenerator("thumb_bg4.jpg",11,78,ml);
		add(thumb4);
		thumb5 = new ThumbNailGenerator("thumb_bg5.jpg",122,78,ml);
		add(thumb5);
		thumb6 = new ThumbNailGenerator("thumb_bg6.jpg",233,78,ml);
		add(thumb6);
		
		switch(cwr.getWallpaper()){
		case "bg1.jpg":
			thumb1.setBorder(new LineBorder(Color.white,4));
			break;
		case "bg2.jpg":
			thumb2.setBorder(new LineBorder(Color.white,4));
			break;
		case "bg3.jpg":
			thumb3.setBorder(new LineBorder(Color.white,4));
			break;
		case "bg4.jpg":
			thumb4.setBorder(new LineBorder(Color.white,4));
			break;
		case "bg5.jpg":
			thumb5.setBorder(new LineBorder(Color.white,4));
			break;
		case "bg6.jpg":
			thumb6.setBorder(new LineBorder(Color.white,4));
			break;
		}
		
	}
	
	public void resetAll(){
		thumb1.setBorder(new LineBorder(Color.white,1));
		thumb2.setBorder(new LineBorder(Color.white,1));
		thumb3.setBorder(new LineBorder(Color.white,1));
		thumb4.setBorder(new LineBorder(Color.white,1));
		thumb5.setBorder(new LineBorder(Color.white,1));
		thumb6.setBorder(new LineBorder(Color.white,1));
	}
	
	public String getSelectedWall(){
		return this.selectedWall;
	}
}
