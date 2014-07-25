package in.weighpro.panels;

import in.weighpro.MainClasses.MainFrame;
import in.weighpro.customComponents.CustomStandardButton;
import in.weighpro.customComponents.CustomStandardLabel;
import in.weighpro.customComponents.InternalTableCellRenderer;
import in.weighpro.dataComponents.Customer;
import in.weighpro.dataComponents.Entry;
import in.weighpro.dataComponents.Material;
import in.weighpro.databaseQueryHandler.RetrieveEntries;
import in.weighpro.dimensionConstants.PanelBounds;
import in.weighpro.dimensionConstants.PanelDimensions;
import in.weighpro.listComponents.CustomScrollBarUI;
import in.weighpro.printingHandler.PrintJobHandler;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class RetrievedReportsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -682093670168477397L;
	private JTable retrievedReportTable;
	private CustomStandardButton print;
	private CustomStandardLabel amount;
	private CustomStandardLabel amountLabel;
	private Entry[] entries;
	
	private String startDate;
	private String stopDate;
	private String customer = "All Customers";
	private String material = "All Materials";
	
	String[] columnHeaders = { "ID", "Vehicle Number", "Customer", "Material",
			"Amount", "Tare", "Gross", "Net", "Tare Date", "Gross Date",
			"Status" };
	String values[][];
	RetrieveEntries retEnt;

	public RetrievedReportsPanel(String startDate, String lastDate)
			throws SQLException, ParseException {
		this.startDate = startDate;
		this.stopDate = lastDate;
		retEnt = new RetrieveEntries(startDate, lastDate);
		commonInitialization();
	}

	public RetrievedReportsPanel(String startDate, String lastDate,
			Customer customer) throws SQLException, ParseException {
		this.startDate = startDate;
		this.stopDate = lastDate;
		this.customer = customer.getValue();
		retEnt = new RetrieveEntries(startDate, lastDate, customer);
		commonInitialization();
	}

	public RetrievedReportsPanel(String startDate, String lastDate,
			Material material) throws SQLException, ParseException {
		this.startDate = startDate;
		this.stopDate = lastDate;
		this.material = material.getValue();
		retEnt = new RetrieveEntries(startDate, lastDate, material);
		commonInitialization();
	}

	public RetrievedReportsPanel(String startDate, String lastDate,
			Customer customer, Material material) throws SQLException,
			ParseException {
		this.startDate = startDate;
		this.stopDate = lastDate;
		this.customer = customer.getValue();
		this.material = material.getValue();
		retEnt = new RetrieveEntries(startDate, lastDate, customer, material);
		commonInitialization();
	}

	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(1.5f));
		g2.setColor(new Color(255, 255, 255, 50));
		g2.drawLine(459, 500, 459, 560);
	}

	void commonInitialization() throws SQLException, ParseException {
		setLayout(null);
		setOpaque(false);
		setBounds(PanelBounds.mainPanelXBound, PanelBounds.mainPanelYBound,
				PanelDimensions.mainPanelWidth, PanelDimensions.mainPanelHeight);
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(100, 100, 100, 120));
		setVisible(false);
		int[] retInd = retEnt.getEntries();
		entries = new Entry[retInd.length];
		values = new String[retInd.length][11];

		for (int i = 0; i < retInd.length; i++) {
			entries[i] = new Entry(retInd[i]);
			values[i][0] = Integer.toString(entries[i].getIndex());
			values[i][1] = entries[i].getVehicleNumber();
			values[i][2] = entries[i].getCustomer();
			values[i][3] = entries[i].getMaterial();
			values[i][4] = Integer.toString(entries[i].getAmount());
			values[i][5] = entries[i].getTareWeight();
			values[i][6] = entries[i].getGrossWeight();
			values[i][7] = entries[i].getNetWeight();
			values[i][8] = entries[i].getTareDate();
			values[i][9] = entries[i].getGrossDate();
			values[i][10] = entries[i].getStatus() ? "Completed" : "Incomplete";
		}
		retrievedReportTable = new JTable(values, columnHeaders) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 4587498716934646873L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		retrievedReportTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		retrievedReportTable.setBorder(new LineBorder(Color.white, 1));

		retrievedReportTable.getColumnModel().getColumn(0)
				.setPreferredWidth(40);
		retrievedReportTable.getColumnModel().getColumn(1)
				.setPreferredWidth(130);
		retrievedReportTable.getColumnModel().getColumn(2)
				.setPreferredWidth(118);
		retrievedReportTable.getColumnModel().getColumn(3)
				.setPreferredWidth(80);
		retrievedReportTable.getColumnModel().getColumn(4)
				.setPreferredWidth(83);
		retrievedReportTable.getColumnModel().getColumn(5)
				.setPreferredWidth(83);
		retrievedReportTable.getColumnModel().getColumn(6)
				.setPreferredWidth(83);
		retrievedReportTable.getColumnModel().getColumn(7)
				.setPreferredWidth(83);
		retrievedReportTable.getColumnModel().getColumn(8)
				.setPreferredWidth(150);
		retrievedReportTable.getColumnModel().getColumn(9)
				.setPreferredWidth(150);
		retrievedReportTable.getColumnModel().getColumn(10)
				.setPreferredWidth(80);

		retrievedReportTable.setOpaque(false);
		retrievedReportTable.setBackground(new Color(0, 0, 0, 0));
		retrievedReportTable.setGridColor(Color.WHITE);
		retrievedReportTable
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		retrievedReportTable.setForeground(Color.white);

		retrievedReportTable.getColumnModel().getColumn(0)
				.setCellRenderer(new InternalTableCellRenderer());
		retrievedReportTable.getColumnModel().getColumn(1)
				.setCellRenderer(new InternalTableCellRenderer());
		retrievedReportTable.getColumnModel().getColumn(2)
				.setCellRenderer(new InternalTableCellRenderer());
		retrievedReportTable.getColumnModel().getColumn(3)
				.setCellRenderer(new InternalTableCellRenderer());
		retrievedReportTable.getColumnModel().getColumn(4)
				.setCellRenderer(new InternalTableCellRenderer());
		retrievedReportTable.getColumnModel().getColumn(5)
				.setCellRenderer(new InternalTableCellRenderer());
		retrievedReportTable.getColumnModel().getColumn(6)
				.setCellRenderer(new InternalTableCellRenderer());
		retrievedReportTable.getColumnModel().getColumn(7)
				.setCellRenderer(new InternalTableCellRenderer());
		retrievedReportTable.getColumnModel().getColumn(8)
				.setCellRenderer(new InternalTableCellRenderer());
		retrievedReportTable.getColumnModel().getColumn(9)
				.setCellRenderer(new InternalTableCellRenderer());
		retrievedReportTable.getColumnModel().getColumn(10)
				.setCellRenderer(new InternalTableCellRenderer());

		JScrollPane pane = new JScrollPane(retrievedReportTable,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

		retrievedReportTable
				.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		retrievedReportTable.setRowSelectionAllowed(true);
		retrievedReportTable.setRowHeight(21);

		pane.getViewport().setOpaque(false);
		pane.setOpaque(false);
		pane.setBorder(new EmptyBorder(0, 0, 0, 0));

		pane.setViewportBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

		pane.setBounds(23, 18, 867, 472);
		pane.getVerticalScrollBar().setOpaque(false);
		pane.getVerticalScrollBar().setUI(new CustomScrollBarUI());

		pane.getHorizontalScrollBar().setOpaque(false);
		pane.getHorizontalScrollBar().setUI(new CustomScrollBarUI());

		pane.setColumnHeader(new JViewport());
		retrievedReportTable.getTableHeader().setDefaultRenderer(
				new InternalTableCellRenderer());
		retrievedReportTable.getTableHeader().setOpaque(false);
		pane.getColumnHeader().setOpaque(false);

		add(pane);

		amountLabel = new CustomStandardLabel("Collected Amount : Rs.");
		amountLabel.setBounds(55, 515, 210, 30);
		add(amountLabel);

		print = new CustomStandardButton("Print Report");
		print.setBounds(470, 495, 410, 70);
		add(print);

		amount = new CustomStandardLabel("");
		amount.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
		amount.setBounds(280, 510, 130, 40);
		add(amount);

		int amountValue = 0;
		for (int i = 0; i < values.length; i++) {
			amountValue += Integer.parseInt(values[i][4]);
		}
		amount.setText(Integer.toString(amountValue));

		retrievedReportTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					MainFrame.reportsPanel.setVisible(false);
					MainFrame.background.remove(MainFrame.printPanel);

					MainFrame.printPanel = new PrintPanel(
							entries[retrievedReportTable.getSelectedRow()]);

					MainFrame.background.add(MainFrame.printPanel);
					MainFrame.menuPanel.setAllDisabled();
					MainFrame.printPanel.setVisible(true);
					MainFrame.menuPanel.print.setClicked(true);

				}
			}

		});
		
		
		print.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new PrintJobHandler(values,startDate,stopDate,customer,material);
			}
			
		});

	}
}
