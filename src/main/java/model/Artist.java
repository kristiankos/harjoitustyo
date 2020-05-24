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

	
		/*
		 * Jotta Artist-objektia voidaan käyttää map-tietorakenteessa avaimena, tulee se voida olla vertailtavissa toiseen Artist-olioon. Tämä metodi on sitä varten.
		 * Jos artistilla ei ole titleä (käyttäjäkerroksessa tämä ei ole mahdollista), tulee se silti olla mahdollista asettaa mapiin.
		 * Metodin ei anneta milloinkaan palauttaa "nolla", jottei toinen samanniminen artisti ylikirjoita ensimmäistä artistia.
		 */

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
