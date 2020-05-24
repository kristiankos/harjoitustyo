package database;

import java.util.List;

import model.Album;
import model.Artist;

public interface AlbumDao {

	public Album getAlbum(long id);

	public List<Album> getAllAlbums();

	public List<Album> getAllAlbumsByArtist(Artist artist);

	public boolean addAlbum(Album album);

	public boolean removeAlbum(Album album);
	
	public List<Album> searchAlbum(String search);

}
