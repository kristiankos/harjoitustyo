package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Album;
import model.Artist;

public class JDBCAlbumDao implements AlbumDao {

	private Database database = new Database();
	
	@Override
	public List<Album> getAllAlbums() {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;
		
		List<Album> allAlbums = new ArrayList<Album>();
		JDBCArtistDao artistDao = new JDBCArtistDao();
		
		try {
			connection = database.connect();
			statement = connection.prepareStatement("SELECT * FROM Album;");
			results = statement.executeQuery();
			
			while (results.next()) {
				Artist artist = artistDao.getArtist(results.getLong("ArtistId"));
				Album album = new Album(results.getLong("AlbumId"), results.getString("Title"), artist);
				allAlbums.add(album);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		return allAlbums;
				
	}

	@Override
	public boolean addAlbum(Album newAlbum) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAlbum(Album newAlbum) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
