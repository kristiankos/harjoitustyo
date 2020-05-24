package model;

public class Album implements MusicData {

	private long id;
	private String title;
	private Artist artist;

	public Album(long id, String title, Artist artist) {
		this.id = id;
		this.title = title;
		this.artist = artist;
	}
	
	public Album(String title, Artist artist) {
		this.title = title;
		this.artist = artist;
	}

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	@Override
	public String getUrl() {
		return "/album?id=" + this.id;
	}

}
