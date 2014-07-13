package in.weighpro.panels;

import in.weighpro.MainClasses.MainFrame;
import in.weighpro.commonComponents.FieldComponents;
import in.weighpro.commonComponents.IncompleteListPane;
import in.weighpro.commonComponents.LabelComponents;
import in.weighpro.commonComponents.WeightPanel;
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

public class SecondEntryPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 228414798259863405L;

	private LabelComponents labelComp;

	private FieldComponents fieldComp;
	
	
	
	CustomMutedTextField ID;
	private int lWidth = 200, lHeight = 28;

	private CustomStandardLabel tareLabel, grossLabel, netLabel, tareDateLabel,
			grossDateLabel, netDateLabel, statusLabel;
	private int l2XBound = 500, l2YBound = 50;

	CustomMutedTextField weightType;
	private int tfWidth = 200, tfHeight = 28;

	private CustomMutedTextField tare, gross, net, tareDate, grossDate,
			netDate, status;
	private int tf2XBound = 650, tf2YBound = 50;

	public WeightPanel weight;

	private CustomStandardLabel unitLabel;

	private CustomStandardButton saveButton, printButton;

	private Entry entry;
	public boolean listVisible = false;
	
	HeadingPanel headingPanel;

	private IncompleteListPane incompleteList;
	
	
	public SecondEntryPanel(final Entry entry) {
		this.entry = entry;
		commonInitialization();
		
	}

	public SecondEntryPanel(int index) throws SQLException {
		entry = new Entry(index);
		commonInitialization();
		
}

	public SecondEntryPanel() throws SQLException {
		listVisible = true;
		setLayout(null);
		setOpaque(false);
		setBounds(PanelBounds.mainPanelXBound, PanelBounds.mainPanelYBound,
				PanelDimensions.mainPanelWidth, PanelDimensions.mainPanelHeight);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(100, 100, 100, 120));
		setVisible(false);

		headingPanel = new HeadingPanel("Incomplete Entries");
		headingPanel.setBounds(498, 45, 348, 35);
		add(headingPanel);
		
		
		incompleteList = new IncompleteListPane();
		add(incompleteList);

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
							if (entry.getStatus()) {
								entryLabel.setText("Entry Already Completed");

							} else {
								MainFrame.background
										.remove(MainFrame.secondEntryPanel);

								MainFrame.secondEntryPanel = new SecondEntryPanel(
										entry);

								MainFrame.background
										.add(MainFrame.secondEntryPanel);

								MainFrame.secondEntryPanel.setVisible(true);

							}
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

	void setTextField2Bounds(Component comp) {
		comp.setBounds(tf2XBound, tf2YBound, tfWidth, tfHeight);
		tf2YBound += tfHeight + 30;
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

		labelComp = new LabelComponents();
		add(labelComp);

		fieldComp = new FieldComponents(entry);
		add(fieldComp);
		
		
		tareLabel = new CustomStandardLabel("Tare Weight");
		setLabel2Bounds(tareLabel);
		add(tareLabel);

		grossLabel = new CustomStandardLabel("Gross Weight");
		setLabel2Bounds(grossLabel);
		add(grossLabel);

		
		netLabel = new CustomStandardLabel("Net Weight");
		setLabel2Bounds(netLabel);
		add(netLabel);

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
		setLabel2Bounds(statusLabel);
		add(statusLabel);

		
		
		weight = new WeightPanel();
		weight.setBounds(230, 392, tfWidth, 40);
		add(weight);

		unitLabel = new CustomStandardLabel("Kg");
		unitLabel.setBounds(390, 400, 40, lHeight);
		add(unitLabel);

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
				try {
					stopThread();
					saveButton.setClicked(true);
					entry.updateEntry(Integer.parseInt(weight.getText().trim()));
					saveButton.setVisible(false);
					printButton.setVisible(true);
					MainFrame.firstEntryPanel.resetList();
					tare.setText(entry.getTareWeight());
					gross.setText(entry.getGrossWeight());
					net.setText(entry.getNetWeight());
					tareDate.setText(entry.getTareDate());
					grossDate.setText(entry.getGrossDate());
					netDate.setText(entry.getNetDate());
					status.setText((entry.getStatus() ? "Completed"
							: "Incomplete"));
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		ID = new CustomMutedTextField(Integer.toString(entry.getIndex()));
		ID.setBounds(220,50,200,28);
		add(ID);

		
		tare = new CustomMutedTextField(entry.getTareWeight());
		setTextField2Bounds(tare);
		add(tare);

		
		gross = new CustomMutedTextField(entry.getGrossWeight());
		setTextField2Bounds(gross);
		add(gross);

		
		net = new CustomMutedTextField(entry.getNetWeight());
		setTextField2Bounds(net);
		add(net);
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
		setTextField2Bounds(status);
		add(status);

		if (entry.getTareWeight().trim().equals("")) {
			weightType = new CustomMutedTextField("Tare");
		} else {
			weightType = new CustomMutedTextField("Gross");
		}
		weightType.setBounds(220, 338, 200, 28);
		add(weightType);
		
		
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


	}

	public void resetList() throws SQLException {
	incompleteList.resetList();	
	}

	public void stopThread() {
		if(weight!=null)
		weight.stopThread();
	}

	
	

}
