/*
* This class provides the database query
* used to update the entry corresponding to the
* passed index

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.databaseQueryHandler;

import in.weighpro.dataComponents.TimeDetails;
import in.weighpro.dataComponents.Weight;
import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEntryQuery {

	//constructor to initialize the database given the index and second weight and exit time
	public UpdateEntryQuery(int index, Weight weight, TimeDetails time)
			throws SQLException {//may throw an SQL Exception because of the database functions
		//creating the query to update the entry 
		String queryString = "UPDATE entries SET tareWeight=?,grossWeight=?,netWeight=?,exitTimeStamp=?,status=?,incomingType=? WHERE ID=?";
		DatabaseConnection connection = new DatabaseConnection();//initializing the database connection
		PreparedStatement prepStat = connection.getConnection()//initializing the prepared statement that will execute the query
				.prepareStatement(queryString);
		prepStat.setInt(1, weight.getTareWeight());//setting the tare weight
		prepStat.setInt(2, weight.getGrossWeight());//setting the gross weight
		prepStat.setInt(3, weight.getNetWeight());//setting the net weight
		prepStat.setString(4, time.getExitTimeStamp());//setting the exit time stamp
		prepStat.setBoolean(5, true);//setting the status
		prepStat.setBoolean(6, weight.getIncomingType());//setting the incoming type
		prepStat.setInt(7, index);//setting the index
		prepStat.executeUpdate();//execute the query
	}
}
