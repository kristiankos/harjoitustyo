package database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Artist;

class JDBCArtistDaoTest {

	private static JDBCArtistDao dao = new JDBCArtistDao();

	@Test
	void testGetAllArtists() {
		List<Artist> artists = dao.getAllArtists();

		assertEquals(275, artists.size());
	}
}
