package model;

public class Track implements MusicData {

	private long id;
	private String title;
	private Album album;

	public Track(long trackId, String title, Album album) {
		this.id = trackId;
		this.title = title;
		this.album = album;
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

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	@Override
	public String getUrl() {
		return "/track?id=" + this.id;
	}

}