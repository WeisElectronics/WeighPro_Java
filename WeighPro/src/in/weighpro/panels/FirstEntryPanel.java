package in.weighpro.panels;

import in.weighpro.MainClasses.MainFrame;
import in.weighpro.commonComponents.FieldComponents;
import in.weighpro.commonComponents.IncompleteListPane;
import in.weighpro.commonComponents.LabelComponents;
import in.weighpro.commonComponents.WeightPanel;
import in.weighpro.customComponents.CustomMutedTextField;
import in.weighpro.customComponents.CustomRadioButton;
import in.weighpro.customComponents.CustomStandardButton;
import in.weighpro.customComponents.CustomStandardLabel;
import in.weighpro.customComponents.HeadingPanel;
import in.weighpro.dataComponents.Entry;
import in.weighpro.databaseQueryHandler.DataCountQuery;
import in.weighpro.dimensionConstants.PanelBounds;
import in.weighpro.dimensionConstants.PanelDimensions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import in.weighpro.printingHandler.*;

public class FirstEntryPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 228414798259863405L;

	private LabelComponents labelComp;

	private FieldComponents fieldComp;
	
	CustomMutedTextField ID;

	
	private CustomRadioButton tareButton, grossButton;

	public WeightPanel weight;

	private CustomStandardLabel unitLabel;

	private CustomStandardButton saveButton, printButton;

	private IncompleteListPane incompleteList;

	final Pattern pat = Pattern.compile("[A-Z]{2} \\d{1,2} [A-Z]*\\s*\\d{1,4}");

	private Entry entry;
	
	public FirstEntryPanel() throws SQLException {
		setLayout(null);
		setOpaque(false);
		setBounds(PanelBounds.mainPanelXBound, PanelBounds.mainPanelYBound,
				PanelDimensions.mainPanelWidth, PanelDimensions.mainPanelHeight);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(100, 100, 100, 120));

		HeadingPanel headingPanel = new HeadingPanel("Incomplete Entries");
		headingPanel.setBounds(498, 45, 348, 35);
		add(headingPanel);

		labelComp = new LabelComponents();
		add(labelComp);

		fieldComp = new FieldComponents();
		add(fieldComp);
		
		DataCountQuery dataCount = new DataCountQuery("entries");

		ID = new CustomMutedTextField(
				Integer.toString(dataCount.getCount() + 1));
		ID.setBounds(220, 50, 200, 28);
		add(ID);

		
		tareButton = new CustomRadioButton("Tare");
		tareButton.setBounds(220, 345, 80, 30);
		add(tareButton);

		grossButton = new CustomRadioButton("Gross");
		grossButton.setBounds(335, 345, 80, 30);
		add(grossButton);

		unitLabel = new CustomStandardLabel("Kg");
		unitLabel.setBounds(390, 400, 40, 28);
		add(unitLabel);

		ActionListener radioButtonMimicListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getActionCommand().equals("Tare")) {
					tareButton.setValidSelection();
					grossButton.setValidSelection();
					tareButton.setSelected(true);
					grossButton.setSelected(false);
				} else if (e.getActionCommand().equals("Gross")) {
					tareButton.setValidSelection();
					grossButton.setValidSelection();
					grossButton.setSelected(true);
					tareButton.setSelected(false);
				}
			}

		};

		tareButton.addActionListener(radioButtonMimicListener);
		grossButton.addActionListener(radioButtonMimicListener);

		incompleteList = new IncompleteListPane();
		add(incompleteList);

		saveButton = new CustomStandardButton("Save");
		saveButton.setBounds(50, 460, 370, 60);
		add(saveButton);

		printButton = new CustomStandardButton("Print");
		printButton.setBounds(50, 460, 370, 60);
		add(printButton);
		printButton.setVisible(false);

		saveButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Matcher mat = pat.matcher(fieldComp.vehicleNumberField.getText().trim());

				if (!mat.matches())
					fieldComp.vehicleNumberField.setBorder(new LineBorder(new Color(150,
							0, 0), 1));
				/* if (fieldComp.customerField.getText().trim().equals(""))
					
					fieldComp.customerField.setBorder(new LineBorder(
							new Color(150, 0, 0), 1));
				if (fieldComp.materialField.getText().trim().equals(""))
					fieldComp.materialField.setBorder(new LineBorder(
							new Color(150, 0, 0), 1));
				*/
				if (fieldComp.amountField.getText().trim().equals(""))
					fieldComp.amountField.setBorder(new LineBorder(new Color(150, 0, 0),
							1));
				else if (!(Integer.parseInt(fieldComp.amountField.getText().trim())<=500))
					fieldComp.amountField.setBorder(new LineBorder(new Color(150, 0, 0),
							1));
				if (!(tareButton.getSelected() | grossButton.getSelected())) {
					tareButton.setNullSelection();
					grossButton.setNullSelection();
				}
				if (mat.matches()
						//&& !fieldComp.customerField.getText().trim().equals("")
						//&& !fieldComp.materialField.getText().trim().equals("")
						&& !fieldComp.amountField.getText().trim().equals("")&&(Integer.parseInt(fieldComp.amountField.getText().trim())<=500)
						&& (tareButton.getSelected() | grossButton
								.getSelected())) {
					
					stopThread();
					String vehNum = fieldComp.vehicleNumberField.getText().trim();
					String modVehNum = vehNum.substring(0,
							vehNum.lastIndexOf(" "));
					String[] parts = vehNum.split(" ");
					int number = Integer.parseInt(parts[parts.length - 1]);
					if (number >= 1 && number <= 9)
						modVehNum += " 000" + number;
					else if (number >= 10 && number <= 99)
						modVehNum += " 00" + number;
					else if (number >= 100 && number <= 999)
						modVehNum += " 0" + number;
					else if (number >= 1000 && number <= 9999)
						modVehNum += " " + number;

					saveButton.setClicked(true);
					fieldComp.vehicleNumberField
							.setBorder(new LineBorder(Color.white, 1));
					fieldComp.customerField.setBorder(new LineBorder(Color.white, 1));
					fieldComp.materialField.setBorder(new LineBorder(Color.white, 1));
					fieldComp.amountField.setBorder(new LineBorder(Color.white, 1));

					try {
					entry =	new Entry(modVehNum, fieldComp.customerField.getText().trim(),
								fieldComp.materialField.getText().trim(), Integer
										.parseInt(weight.getText()), tareButton
										.getSelected(), Integer
										.parseInt(fieldComp.amountField.getText().trim()));
						incompleteList.resetList();
						saveButton.setVisible(false);
						printButton.setVisible(true);
						if (MainFrame.secondEntryPanel.listVisible)
							MainFrame.secondEntryPanel.resetList();
						try {
							((ReportsPanel) MainFrame.reportsPanel)
									.resetLists();
						} catch (ClassCastException e2) {

						}
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						saveButton.setClicked(false);

					}
				} else {
					saveButton.setClicked(false);
				}

			}

		});
		weight = new WeightPanel();
		weight.setBounds(230, 392, 200, 40);
		add(weight);
		
		
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

		setVisible(true);
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

	
	public void stopThread() {
		weight.stopThread();
	}

	public void resetList() throws SQLException {
		incompleteList.resetList();
	}

}
