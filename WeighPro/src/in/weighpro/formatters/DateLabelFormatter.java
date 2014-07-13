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

	public DateLabelFormatter() {
		
			datePattern = "dd MMMM yyyy";

		
		dateFormatter = new SimpleDateFormat(datePattern);
	}

	@Override
	public Object stringToValue(String text) throws ParseException {
		return dateFormatter.parseObject(text);
	}

	@Override
	public String valueToString(Object value) throws ParseException {
		if (value != null) {
			Calendar cal = (Calendar) value;
			return dateFormatter.format(cal.getTime());
		}

		return "";
	}

	
	
	

}