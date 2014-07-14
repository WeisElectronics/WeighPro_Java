/* This class provides the
*pluggable date object
*which is used in the storage and retrieval
*of the entries

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/
package in.weighpro.dataComponents;

import java.util.Calendar;
import java.util.Formatter;



//this class provides the entry and exit dates of an entry which eases the data retrieval process
//It also handles the conversion from one date format to another to make it more understandable to the
//user

public class TimeDetails {
	private String entryTimeStamp = "00-01-01 00:00:00";
	private String exitTimeStamp = "00-01-01 00:00:00";


//Constructor to get the entry time stamp
//Used when a new Entry is created
//Used when the first weight is being recorded
	public TimeDetails() {
		entryTimeStamp = getTimeStamp();
	}

//constructor to get the exit time stamp
//Used when time details are retrieved from the database
//Used when second weight is being recorded
	public TimeDetails(String timeStamp) {//Takes the timestamp from the database to create a entry time String
		entryTimeStamp = timeStamp;
	}

//Constructor to get the entry as well as an exit time stamp
//Used when the details are retrieved from the database 
//usually during print and retrieving reports
	public TimeDetails(String entryTimeStamp, String exitTimeStamp) {
		this.entryTimeStamp = entryTimeStamp;
		this.exitTimeStamp = exitTimeStamp;
	}

//method to get the entry timestamp
	public String getEntryTimeStamp() {
		return entryTimeStamp;
	}

//method to get the exit timestamp
	public String getExitTimeStamp() {
		return exitTimeStamp;
	}

//Method which creates a formatted String using the date
	String getTimeStamp() {
		Calendar calendar = Calendar.getInstance();//Getting a Calendar instance
		Formatter formatter = new Formatter();//Creating a new formatter object
		formatter.format("%tF %<tT", calendar);//creating a string of the format YYYY-MM-DD HH-mm-ss
		String str = formatter.toString();//get a string of the above format
		formatter.close();//close the formatter 
		return str.trim();//retuen the formatted date string
	}


//method to set the exit time stamp
//Used when the second weight is being recorded
	public void setExitTimeStamp() {
		exitTimeStamp = getTimeStamp();
	}
}
