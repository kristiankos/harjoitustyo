package database;

import java.util.List;

import model.Artist;

public interface ArtistDao {

	public List<Artist> getAllArtists();
	
	public Artist getArtist(long id);
	
	public boolean addArtist(Artist newItem);
	
	public boolean removeArtist(Artist newArtist);
	
}
