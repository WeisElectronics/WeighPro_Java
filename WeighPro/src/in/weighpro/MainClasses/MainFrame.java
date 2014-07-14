/* This class initializes the
*main frame which contains all the 
*sub components

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/




package in.weighpro.MainClasses;

import in.weighpro.customImageLabels.LogoLabel;
import in.weighpro.customImageLabels.WallpaperLabel;
import in.weighpro.dimensionConstants.ScreenDimensions;
import in.weighpro.panels.FirstEntryPanel;
import in.weighpro.panels.MenuPanel;
import in.weighpro.panels.PrintPanel;
import in.weighpro.panels.ReportsPanel;
import in.weighpro.panels.SecondEntryPanel;
import in.weighpro.panels.SettingsPanel;
import in.weighpro.panels.SystemFunctionsPanel;
import in.weighpro.panels.TimePanel;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;



//--This class is an extension from the default JFrame class of the swing package
public class MainFrame extends JFrame {

	static Font digitalFont;     
	static Font menuPanelFont;
	/**
	 * 
	 */
	private static final long serialVersionUID = 6297858946347238643L;
	public static WallpaperLabel background;
	public static JFrame mainFrm;
	public static MenuPanel menuPanel;
	private SystemFunctionsPanel sysFuncPan;
	private LogoLabel logo;
	public static TimePanel timePanel;
	public static FirstEntryPanel firstEntryPanel;
	public static SecondEntryPanel secondEntryPanel;
	public static PrintPanel printPanel;
	public static JPanel reportsPanel;
	public static SettingsPanel settingsPanel;

//----constructor to initialize all the sub panels-------------------------
	MainFrame() {
		setSize(new Dimension(ScreenDimensions.width, ScreenDimensions.height));//Setting the dimensions of the frame
		setLayout(null);//custom layout
		setUndecorated(true);//no titlebar

		mainFrm = this;//getting an instance of this class

		try {

			background = new WallpaperLabel();//This is the virtual background wallpaper
			add(background);//adding the background
			settingsPanel = new SettingsPanel();//This panel contains all the settings concerning the software
							   //both serial settings and appearance settings

			menuPanel = new MenuPanel();//This panel contains all the menu navigation buttons 
			background.add(menuPanel);//adding the menu panel to the frame

			sysFuncPan = new SystemFunctionsPanel();//This panel contains the shutdown, restart and close buttons
			background.add(sysFuncPan);//Adding the system functions panel to the frame

			logo = new LogoLabel();//Initializing the product logo
			background.add(logo);//Adding the product logo to the frame

			timePanel = new TimePanel();//This panel is the deck that shows the time as well as the comapny name
			background.add(timePanel);//Add the time panel
			
			firstEntryPanel = new FirstEntryPanel();//First entry panel has a form for the first entry 
			secondEntryPanel = new SecondEntryPanel();//Second entry is a form for the second entry for a particular id
			printPanel = new PrintPanel();//Print panel is a form for printing the data of a particular ID
			reportsPanel = new ReportsPanel();//Reports panel shows all the entries depending on the constraints entered
			
			background.add(firstEntryPanel);//adding the first entry panel
			background.add(secondEntryPanel);//adding the second entry panel
			background.add(printPanel);//adding the print panel
			background.add(reportsPanel);//adding the reports panel
			background.add(settingsPanel);//adding the settings panel
//During the process of initializing panels which are based on database retrieval an SQL exception maybe thrown 
//This block processes that scenario
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//The application closes when the close button is pressed
								//or an ALT+F4 is pressed
		setVisible(true);//Making the frame visible
	}

}
