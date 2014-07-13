package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncompleteEntriesRetriever {
	int[] incompleteIndices;

	public IncompleteEntriesRetriever() throws SQLException {
		String queryString = "SELECT ID FROM entries WHERE status=?";
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);
		prepStat.setBoolean(1, false);
		ResultSet rs = prepStat.executeQuery();
		rs.last();
		incompleteIndices = new int[rs.getRow()];
		rs.beforeFirst();
		int i = 0;
		while(rs.next()) {
			incompleteIndices[i++] = rs.getInt(1);
		}

	}
	
	public int[] getIncompleteEntries(){
		return this.incompleteIndices;
	}
}
