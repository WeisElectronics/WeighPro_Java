/*
* This class provides the database query
* used to get the entry corresponding to the 
* index passed

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveEntryQuery {
	private int index=-1;
	private int amount;
	private int customerID;
	private String entryTimeStamp;
	private String exitTimeStamp;
	private int grossWeight;
	private boolean incomingType;
	private int materialID;
	private int netWeight;
	private boolean status;
	private int tareWeight;
	private int vehicleID;

	//constructor to initialize the database connection and retrieve the entry
	public RetrieveEntryQuery(int index) throws SQLException {//Takes the index as the argument
		//may throw an SQL Exception due to the database functions
		//Creating the query to retrieve the entry from the database
		String queryString = "SELECT vehicleID,customerID,materialID,tareWeight,grossWeight,netWeight,amount,entryTimeStamp,exitTimeStamp,status,incomingType FROM entries WHERE ID=?";
		DatabaseConnection connection = new DatabaseConnection();//initializing the database connection
		PreparedStatement prepStat = connection.getConnection()//initializing the prepared statement that will execute the query
				.prepareStatement(queryString);
		prepStat.setInt(1, index);//setting the index in the prepared statement
		ResultSet rs = prepStat.executeQuery();//execute the query and store the retrieved entries in the result set
		if (rs.next()) {//iterating through the result set and retrieving the data
			this.index = index;
			vehicleID = rs.getInt(1);//getting the vehicle id
			customerID = rs.getInt(2);//getting the customer id
			materialID = rs.getInt(3);//getting the material id
			tareWeight = rs.getInt(4);//getting the tare weight
			grossWeight = rs.getInt(5);//getting the gross weight
			netWeight = rs.getInt(6);//getting the net weight
			amount = rs.getInt(7);//getting the amount
			entryTimeStamp = rs.getString(8);//getting the entry time stamp
			exitTimeStamp = rs.getString(9);//getting the exit time stamp
			status = rs.getBoolean(10);//getting the status of the entry
			incomingType = rs.getBoolean(11);//getting the incoming type
		}
	}

	
	//method to get the amount
	public int getAmount() {
		return amount;
	}

	//method to get the customer index
	public int getCustomerIndex() {
		return customerID;
	}

	//method ot get the entry time stamp
	public String getEntryTimeStamp() {
		return entryTimeStamp;
	}

	//method to get the exit time stamp
	public String getExitTimeStamp() {
		return exitTimeStamp;
	}

	//method to get the gross weight
	public int getGrossWeight() {
		return grossWeight;
	}

	//method to get the incoming type
	public boolean getIncomingType() {
		return incomingType;
	}

	//method to get the material index
	public int getMaterialIndex() {
		return materialID;
	}

	//method to get the net weight
	public int getNetWeight() {
		return netWeight;
	}

	//method to get the status
	public boolean getStatus() {
		return status;
	}

	//method to get the tare weight
	public int getTareWeight() {
		return tareWeight;
	}

	//method to get the vehicle index
	public int getVehicleIndex() {
		return vehicleID;
	}

	//method to get the index of the entry
	public int getIndex(){
		return this.index;
	}
}
