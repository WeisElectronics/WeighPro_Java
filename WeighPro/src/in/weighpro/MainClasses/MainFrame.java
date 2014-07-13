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

	MainFrame() {
		setSize(new Dimension(ScreenDimensions.width, ScreenDimensions.height));
		setLayout(null);
		setUndecorated(true);

		mainFrm = this;

		try {

			background = new WallpaperLabel();
			add(background);
			settingsPanel = new SettingsPanel();

			menuPanel = new MenuPanel();
			background.add(menuPanel);

			sysFuncPan = new SystemFunctionsPanel();
			background.add(sysFuncPan);

			logo = new LogoLabel();
			background.add(logo);

			timePanel = new TimePanel();
			background.add(timePanel);
			
			firstEntryPanel = new FirstEntryPanel();
			secondEntryPanel = new SecondEntryPanel();
			printPanel = new PrintPanel();
			reportsPanel = new ReportsPanel();
			
			background.add(firstEntryPanel);
			background.add(secondEntryPanel);
			background.add(printPanel);
			background.add(reportsPanel);
			background.add(settingsPanel);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
