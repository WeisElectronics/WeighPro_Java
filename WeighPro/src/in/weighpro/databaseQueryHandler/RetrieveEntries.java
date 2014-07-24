/*
* This class provides the database query
* used to get the entries corresponding to the 
* constraints mentioned before retrieving reports

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

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

	//constructor to initialize the database connection and execute the retrieval query if only dates are provided
	public RetrieveEntries(String startDate, String endDate)
			throws SQLException {//takes the start date and the end date
	    String	queryString = "SELECT ID FROM entries where entryTimeStamp>=? and exitTimeStamp<=?";//creating the query to retrieve indices of the entries between the given dates
		DatabaseConnection connection = new DatabaseConnection();//initializing the database connection
		prepStat = connection.getConnection()
				.prepareStatement(queryString);//initializing the prepared statement that will be used to execute the query
		prepStat.setString(1, startDate);//setting the start date
		prepStat.setString(2, endDate);//setting the end date
		commonProcedure();//this is the common method that will be called in all cases
		
	}
	
	//constructor to initialize the database connection and execute the retrieval query if dates and customer is mentioned
	public RetrieveEntries(String startDate, String endDate,Customer customer)
			throws SQLException {//takes the startdate end date and customer as the arguments
		//may throw an SQL Exception because of the database handling
		String	queryString = "SELECT ID FROM entries where entryTimeStamp>=? and exitTimeStamp<=? and customerID=?";//creating the query to retrieve the data
		DatabaseConnection connection = new DatabaseConnection();//initializing the database connection
		prepStat = connection.getConnection()//initializing the prepared statement that will be used to retrieve the data from the database using the constraints
				.prepareStatement(queryString);
		prepStat.setString(1, startDate);//setting the start date
		prepStat.setString(2, endDate);//setting the end date
		prepStat.setInt(3, customer.getIndex());//setting the customer index
		commonProcedure();//calling the common method 
		
	}
	
	//constructor to initialize the database connection and execute the retrieval query if dates and material is mentioned
	public RetrieveEntries(String startDate, String endDate,Material material)
			throws SQLException {//takes the startdate end date and material as the arguments
		//may throw an SQL Exception because of the database handling
		String	queryString = "SELECT ID FROM entries where entryTimeStamp>=? and exitTimeStamp<=? and materialID=?";//creating the query to retrieve the data
		DatabaseConnection connection = new DatabaseConnection();//initializing the database connection
		prepStat = connection.getConnection()//initializing the prepared statement that will be used to retrieve the data from the database using the constraints
				.prepareStatement(queryString);
		prepStat.setString(1, startDate);//setting the start date
		prepStat.setString(2, endDate);;//setting the end date
		prepStat.setInt(3, material.getIndex());//setting the material index
		commonProcedure();//calling the common method 
		
	}
	
	
	//constructor to initialize the database connection and execute the retrieval query if dates,customer and material is mentioned
	public RetrieveEntries(String startDate, String endDate,Customer customer,Material material)
			throws SQLException {//takes the startdate end date,customer and material as the arguments
		//may throw an SQL Exception because of the database handling
		String	queryString = "SELECT ID FROM entries where entryTimeStamp>=? and exitTimeStamp<=? and customerID=? and materialID=?";//creating the query to retrieve the data
		DatabaseConnection connection = new DatabaseConnection();//initializing the database connection
		prepStat = connection.getConnection()
				.prepareStatement(queryString);//initializing the prepared statement that will be used to retrieve the data from the database using the constraints
		prepStat.setString(1, startDate);//setting the start date
		prepStat.setString(2, endDate);//setting the end date
		prepStat.setInt(3, customer.getIndex());//setting the customer index
		prepStat.setInt(4, material.getIndex());//setting the material index
		commonProcedure();//calling the common method 
		
	}
	
	
//common method that is called in all of the above constructors which packages the retrieved entries into an array of their indices
	private void commonProcedure() throws SQLException {//may throw an SQL Exception since it handles database functions

		
		ResultSet rs = prepStat.executeQuery();//result set to store the retrieved entries
		rs.last();//moving to the last line to get the count of the retrieved entries
		entryIndices = new int[rs.getRow()];//initializing the array which will store the indices of the retrieved entries
		rs.beforeFirst();//moving it to the row before the first row
		int i = 0;
		while (rs.next()) {//iterating through the result set and storing the retrieved indices in the array
			entryIndices[i++] = rs.getInt(1);//getting the index from the result set
		}

	}

	//method to get the array of the retrieved indices
	public int[] getEntries() {
		return this.entryIndices;
	}
}
