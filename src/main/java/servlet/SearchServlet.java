package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Album;
import model.Artist;
import model.MusicData;

@WebServlet("/search")
public class SearchServlet extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String keyword = req.getParameter("keyword");
		
		List<Album> albums = new ArrayList<Album>(); // this.albumdao.search(keyword);
		List<Artist> artists = new ArrayList<Artist>(); // this.artistdao.search(keyword);
		
		List<MusicData> searchResults = new ArrayList<>();
		searchResults.addAll(albums);
		searchResults.addAll(artists);
		
		searchResults.sort(Comparator.comparing(MusicData::getTitle));
		
		// "jsp-toteutus"
		for (MusicData data : searchResults) {
			resp.getWriter().println(data.getTitle() + ", " + data.getUrl());
		}
		
	
	}
}
