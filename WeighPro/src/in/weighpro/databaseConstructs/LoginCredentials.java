package in.weighpro.databaseConstructs;

public class LoginCredentials {
	private String databaseName = "YOYO";
	private String password = "gun$Nroses";
	private String url = "jdbc:mysql://localhost:3306";
	private String userName = "root";

	String getDatabaseName() {
		return databaseName;
	}

	String getPassword() {
		return password;
	}

	String getUrl() {
		return url;
	}

	String getUserName() {
		return userName;
	}

}
