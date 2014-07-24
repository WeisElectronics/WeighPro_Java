/*
* This class provides the database query
* used to get the incomplete entries
*  from the database

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncompleteEntriesRetriever {
	int[] incompleteIndices;

	//Constructor to initialize the database connection and retrieve the incomplete entries
	public IncompleteEntriesRetriever() throws SQLException {//may throw an SQL Exception because of database handling
		String queryString = "SELECT ID FROM entries WHERE status=?";//Creating the database query
		DatabaseConnection connection = new DatabaseConnection();//initializing the database connection
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);//Initializing the prepared statement to execute the query
		prepStat.setBoolean(1, false);//to get entries where status is false(incomplete)
		ResultSet rs = prepStat.executeQuery();//Result set to store the retrieved entries
		rs.last();//move to the last row to get the count
		incompleteIndices = new int[rs.getRow()];//initializing an array to store the incomplete entries
		rs.beforeFirst();//move to the row before the first row
		int i = 0;
		while(rs.next()) {//iterating through the result set to store the entries in the array
			incompleteIndices[i++] = rs.getInt(1);//getting the indices of the incomplete entries
		}

	}
	
	//method to get the array containing the indices of the incomplete entries
	public int[] getIncompleteEntries(){
		return this.incompleteIndices;
	}
}
