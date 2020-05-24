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

public class JDBCArtistDao implements ArtistDao {

	private Database database = new Database();

	@Override
	public Artist getArtist(long id) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			connection = database.connect();
			statement = connection.prepareStatement("SELECT * FROM Artist WHERE ArtistId = (?);");
			statement.setLong(1, id);
			results = statement.executeQuery();

			if (results.next()) {
				Artist artist = new Artist(results.getLong("ArtistId"), results.getString("Name"));
				return artist;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		
		return null;
	}

	@Override
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
				Artist artist = new Artist(results.getLong("ArtistId"), results.getString("Name"));
				artists.add(artist);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		return artists;
	}

	@Override
	public Map<Artist, Integer> getAllArtistsAndAlbumCount() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		Map<Artist, Integer> albums = new TreeMap<>();

		try {
			connection = database.connect();
			statement = connection.prepareStatement(
					"SELECT Name, Artist.ArtistId, count(Album.AlbumId) as Count FROM Artist left join Album on Artist.ArtistId = Album.ArtistId GROUP BY Artist.ArtistId ORDER BY Artist.Name ASC;");
			results = statement.executeQuery();

			while (results.next()) {
				Artist artist = getArtist(results.getLong("ArtistId"));
				Integer albumCount = (results.getInt("count"));
				albums.put(artist, albumCount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		return albums;
	}

	@Override
	public boolean addArtist(Artist artist) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			connection = database.connect();
			// By Yishai & Lukas Eder, cc by-sa 4.0
			// https://stackoverflow.com/a/1376241/12748248
			statement = connection.prepareStatement("INSERT INTO Artist (Name) VALUES (?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, artist.getTitle());
			statement.execute();
			results = statement.getGeneratedKeys();
			if (results.next()) {
				artist.setId(results.getLong(1));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		return false;
	}

	@Override
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
