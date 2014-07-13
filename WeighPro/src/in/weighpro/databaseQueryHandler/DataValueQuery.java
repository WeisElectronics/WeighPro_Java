package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataValueQuery {

	private int index = -1;

	public DataValueQuery(String type, String value) throws SQLException {
		String queryString = "INSERT INTO "
				+ type
				+ " (value) SELECT * FROM (SELECT ?) AS tmp WHERE NOT EXISTS (SELECT value FROM "
				+ type + " WHERE value = ?) LIMIT 1";
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);
		prepStat.setString(1, value);
		prepStat.setString(2, value);
		prepStat.executeUpdate();
		queryString = "SELECT ID FROM " + type + " WHERE value = ?";
		prepStat = connection.getConnection().prepareStatement(queryString);
		prepStat.setString(1, value);
		ResultSet rs = prepStat.executeQuery();
		if (rs.next())
			index = rs.getInt(1);

	}

	public int getIndex() {
		return index;
	}

}
