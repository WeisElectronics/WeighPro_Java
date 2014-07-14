/*
* This class initializes the database 
* by creating the database if it does not exist
* and creating all the necessary tables needed for the 
* application to work

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/



package in.weighpro.databaseConstructs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitializer {

	public static boolean initialized = false;//flag to track whether the database and corresponding table have been
						  //initialized

//Constructor to create all the SQL queries and perform respective actions on the database

	public DatabaseInitializer(Connection connection, String databaseName)
			throws SQLException {
		
		//These statements create the database if it does not exist
		PreparedStatement preparedStatement = connection
				.prepareStatement("CREATE DATABASE IF NOT EXISTS "
						+ databaseName);
		preparedStatement.executeUpdate();

		//using the database
		preparedStatement = connection.prepareStatement("USE " + databaseName);
		preparedStatement.executeQuery();

		//These statements create the table for vehicle entries if it does not exist
		String query = "CREATE TABLE IF NOT EXISTS vehicles (ID INT NOT NULL AUTO_INCREMENT,value VARCHAR(255) NOT NULL,PRIMARY KEY(ID))";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();

		//These statements create the table for customer entries if it does not exist
		query = "CREATE TABLE IF NOT EXISTS customers (ID INT NOT NULL AUTO_INCREMENT,value VARCHAR(255) NOT NULL,PRIMARY KEY(ID))";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();


		//These statements create the table for material entries if it does not exist
		query = "CREATE TABLE IF NOT EXISTS materials (ID INT NOT NULL AUTO_INCREMENT,value VARCHAR(255) NOT NULL,PRIMARY KEY(ID))";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();


		//These statements create the main entries table if it does not exist
		query = "CREATE TABLE IF NOT EXISTS entries(ID INT NOT NULL AUTO_INCREMENT,vehicleID INT NOT NULL,customerID INT NOT NULL,materialID INT NOT NULL,tareWeight INT NOT NULL,grossWeight INT NOT NULL,netWeight INT NOT NULL,amount INT NOT NULL,entryTimeStamp DATETIME NOT NULL,exitTimeStamp DATETIME NOT NULL,status BIT NOT NULL,incomingType BIT NOT NULL,PRIMARY KEY (ID),FOREIGN KEY (vehicleID) REFERENCES vehicles(ID),FOREIGN KEY (customerID) REFERENCES customers(ID),FOREIGN KEY (materialID) REFERENCES materials(ID))";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();


		//These statements create a table to store the serial port settings if it does not exist
		query = "CREATE TABLE IF NOT EXISTS serialSettings ("
				+ "ID INT NOT NULL," + "port VARCHAR(30) NOT NULL,"
				+ "baudRate INT NOT NULL," + "dataBits INT NOT NULL,"
				+ "stopBits INT NOT NULL," + "parity INT NOT NULL,"
				+ "flowControl INT NOT NULL" + ")";
		preparedStatement = connection.prepareStatement(query);

		preparedStatement.executeUpdate();


		//These statements create a table for storing the appearance settings if it does not exist
		//The current wallpaper and company name
		query = "CREATE TABLE IF NOT EXISTS settings (wallpaper VARCHAR(255) NOT NULL,company VARCHAR(255) NOT NULL);";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();

		initialized = true;//successfully created all the tables hence setting the database initialized flag to true
	}
}
