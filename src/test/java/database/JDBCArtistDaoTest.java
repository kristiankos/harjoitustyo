package database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Artist;

class JDBCArtistDaoTest {

	private static JDBCArtistDao dao = new JDBCArtistDao();

	@Test
	void testGetAllArtists() {
		List<Artist> artists = dao.getAllArtists();

		assertFalse(artists.isEmpty());
	}
	
	@Test
	void testGetArtist() {
		
		Artist artist = dao.getArtist(1);
		
		assertEquals("AC/DC", artist.getName());
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

}
