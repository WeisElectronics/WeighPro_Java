/*
* This class provides the database query
* used to get the serial settings from the 
* database

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.databaseQueryHandler;

import gnu.io.SerialPort;
import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SerialSettingsRetriever {

	private String serialPortName;
	private int baudRate;
	private int dataBits;
	private int stopBits;
	private int parity;
	private int flowControl;

	//constructor to initialize the database connection and retrieve the serial settings from the database
	public SerialSettingsRetriever() throws SQLException {//may throw an SQL Exception because of database functions
		String queryString = "SELECT * FROM serialSettings WHERE ID=1";//creating the query
		DatabaseConnection connection = new DatabaseConnection();//initializing the database connection
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);//initializing the prepared statement that will used to retrieve data from the database
		ResultSet rs = prepStat.executeQuery();//result set to store the retrieved data after executing the query 
		if (rs.next()) {//iterating through the result set

			serialPortName = rs.getString(2);//getting the serial port name
			baudRate = rs.getInt(3);//getting the baud rate
			dataBits = rs.getInt(4);//getting the data bits
			stopBits = rs.getInt(5);//getting the stop bits
			parity = rs.getInt(6);//getting the parity
			flowControl = rs.getInt(7);//getting the flow control

		} else {//if no entry is present in the database then put the default values
			queryString = "INSERT INTO serialSettings VALUES (1,'/dev/tty/USB0',9600,8,1,0,0)";//creating the query to insert the default values in the database
			prepStat = connection.getConnection().prepareStatement(queryString);//initializing the prepared statement that will execute the query
			prepStat.executeUpdate();//execute the query
			serialPortName = "/dev/tty/USB0";//default port name
			baudRate = 9600;//default baud rate
			dataBits = SerialPort.DATABITS_8;//default data bits
			stopBits = SerialPort.STOPBITS_1;//default stop bits
			parity = SerialPort.PARITY_NONE;//default parity
			flowControl = SerialPort.FLOWCONTROL_NONE;//default flow control
		}
	}
	
	//method to get the baud rate
	public int getBaudRate(){
		return baudRate;
	}
	
	//method to get the serial port name
	public String getSerialPortName(){
		return serialPortName;
	}
	
	//method to get the data bits
	public int getDataBits(){
		return dataBits;
	}
	
	//method to get the stop bits
	public int getStopBits(){
		return stopBits;
	}
	
	//method to get the parity
	public int getParity(){
		return parity;
	}
	
	//method to get the flow control
	public int getFlowControl(){
		return flowControl;
	}
}
