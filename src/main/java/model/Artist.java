package model;


public class Artist implements Comparable<Artist>, MusicData {


	private String name;
	private long id;

	public Artist(String name) {
		this.name = name;
	}

	public Artist(String name, long id) {
		this.name = name;
		this.id = id;
	}

	@Override
	public String getTitle() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int compareTo(Artist artist) {
		
		
		  // ei anneta tämän palauttaa milloinkaan "0", koska ei haluta että mapilta jää pois tavaraa
		if (this.getTitle().compareToIgnoreCase(artist.getTitle()) >=0) {
			return 1;
		} else  {
			return -1;
		}
		 
		 
	}
	
	@Override
	public String getUrl() {
		return "/artist?ArtistId=" + this.id;
	}
	
}
