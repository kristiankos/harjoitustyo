package database;

import java.util.List;

import model.Album;
import model.Track;

public interface TrackDao {

	public Track getTrack(long id);

	public List<Track> getAllTracks();

	public List<Track> getAllTracksByAlbum(Album album);

	public boolean addTrack(Track track);

	public boolean removeTrack(Track track);

}
