package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveEntryQuery {
	private int index=-1;
	private int amount;
	private int customerID;
	private String entryTimeStamp;
	private String exitTimeStamp;
	private int grossWeight;
	private boolean incomingType;
	private int materialID;
	private int netWeight;
	private boolean status;
	private int tareWeight;
	private int vehicleID;

	public RetrieveEntryQuery(int index) throws SQLException {
		String queryString = "SELECT vehicleID,customerID,materialID,tareWeight,grossWeight,netWeight,amount,entryTimeStamp,exitTimeStamp,status,incomingType FROM entries WHERE ID=?";
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);
		prepStat.setInt(1, index);
		ResultSet rs = prepStat.executeQuery();
		if (rs.next()) {
			this.index = index;
			vehicleID = rs.getInt(1);
			customerID = rs.getInt(2);
			materialID = rs.getInt(3);
			tareWeight = rs.getInt(4);
			grossWeight = rs.getInt(5);
			netWeight = rs.getInt(6);
			amount = rs.getInt(7);
			entryTimeStamp = rs.getString(8);
			exitTimeStamp = rs.getString(9);
			status = rs.getBoolean(10);
			incomingType = rs.getBoolean(11);
		}
	}

	public int getAmount() {
		return amount;
	}

	public int getCustomerIndex() {
		return customerID;
	}

	public String getEntryTimeStamp() {
		return entryTimeStamp;
	}

	public String getExitTimeStamp() {
		return exitTimeStamp;
	}

	public int getGrossWeight() {
		return grossWeight;
	}

	public boolean getIncomingType() {
		return incomingType;
	}

	public int getMaterialIndex() {
		return materialID;
	}

	public int getNetWeight() {
		return netWeight;
	}

	public boolean getStatus() {
		return status;
	}

	public int getTareWeight() {
		return tareWeight;
	}

	public int getVehicleIndex() {
		return vehicleID;
	}

	public int getIndex(){
		return this.index;
	}
}
