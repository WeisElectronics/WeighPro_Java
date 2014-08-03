package in.weighpro.panels;

import in.weighpro.customComponents.CustomCommandLineButton;
import in.weighpro.customComponents.CustomPowerButton;
import in.weighpro.customComponents.CustomRestartButton;
import in.weighpro.dimensionConstants.PanelBounds;
import in.weighpro.dimensionConstants.PanelDimensions;
import in.weighpro.MainClasses.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

public class SystemFunctionsPanel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1105752583917786553L;
	private CustomPowerButton powerButton;
	private CustomRestartButton restartButton;
	private CustomCommandLineButton closeButton;

	public SystemFunctionsPanel() {
		setLayout(null);
		setOpaque(false);

		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(100, 100, 100, 120));

		setBounds(PanelBounds.systemFunctionsPanelXBound,
				PanelBounds.systemFunctionsPanelYBound,
				PanelDimensions.systemFunctionsPanelWidth,
				PanelDimensions.systemFunctionsPanelHeight);

		powerButton = new CustomPowerButton();
		powerButton.setBounds(17, 5, 72, 85);
		add(powerButton);

		restartButton = new CustomRestartButton();
		restartButton.setBounds(122, 5, 70, 85);
		add(restartButton);

		closeButton = new CustomCommandLineButton();
		closeButton.setBounds(227, 5, 70, 85);
		add(closeButton);

		closeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Runtime rt = Runtime.getRuntime();
				JPanel panel = new JPanel();
				JLabel label = new JLabel("Enter the password:");
				JPasswordField pass = new JPasswordField(10);
				panel.add(label);
				panel.add(pass);
				String[] options = new String[]{"OK", "Cancel"};
				int option = JOptionPane.showOptionDialog(null, panel, "The title",
				                         JOptionPane.NO_OPTION, JOptionPane.WARNING_MESSAGE,
				                         null, options, options[1]);
				
				if(option == 0) // pressing OK button
				{	char[] password = pass.getPassword();
			    String s =  new String(password);
				if ((s != null) && (s.length() > 0)) {
					if(s.trim().equals("weis321")){
						 try { rt.exec("kill " + ManagementFactory .getRuntimeMXBean()
								  .getName() .substring( 0, ManagementFactory
								  .getRuntimeMXBean() .getName().lastIndexOf("@")));
								  
								  } catch (IOException e1) { // TODO Auto-generated catch block
								  e1.printStackTrace(); }
								 
					}
					return;
				}
				}
				 
			}

		});

		powerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Runtime rt = Runtime.getRuntime();
				int n = JOptionPane.showConfirmDialog(
					    MainFrame.settingsPanel,
					    "Shutdown System?",
					    "System Shutdown",
					    JOptionPane.YES_NO_OPTION);
				if(n==0){
				try {
					rt.exec("sudo halt");

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}

		});

		restartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Runtime rt = Runtime.getRuntime();
				int n = JOptionPane.showConfirmDialog(
					    MainFrame.settingsPanel,
					    "Restart System?",
					    "System Restart",
					    JOptionPane.YES_NO_OPTION);
				if(n==0){
				
				try {
					rt.exec("sudo reboot");

				} catch (IOException e1) {
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
}
