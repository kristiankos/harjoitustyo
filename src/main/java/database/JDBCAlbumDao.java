package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Album;
import model.Artist;

public class JDBCAlbumDao implements AlbumDao {

	private Database database = new Database();
	private JDBCArtistDao artistDao = new JDBCArtistDao();

	@Override
	public Album getAlbum(long id) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			connection = database.connect();
			statement = connection.prepareStatement("SELECT * FROM Album WHERE AlbumId = (?);");
			statement.setLong(1, id);
			results = statement.executeQuery();

			if (results.next()) {
				Artist artist = artistDao.getArtist(results.getLong("ArtistId"));
				Album album = new Album(results.getLong("AlbumId"), results.getString("Title"), artist);
				return album;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		
		return null;
	}

	@Override
	public List<Album> getAllAlbums() {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		List<Album> albums = new ArrayList<Album>();

		try {
			connection = database.connect();
			statement = connection.prepareStatement("SELECT * FROM Album;");
			results = statement.executeQuery();

			while (results.next()) {
				Artist artist = this.artistDao.getArtist(results.getLong("ArtistId"));
				Album album = new Album(results.getLong("AlbumId"), results.getString("Title"), artist);
				albums.add(album);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		return albums;

	}

	@Override
	public List<Album> getAllAlbumsByArtist(Artist artist) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		List<Album> albums = new ArrayList<Album>();

		try {
			connection = database.connect();
			statement = connection.prepareStatement("SELECT * FROM Album WHERE ArtistId = (?) ORDER BY Title;");
			statement.setLong(1, artist.getId());
			results = statement.executeQuery();

			while (results.next()) {
				Album album = new Album(results.getLong("AlbumId"), results.getString("Title"), artist);
				albums.add(album);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}

		return albums;
	}

	@Override
	public boolean addAlbum(Album album) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			connection = database.connect();
			// By Yishai & Lukas Eder, cc by-sa 4.0
			// https://stackoverflow.com/a/1376241/12748248
			statement = connection.prepareStatement("INSERT INTO Album (Title, ArtistId) VALUES (?), (?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, album.getTitle());
			statement.setLong(2, album.getArtist().getId());
			results = statement.executeQuery();
			results = statement.getGeneratedKeys();
			if (results.next()) {
				album.setId(results.getLong(1));
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
	public boolean removeAlbum(Album album) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Album> searchAlbum(String search) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		
		List<Album> albums = new ArrayList<Album>();

		try {
			connection = database.connect();
			statement = connection.prepareStatement("SELECT * FROM Album WHERE Title LIKE (?) ORDER BY Title ASC;");
			statement.setString(1, "%" + search + "%");
			results = statement.executeQuery();
			
			while (results.next()) {
				Album album = getAlbum(results.getLong("AlbumId"));
				albums.add(album);
			}


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}

		return albums;
	}


}
