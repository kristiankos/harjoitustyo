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

	private JDBCArtistDao artistDao = new JDBCArtistDao();
	private Database database = new Database();
	
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
			statement = connection.prepareStatement("SELECT * FROM Album WHERE ArtistId = (?);");
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
	public boolean addAlbum(Album newAlbum) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			connection = database.connect();
			statement = connection.prepareStatement("INSERT INTO Album (Title, ArtistId) VALUES (?), (?);",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, newAlbum.getTitle());
			statement.setLong(2, newAlbum.getArtist().getId());
			results = statement.executeQuery();
			results = statement.getGeneratedKeys();
			if (results.next()) {
				newAlbum.setId(results.getLong(1));
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
	public boolean removeAlbum(Album newAlbum) {
		// TODO Auto-generated method stub
		return false;
	}

}
