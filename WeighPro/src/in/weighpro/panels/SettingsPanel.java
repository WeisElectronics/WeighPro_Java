package in.weighpro.panels;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;
import in.weighpro.MainClasses.MainFrame;
import in.weighpro.customComponents.CustomStandardButton;
import in.weighpro.customComponents.CustomStandardTextField;
import in.weighpro.customComponents.HeadingPanel;
import in.weighpro.databaseConstructs.DatabaseConnection;
import in.weighpro.databaseQueryHandler.CurrentWallpaperRetriever;
import in.weighpro.databaseQueryHandler.SerialSettingsRetriever;
import in.weighpro.databaseQueryHandler.UpdateWallpaperQuery;
import in.weighpro.dimensionConstants.PanelBounds;
import in.weighpro.dimensionConstants.PanelDimensions;
import in.weighpro.serialHandler.BaudRatePanel;
import in.weighpro.serialHandler.DataBitsSelectorPanel;
import in.weighpro.serialHandler.FlowControlSelectorPanel;
import in.weighpro.serialHandler.ParitySelectorPanel;
import in.weighpro.serialHandler.SerialPortSelectorPanel;
import in.weighpro.serialHandler.StopBitsSelectorPanel;
import in.weighpro.settingsComponents.WallpaperHandler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class SettingsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3304495405844417033L;
	private BaudRatePanel baudRatePanel;
	private DataBitsSelectorPanel dataBitsSelector;
	private ParitySelectorPanel paritySelector;
	private StopBitsSelectorPanel stopBitsSelector;
	private FlowControlSelectorPanel flowControlSelector;
	private SerialPortSelectorPanel serialPortSelector;
	private ArrayList<String> serialPortsList;
	public static SerialPort serialPort;
	private String serialPortName;
	private int baudRate;
	private int dataBits;
	private int stopBits;
	private int parity;
	private int flowControl;
	private boolean updateFlag = false;
	private CustomStandardButton saveButton;
	private CustomStandardTextField company;

	private Connection connection;

	private PreparedStatement preparedStatement;

	private WallpaperHandler wallHand;

	public SettingsPanel() throws SQLException {

		this.connection = getConnection();

		setLayout(null);
		setOpaque(false);
		setBounds(PanelBounds.mainPanelXBound, PanelBounds.mainPanelYBound,
				PanelDimensions.mainPanelWidth, PanelDimensions.mainPanelHeight);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(100, 100, 100, 120));
		setVisible(false);

		serialPortsList = new ArrayList<String>();

		@SuppressWarnings("rawtypes")
		Enumeration portList = CommPortIdentifier.getPortIdentifiers();

		while (portList.hasMoreElements()) {
			CommPortIdentifier portId = (CommPortIdentifier) portList
					.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				serialPortsList.add(portId.getName());

			}

		}
		serialPortSelector = new SerialPortSelectorPanel(serialPortsList);
		serialPortSelector.setBounds(0, 50, 450, 170);
		add(serialPortSelector);

		baudRatePanel = new BaudRatePanel();
		baudRatePanel.setBounds(0, 120, 450, 170);
		add(baudRatePanel);

		dataBitsSelector = new DataBitsSelectorPanel();
		dataBitsSelector.setBounds(0, 190, 450, 170);
		add(dataBitsSelector);
		stopBitsSelector = new StopBitsSelectorPanel();
		stopBitsSelector.setBounds(0, 260, 450, 170);
		add(stopBitsSelector);

		paritySelector = new ParitySelectorPanel();
		paritySelector.setBounds(0, 330, 450, 170);
		add(paritySelector);

		flowControlSelector = new FlowControlSelectorPanel();
		flowControlSelector.setBounds(0, 400, 450, 170);
		add(flowControlSelector);

		SerialSettingsRetriever ssr = new SerialSettingsRetriever();
		serialPortName = ssr.getSerialPortName();
		baudRate = ssr.getBaudRate();
		dataBits = ssr.getDataBits();
		stopBits = ssr.getStopBits();
		parity = ssr.getParity();
		flowControl = ssr.getFlowControl();

		serialPortSelector.setPort(serialPortName);
		baudRatePanel.setBaudRate(baudRate);
		dataBitsSelector.setDataBits(dataBits);
		flowControlSelector.setFlowControl(flowControl);
		paritySelector.setParity(parity);
		stopBitsSelector.setStopBits(stopBits);
		if (serialPortsList.contains(serialPortName)) {
			updatePort();
		} else {
			serialPortSelector.setPortNull();
		}

		saveButton = new CustomStandardButton("Save");
		saveButton.setBounds(320, 490, 280, 70);
		add(saveButton);

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					new UpdateWallpaperQuery(wallHand.getSelectedWall(),
							company.getText().trim());
					MainFrame.timePanel
							.setCompanyName(company.getText().trim());
					MainFrame.background.setWallpaper(wallHand
							.getSelectedWall());

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (serialPortName.equals(serialPortsList
						.get(serialPortSelector.getPort()))) {
					updateFlag = true;
				}

				serialPortName = serialPortsList.get((serialPortSelector
						.getPort()));

				baudRate = baudRatePanel.getBaudRate();

				switch (dataBitsSelector.getDataBits()) {
				case 5:
					dataBits = SerialPort.DATABITS_5;
					break;
				case 6:
					dataBits = SerialPort.DATABITS_6;
					break;
				case 7:
					dataBits = SerialPort.DATABITS_7;
					break;
				case 8:
					dataBits = SerialPort.DATABITS_8;
					break;
				default:
					dataBits = SerialPort.DATABITS_8;

				}

				switch (paritySelector.getParity()) {
				case 0:
					parity = SerialPort.PARITY_NONE;
					break;
				case 1:
					parity = SerialPort.PARITY_ODD;
					break;
				case 2:
					parity = SerialPort.PARITY_EVEN;
					break;
				case 3:
					parity = SerialPort.PARITY_MARK;
					break;
				case 4:
					parity = SerialPort.PARITY_SPACE;
					break;
				default:
					parity = SerialPort.PARITY_NONE;
				}

				switch (stopBitsSelector.getStopBits()) {
				case 0:
					stopBits = SerialPort.STOPBITS_1;
					break;
				case 1:
					stopBits = SerialPort.STOPBITS_2;
					break;
				case 2:
					stopBits = SerialPort.STOPBITS_1_5;
					break;
				default:
					stopBits = SerialPort.STOPBITS_1;
				}

				switch (flowControlSelector.getFlowControl()) {
				case 0:
					flowControl = SerialPort.FLOWCONTROL_NONE;
					break;
				case 1:
					flowControl = SerialPort.FLOWCONTROL_RTSCTS_IN;
					break;
				case 2:
					flowControl = SerialPort.FLOWCONTROL_RTSCTS_OUT;
					break;
				case 3:
					flowControl = SerialPort.FLOWCONTROL_XONXOFF_IN;
					break;
				case 4:
					flowControl = SerialPort.FLOWCONTROL_XONXOFF_OUT;
					break;
				default:
					flowControl = SerialPort.FLOWCONTROL_NONE;
				}

				try {
					preparedStatement = connection
							.prepareStatement("UPDATE serialSettings SET port='"
									+ serialPortName
									+ "',baudRate="
									+ baudRate
									+ ",dataBits="
									+ dataBits
									+ ",stopBits="
									+ stopBits
									+ ",parity="
									+ parity
									+ ",flowControl="
									+ flowControl
									+ " WHERE ID=1");
					preparedStatement.executeUpdate();

					serialPortSelector.setPort(serialPortName);
					baudRatePanel.setBaudRate(baudRate);
					dataBitsSelector.setDataBits(dataBits);
					flowControlSelector.setFlowControl(flowControl);
					paritySelector.setParity(parity);
					stopBitsSelector.setStopBits(stopBits);
					updatePort();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		wallHand = new WallpaperHandler();
		add(wallHand);
		HeadingPanel heading = new HeadingPanel("Select Wallpaper");
		heading.setBounds(498, 45, 348, 35);
		add(heading);

		HeadingPanel compHead = new HeadingPanel("Company");
		compHead.setBounds(498, 250, 348, 35);
		add(compHead);

		CurrentWallpaperRetriever cwr = new CurrentWallpaperRetriever();
		company = new CustomStandardTextField(cwr.getCompany());
		company.setBounds(498, 290, 348, 30);
		add(company);
	}

	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1.5f));
		g2.setColor(new Color(255, 255, 255, 50));
		g2.drawLine(459, 51, 459, 470);
	}

	public void updatePort() {
		if (serialPort != null) {
			if (updateFlag) {
				try {
					serialPort.setSerialPortParams(baudRate, dataBits,
							stopBits, parity);
					serialPort.setFlowControlMode(flowControl);
				} catch (UnsupportedCommOperationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {

				initializePort();
			}
		} else {

			initializePort();
		}
	}

	public boolean initializePort() {
		CommPortIdentifier portId;
		try {

			portId = CommPortIdentifier.getPortIdentifier(serialPortName);
			serialPort = (SerialPort) portId.open("WeighPro", 5000);
			serialPort
					.setSerialPortParams(baudRate, dataBits, stopBits, parity);
			serialPort.setFlowControlMode(flowControl);

			return true;

		} catch (NoSuchPortException e) {
			e.printStackTrace();
			return false;
		} catch (PortInUseException e) {
			e.printStackTrace();
			return false;
		} catch (UnsupportedCommOperationException e) {
			e.printStackTrace();
			return false;
		}

	}

	Connection getConnection() throws SQLException {
		DatabaseConnection conn = new DatabaseConnection();
		Connection connection = conn.getConnection();
		return connection;
	}

	public synchronized String getData() throws IOException,
			InterruptedException {
		if (serialPort != null) {
			InputStream inStream = serialPort.getInputStream();
			byte[] buffer = new byte[6];
			byte c = 0;
			int i = 0;
			inStream.read();
			while (c != ' ') {
				c = (byte) inStream.read();
				buffer[i++] = (byte) c;
				if(i>5)
					break;
			}
			String newString = new String(buffer);
			newString = newString.trim();
			if (newString.length() == 5) {
				try {
					Integer.parseInt(newString);
					if(MainFrame.firstEntryPanel!=null&&MainFrame.secondEntryPanel!=null){
					if (MainFrame.firstEntryPanel.weight != null
							&& MainFrame.secondEntryPanel.weight != null) {
						if (MainFrame.firstEntryPanel.weight.isRunning()
								&& MainFrame.secondEntryPanel.weight
										.isRunning()) {
							notify();

							wait();

						} else if (MainFrame.firstEntryPanel.weight.thread
								.getState().equals(Thread.State.WAITING)
								| MainFrame.secondEntryPanel.weight.thread
										.getState()
										.equals(Thread.State.WAITING))
							notify();

					}
					}
					return newString;

				} catch (NumberFormatException e) {
					e.printStackTrace();
					return "00000";
				}
			}
		}
		return "00000";
	}
}
