package model;


public class Artist implements Comparable<Artist>{


	private long id;
	private String name;

	public Artist(String name) {
		this.name = name;
	}
	public Artist(long id) {
		this.id = id;
	}

	public Artist(String name, long id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int compareTo(Artist artist) {
		// ei anneta tämän palauttaa milloinkaan "0", koska ei haluta että mapilta jää pois tavaraa
		if (this.getName().compareToIgnoreCase(artist.getName()) >= 0) {
			return 1;
			
		} else {
			return -1;
		}

	}
	
}
