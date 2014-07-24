/*
* This class provides the database query
* used to get the index corresponding to 
* the given value from the database

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataValueQuery {

	private int index = -1;

	//constructor to initialize the database connection and execute the query
	public DataValueQuery(String type, String value) throws SQLException {//Takes the type and value as the arguments
																		//can throw an SQL Exception due to the database handling
		String queryString = "INSERT INTO "
				+ type
				+ " (value) SELECT * FROM (SELECT ?) AS tmp WHERE NOT EXISTS (SELECT value FROM "
				+ type + " WHERE value = ?) LIMIT 1";//creating the database query
		DatabaseConnection connection = new DatabaseConnection();//initializing the connection
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);//initializing the prepared statement that will be used to execute the query
		prepStat.setString(1, value);//inserting the value in the prepared statement
		prepStat.setString(2, value);
		prepStat.executeUpdate();
		queryString = "SELECT ID FROM " + type + " WHERE value = ?";//getting id from the database corresponding to the value
		prepStat = connection.getConnection().prepareStatement(queryString);
		prepStat.setString(1, value);//inserting the value in the prepared statement
		ResultSet rs = prepStat.executeQuery();//Result set to store the retrieved values
		if (rs.next())
			index = rs.getInt(1);//getting the retrieved index

	}

	//method to get the index corresponding to the value
	public int getIndex() {
		return index;
	}

}
