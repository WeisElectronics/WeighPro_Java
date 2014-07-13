package in.weighpro.databaseConstructs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection connection;

	public DatabaseConnection() throws SQLException {
		LoginCredentials logCred = new LoginCredentials();
		if (!DatabaseInitializer.initialized) {
			connection = DriverManager.getConnection(logCred.getUrl(),
					logCred.getUserName(), logCred.getPassword());
			new DatabaseInitializer(connection, logCred.getDatabaseName());
		}

	}

	public Connection getConnection() {
		return connection;
	}
}
