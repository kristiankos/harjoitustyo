package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Artist;

class JDBCArtistDaoTest {

	private static JDBCArtistDao dao = new JDBCArtistDao();
	private static Database database = new Database();
	
	@BeforeEach
	public void setUp() throws Exception {
		Connection connection = database.connect();
		connection.prepareStatement("DELETE FROM Artist").executeUpdate();
		connection.prepareStatement("INSERT INTO Artist (Name) VALUES ('ABBA'), ('AC/DC')").executeUpdate();
		connection.close();
		
	}

	@Test
	void testGetAllArtists() {
		List<Artist> artists = dao.getAllArtists();

		assertEquals(2, artists.size());
	}
	
	@Test
	void testGetArtist() {
		
		Artist artist = dao.getArtist(1);
		
		assertEquals("ABBA", artist.getTitle());
	}
	
	@Test
	void testAddArtist() {
		Artist newArtist = new Artist("TestiArtisti");
		assertTrue(dao.addArtist(newArtist));
	}
	
	@Test
	void testRemoveArtist() {
		Artist artist = new Artist("TestiArtisti");
		dao.addArtist(artist);
		assertTrue(dao.removeArtist(artist));
		
	}
	
	@Test
	void testgetAllArtistsAndAlbumCount() {
		Artist artist = new Artist("TestiArtisti");
		dao.addArtist(artist);
		Artist artist2 = new Artist("testiartisti");
		dao.addArtist(artist2);
		Artist artist3 = new Artist("testiartisti");
		dao.addArtist(artist3);
		Map<Artist, Integer> map = dao.getAllArtistsAndAlbumCount();
		assertEquals(5, map.size());
		
	}

}
