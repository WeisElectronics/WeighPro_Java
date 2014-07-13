package in.weighpro.databaseConstructs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseInitializer {

	public static boolean initialized = false;

	public DatabaseInitializer(Connection connection, String databaseName)
			throws SQLException {
		PreparedStatement preparedStatement = connection
				.prepareStatement("CREATE DATABASE IF NOT EXISTS "
						+ databaseName);
		preparedStatement.executeUpdate();

		preparedStatement = connection.prepareStatement("USE " + databaseName);
		preparedStatement.executeQuery();

		String query = "CREATE TABLE IF NOT EXISTS vehicles (ID INT NOT NULL AUTO_INCREMENT,value VARCHAR(255) NOT NULL,PRIMARY KEY(ID))";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();

		query = "CREATE TABLE IF NOT EXISTS customers (ID INT NOT NULL AUTO_INCREMENT,value VARCHAR(255) NOT NULL,PRIMARY KEY(ID))";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();

		query = "CREATE TABLE IF NOT EXISTS materials (ID INT NOT NULL AUTO_INCREMENT,value VARCHAR(255) NOT NULL,PRIMARY KEY(ID))";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();

		query = "CREATE TABLE IF NOT EXISTS entries(ID INT NOT NULL AUTO_INCREMENT,vehicleID INT NOT NULL,customerID INT NOT NULL,materialID INT NOT NULL,tareWeight INT NOT NULL,grossWeight INT NOT NULL,netWeight INT NOT NULL,amount INT NOT NULL,entryTimeStamp DATETIME NOT NULL,exitTimeStamp DATETIME NOT NULL,status BIT NOT NULL,incomingType BIT NOT NULL,PRIMARY KEY (ID),FOREIGN KEY (vehicleID) REFERENCES vehicles(ID),FOREIGN KEY (customerID) REFERENCES customers(ID),FOREIGN KEY (materialID) REFERENCES materials(ID))";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();

		query = "CREATE TABLE IF NOT EXISTS serialSettings ("
				+ "ID INT NOT NULL," + "port VARCHAR(30) NOT NULL,"
				+ "baudRate INT NOT NULL," + "dataBits INT NOT NULL,"
				+ "stopBits INT NOT NULL," + "parity INT NOT NULL,"
				+ "flowControl INT NOT NULL" + ")";
		preparedStatement = connection.prepareStatement(query);

		preparedStatement.executeUpdate();

		query = "CREATE TABLE IF NOT EXISTS settings (wallpaper VARCHAR(255) NOT NULL,company VARCHAR(255) NOT NULL);";
		preparedStatement = connection.prepareStatement(query);
		preparedStatement.executeUpdate();

		initialized = true;
	}
}
