package database;

import java.util.List;

import model.Album;


public interface AlbumDao {

	public List<Album> getAllAlbums();
		
	public boolean addAlbum(Album newAlbum);
	
	public boolean removeAlbum(Album newAlbum);

	
	}
