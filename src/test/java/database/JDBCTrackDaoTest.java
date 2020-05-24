package database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Track;

class JDBCTrackDaoTest {
	
	private static JDBCTrackDao trackDao = new JDBCTrackDao();

	@Test
	void testGetTrack() {
		assertTrue(trackDao.getTrack(1) != null);
		
		
	}
	
	
	@Test
	void testGetAllTracks() {
		List<Track> tracks = trackDao.getAllTracks();
		
		assertTrue(!tracks.isEmpty());
	}

}
