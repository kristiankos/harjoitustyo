package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AlbumDao;
import database.ArtistDao;
import database.JDBCAlbumDao;
import database.JDBCArtistDao;
import model.Album;
import model.Artist;
import model.MusicData;

@WebServlet("/search")
public class SearchServlet extends HttpServlet{
	
	private final ArtistDao artistDao = new JDBCArtistDao();
	private final AlbumDao albumDao = new JDBCAlbumDao();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String keyword = req.getParameter("keyword");
		
		List<Album> albums = new ArrayList<Album>();
		albums = this.albumDao.searchAlbum(keyword);
		List<Artist> artists = new ArrayList<Artist>();
		artists = this.artistDao.searchArtist(keyword);
		
		List<MusicData> searchResults = new ArrayList<>();
		searchResults.addAll(albums);
		searchResults.addAll(artists);
		
		searchResults.sort(Comparator.comparing(MusicData::getTitle));
		
		req.setAttribute("search", searchResults);

		req.getRequestDispatcher("WEB-INF/MusicCatalog/search.jsp").forward(req, resp);
		
	
	}
}
