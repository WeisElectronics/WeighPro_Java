package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateWallpaperQuery {
	public UpdateWallpaperQuery(String wallpaper,String company)
			throws SQLException {
		String queryString = "UPDATE settings SET wallpaper=?,company=?";
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);
		prepStat.setString(1, wallpaper);
		prepStat.setString(2, company);
		prepStat.executeUpdate();
	}
}
