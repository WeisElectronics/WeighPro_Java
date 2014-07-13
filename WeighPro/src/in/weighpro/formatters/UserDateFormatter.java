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

	public UserDateFormatter() {

		datePattern = "dd-MM-yyyy hh:mm:ss";

		dateFormatter = new SimpleDateFormat(datePattern);
	}

	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime((Date) value);
			 if(cal.get(Calendar.YEAR)<2014)
				return "";
				 return dateFormatter.format(cal.getTime());
		}

		return "";
	}

}