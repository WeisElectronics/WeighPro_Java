package in.weighpro.panels;

import in.weighpro.MainClasses.MainFrame;
import in.weighpro.customComponents.CustomMutedTextField;
import in.weighpro.customComponents.CustomStandardButton;
import in.weighpro.customComponents.CustomStandardLabel;
import in.weighpro.customComponents.CustomStandardTextField;
import in.weighpro.customComponents.HeadingPanel;
import in.weighpro.dataComponents.Entry;
import in.weighpro.dimensionConstants.PanelBounds;
import in.weighpro.dimensionConstants.PanelDimensions;
import in.weighpro.documentFilters.NumberOnlyFilter;
import in.weighpro.printingHandler.PrintJobHandler;
import in.weighpro.printingHandler.PrintPreviewPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;

public class PrintPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 228414798259863405L;
	private CustomStandardLabel IDLabel, vehicleNumberLabel, customerLabel,
			materialLabel, amountLabel;
	CustomMutedTextField ID;
	private int lXBound = 50, lYBound = 50, lWidth = 200, lHeight = 28;

	private CustomStandardLabel tareLabel, grossLabel, netLabel, tareDateLabel,
			grossDateLabel, netDateLabel, statusLabel;
	private int l2XBound = 500, l2YBound = 50;

	private CustomMutedTextField vehicleNumber, customer, material, amount;
	
	private int tfXBound = 220, tfYBound = 50, tfWidth = 200, tfHeight = 28;

	private CustomMutedTextField tare, gross, net, tareDate, grossDate,
			netDate, status;
	private int tf2XBound = 650, tf2YBound = 50;

	private CustomStandardButton printButton;

	private Entry entry;

	public PrintPanel(final Entry entry) {
		this.entry = entry;
		commonInitialization();
	}

	public PrintPanel(int index) throws SQLException {
		entry = new Entry(index);
		commonInitialization();
	}

	public PrintPanel() throws SQLException {
		setLayout(null);
		setOpaque(false);
		setBounds(PanelBounds.mainPanelXBound, PanelBounds.mainPanelYBound,
				PanelDimensions.mainPanelWidth, PanelDimensions.mainPanelHeight);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(100, 100, 100, 120));
		setVisible(false);
		PrintPreviewPanel ppp = new PrintPreviewPanel();
		add(ppp);
		HeadingPanel heading = new HeadingPanel("Print Preview");
		heading.setBounds(498, 45, 348, 35);
		add(heading);
		
		
		final CustomStandardLabel entryLabel = new CustomStandardLabel(
				"Enter Serial Number");
		entryLabel.setHorizontalAlignment(JLabel.CENTER);
		entryLabel.setBounds(10, 227, 438, 30);
		add(entryLabel);

		CustomStandardButton retrieveButton = new CustomStandardButton(
				"Retrieve Entry");
		retrieveButton.setBounds(110, 320, 250, 60);
		add(retrieveButton);

		final CustomStandardTextField entryField = new CustomStandardTextField(
				20);
		entryField.setBounds(110, 272, 250, 28);
		((AbstractDocument) entryField.getDocument())
				.setDocumentFilter(new NumberOnlyFilter());

		add(entryField);

		retrieveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (entryField.getText().trim().equals("")) {
					entryField
							.setBorder(new LineBorder(new Color(150, 0, 0), 1));
				} else {
					try {
						Entry entry = new Entry(Integer.parseInt(entryField
								.getText().trim()));
						if (entry.getIndex() != -1) {

							MainFrame.background.remove(MainFrame.printPanel);

							MainFrame.printPanel = new PrintPanel(entry);

							MainFrame.background.add(MainFrame.printPanel);

							MainFrame.printPanel.setVisible(true);

						} else {
							entryLabel.setText("Entry Not Present");

						}
					} catch (NumberFormatException | SQLException e1) {
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
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1.5f));
		g2.setColor(new Color(255, 255, 255, 50));
		g2.drawLine(459, 51, 459, 520);
	}

	void setTextFieldBounds(Component comp) {
		comp.setBounds(tfXBound, tfYBound, tfWidth, tfHeight);
		tfYBound += tfHeight + 30;
	}

	void setTextField2Bounds(Component comp) {
		comp.setBounds(tf2XBound, tf2YBound, tfWidth, tfHeight);
		tf2YBound += tfHeight + 30;
	}

	void setLabelBounds(Component comp) {
		comp.setBounds(lXBound, lYBound, lWidth, lHeight);
		lYBound += tfHeight + 30;
	}

	void setLabel2Bounds(Component comp) {
		comp.setBounds(l2XBound, l2YBound, lWidth, lHeight);
		l2YBound += tfHeight + 30;
	}

	void commonInitialization() {

		setLayout(null);
		setOpaque(false);
		setBounds(PanelBounds.mainPanelXBound, PanelBounds.mainPanelYBound,
				PanelDimensions.mainPanelWidth, PanelDimensions.mainPanelHeight);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(100, 100, 100, 120));
		setVisible(false);

		IDLabel = new CustomStandardLabel("ID");
		setLabelBounds(IDLabel);
		add(IDLabel);

		vehicleNumberLabel = new CustomStandardLabel("Vehicle Number");
		setLabelBounds(vehicleNumberLabel);
		add(vehicleNumberLabel);

		tareLabel = new CustomStandardLabel("Tare Weight");
		setLabel2Bounds(tareLabel);
		add(tareLabel);

		customerLabel = new CustomStandardLabel("Customer");
		setLabelBounds(customerLabel);
		add(customerLabel);

		grossLabel = new CustomStandardLabel("Gross Weight");
		setLabel2Bounds(grossLabel);
		add(grossLabel);

		materialLabel = new CustomStandardLabel("Material");
		setLabelBounds(materialLabel);
		add(materialLabel);

		netLabel = new CustomStandardLabel("Net Weight");
		setLabel2Bounds(netLabel);
		add(netLabel);

		amountLabel = new CustomStandardLabel("Amount");
		setLabelBounds(amountLabel);
		add(amountLabel);

		tareDateLabel = new CustomStandardLabel("Tare Date");
		setLabel2Bounds(tareDateLabel);
		add(tareDateLabel);

		grossDateLabel = new CustomStandardLabel("Gross Date");
		setLabel2Bounds(grossDateLabel);
		add(grossDateLabel);

		netDateLabel = new CustomStandardLabel("Net Date");
		setLabel2Bounds(netDateLabel);
		add(netDateLabel);

		statusLabel = new CustomStandardLabel("Status");
		setLabelBounds(statusLabel);
		add(statusLabel);

		

		printButton = new CustomStandardButton("Print");
		printButton.setBounds(50, 460, 370, 60);
		add(printButton);
		
		printButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new PrintJobHandler(entry);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		});
		
		
		ID = new CustomMutedTextField(Integer.toString(entry.getIndex()));
		setTextFieldBounds(ID);
		add(ID);

		vehicleNumber = new CustomMutedTextField(entry.getVehicleNumber());
		setTextFieldBounds(vehicleNumber);
		add(vehicleNumber);

		tare = new CustomMutedTextField(entry.getTareWeight());
		setTextField2Bounds(tare);
		add(tare);

		customer = new CustomMutedTextField(entry.getCustomer());
		setTextFieldBounds(customer);
		add(customer);

		gross = new CustomMutedTextField(entry.getGrossWeight());
		setTextField2Bounds(gross);
		add(gross);

		material = new CustomMutedTextField(entry.getMaterial());
		setTextFieldBounds(material);
		add(material);

		net = new CustomMutedTextField(entry.getNetWeight());
		setTextField2Bounds(net);
		add(net);

		amount = new CustomMutedTextField(Integer.toString(entry.getAmount()));
		setTextFieldBounds(amount);
		add(amount);

		try {
			tareDate = new CustomMutedTextField(entry.getTareDate());
			grossDate = new CustomMutedTextField(entry.getGrossDate());
			netDate = new CustomMutedTextField(entry.getNetDate());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTextField2Bounds(tareDate);
		add(tareDate);

		setTextField2Bounds(grossDate);
		add(grossDate);

		setTextField2Bounds(netDate);
		add(netDate);
		status = new CustomMutedTextField((entry.getStatus()) ? "Completed"
				: "Incomplete");
		setTextFieldBounds(status);
		add(status);

		
	}

}
