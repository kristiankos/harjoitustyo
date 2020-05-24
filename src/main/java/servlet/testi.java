package servlet;


import java.util.List;



import database.AlbumDao;
import database.JDBCAlbumDao;
import database.JDBCTrackDao;
import database.TrackDao;
import model.Album;
import model.Track;

public class testi {

	public static void main(String[] args) {
		
		AlbumDao albumDao = new JDBCAlbumDao();
		TrackDao trackDao = new JDBCTrackDao();

		List<Track> tracks = null;
		
			String parameter = "1";
			if (parameter != null) {
				long id = Long.parseLong(parameter);
				Album album = albumDao.getAlbum(id);
				if (album != null) {
				tracks = trackDao.getAllTracksByAlbum(album);
				}
			}
			
			System.out.println(tracks);

		

}

}
