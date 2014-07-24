/*
* This class provides the database query
* used to get the current wallpaper from the database

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentWallpaperRetriever {

	private String wallpaper;
	private String company;

	
	//Constructor to initialize the connection and execute the query
	public CurrentWallpaperRetriever() throws SQLException {
		String queryString = "SELECT wallpaper,company FROM settings";//settings table stores the wallpaper and the company name
		DatabaseConnection connection = new DatabaseConnection();//Initializing the database connection
		PreparedStatement prepStat = connection.getConnection()//Creating the prepared statement that will execute the query
				.prepareStatement(queryString);
		ResultSet rs = prepStat.executeQuery();//result set to store the data retrieved
		if (rs.next()) {
			wallpaper = rs.getString(1);//getting the current wallpaper
			company = rs.getString(2);//getting the current company name
		} else {//Setting the default values if the table is empty
			wallpaper = "bg1.jpg";
			company = "Weis Electronics Pvt. Ltd.";
			queryString = "INSERT INTO settings VALUES (?,?)";//query to retrieve the data
			prepStat = connection.getConnection().prepareStatement(queryString);//prepared statement from the query
			prepStat.setString(1, wallpaper);
			prepStat.setString(2, company);
			prepStat.executeUpdate();//execute the query (insertion of default values of wallpaper and the company)
			
		}
	}

	
	//method to return the current wallpaper
	public String getWallpaper() {
		return wallpaper;
	}

	
	//method to return the current company name
	public String getCompany() {
		return company;
	}
}
