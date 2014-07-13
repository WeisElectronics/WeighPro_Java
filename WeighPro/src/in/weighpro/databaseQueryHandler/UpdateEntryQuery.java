package in.weighpro.databaseQueryHandler;

import in.weighpro.dataComponents.TimeDetails;
import in.weighpro.dataComponents.Weight;
import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateEntryQuery {

	public UpdateEntryQuery(int index, Weight weight, TimeDetails time)
			throws SQLException {
		String queryString = "UPDATE entries SET tareWeight=?,grossWeight=?,netWeight=?,exitTimeStamp=?,status=?,incomingType=? WHERE ID=?";
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);
		prepStat.setInt(1, weight.getTareWeight());
		prepStat.setInt(2, weight.getGrossWeight());
		prepStat.setInt(3, weight.getNetWeight());
		prepStat.setString(4, time.getExitTimeStamp());
		prepStat.setBoolean(5, true);
		prepStat.setBoolean(6, weight.getIncomingType());
		prepStat.setInt(7, index);
		prepStat.executeUpdate();
	}
}
