package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Artist;

public class JDBCArtistDao implements ArtistDao{

	private Database database = new Database();
	
	public List<Artist> getAllArtists() {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		List<Artist> artists = new ArrayList<Artist>();
		
		try {
			connection = database.connect();
			statement = connection.prepareStatement("SELECT * FROM Artist ORDER BY Name ASC;");
			results = statement.executeQuery();

			while (results.next()) {
				Artist newArtist = new Artist(results.getString("Name"), results.getLong("ArtistId"));
				artists.add(newArtist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		return artists;
	}

	public Artist getArtist(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addArtist(Artist newItem) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean removeArtist(Artist newArtist) {
		// TODO Auto-generated method stub
		return false;
	}

}
