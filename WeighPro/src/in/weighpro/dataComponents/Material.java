package in.weighpro.dataComponents;

import java.sql.SQLException;

public class Material extends DataObject {

	public Material(int index) throws SQLException {
		super("materials", index);

	}

	public Material(String value) throws SQLException {
		super("materials", value);

	}
}
