/*
* This class provides the pluggable database connection
* used to access the database

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/


package in.weighpro.databaseConstructs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection connection;//only one instance of the database connection is used

//constructor to initialize the login credentials to access the database
	public DatabaseConnection() throws SQLException {//may throw an SQL Exception because of the database related actions
		LoginCredentials logCred = new LoginCredentials();//getting the login credentials 
		if (!DatabaseInitializer.initialized) {//check to see if the database constructors have already been initialized
							//to avoid duplicate attempts and processing overhead
			connection = DriverManager.getConnection(logCred.getUrl(),
					logCred.getUserName(), logCred.getPassword());//getting a connection to the database
			new DatabaseInitializer(connection, logCred.getDatabaseName());//initialize the database constructs
		}

	}

//method to get the connection to the database
	public Connection getConnection() {
		return connection;
	}
}
