package in.weighpro.verificationHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VerifierCreation {
	public VerifierCreation(String address) throws SQLException{
		String queryString = "CREATE TABLE verification(address VARCHAR(255) NOT NULL)";
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);
		prepStat.executeUpdate();
		queryString = "INSERT INTO verification VALUES (?)";
		prepStat = connection.getConnection().prepareStatement(queryString);
		prepStat.setString(1, address);
		prepStat.executeUpdate();
	}
}
