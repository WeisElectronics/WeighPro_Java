package in.weighpro.dataComponents;

import java.sql.SQLException;

public class Customer extends DataObject {

	public Customer(int index) throws SQLException {
		super("customers", index);
	}

	public Customer(String value) throws SQLException {
		super("customers", value);
	}
}
