/*
* This class provides the pluggable login credentials 
* used to access the database 

* Made for WeighPro
* Author Weis Electonics Pvt. Ltd.
* Contribution by emdroidery
*/


package in.weighpro.databaseConstructs;

public class LoginCredentials {
	private String databaseName = "YOYO";
	private String password = "gun$Nroses";
	private String url = "jdbc:mysql://localhost:3306";
	private String userName = "root";

//method to gett the database name
	String getDatabaseName() {
		return databaseName;
	}

//method to get the password
	String getPassword() {
		return password;
	}

//method to get the url to setup the connection
	String getUrl() {
		return url;
	}

//method to get the user name to acccess the database
	String getUserName() {
		return userName;
	}

}
