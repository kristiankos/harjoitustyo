package database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Album;

class JDBCAlbumDaoTest {
	
	private static JDBCAlbumDao dao = new JDBCAlbumDao();

	@Test
	void testGetAllAlbums() {
		List<Album> albums = dao.getAllAlbums();

		assertTrue(!albums.isEmpty());
	}

}
