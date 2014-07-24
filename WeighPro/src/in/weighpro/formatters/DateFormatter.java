/*
* This class provides the
* pluggable date formatter which is 
* used to store dates in the database

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.formatters;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateFormatter extends AbstractFormatter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2252560729103662967L;
	private String datePattern;
	private SimpleDateFormat dateFormatter;

	//constructor to initialize the formatter using the pattern
	public DateFormatter() {

		datePattern = "yyyy-MM-dd hh:mm:ss";//example date would be of the form 2014-07-24 18:35:00(corresponding to 24th July 2014 6:35 pm)

		dateFormatter = new SimpleDateFormat(datePattern);//initialize the formatter object that will be used to format string in the specified format
	}

	//method called when converting a date string into a date object of the specified format
	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);//parse the passed string using the formatter
	}

	//method called when converting a date object into a String of the specified format
	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {//when the passed object is not null
			Calendar cal = (Calendar) value;//casting the object to a Calendar object since most methods of the date class have been deprecated
			return dateFormatter.format(cal.getTime());//get time from the created calendar object
		}

		return "";//if null is passed return an empty string
	}

}