/*
* This class provides the database query
* used to insert an entry 
* in the database

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.databaseQueryHandler;

import in.weighpro.dataComponents.Customer;
import in.weighpro.dataComponents.Material;
import in.weighpro.dataComponents.TimeDetails;
import in.weighpro.dataComponents.Vehicle;
import in.weighpro.dataComponents.Weight;
import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertEntryQuery {
	private int index;

	//constructor to initialize the database connection and execute the insert in the database
	public InsertEntryQuery(Vehicle vehicle, Customer customer,
			Material material, int amount, Weight weight, TimeDetails time)
			throws SQLException {//Takes vehicle,customer,material,amount,weight,and time objects as the arguments
		//May throw an SQL Exception due to the database handling
		//creating the query to insert the data in the database
		String query = "INSERT INTO entries (vehicleID,customerID,materialID,tareWeight,grossWeight,netWeight,amount,entryTimeStamp,exitTimeStamp,status,incomingType)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		DatabaseConnection connection = new DatabaseConnection();//Initializing the database connection 
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(query);//initialing the prepared statement that will be used to execute the query
		prepStat.setInt(1, vehicle.getIndex());//setting the vehicle index
		prepStat.setInt(2, customer.getIndex());//setting the customer index
		prepStat.setInt(3, material.getIndex());//setting the material index
		prepStat.setInt(4, weight.getTareWeight());//setting the tare weight
		prepStat.setInt(5, weight.getGrossWeight());//setting the gross weight
		prepStat.setInt(6, weight.getNetWeight());//setting the net weight
		prepStat.setInt(7, amount);//setting the amount
		prepStat.setString(8, time.getEntryTimeStamp());//setting the entry time stamp
		prepStat.setString(9, time.getExitTimeStamp());//setting the exit time stamp
		prepStat.setBoolean(10, false);//setting the status
		prepStat.setBoolean(11, weight.getIncomingType());//setting the incoming type
		prepStat.executeUpdate();//update the database

		query = "SELECT ID FROM entries WHERE entryTimeStamp = ?";//getting the id of the inserted entry
		prepStat = connection.getConnection().prepareStatement(query);//initializing the prepared statement that will be used to retrieve the id 
		prepStat.setString(1, time.getEntryTimeStamp());//the query has the timestamp as the criteria for selection
		ResultSet rs = prepStat.executeQuery();//result set to store the retrieved entries
		if (rs.next())
			index = rs.getInt(1);//getting the index from the retrieved data stored in the result set
	}

	
	//method to get the index of the inserted entry
	public int getIndex() {
		return index;
	}

}
