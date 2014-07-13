package in.weighpro.dateSelector;
import in.weighpro.formatters.DateLabelFormatter;

import java.util.Date;

import javax.swing.JComponent;
import javax.swing.JPanel;

import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.UtilDateModel;




public class CustomDatePickerPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1473954418301489314L;
	private JDatePicker initialDatePicker;
	private UtilDateModel dateModel ;
	
	
	public CustomDatePickerPanel(){
		setSize(250, 250);
		setLayout(null);
	setOpaque(false);
	dateModel = new UtilDateModel(new Date());

	JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
	
	initialDatePicker = new JDatePickerImpl(datePanel,
			new DateLabelFormatter());
	JComponent comp = (JComponent) initialDatePicker;
	comp.setBounds(0, 0, 222, 30);
	add(comp);
	
	datePanel.setBounds(0, 33, 222, 180);
	add(datePanel);
	
	
	
	}
	
	public UtilDateModel getDateModel(){
		return this.dateModel;
	}



}
