package in.weighpro.databaseQueryHandler;

import in.weighpro.dataComponents.DataObject;
import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataArrayRetriever {
	
	DataObject[] items;
	
	public DataArrayRetriever(String type) throws SQLException{
		String queryString = "SELECT value FROM "+type;
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);
		ResultSet rs = prepStat.executeQuery();
		rs.last();
		items = new DataObject[rs.getRow()];
		rs.beforeFirst();
		int i = 0;
		while(rs.next()) {
			items[i++] = new DataObject(type,rs.getString(1));
		}
	}
	
	public DataObject[] getItems(){
		return this.items;
	}

}
