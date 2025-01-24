package dailymixes;

import student.TestCase;

// -------------------------------------------------------------------------
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Aadarsh Ganta (aadarshg@vt.edu)
// -------------------------------------------------------------------------
/**
 * Test class for PlaylistCalculator
 * 
 * @author Aadarsh
 * @version Nov 4, 2023
 */
public class PlaylistCalculatorTest
    extends TestCase
{
    // ~ Fields ................................................................
    private Song song1;
    private Song song2;
    private Song song3;
    private Song song4;
    private Playlist playlist;
    private Playlist[] playlists;
    private ArrayQueue<Song> queue;
    private PlaylistCalculator playlistCalculator;

    /**
     * Sets up test methods
     */
    // ~Public Methods ........................................................
    public void setUp()
    {
        song1 = new Song("S1", 10, 5, 5, "First Playlist");
        song2 = new Song("S2", 5, 2, 2, "Third Playlist");
        song3 = new Song("S3", 5, 5, 5, "");
        song4 = new Song("S4", 50, 50, 50, "");
        playlist = new Playlist("First Playlist", 2, 2, 2, 10, 10, 10, 3);
        playlists = new Playlist[PlaylistCalculator.NUM_PLAYLISTS];
        playlists[0] = playlist;
        queue = new ArrayQueue<Song>();
        queue.enqueue(song1);
        queue.enqueue(song2);
        queue.enqueue(song3);
        queue.enqueue(song4);
        playlistCalculator = new PlaylistCalculator(queue, playlists);
    }


    /**
     * Test method for reject
     */
    public void testReject()
    {
        assertEquals(4, queue.getSize());
        playlistCalculator.reject();
        assertEquals(3, queue.getSize());
        assertEquals(song2, queue.toArray()[0]);
    }


    /**
     * Test method for addSongToPlaylist
     */
    public void testAddSongToPlaylist()
    {
        assertTrue(playlistCalculator.addSongToPlaylist());
        assertEquals(song1, playlist.getSongs()[0]);
        assertEquals(song2, queue.toArray()[0]);

        ArrayQueue<Song> newQueue = new ArrayQueue<Song>();
        PlaylistCalculator playlistCalculator2 =
            new PlaylistCalculator(newQueue, playlists);
        assertFalse(playlistCalculator2.addSongToPlaylist());

        newQueue.enqueue(null);
        assertFalse(playlistCalculator2.addSongToPlaylist());
    }


    /**
     * Test method for getPlaylistForSong
     */
    public void testGetPlaylistForSong()
    {
        Playlist otherPlaylist =
            new Playlist("Second Playlist", 5, 5, 5, 15, 15, 15, 2);
        playlists[1] = otherPlaylist;
        Playlist fullPlaylist =
            new Playlist("Third Playlist", 10, 10, 10, 20, 20, 20, 2);
        fullPlaylist.addSong(song1);
        fullPlaylist.addSong(song2);
        playlists[2] = fullPlaylist;
        assertNull(playlistCalculator.getPlaylistForSong(null));
        assertEquals(playlist, playlistCalculator.getPlaylistForSong(song1));
        assertNull(playlistCalculator.getPlaylistForSong(song2));
        assertEquals(playlist, playlistCalculator.getPlaylistForSong(song3));
        assertNull(playlistCalculator.getPlaylistForSong(song4));
    }


    /**
     * Test method for getQueue
     */
    public void testGetQueue()
    {
        assertEquals(queue, playlistCalculator.getQueue());
        ArrayQueue<Song> newQueue = null;
        IllegalArgumentException caughtException = null;
        try
        {
            PlaylistCalculator playlistCalculator2 =
                new PlaylistCalculator(newQueue, playlists);
        }
        catch (IllegalArgumentException e)
        {
            caughtException = e;
        }
        assertNotNull(caughtException);
    }


    /**
     * Test method for getPlaylistIndex
     */
    public void testGetPlaylistIndex()
    {
        Playlist otherPlaylist =
            new Playlist("Second Playlist", 5, 5, 5, 15, 15, 15, 2);
        playlists[1] = otherPlaylist;
        Playlist fullPlaylist =
            new Playlist("Third Playlist", 10, 10, 10, 20, 20, 20, 2);
        fullPlaylist.addSong(song1);
        fullPlaylist.addSong(song2);
        playlists[2] = fullPlaylist;
        assertEquals(0, playlistCalculator.getPlaylistIndex("First Playlist"));
        assertEquals(-1, playlistCalculator.getPlaylistIndex("Playlist1"));
    }


    /**
     * Test method for getPlaylists
     */
    public void testGetPlaylists()
    {
        assertEquals(playlists, playlistCalculator.getPlaylists());
    }
}
