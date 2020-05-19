package database;

import java.util.List;
import java.util.Map;

import model.Artist;

public interface ArtistDao {

	public List<Artist> getAllArtists();
	
	public Map<Artist, Integer> getAllArtistsAndAlbumCount();
	
	public Artist getArtist(long id);
	
	public boolean addArtist(Artist newItem);
	
	public boolean removeArtist(Artist newArtist);
	
}
