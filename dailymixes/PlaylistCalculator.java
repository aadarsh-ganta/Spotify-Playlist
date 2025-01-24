package dailymixes;

import list.AList;
import java.util.Arrays;

// -------------------------------------------------------------------------
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Aadarsh Ganta (aadarshg@vt.edu)
// -------------------------------------------------------------------------
/**
 * Performs the major calculations for the program, handles adding and rejecting
 * a song, and checks that all requirements for a song are met before being
 * added to a playlist.
 * 
 * @author Aadarsh
 * @version Nov 4, 2023
 */
public class PlaylistCalculator
{

    // ~ Fields ................................................................
    private Playlist[] playlists;
    /**
     * Sets the number of playlists to 3
     */
    public static final int NUM_PLAYLISTS = 3;
    /**
     * Sets the minimum percent to 0
     */
    public static final int MIN_PERCENT = 0;
    private AList<Song> rejectedTracks;
    private ArrayQueue<Song> songQueue;
    /**
     * Sets the maximum percent to 100
     */
    public static final int MAX_PERCENT = 100;

    // ~ Constructors ..........................................................
    /**
     * Initializes the playlists array, rejectedTracks list, and songQueue
     * objects.
     * 
     * @param queue
     *            queue of songs
     * @param playlists
     *            array of playlists
     * @throws IllegalArgumentException
     */
    public PlaylistCalculator(ArrayQueue<Song> queue, Playlist[] playlists)
    {
        if (queue == null)
        {
            throw new IllegalArgumentException();
        }
        this.playlists = playlists;
        rejectedTracks = new AList<>();
        this.songQueue = queue;
    }


    // ~Public Methods ........................................................
    /**
     * Rejects a song by removing it from the queue and adding it to the
     * rejectedTracks list
     */
    public void reject()
    {
        rejectedTracks.add(songQueue.dequeue());
    }


    /**
     * Finds the playlist with the most room that the song can be put in
     * 
     * @param song
     *            song to be added to playlist
     * @return the playlist with most room that or null if song can't be added
     *             to that playlist
     */
    private Playlist getPlaylistWithMostRoom(Song song)
    {
        Playlist[] copyArray = Arrays.copyOf(playlists, playlists.length);
        Arrays.sort(copyArray);
        for (int i = copyArray.length - 1; i >= 0; i--)
        {
            if (!copyArray[i].isFull() && canAccept(copyArray[i], song))
            {
                return copyArray[i];
            }
        }
        return null;
    }


    /**
     * Adds a song to a playlist
     * 
     * @return true if the song is added; otherwise return false
     */
    public boolean addSongToPlaylist()
    {
        if (!songQueue.isEmpty())
        {
            Song songToAdd = songQueue.dequeue();
            if (getPlaylistForSong(songToAdd) != null)
            {
                getPlaylistForSong(songToAdd).addSong(songToAdd);
                return true;
            }
        }
        return false;
    }


    /**
     * Gets the suggested playlist for a song and finds the biggest playlist if
     * there's no suggested playlist
     * 
     * @return the playlist with the most room or returns null if the song is
     *             null or if the song can't be added to any playlist
     * @param nextSong
     *            next song in queue
     */
    public Playlist getPlaylistForSong(Song nextSong)
    {
        if (nextSong == null)
        {
            return null;
        }
        int index = getPlaylistIndex(nextSong.getPlaylistName());
        if (index != -1)
        {
            Playlist playlist = playlists[index];
            if (canAccept(playlist, nextSong))
            {
                return playlist;
            }
            return null;
        }
        return getPlaylistWithMostRoom(nextSong);
    }


    /**
     * Gets the queue of songs
     * 
     * @return songQueue
     */
    public ArrayQueue<Song> getQueue()
    {
        return songQueue;
    }


    /**
     * Checks if the song can be accepted into the specified playlist
     * 
     * @param playlist
     *            playlist
     * @param song
     *            song
     * @return true if the playlist is not full and the song fits the
     *             requirements; otherwise returns false
     */
    private boolean canAccept(Playlist playlist, Song song)
    {
        return (!playlist.isFull() && playlist.isQualified(song));
    }


    /**
     * Gets the index of the specified playlist based on its name
     * 
     * @param playlist
     *            name of playlist
     * @return index of playlist or -1 if it's not found
     */
    public int getPlaylistIndex(String playlist)
    {
        for (int i = 0; i < playlists.length; i++)
        {
            if (playlists[i].getName().equals(playlist))
            {
                return i;
            }
        }
        return -1;
    }


    /**
     * Gets the array of playlists
     * 
     * @return playlists array of playlists
     */
    public Playlist[] getPlaylists()
    {
        return playlists;
    }
}
