package database;

import java.util.List;

import model.Album;
import model.Artist;


public interface AlbumDao {

	public List<Album> getAllAlbums();
	
	public List<Album> getAllAlbumsByArtist(Artist artist);
		
	public boolean addAlbum(Album newAlbum);
	
	public boolean removeAlbum(Album newAlbum);

	
	}
