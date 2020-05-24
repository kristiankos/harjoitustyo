package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.AlbumDao;
import database.JDBCAlbumDao;
import database.JDBCTrackDao;
import database.TrackDao;
import model.Album;
import model.Track;

@WebServlet("/album")
public class AlbumServlet  extends HttpServlet {
	
	private final AlbumDao albumDao = new JDBCAlbumDao();
	private final TrackDao trackDao = new JDBCTrackDao();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String parameter = req.getParameter("id");
		if (parameter != null) {
			long id = Long.parseLong(parameter);
			Album album = this.albumDao.getAlbum(id);
			if (album != null) {
			List<Track> tracks = this.trackDao.getAllTracksByAlbum(album);
			req.setAttribute("album", album);
			req.setAttribute("tracks", tracks);
			}
		}

		req.getRequestDispatcher("WEB-INF/MusicCatalog/album.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.sendRedirect("/album");
	}

}
