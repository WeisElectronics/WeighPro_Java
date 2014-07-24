/*
* This class provides the database query
* used to get the value corresponding to 
* the given index from the database

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataIndexQuery {

	private String value;

	//constructor to initialize the database connection and execute the query
	public DataIndexQuery(String type, int index) throws SQLException {//Takes the type and index as the arguments
																		//can throw an SQL Exception due to the database handling
		String queryString = "SELECT value FROM " + type + " WHERE ID=?";//creating the database query
		DatabaseConnection connection = new DatabaseConnection();//initializing the connection
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);//initializing the prepared statement that will be used to execute the query
		prepStat.setInt(1, index);//inserting the index in the prepared statement
		ResultSet rs = prepStat.executeQuery();//Result set to store the retrieved values
		if (rs.next())
			value = rs.getString(1);//getting the retrieved value

	}

	
	//method to get the value of the data type corresponding to the index
	public String getValue() {
		return this.value;
	}

}
