package in.weighpro.panels;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import in.weighpro.customImageLabels.LogoLabel;
import in.weighpro.dimensionConstants.ScreenDimensions;

import javax.swing.JPanel;

public class FunctionsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8177156308654849817L;
	public MenuPanel menuPanel;
	private SystemFunctionsPanel sysFuncPan;
	private LogoLabel logo;
	
	public FunctionsPanel() {
		setLayout(null);
		setOpaque(false);
		setBounds(950, 0, 410, 768);
		menuPanel = new MenuPanel();// This panel contains all the menu
									// navigation buttons
		add(menuPanel);// adding the menu panel to the frame

		sysFuncPan = new SystemFunctionsPanel();// This panel contains the
												// shutdown, restart and close
												// buttons
		add(sysFuncPan);// Adding the system functions panel to the frame

		logo = new LogoLabel();// Initializing the product logo
		add(logo);// Adding the product logo to the frame

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				if(ScreenDimensions.width<1360){
				setOpaque(true);
				setBounds(ScreenDimensions.width-410, 0, 410, 768);
				repaint();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if(ScreenDimensions.width<1360){
				Point p = MouseInfo.getPointerInfo().getLocation();
				if (p.getX() < ScreenDimensions.width-410 | p.getX() > ScreenDimensions.width | p.getY() < 0
						| p.getY() > 768) {
					setOpaque(false);
					setBounds(950, 0, 410, 768);
					repaint();
				}
			}
			}

		});
	}
}
