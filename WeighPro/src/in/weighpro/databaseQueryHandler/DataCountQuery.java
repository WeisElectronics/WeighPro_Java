package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataCountQuery {

	private int count = -1;

	public DataCountQuery(String type) throws SQLException {
		String queryString = "SELECT COUNT(*) FROM " + type;
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);
		ResultSet rs = prepStat.executeQuery();
		if (rs.next())
			count = rs.getInt(1);

	}

	public int getCount() {
		return count;
	}

}
