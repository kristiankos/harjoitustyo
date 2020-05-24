package database;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Album;

class JDBCAlbumDaoTest {
	
	private static JDBCArtistDao artistDao = new JDBCArtistDao();
	private static JDBCAlbumDao albumDao = new JDBCAlbumDao();

	@Test
	void testGetAllAlbums() {
		List<Album> albums = albumDao.getAllAlbums();

		assertTrue(!albums.isEmpty());
	}
	
	@Test
	void testGetAllAlbumsByArtist() {
		List<Album> albums = albumDao.getAllAlbumsByArtist(artistDao.getArtist(1));
		
		assertTrue(!albums.isEmpty());
	}
	
	@Test
	void testGetAlbum() {
		
	}

}
