package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ArtistDao;
import database.JDBCArtistDao;
import model.Artist;

@WebServlet("/artist")
public class ArtistServlet extends HttpServlet {

	private final ArtistDao artistDao = new JDBCArtistDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String parameter = req.getParameter("artistId");
		if (parameter != null) {
			long id = Long.parseLong(parameter);
			Artist artist = this.artistDao.getArtist(id);
			req.setAttribute("artist", artist);
		}

		req.getRequestDispatcher("WEB-INF/MusicCatalog/artist.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.sendRedirect("/artist");
	}

}
