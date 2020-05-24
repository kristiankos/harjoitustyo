package database;

import java.util.List;
import java.util.Map;

import model.Artist;

public interface ArtistDao {

	public Artist getArtist(long id);

	public List<Artist> getAllArtists();

	public Map<Artist, Integer> getAllArtistsAndAlbumCount();

	public boolean addArtist(Artist artist);

	public boolean removeArtist(Artist artist);

}
