package in.weighpro.panels;

import in.weighpro.MainClasses.MainFrame;
import in.weighpro.customComponents.CustomComboBox;
import in.weighpro.customComponents.CustomStandardButton;
import in.weighpro.customComponents.CustomStandardLabel;
import in.weighpro.dataComponents.Customer;
import in.weighpro.dataComponents.Material;
import in.weighpro.databaseQueryHandler.DataArrayRetriever;
import in.weighpro.dateSelector.CustomDatePickerPanel;
import in.weighpro.dimensionConstants.PanelBounds;
import in.weighpro.dimensionConstants.PanelDimensions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class ReportsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1938587068515377287L;
	private CustomDatePickerPanel initialDateSelector;
	private CustomDatePickerPanel finalDateSelector;
	private CustomStandardLabel startLabel;
	private CustomStandardLabel lastLabel;
	private UtilDateModel initialDateModel;
	private UtilDateModel finalDateModel;
	private CustomComboBox customerSelector;
	private CustomStandardLabel customerLabel;
	private CustomComboBox materialSelector;
	private CustomStandardLabel materialLabel;
	private CustomStandardButton retrieveButton;
	private CustomStandardLabel note;

	public ReportsPanel() throws SQLException {

		setLayout(null);
		setOpaque(false);
		setBounds(PanelBounds.mainPanelXBound, PanelBounds.mainPanelYBound,
				PanelDimensions.mainPanelWidth, PanelDimensions.mainPanelHeight);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(100, 100, 100, 120));
		setVisible(false);

		initialDateSelector = new CustomDatePickerPanel();
		initialDateSelector.setBounds(187, 50, 222, 231);
		add(initialDateSelector);

		startLabel = new CustomStandardLabel("Start Date");
		startLabel.setBounds(50, 50, 187, 30);
		add(startLabel);

		finalDateSelector = new CustomDatePickerPanel();
		finalDateSelector.setBounds(187, 307, 222, 231);
		add(finalDateSelector);

		lastLabel = new CustomStandardLabel("Last Date");
		lastLabel.setBounds(50, 307, 187, 30);
		add(lastLabel);

		initialDateModel = initialDateSelector.getDateModel();
		finalDateModel = finalDateSelector.getDateModel();

		initialDateModel.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (initialDateModel.getValue() == null)

					initialDateModel.setValue(new Date());

				finalDateModel.setValue(initialDateModel.getValue());
			}

		});

		finalDateModel.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				if (finalDateModel.getValue() != null) {
					if (!checkValidity()) {
						finalDateModel.setValue(initialDateModel.getValue());

					}
				} else {
					finalDateModel.setValue(initialDateModel.getValue());

				}
			}

		});

		customerLabel = new CustomStandardLabel("Customer");
		customerLabel.setBounds(500, 50, 100, 30);
		add(customerLabel);

		materialLabel = new CustomStandardLabel("Material");
		materialLabel.setBounds(500, 240, 100, 30);
		add(materialLabel);

		retrieveButton = new CustomStandardButton("Retrieve");
		retrieveButton.setBounds(500, 460, 360, 60);
		add(retrieveButton);

		retrieveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.background.remove(MainFrame.reportsPanel);
				try {
					if (customerSelector.getValue().equals("")
							&& materialSelector.getValue().equals(""))
						MainFrame.reportsPanel = new RetrievedReportsPanel(
								getSelectedInitialDate(),
								getSelectedFinalDate());
					else if (!customerSelector.getValue().equals("")
							&& materialSelector.getValue().equals("")) {
						Customer customer = new Customer(customerSelector
								.getValue());
						MainFrame.reportsPanel = new RetrievedReportsPanel(
								getSelectedInitialDate(),
								getSelectedFinalDate(), customer);
					} else if (customerSelector.getValue().equals("")
							&& !materialSelector.getValue().equals("")) {
						Material material = new Material(materialSelector
								.getValue());
						MainFrame.reportsPanel = new RetrievedReportsPanel(
								getSelectedInitialDate(),
								getSelectedFinalDate(), material);
					} else if (!customerSelector.getValue().equals("")
							&& !materialSelector.getValue().equals("")) {
						Customer customer = new Customer(customerSelector
								.getValue());
						Material material = new Material(materialSelector
								.getValue());
						MainFrame.reportsPanel = new RetrievedReportsPanel(
								getSelectedInitialDate(),
								getSelectedFinalDate(), customer, material);

					}

					MainFrame.background.add(MainFrame.reportsPanel);
					MainFrame.reportsPanel.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});

		note = new CustomStandardLabel(
				"All customers and materials are selected by default");
		note.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		note.setBounds(500, 400, 360, 60);
		add(note);

		resetLists();

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

	public String getSelectedInitialDate() {
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String formattedDate = df2.format(initialDateModel.getValue());
		return formattedDate;
	}

	public String getSelectedFinalDate() {
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		String formattedDate = df2.format(finalDateModel.getValue());
		return formattedDate;
	}

	boolean checkValidity() {
		Date initialDate = new Date(), finalDate = new Date();
		initialDate = (Date) initialDateModel.getValue();
		finalDate = (Date) finalDateModel.getValue();
		return (initialDate.compareTo(finalDate) <= 0);

	}

	public void resetLists() throws SQLException {
		if (customerSelector != null)
			remove(customerSelector);
		if (materialSelector != null)
			remove(materialSelector);
		DataArrayRetriever dar = new DataArrayRetriever("customers");

		Rectangle bounds = new Rectangle(650, 50, 201, 31);

		customerSelector = new CustomComboBox(dar.getItems(), true, bounds);
		add(customerSelector);

		bounds = new Rectangle(650, 240, 201, 31);
		dar = new DataArrayRetriever("materials");
		materialSelector = new CustomComboBox(dar.getItems(), true, bounds);
		add(materialSelector);
	}
}
