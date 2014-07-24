/*
* This class provides the database query
* used to get all the available data(vehicle number, customer and material) from the database

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/



package in.weighpro.databaseQueryHandler;

import in.weighpro.dataComponents.DataObject;
import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DataArrayRetriever {
	
	DataObject[] items;
	
	//Constructor to initialize the connection and execute the query
	public DataArrayRetriever(String type) throws SQLException{//may throw an SQL Exception due to the database handling
		String queryString = "SELECT value FROM "+type;//type specifies the table from which data is to be extracted
		DatabaseConnection connection = new DatabaseConnection();//getting the database connection
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);//creating a prepared statement
		ResultSet rs = prepStat.executeQuery();//result set to store the retrieved values
		rs.last();//move to the last row to get the count
		items = new DataObject[rs.getRow()];//initialize the array to be returned
		rs.beforeFirst();//move to initial row
		int i = 0;
		while(rs.next()) {
			items[i++] = new DataObject(type,rs.getString(1));//get data from the result set and store it in the array
		}
	}
	
	
	//method to return the array that contains the data items
	public DataObject[] getItems(){
		return this.items;
	}

}
