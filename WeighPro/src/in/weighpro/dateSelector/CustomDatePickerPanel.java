/*
* This class provides the pluggable 
* date panel which is transparent and
* has a custom appearance

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

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
	
	
	//constructor to initialize the panel and set default functionality
	public CustomDatePickerPanel(){
		setSize(250, 250);//set the size
		setLayout(null);//custom layout
	setOpaque(false);//transparent
	dateModel = new UtilDateModel(new Date());//initializing the date model 
	

	JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);//initializing the date panel implementation
	
	initialDatePicker = new JDatePickerImpl(datePanel,
			new DateLabelFormatter());//setting up the initial date picker implementation
	JComponent comp = (JComponent) initialDatePicker;//casting the initial date picker to a component
	comp.setBounds(0, 0, 222, 30);//setting the bounds of the initial date picker
	add(comp);//adding the initial date picker to the panel
	
	datePanel.setBounds(0, 33, 222, 180);//setting the bounds of the date panel
	add(datePanel);//add the date panel
	
	
	
	}
	
	//method to get the date model
	//date model has methods to get the date 
	public UtilDateModel getDateModel(){
		return this.dateModel;
	}



}
