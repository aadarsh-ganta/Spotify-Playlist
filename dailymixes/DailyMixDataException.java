package dailymixes;

//Virginia Tech Honor Code Pledge:
//
//As a Hokie, I will conduct myself with honor and integrity at all times.
//I will not lie, cheat, or steal, nor will I accept the actions of those who
//do.
//-- Aadarsh Ganta (aadarshg@vt.edu)
// -------------------------------------------------------------------------
/**
 * This exception will be thrown if data is incorrect in the input files.
 * 
 * @author Aadarsh
 * @version Nov 3, 2023
 */
public class DailyMixDataException
    extends Exception
{
    /**
     * Accepts string as an input so that a error message can be displayed
     * 
     * @param string
     *            message to be displayed
     */
    // ~ Constructor ..........................................................
    public DailyMixDataException(String string)
    {
        super(string);
    }
}
