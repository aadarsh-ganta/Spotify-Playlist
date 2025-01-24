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
 * Test class for GenreSet
 * 
 * @author Aadarsh
 * @version Oct 30, 2023
 */
public class GenreSetTest
    extends TestCase
{
    // ~ Fields ................................................................
    private GenreSet genreSet;
    private GenreSet otherGenreSet;

    // ~Public Methods ........................................................
    /**
     * Sets up test methods
     */
    public void setUp()
    {
        genreSet = new GenreSet(10, 15, 20);
        otherGenreSet = new GenreSet(5, 2, 2);
    }


    /**
     * Test method for getRock
     */
    public void testGetRock()
    {
        assertEquals(15, genreSet.getRock());
    }


    /**
     * Test method for getPop
     */
    public void testGetPop()
    {
        assertEquals(10, genreSet.getPop());
    }


    /**
     * Test method for toString
     */
    public void testToString()
    {
        assertEquals("Pop:10 Rock:15 Country:20", genreSet.toString());
    }


    /**
     * Test method for equals
     */
    public void testEquals()
    {
        assertFalse(genreSet.equals(otherGenreSet));

        otherGenreSet = new GenreSet(10, 15, 20);
        assertEquals(genreSet, otherGenreSet);

        assertFalse(genreSet.equals(null));
        assertTrue(genreSet.equals(genreSet));

        assertFalse(genreSet.equals(""));
        
        assertEquals(genreSet.getPop(), otherGenreSet.getPop());
        assertEquals(genreSet.getRock(), otherGenreSet.getRock());
        assertEquals(genreSet.getCountry(), otherGenreSet.getCountry());

    }


    /**
     * Test method for isWithinRange
     */
    public void testIsWithinRange()
    {
        GenreSet minGenreSet = new GenreSet(1, 1, 1);
        GenreSet maxGenreSet = new GenreSet(50, 50, 50);
        assertFalse(genreSet.isWithinRange(null, null));
        assertTrue(genreSet.isWithinRange(minGenreSet, maxGenreSet));

        GenreSet minGenreSet1 = new GenreSet(30, 30, 30);
        assertFalse(genreSet.isWithinRange(minGenreSet1, maxGenreSet));

        GenreSet minGenreSet2 = new GenreSet(15, 30, 30);
        assertFalse(genreSet.isWithinRange(minGenreSet2, maxGenreSet));

        GenreSet maxGenreSet1 = new GenreSet(1, 1, 1);
        assertFalse(genreSet.isWithinRange(minGenreSet, maxGenreSet1));

        GenreSet equalMaxGenreSet = new GenreSet(10, 15, 20);
        GenreSet equalMinGenreSet = new GenreSet(5, 2, 2);
        assertTrue(genreSet.isWithinRange(equalMinGenreSet, equalMaxGenreSet));
    }


    /**
     * Test method for compareTo
     */
    public void testCompareTo()
    {
        IllegalArgumentException caughtException = null;
        try
        {
            genreSet.compareTo(null);
        }
        catch (IllegalArgumentException e)
        {
            caughtException = e;
        }
        assertNotNull(caughtException);

        assertEquals(-36, otherGenreSet.compareTo(genreSet));
        assertEquals(36, genreSet.compareTo(otherGenreSet));
        GenreSet equalGenreSet = new GenreSet(10, 15, 20);
        assertEquals(0, genreSet.compareTo(equalGenreSet));
    }


    /**
     * Test method for getCountry
     */
    public void testGetCountry()
    {
        assertEquals(20, genreSet.getCountry());
    }
}
