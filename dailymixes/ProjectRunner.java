package dailymixes;

import java.io.FileNotFoundException;
import java.text.ParseException;

//Virginia Tech Honor Code Pledge:
//
//As a Hokie, I will conduct myself with honor and integrity at all times.
//I will not lie, cheat, or steal, nor will I accept the actions of those who
//do.
//-- Aadarsh Ganta (aadarshg@vt.edu)

// -------------------------------------------------------------------------
/**
 * Demonstrates the functionalities of the PlaylistReader class
 * 
 * @author Aadarsh
 * @version Nov 4, 2023
 */
public class ProjectRunner
{
    // ~ Constructors ..........................................................
    /**
     * Empty constructor
     */
    public ProjectRunner()
    {
    }


    // ~Public Methods ........................................................
    /**
     * Main method for project
     * 
     * @param args
     *            arguments to be inputted
     * @throws ParseException
     * @throws DailyMixDataException
     * @throws FileNotFoundException
     */
    public static void main(String[] args)
        throws ParseException,
        DailyMixDataException,
        FileNotFoundException
    {
        if (args.length == 2)
        {
            String songFile = args[0];
            String playlistFile = args[1];
            PlaylistReader playlistReader =
                new PlaylistReader(songFile, playlistFile);
        }
        else
        {
            String songFile = "input.txt";
            String playlistFile = "playlists.txt";
            PlaylistReader playlistReader =
                new PlaylistReader(songFile, playlistFile);
        }
    }
}
