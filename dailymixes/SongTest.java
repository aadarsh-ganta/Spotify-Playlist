package dailymixes;

import student.TestCase;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Aadarsh Ganta (aadarshg@vt.edu)
// -------------------------------------------------------------------------
/**
 * Test class for Song
 * 
 * @author Aadarsh
 * @version Oct 30, 2023
 */
public class SongTest
    extends TestCase
{
    // ~ Fields ................................................................
    private Song song;
    private Song otherSong;

    // ~Public Methods ........................................................
    /**
     * Sets up test methods
     */
    public void setUp()
    {
        song = new Song("Song", 10, 15, 20, "Playlist");
        otherSong = new Song("S1", 5, 2, 2, null);
    }


    /**
     * Test method for toString
     */
    public void testToString()
    {
        assertEquals(
            "Song Pop:10 Rock:15 Country:20 Suggested: Playlist",
            song.toString());
        assertEquals(
            "No-Playlist S1 Pop:5 Rock:2 Country:2",
            otherSong.toString());
    }


    /**
     * Test method for getPlaylistName
     */
    public void testGetPlaylistName()
    {
        assertEquals("Playlist", song.getPlaylistName());
        assertNull(otherSong.getPlaylistName());
    }


    /**
     * Test method for equals
     */
    public void testEquals()
    {
        assertFalse(song.equals(otherSong));

        otherSong = new Song("Song", 10, 15, 20, "Playlist");
        assertEquals(song, otherSong);

        assertFalse(song.equals(null));
        assertTrue(song.equals(song));

        assertFalse(song.equals(""));
    }


    /**
     * Test method for getName
     */
    public void testGetName()
    {
        assertEquals("Song", song.getName());
    }


    /**
     * Test method for getGenreSet
     */
    public void testGetGenreSet()
    {
        GenreSet genreSet = new GenreSet(10, 15, 20);
        assertEquals(genreSet, song.getGenreSet());
        assertEquals(10, song.getGenreSet().getPop());
        assertEquals(15, song.getGenreSet().getRock());
        assertEquals(20, song.getGenreSet().getCountry());
        GenreSet genreSet1 = new GenreSet(10, 10, 10);
        assertFalse(genreSet1.equals(song.getGenreSet()));
    }
}
