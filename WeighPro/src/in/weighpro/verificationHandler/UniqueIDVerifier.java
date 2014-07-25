package in.weighpro.verificationHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UniqueIDVerifier {
	
	private String uniqueID="";
	
public UniqueIDVerifier() throws SQLException{
	String queryString = "SELECT address FROM verification";
	DatabaseConnection connection = new DatabaseConnection();
	PreparedStatement prepStat = connection.getConnection()
			.prepareStatement(queryString);
	ResultSet rs = prepStat.executeQuery();
			if(rs.next()){
				uniqueID = rs.getString(1); 
			}
}

public String getUniqueID(){
	return this.uniqueID;
}
}
