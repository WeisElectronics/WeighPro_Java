/* This class pulls
 * the verification data from
 * the database
 * 
 * Made for WeighPro
 * Author Weis Electonics Pvt. Ltd.
 * Contribution by emdroidery
 */

package in.weighpro.verificationHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UniqueIDVerifier {

	private String uniqueID = "";

	// constructor to initialize the database connection and executes the query
	public UniqueIDVerifier() throws SQLException {// may throw an SQL Exception
													// because of the database
													// functionality
		String queryString = "SELECT address FROM verification";// create query
																// for pulling
																// the
																// verification
																// address
		DatabaseConnection connection = new DatabaseConnection();// initialize
																	// the
																	// database
																	// connection
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);// initialize the prepared
												// statement to perform the
												// query
		ResultSet rs = prepStat.executeQuery();// result set to hold the
												// retrieved value
		if (rs.next()) {
			uniqueID = rs.getString(1); // store the verification handler in
										// uniqueID
		}
	}

	//constructor to get the unique id
	public String getUniqueID() {
		return this.uniqueID;
	}
}
