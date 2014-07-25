/* This class handles the
 * creation of database 
 * table for holding the verification
 * details

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/


package in.weighpro.verificationHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VerifierCreation {
	
	//constructor which inserts the address passed in the table 
	public VerifierCreation(String address) throws SQLException{//takes the address as an argument and can throw an SQL Exception because of the database functionality
		String queryString = "CREATE TABLE verification(address VARCHAR(255) NOT NULL)";//creating the query for creating the table
		DatabaseConnection connection = new DatabaseConnection();//initializing the database connection
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);//initializing the prepared statement that will be used to execute the query
		prepStat.executeUpdate();//execute the query
		queryString = "INSERT INTO verification VALUES (?)";//create query to insert value into the verification table
		prepStat = connection.getConnection().prepareStatement(queryString);//initialize the prepared statement to execute the query
		prepStat.setString(1, address);//set the address 
		prepStat.executeUpdate();//execute the query
	}
}
