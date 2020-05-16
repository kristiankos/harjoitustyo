package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ArtistDao;
import database.JDBCArtistDao;
import model.Artist;

@WebServlet("/musiccatalog")
public class ArtistServlet extends HttpServlet{
	
	private final ArtistDao artistDao = new JDBCArtistDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Map<String, Integer> artists = this.albumDao.getAllAlbumsByArtist(String.CASE_INSENSITIVE_ORDER);
		Map<Artist, Integer> artists = this.artistDao.getAllAlbumsByArtist();

		req.setAttribute("artists", artists);
		
		req.getRequestDispatcher("WEB-INF/MusicCatalog/index.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		Artist artist = new Artist(name);
		artistDao.addArtist(artist);
		
		resp.sendRedirect("/musiccatalog");
	}

}
