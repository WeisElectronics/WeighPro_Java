/*
* This class provides the
* pluggable date formatter which is 
* used to display dates in the reports table

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.formatters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class UserDateFormatter extends AbstractFormatter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2252560729103662967L;
	private String datePattern;
	private SimpleDateFormat dateFormatter;

	
	//constructor to initialize the formatter object which will be used to parse the date
	public UserDateFormatter() {

		datePattern = "dd-MM-yyyy hh:mm:ss";//example would be of the form 24-07-2014 18:49:00

		dateFormatter = new SimpleDateFormat(datePattern);//initializing the formatter object
	}

	//this method is called when a date object is created from the string passed
	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);//parse the passed string to a get a date object
	}

	
	//this method is called when a date object is parsed to get a string in the specified format
	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {//verification to see that the passed object is not null
			Calendar cal = Calendar.getInstance();//getting an instance of the calendar object
			cal.setTime((Date) value);//setting time to the passed time
			 if(cal.get(Calendar.YEAR)<2014)//if the year is less than 2014 display an empty string
				return "";
				 return dateFormatter.format(cal.getTime());//parse the object in the specified format
		}

		return "";
	}

}