/*
* This class provides the database query
* used to get the count of the items of specific type of data 
* present in the database(vehicle numbers,customers,materials)

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataCountQuery {

	private int count = -1;

	//Constructor to initialize the database connection and execute the query
	public DataCountQuery(String type) throws SQLException {//Takes 'vehicle','customer,'material' as an argument 
															//may throw an SQL Exception due to the database handling
		String queryString = "SELECT COUNT(*) FROM " + type;//creating the query
		DatabaseConnection connection = new DatabaseConnection();//initializing the database connection
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);//creating a prepared statement to execute the query
		ResultSet rs = prepStat.executeQuery();//result set to store the retrieved data
		if (rs.next())
			count = rs.getInt(1);//getting the count

	}

	//method to return the count of the specific data
	public int getCount() {
		return count;
	}

}
