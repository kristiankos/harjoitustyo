package servlet;

import java.io.IOException;
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

@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {

	private final ArtistDao artistDao = new JDBCArtistDao();
	private final AlbumDao albumDao = new JDBCAlbumDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String parameter = req.getParameter("id");
		if (parameter != null) {
			long id = Long.parseLong(parameter);
			Artist artist = this.artistDao.getArtist(id);
			if (artist != null) {
			List<Album> albums = this.albumDao.getAllAlbumsByArtist(artist);
			req.setAttribute("artist", artist);
			req.setAttribute("albums", albums);
			}
		}

		req.getRequestDispatcher("WEB-INF/MusicCatalog/artist.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.sendRedirect("/artist");
	}

}
