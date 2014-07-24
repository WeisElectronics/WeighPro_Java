/*
* This class provides the
* pluggable date formatter which is 
* used to show dates to the user
* in the reports panel

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.formatters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2252560729103662967L;
	private String datePattern;
	private SimpleDateFormat dateFormatter;

	
	//constructor to initialize the formatter object that formats the date in the specified format
	public DateLabelFormatter() {
		
			datePattern = "dd MMMM yyyy";//example date would be of the format 24 July 2014

		
		dateFormatter = new SimpleDateFormat(datePattern);//initializing the formatter object
	}

	
	//this method is called when a date object is returned after parsing a string
	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);//create an object after parsing the string
	}

	//method is called when a date object has to be converted to a string of the specified format
	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {//verification to check if the object passed is not null
			Calendar cal = (Calendar) value;//casting the object to a calendar object
			return dateFormatter.format(cal.getTime());//get time from the calendar object
		}

		return "";//return empty string if the object passed is null
	}

	
	
	

}