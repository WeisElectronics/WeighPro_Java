package in.weighpro.databaseQueryHandler;

import in.weighpro.dataComponents.Customer;
import in.weighpro.dataComponents.Material;
import in.weighpro.dataComponents.TimeDetails;
import in.weighpro.dataComponents.Vehicle;
import in.weighpro.dataComponents.Weight;
import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertEntryQuery {
	private int index;

	public InsertEntryQuery(Vehicle vehicle, Customer customer,
			Material material, int amount, Weight weight, TimeDetails time)
			throws SQLException {
		String query = "INSERT INTO entries (vehicleID,customerID,materialID,tareWeight,grossWeight,netWeight,amount,entryTimeStamp,exitTimeStamp,status,incomingType)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(query);
		prepStat.setInt(1, vehicle.getIndex());
		prepStat.setInt(2, customer.getIndex());
		prepStat.setInt(3, material.getIndex());
		prepStat.setInt(4, weight.getTareWeight());
		prepStat.setInt(5, weight.getGrossWeight());
		prepStat.setInt(6, weight.getNetWeight());
		prepStat.setInt(7, amount);
		prepStat.setString(8, time.getEntryTimeStamp());
		prepStat.setString(9, time.getExitTimeStamp());
		prepStat.setBoolean(10, false);
		prepStat.setBoolean(11, weight.getIncomingType());
		prepStat.executeUpdate();

		query = "SELECT ID FROM entries WHERE entryTimeStamp = ?";
		prepStat = connection.getConnection().prepareStatement(query);
		prepStat.setString(1, time.getEntryTimeStamp());
		ResultSet rs = prepStat.executeQuery();
		if (rs.next())
			index = rs.getInt(1);
	}

	public int getIndex() {
		return index;
	}

}
