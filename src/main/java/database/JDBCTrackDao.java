package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Album;
import model.Track;

public class JDBCTrackDao implements TrackDao {

	private Database database = new Database();
	private JDBCAlbumDao albumDao = new JDBCAlbumDao();

	@Override
	public Track getTrack(long id) {
		
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			connection = database.connect();
			statement = connection.prepareStatement("SELECT * FROM Track WHERE TrackId = (?);");
			statement.setLong(1, id);
			results = statement.executeQuery();

			while (results.next()) {
				Album album = albumDao.getAlbum(results.getLong("AlbumId"));
				Track track = new Track(results.getLong("TrackId"), results.getString("Name"), album);
				return track;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}

		return null;
	}

	@Override
	public List<Track> getAllTracks() {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		List<Track> tracks = new ArrayList<Track>();

		try {
			connection = database.connect();
			statement = connection.prepareStatement("SELECT * FROM Track;");
			results = statement.executeQuery();

			while (results.next()) {
				Album album = albumDao.getAlbum(results.getLong("AlbumId"));
				Track track = new Track(results.getLong("TrackId"), results.getString("Name"), album);
				tracks.add(track);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}

		return tracks;
	}

	@Override
	public List<Track> getAllTracksByAlbum(Album album) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		List<Track> tracks = new ArrayList<Track>();

		try {
			connection = database.connect();
			statement = connection.prepareStatement("SELECT * FROM Track WHERE AlbumId = (?);");
			statement.setLong(1, album.getId());
			results = statement.executeQuery();

			while (results.next()) {
				Track track = getTrack(results.getLong("TrackId"));
				tracks.add(track);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		
		return tracks;
		
	}

	@Override
	public boolean addTrack(Track track) {

		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		try {
			connection = database.connect();
			// By Yishai & Lukas Eder, cc by-sa 4.0
			// https://stackoverflow.com/a/1376241/12748248
			/*
			 * statement = connection.
			 * prepareStatement("INSERT INTO Album (Title, ArtistId) VALUES (?), (?);",
			 * Statement.RETURN_GENERATED_KEYS); statement.setString(1,
			 * newAlbum.getTitle()); statement.setLong(2, newAlbum.getArtist().getId());
			 * results = statement.executeQuery(); results = statement.getGeneratedKeys();
			 * if (results.next()) { newAlbum.setId(results.getLong(1)); return true; }
			 */

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			database.close(connection, statement, results);
		}
		return false;
	}

	@Override
	public boolean removeTrack(Track track) {
		// TODO Auto-generated method stub
		return false;
	}

}
