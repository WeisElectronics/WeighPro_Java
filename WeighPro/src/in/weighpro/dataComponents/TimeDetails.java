package in.weighpro.dataComponents;

import java.util.Calendar;
import java.util.Formatter;

public class TimeDetails {
	private String entryTimeStamp = "00-01-01 00:00:00";
	private String exitTimeStamp = "00-01-01 00:00:00";

	public TimeDetails() {
		entryTimeStamp = getTimeStamp();
	}

	public TimeDetails(String timeStamp) {
		entryTimeStamp = timeStamp;
	}

	public TimeDetails(String entryTimeStamp, String exitTimeStamp) {
		this.entryTimeStamp = entryTimeStamp;
		this.exitTimeStamp = exitTimeStamp;
	}

	public String getEntryTimeStamp() {
		return entryTimeStamp;
	}

	public String getExitTimeStamp() {
		return exitTimeStamp;
	}

	String getTimeStamp() {
		Calendar calendar = Calendar.getInstance();
		Formatter formatter = new Formatter();
		formatter.format("%tF %<tT", calendar);
		String str = formatter.toString();
		formatter.close();
		return str.trim();
	}

	public void setExitTimeStamp() {
		exitTimeStamp = getTimeStamp();
	}
}
