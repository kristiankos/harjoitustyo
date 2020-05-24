package model;

public class Artist implements Comparable<Artist>, MusicData {

	private long id;
	private String name;

	public Artist(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Artist(String name) {
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
	public String getTitle() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Artist artist) {
		
		// Jotta Artist-objektia voidaan käyttää map-tietorakenteessa avaimena, tehdään sille metodi, joka vertaa kahta oliota titlen perusteella.
		// ei anneta tämän palauttaa milloinkaan "0", koska ei haluta että mapilta jää
		// pois tavaraa
		
		if (this.getTitle() == null) {
				
			}
		
		if (this.getTitle().compareToIgnoreCase(artist.getTitle()) >= 0) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public String getUrl() {
		return "/artist?id=" + this.id;
	}

}
