package database;

import java.util.List;

import model.Artist;

public class Main {

	public static void main(String[] args) {
		
		JDBCArtistDao dao = new JDBCArtistDao();
		
		List<Artist> list = dao.getAllArtists();
		
		for (int i =0; i<list.size(); i++) {
			Artist a = list.get(i);
			System.out.println(a.getId() + ". " + a.getName());
		}
		
	}

}
