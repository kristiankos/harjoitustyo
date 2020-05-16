package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
			statement = connection.prepareStatement("SELECT * FROM Artist ORDER BY Name COLLATE NOCASE ASC;");
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
	
	public Map<Artist, Integer> getAllAlbumsByArtist() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		Map<Artist, Integer> albums = new TreeMap<Artist, Integer>();

		try {
			connection = database.connect();
			statement = connection.prepareStatement(
					"SELECT Name, count(al.AlbumId) as Count FROM Artist as a left join Album as al on a.ArtistId = al.ArtistId GROUP BY Name ORDER BY name ASC;");
			results = statement.executeQuery();

			while (results.next()) {
				Artist newArtist = new Artist(results.getString("Name"));
				Integer count = (results.getInt("count"));
				albums.put(newArtist, count);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		return albums;
	}

	public Artist getArtist(long id) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			connection = database.connect();
			statement = connection.prepareStatement("SELECT * FROM Artist WHERE ArtistId = (?);");
			statement.setLong(1, id);
			results = statement.executeQuery();
			if(results.next()) {
				Artist artist = new Artist(results.getString("Name"), results.getLong("ArtistId"));
				return artist;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		return null;
	}

	public boolean addArtist(Artist newArtist) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			connection = database.connect();
			// By Yishai & Lukas Eder, cc by-sa 4.0
			// https://stackoverflow.com/a/1376241/12748248
			statement = connection.prepareStatement("INSERT INTO Artist (Name) VALUES (?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newArtist.getName());
			statement.execute();
			results = statement.getGeneratedKeys();
			if (results.next()) {
				newArtist.setId(results.getLong(1));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		return false;
	}

	public boolean removeArtist(Artist artist) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			connection = database.connect();
			statement = connection.prepareStatement("DELETE FROM Artist WHERE ArtistId = (?);");
			statement.setLong(1, artist.getId());
			// executeUpdate palauttaa poistettujen rivien määrän.
			int deletedRows = statement.executeUpdate();
			if (deletedRows > 0) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}

		return false;
	}
	
}
