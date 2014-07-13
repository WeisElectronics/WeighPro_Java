package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataIndexQuery {

	private String value;

	public DataIndexQuery(String type, int index) throws SQLException {
		String queryString = "SELECT value FROM " + type + " WHERE ID=?";
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);
		prepStat.setInt(1, index);
		ResultSet rs = prepStat.executeQuery();
		if (rs.next())
			value = rs.getString(1);

	}

	public String getValue() {
		return this.value;
	}

}
