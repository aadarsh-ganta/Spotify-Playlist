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
 * Test class for Playlist
 * 
 * @author Aadarsh
 * @version Nov 3, 2023
 */
public class PlaylistTest
    extends TestCase
{
    // ~ Fields ................................................................
    private Playlist playlist;
    private Playlist otherPlaylist;
    private Song song1;
    private Song song2;

    /**
     * Sets up test methods
     */
    // ~Public Methods ........................................................
    public void setUp()
    {

        playlist = new Playlist("First Playlist", 2, 2, 2, 10, 10, 10, 3);
        otherPlaylist = new Playlist("Second Playlist", 5, 5, 5, 15, 15, 15, 2);
        song1 = new Song("Song", 10, 5, 5, "Playlist");
        song2 = new Song("S1", 5, 2, 2, "P1");
        playlist.addSong(song1);
        playlist.addSong(song2);
    }


    /**
     * Test method for getMinGenreSet
     */
    public void testGetMinGenreSet()
    {
        GenreSet genreSet = new GenreSet(2, 2, 2);
        assertEquals(genreSet, playlist.getMinGenreSet());
    }


    /**
     * Test method for setName
     */
    public void testSetName()
    {
        assertEquals("First Playlist", playlist.getName());
        playlist.setName("Playlist1");
        assertEquals("Playlist1", playlist.getName());
    }


    /**
     * Test method for getSpacesLeft
     */
    public void testGetSpacesLeft()
    {
        assertEquals(1, playlist.getSpacesLeft());
    }


    /**
     * Test method for getMaxGenreSet
     */
    public void testGetMaxGenreSet()
    {
        GenreSet genreSet = new GenreSet(10, 10, 10);
        assertEquals(genreSet, playlist.getMaxGenreSet());
    }


    /**
     * Test method for comapreTo
     */
    public void testCompareTo()
    {
        assertEquals(1, playlist.compareTo(otherPlaylist));
        Playlist newPlaylist =
            new Playlist("Third Playlist", 2, 2, 2, 10, 10, 10, 2);
        assertEquals(1, playlist.compareTo(newPlaylist));
        
        newPlaylist = new Playlist("Third Playlist", 2, 2, 2, 10, 10, 10, 3);
        newPlaylist.addSong(song1);
        assertEquals(-1 , playlist.compareTo(newPlaylist));
        
        newPlaylist = new Playlist("Third Playlist", 1, 1, 1, 10, 10, 10, 3);
        newPlaylist.addSong(song1);
        newPlaylist.addSong(song2);
        assertEquals(3 , playlist.compareTo(newPlaylist));
        
        newPlaylist = new Playlist("Third Playlist", 2, 2, 2, 20, 20, 20, 3);
        newPlaylist.addSong(song1);
        newPlaylist.addSong(song2);
        assertEquals(-30 , playlist.compareTo(newPlaylist));
        
        newPlaylist = new Playlist("Third Playlist", 2, 2, 2, 10, 10, 10, 3);
        newPlaylist.addSong(song1);
        newPlaylist.addSong(song2);
        assertEquals(-14, playlist.compareTo(newPlaylist));        
    }


    /**
     * Test method for getNumberOfSongs
     */
    public void testGetNumberOfSongs()
    {
        assertEquals(2, playlist.getNumberOfSongs());
    }


    /**
     * Test method for addSong
     */
    public void testAddSong()
    {
        Song otherSong = new Song("Song", 20, 20, 20, "P1");
        assertFalse(playlist.addSong(otherSong));
        assertEquals(2, playlist.getNumberOfSongs());
        assertTrue(playlist.addSong(song2));
        assertEquals(3, playlist.getNumberOfSongs()); 
        assertFalse(playlist.addSong(otherSong));
        Song newSong = new Song("Song", 5, 5, 5, "P1");
        assertFalse(playlist.addSong(otherSong));
    }


    /**
     * Test method for toString
     */
    public void testToString()
    {
        assertEquals(
            "Playlist: First Playlist, # of songs: 2 (cap: 3), "
                + "Requires: Pop:2%-10%, Rock:2%-10%, Country:2%-10%",
            playlist.toString());
    }


    /**
     * Test method for isFull
     */
    public void testIsFull()
    {
        playlist.addSong(song1);
        assertTrue(playlist.isFull());
    }


    /**
     * Test method for equals
     */
    public void testEquals()
    {
        assertFalse(playlist.equals(otherPlaylist));
        Playlist newPlaylist =
            new Playlist("First Playlist", 2, 2, 2, 10, 10, 10, 3);
        newPlaylist.addSong(song1);
        newPlaylist.addSong(song2);
        assertTrue(playlist.equals(newPlaylist));
        assertEquals(playlist, newPlaylist);
        assertFalse(playlist.equals(null));
        assertTrue(playlist.equals(playlist));
        assertFalse(playlist.equals(""));
        newPlaylist = new Playlist("First Playlist", 5, 5, 5, 10, 10, 10, 3);
        assertFalse(playlist.equals(newPlaylist));
        newPlaylist = new Playlist("First Playlist", 2, 2, 2, 20, 20, 20, 3);
        assertFalse(playlist.equals(newPlaylist));
        newPlaylist = new Playlist("First Playlist", 2, 2, 2, 10, 10, 10, 1);
        assertFalse(playlist.equals(newPlaylist)); 
        newPlaylist = new Playlist("First Playlist", 2, 2, 2, 10, 10, 10, 3);
        assertFalse(playlist.equals(newPlaylist));
        newPlaylist = new Playlist("First Playlist", 2, 2, 2, 10, 10, 10, 3);
        Playlist newPlaylist1 = 
            new Playlist("First Playlist", 2, 2, 2, 10, 10, 10, 3);
        assertTrue(newPlaylist.equals(newPlaylist1));
        newPlaylist = new Playlist("First Playlist", 2, 2, 2, 10, 10, 10, 3);
        newPlaylist.addSong(song2);
        newPlaylist.addSong(song1);
        assertFalse(playlist.equals(newPlaylist));
    }


    /**
     * Test method for getSongs
     */
    public void testGetSongs()
    {
        assertEquals(song1, playlist.getSongs()[0]);
        assertEquals(song2, playlist.getSongs()[1]);
    }


    /**
     * Test method for getCapacity
     */
    public void testGetCapacity()
    {
        assertEquals(3, playlist.getCapacity());
    }


    /**
     * Test method for getName
     */
    public void testGetName()
    {
        assertEquals("First Playlist", playlist.getName());
    }


    /**
     * Test method for isQualified
     */
    public void testIsQualified()
    {
        assertTrue(playlist.isQualified(song1));
        Song otherSong = new Song("Song", 20, 20, 20, "P1");
        assertFalse(playlist.isQualified(otherSong));
    }
}
