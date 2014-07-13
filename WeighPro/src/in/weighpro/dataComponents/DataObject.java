package in.weighpro.dataComponents;

import java.sql.SQLException;

import in.weighpro.databaseQueryHandler.DataIndexQuery;
import in.weighpro.databaseQueryHandler.DataValueQuery;

@SuppressWarnings("rawtypes")
public class DataObject implements Comparable{
	private int index;
	private String value;

	public DataObject(String type, int index) throws SQLException {
		this.index = index;
		DataIndexQuery dataIndxQuery = new DataIndexQuery(type, index);
		value = dataIndxQuery.getValue();
	}

	public DataObject(String type, String value) throws SQLException {
		this.value = value;
		DataValueQuery dataInsQuery = new DataValueQuery(type, value);
		index = dataInsQuery.getIndex();
	}

	public int getIndex() {
		return this.index;
	}

	public String getValue() {
		return this.value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	@Override
	public int compareTo(Object o) {
		return this.toString().compareTo(o.toString());
	}
	
	
	

}
