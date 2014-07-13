package in.weighpro.dataComponents;

import java.sql.SQLException;

public class Vehicle extends DataObject {

	public Vehicle(int index) throws SQLException {
		super("vehicles", index);
	}

	public Vehicle(String value) throws SQLException {
		super("vehicles", value);
	}

}
