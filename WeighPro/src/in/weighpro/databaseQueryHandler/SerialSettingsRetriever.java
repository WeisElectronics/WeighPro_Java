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

	public SerialSettingsRetriever() throws SQLException {
		String queryString = "SELECT * FROM serialSettings WHERE ID=1";
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);
		ResultSet rs = prepStat.executeQuery();
		if (rs.next()) {

			serialPortName = rs.getString(2);
			baudRate = rs.getInt(3);
			dataBits = rs.getInt(4);
			stopBits = rs.getInt(5);
			parity = rs.getInt(6);
			flowControl = rs.getInt(7);

		} else {
			queryString = "INSERT INTO serialSettings VALUES (1,'/dev/tty/USB0',9600,8,1,0,0)";
			prepStat = connection.getConnection().prepareStatement(queryString);
			prepStat.executeUpdate();
			serialPortName = "/dev/tty/USB0";
			baudRate = 9600;
			dataBits = SerialPort.DATABITS_8;
			stopBits = SerialPort.STOPBITS_1;
			parity = SerialPort.PARITY_NONE;
			flowControl = SerialPort.FLOWCONTROL_NONE;
		}
	}
	
	public int getBaudRate(){
		return baudRate;
	}
	public String getSerialPortName(){
		return serialPortName;
	}
	public int getDataBits(){
		return dataBits;
	}
	public int getStopBits(){
		return stopBits;
	}
	
	public int getParity(){
		return parity;
	}
	
	public int getFlowControl(){
		return flowControl;
	}
}
