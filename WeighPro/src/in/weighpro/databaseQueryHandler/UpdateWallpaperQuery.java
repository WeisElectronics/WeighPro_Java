/*
* This class provides the database query
* used to update the wallpaper and the company name 
* in the settings table of the database

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/

package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateWallpaperQuery {
	
	//Constructor to initialize the database connection and execute the update settings query
	public UpdateWallpaperQuery(String wallpaper,String company)
			throws SQLException {//takes wallpaper name and the company name as the arguments
		String queryString = "UPDATE settings SET wallpaper=?,company=?";//creating the query to update the wallpaper and company name
		DatabaseConnection connection = new DatabaseConnection();//initializing the database connection
		PreparedStatement prepStat = connection.getConnection()//initializing the prepared statement that will execute the query
				.prepareStatement(queryString);
		prepStat.setString(1, wallpaper);//set the wallpaper
		prepStat.setString(2, company);//set the company name
		prepStat.executeUpdate();//execute the query
	}
}
