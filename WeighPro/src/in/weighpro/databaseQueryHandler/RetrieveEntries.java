package in.weighpro.databaseQueryHandler;

import in.weighpro.dataComponents.Customer;
import in.weighpro.dataComponents.Material;
import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RetrieveEntries {
	private int[] entryIndices;
	private PreparedStatement prepStat ;

	public RetrieveEntries(String startDate, String endDate)
			throws SQLException {
	    String	queryString = "SELECT ID FROM entries where entryTimeStamp>=? and exitTimeStamp<=?";
		DatabaseConnection connection = new DatabaseConnection();
		prepStat = connection.getConnection()
				.prepareStatement(queryString);
		prepStat.setString(1, startDate);
		prepStat.setString(2, endDate);
		commonProcedure();
		
	}
	
	public RetrieveEntries(String startDate, String endDate,Customer customer)
			throws SQLException {
		String	queryString = "SELECT ID FROM entries where entryTimeStamp>=? and exitTimeStamp<=? and customerID=?";
		DatabaseConnection connection = new DatabaseConnection();
		prepStat = connection.getConnection()
				.prepareStatement(queryString);
		prepStat.setString(1, startDate);
		prepStat.setString(2, endDate);
		prepStat.setInt(3, customer.getIndex());
		commonProcedure();
		
	}
	
	
	public RetrieveEntries(String startDate, String endDate,Material material)
			throws SQLException {
		String	queryString = "SELECT ID FROM entries where entryTimeStamp>=? and exitTimeStamp<=? and materialID=?";
		DatabaseConnection connection = new DatabaseConnection();
		prepStat = connection.getConnection()
				.prepareStatement(queryString);
		prepStat.setString(1, startDate);
		prepStat.setString(2, endDate);
		prepStat.setInt(3, material.getIndex());
		commonProcedure();
		
	}
	
	
	public RetrieveEntries(String startDate, String endDate,Customer customer,Material material)
			throws SQLException {
		String	queryString = "SELECT ID FROM entries where entryTimeStamp>=? and exitTimeStamp<=? and customerID=? and materialID=?";
		DatabaseConnection connection = new DatabaseConnection();
		prepStat = connection.getConnection()
				.prepareStatement(queryString);
		prepStat.setString(1, startDate);
		prepStat.setString(2, endDate);
		prepStat.setInt(3, customer.getIndex());
		prepStat.setInt(4, material.getIndex());
		commonProcedure();
		
	}
	
	

	private void commonProcedure() throws SQLException {

		
		ResultSet rs = prepStat.executeQuery();
		rs.last();
		entryIndices = new int[rs.getRow()];
		rs.beforeFirst();
		int i = 0;
		while (rs.next()) {
			entryIndices[i++] = rs.getInt(1);
		}

	}

	public int[] getEntries() {
		return this.entryIndices;
	}
}
