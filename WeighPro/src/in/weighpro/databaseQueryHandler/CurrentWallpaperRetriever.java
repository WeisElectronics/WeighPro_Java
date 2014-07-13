package in.weighpro.databaseQueryHandler;

import in.weighpro.databaseConstructs.DatabaseConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrentWallpaperRetriever {

	private String wallpaper;
	private String company;

	public CurrentWallpaperRetriever() throws SQLException {
		String queryString = "SELECT wallpaper,company FROM settings";
		DatabaseConnection connection = new DatabaseConnection();
		PreparedStatement prepStat = connection.getConnection()
				.prepareStatement(queryString);
		ResultSet rs = prepStat.executeQuery();
		if (rs.next()) {
			wallpaper = rs.getString(1);
			company = rs.getString(2);
		} else {
			wallpaper = "bg1.jpg";
			company = "Weis Electronics Pvt. Ltd.";
			queryString = "INSERT INTO settings VALUES (?,?)";
			prepStat = connection.getConnection().prepareStatement(queryString);
			prepStat.setString(1, wallpaper);
			prepStat.setString(2, company);
			prepStat.executeUpdate();
			
		}
	}

	public String getWallpaper() {
		return wallpaper;
	}

	public String getCompany() {
		return company;
	}
}
