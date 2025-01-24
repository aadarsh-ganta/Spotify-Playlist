package dailymixes;

// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Aadarsh Ganta (aadarshg@vt.edu)
// -------------------------------------------------------------------------
/**
 * Represents a playlist object, which contains the playlist's name as a string,
 * the minimum and maximum percentages for the genres as a GenreSet object, an
 * array of songs, and the capacity of the playlist
 * 
 * @author Aadarsh
 * @version Nov 2, 2023
 */
public class Playlist
    implements Comparable<Playlist>
{
    // ~ Fields ................................................................
    private GenreSet minGenreSet;
    private GenreSet maxGenreSet;
    private Song[] songs;
    private int capacity;
    private int numberOfSongs;
    private String name;

    /**
     * Creates the Playlist object and initalizes the playlist name, the minimum
     * and maximum GenreSets, the number of songs in the playlist, and the
     * capacity of the playlist
     * 
     * @param playlistName
     *            name of playlist
     * @param minPop
     *            minimum percentage for pop genre
     * @param minRock
     *            minimum percentage for rock genre
     * @param minCountry
     *            minimum percentage for country genre
     * @param maxPop
     *            maximum percentage for pop genre
     * @param maxRock
     *            maximum percentage for pop genre
     * @param maxCountry
     *            maximum percentage for pop genre
     * @param playlistCap
     *            capacity of playlist
     */
    // ~ Constructors ..........................................................
    public Playlist(
        String playlistName,
        int minPop,
        int minRock,
        int minCountry,
        int maxPop,
        int maxRock,
        int maxCountry,
        int playlistCap)
    {
        this.minGenreSet = new GenreSet(minPop, minRock, minCountry);
        this.maxGenreSet = new GenreSet(maxPop, maxRock, maxCountry);
        this.songs = new Song[playlistCap];
        this.capacity = playlistCap;
        this.numberOfSongs = 0;
        this.name = playlistName;
    }


    // ~Public Methods ........................................................
    /**
     * Gets minimum GenreSet
     * 
     * @return minGenreSet
     */
    public GenreSet getMinGenreSet()
    {
        return minGenreSet;
    }


    /**
     * Sets name of playlist
     * 
     * @param playlistName
     *            current name of playlist
     */
    public void setName(String playlistName)
    {
        this.name = playlistName;
    }


    /**
     * Gets remaining number of spaces in playlist
     * 
     * @return remaining spaces in playlist
     */
    public int getSpacesLeft()
    {
        return capacity - numberOfSongs;
    }


    /**
     * Gets maximum GenreSet
     * 
     * @return maxGenreSet
     */
    public GenreSet getMaxGenreSet()
    {
        return maxGenreSet;
    }


    /**
     * Compares two Playlist objects based on their capacity, then by the number
     * of spaces left, then by their minimum GenreSets, then by their maximum
     * GenreSets, and finally by their names if all the previous comparisons are
     * equal
     * 
     * @return positive number if the current playlist is greater or negative
     *             number if the current playlist is less than the other
     *             playlist
     */
    @Override
    public int compareTo(Playlist other)
    {
        if (this.capacity - other.capacity != 0)
        {
            return this.capacity - other.capacity;
        }
        if (this.getSpacesLeft() - other.getSpacesLeft() != 0)
        {
            return this.getSpacesLeft() - other.getSpacesLeft();
        }
        if (this.minGenreSet.compareTo(other.getMinGenreSet()) != 0)
        {
            return this.minGenreSet.compareTo(other.getMinGenreSet());
        }
        if (this.maxGenreSet.compareTo(other.getMaxGenreSet()) != 0)
        {
            return this.maxGenreSet.compareTo(other.getMaxGenreSet());
        }
        return this.name.compareTo(other.getName());
    }


    /**
     * Gets number of songs in playlist
     * 
     * @return numberOfSongs
     */
    public int getNumberOfSongs()
    {
        return numberOfSongs;
    }


    /**
     * Adds song to playlist
     * 
     * @param newSong
     *            song to be added
     * @return true if the song can be added; otherwise returns false
     */
    public boolean addSong(Song newSong)
    {
        if (!isFull() && isQualified(newSong))
        {
            songs[numberOfSongs] = newSong;
            numberOfSongs++;
            return true;
        }
        return false;
    }


    /**
     * Creates a string representation of the playlist
     * 
     * @return playlist as string
     */
    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Playlist: " + name + ",");
        str.append(" # of songs: " + numberOfSongs);
        str.append(" (cap: " + capacity + "),");
        str.append(
            " Requires: Pop:" + minGenreSet.getPop() + "%-"
                + maxGenreSet.getPop() + "%,");
        str.append(
            " Rock:" + minGenreSet.getRock() + "%-" + maxGenreSet.getRock()
                + "%,");
        str.append(
            " Country:" + minGenreSet.getCountry() + "%-"
                + maxGenreSet.getCountry() + "%");
        return str.toString();
    }


    /**
     * Checks if the playlist is full
     * 
     * @return true if full; otherwise return false
     */
    public boolean isFull()
    {
        return getSpacesLeft() == 0;
    }


    /**
     * Checks if two playlists are equal
     * 
     * @param obj
     *            object to be compared with current object
     * @return true if they are equal; otherwise return false
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
            Playlist other = (Playlist)obj;
            if (this.name.equals(other.getName())
                && this.minGenreSet.equals(other.getMinGenreSet())
                && this.maxGenreSet.equals(other.getMaxGenreSet())
                && this.capacity == other.getCapacity()
                && this.numberOfSongs == other.getNumberOfSongs())
            {
                if (this.numberOfSongs == 0 && other.getNumberOfSongs() == 0)
                {
                    return true;
                }
                for (int i = 0; i < numberOfSongs; i++)
                {
                    if (!this.songs[i].equals(other.songs[i])) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }


    /**
     * Gets songs in playlist as an array
     * 
     * @return songs
     */
    public Song[] getSongs()
    {
        return songs;
    }


    /**
     * Gets capacity of playlist
     * 
     * @return capacity
     */
    public int getCapacity()
    {
        return capacity;
    }


    /**
     * Gets name of playlist
     * 
     * @return name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Checks if a song is qualified to go into a certain playlist
     * 
     * @param song
     *            song to be checked
     * @return true if qualified; otherwise returns false
     */
    public boolean isQualified(Song song)
    {
        return song.getGenreSet().isWithinRange(minGenreSet, maxGenreSet);
    }
}
