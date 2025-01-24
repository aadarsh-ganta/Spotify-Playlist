package dailymixes;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Aadarsh Ganta (aadarshg@vt.edu)
// -------------------------------------------------------------------------
/**
 * Represents a song object, which contains the song’s name as a string, a
 * GenreSet object, and the song's suggested playlist as a string
 * 
 * @author Aadarsh
 * @version Oct 30, 2023
 */
public class Song
{
    // ~ Fields ................................................................
    private String name;
    private String suggestedPlaylist;
    private GenreSet genreSet;

    /**
     * Creates the song object and initializes the song name, GenreSet, and
     * suggested playlist
     * 
     * @param name
     *            name of song
     * @param pop
     *            pop genre
     * @param rock
     *            rock genre
     * @param country
     *            country genre
     * @param suggested
     *            suggested playlist for song
     */
    // ~ Constructors ..........................................................
    public Song(String name, int pop, int rock, int country, String suggested)
    {
        this.name = name;
        this.genreSet = new GenreSet(pop, rock, country);
        this.suggestedPlaylist = suggested;
    }


    // ~Public Methods ........................................................
    /**
     * Creates string representation of the song
     * 
     * @return song as string
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append(name);
        str.append(" Pop:" + genreSet.getPop());
        str.append(" Rock:" + genreSet.getRock());
        str.append(" Country:" + genreSet.getCountry());
        if (this.suggestedPlaylist == null
            || this.suggestedPlaylist.length() == 0)
        {
            str.insert(0, "No-Playlist ");
        }
        else if (this.suggestedPlaylist.length() > 0)
        {
            str.append(" Suggested: " + this.getPlaylistName());
        }
        return str.toString();
    }


    /**
     * Gets suggestedPlaylist's name
     * 
     * @return suggestedPlaylist
     */
    public String getPlaylistName()
    {
        return suggestedPlaylist;
    }


    /**
     * Checks if two Song objects are equal
     * 
     * @param obj
     *            object to be compared with current object
     * @return true if they are equal; otherwise returns false
     */
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (this.getClass() == obj.getClass())
        {
            Song other = (Song)obj;
            return this.name.equals(other.getName())
                && this.genreSet.equals(other.getGenreSet())
                && this.suggestedPlaylist.equals(other.getPlaylistName());
        }
        return false;
    }


    /**
     * Gets name of song
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Gets GenreSet
     * 
     * @return genreSet
     */
    public GenreSet getGenreSet()
    {
        return genreSet;
    }
}
