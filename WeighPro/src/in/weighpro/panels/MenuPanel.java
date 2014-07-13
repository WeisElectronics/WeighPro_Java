package in.weighpro.panels;

import in.weighpro.MainClasses.MainFrame;
import in.weighpro.customComponents.MenuButton;
import in.weighpro.customComponents.ResetButton;
import in.weighpro.dimensionConstants.PanelBounds;
import in.weighpro.dimensionConstants.PanelDimensions;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MenuPanel extends JPanel {

	public MenuButton firstEntry, secondEntry, print, reports, settings;
	public ResetButton reset;
	private int xBound = 10, yBound = 10, width = 296, height = 60;

	private static final long serialVersionUID = -9059063168669321999L;

	private ActionListener commonActionListener;

	public MenuPanel() {
		setLayout(null);
		setOpaque(false);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(100, 100, 100, 120));
		setBounds(PanelBounds.menuPanelXBound, PanelBounds.menuPanelYBound,
				PanelDimensions.menuPanelWidth, PanelDimensions.menuPanelHeight);
		commonActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setAllDisabled();
				switch (e.getActionCommand()) {
				case "First Entry":
					firstEntry.setClicked(true);
					MainFrame.firstEntryPanel.setVisible(true);
					break;
				case "Second Entry":
					secondEntry.setClicked(true);
					MainFrame.secondEntryPanel.setVisible(true);
					break;
				case "Print":
					print.setClicked(true);
					MainFrame.printPanel.setVisible(true);
					break;
				case "Reports":
					reports.setClicked(true);
					MainFrame.reportsPanel.setVisible(true);
					break;
				case "Settings":
					settings.setClicked(true);
					MainFrame.settingsPanel.setVisible(true);
					break;
				}
			}

		};

		firstEntry = new MenuButton("First Entry", commonActionListener);
		setBounds(firstEntry);
		firstEntry.setClicked(true);
		add(firstEntry);

		secondEntry = new MenuButton("Second Entry", commonActionListener);
		setBounds(secondEntry);
		add(secondEntry);

		print = new MenuButton("Print", commonActionListener);
		setBounds(print);
		add(print);

		reports = new MenuButton("Reports", commonActionListener);
		setBounds(reports);
		add(reports);

		settings = new MenuButton("Settings", commonActionListener);
		setBounds(settings);
		add(settings);

		reset = new ResetButton("Clear");

		setBounds(reset);
		add(reset);

		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (secondEntry.getClicked()) {
					try {
						MainFrame.secondEntryPanel.stopThread();
						MainFrame.background.remove(MainFrame.secondEntryPanel);
						MainFrame.secondEntryPanel = new SecondEntryPanel();
						MainFrame.background.add(MainFrame.secondEntryPanel);
						MainFrame.secondEntryPanel.setVisible(true);
						reset.setClicked(false);
						return;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				
				if (firstEntry.getClicked()) {
					try {
						MainFrame.firstEntryPanel.stopThread();
						MainFrame.firstEntryPanel.setVisible(false);
						MainFrame.background.remove(MainFrame.firstEntryPanel);
						MainFrame.firstEntryPanel = new FirstEntryPanel();
						MainFrame.background.add(MainFrame.firstEntryPanel);
						reset.setClicked(false);
						return;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				
				
				if (print.getClicked()) {
					try {
						MainFrame.background.remove(MainFrame.printPanel);
						MainFrame.printPanel = new PrintPanel();
						MainFrame.background.add(MainFrame.printPanel);
						MainFrame.printPanel.setVisible(true);
						reset.setClicked(false);
						return;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				
				if (reports.getClicked()) {
					try {
						MainFrame.background.remove(MainFrame.reportsPanel);
						MainFrame.reportsPanel = new ReportsPanel();
						MainFrame.background.add(MainFrame.reportsPanel);
						MainFrame.reportsPanel.setVisible(true);
						reset.setClicked(false);
						return;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				
				
				if (settings.getClicked()) {
					try {
						MainFrame.background.remove(MainFrame.settingsPanel);
						MainFrame.settingsPanel = new SettingsPanel();
						MainFrame.background.add(MainFrame.settingsPanel);
						MainFrame.settingsPanel.setVisible(true);
						reset.setClicked(false);
						return;
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		});

	}

	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);

	}

	void setBounds(Component comp) {
		comp.setBounds(xBound, yBound, width, height);
		yBound += height + 7;
	}

	public void setAllDisabled() {
		firstEntry.setClicked(false);
		MainFrame.firstEntryPanel.setVisible(false);
		secondEntry.setClicked(false);
		MainFrame.secondEntryPanel.setVisible(false);
		print.setClicked(false);
		MainFrame.printPanel.setVisible(false);
		reports.setClicked(false);
		MainFrame.reportsPanel.setVisible(false);
		settings.setClicked(false);
		MainFrame.settingsPanel.setVisible(false);

	}
}
