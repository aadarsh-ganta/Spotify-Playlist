package dailymixes;

// -------------------------------------------------------------------------
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Aadarsh Ganta (aadarshg@vt.edu)

/**
 * Represents an object that contains the percentages of three different genres
 * (pop, rock, and country)
 * 
 * @author Aadarsh
 * @version Oct 30, 2023
 */
public class GenreSet
    implements Comparable<GenreSet>
{
    // ~ Fields ................................................................
    private int pop;
    private int rock;
    private int country;

    /**
     * Takes in the percentages as parameters and initializes the genres
     * 
     * @param pop
     *            pop genre
     * @param rock
     *            rock genre
     * @param country
     *            country genre
     */
    // ~ Constructors ..........................................................
    public GenreSet(int pop, int rock, int country)
    {
        this.pop = pop;
        this.rock = rock;
        this.country = country;
    }


    /**
     * Gets percentage of rock genre
     * 
     * @return rock
     */
    // ~Public Methods ........................................................
    public int getRock()
    {
        return rock;
    }


    /**
     * Gets percentage of pop genre
     * 
     * @return pop
     */
    public int getPop()
    {
        return pop;
    }


    /**
     * Creates a string representation of GenreSet
     * 
     * @return GenreSet as string
     */
    public String toString()
    {
        return "Pop:" + this.pop + " Rock:" + this.rock + " Country:"
            + this.country;
    }


    /**
     * Checks if two GenreSet objects are equal
     * 
     * @param obj
     *            object to be compared to current object
     * @return true if equal; otherwise, returns false
     */
    @Override
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
            GenreSet other = (GenreSet)obj;
            return this.pop == other.getPop() && this.rock == other.getRock()
                && this.country == other.getCountry();
        }
        return false;
    }


    /**
     * Checks if the GenreSet is within a certain range
     * 
     * @param minGenreSet
     *            lowest percentage of genres
     * @param maxGenreSet
     *            highest percentage of genres
     * @return true if GenreSet is between range; otherwise, returns false
     */
    public boolean isWithinRange(GenreSet minGenreSet, GenreSet maxGenreSet)
    {
        return this.isLessThanOrEqualTo(maxGenreSet)
            && minGenreSet.isLessThanOrEqualTo(this);
    }


    /**
     * Checks if the GenreSet is less than equal to another GenreSet
     * 
     * @param other
     *            other GenreSet to be compared with current GenreSet
     * @return true if GenreSet is less than or equal to other GenreSet;
     *             otherwise, returns false
     */
    private boolean isLessThanOrEqualTo(GenreSet other)
    {
        if (other == null)
        {
            return false;
        }
        return this.pop <= other.getPop() && this.rock <= other.getRock()
            && this.country <= other.getCountry();
    }


    /**
     * Compares two GenreSet objects by comparing the sum of the genre
     * percentages
     * 
     * @param other
     *            Other GenreSet to be compared with current GenreSet
     * @return positive number if the current GenreSet is greater or negative
     *             number if the current GenreSet is less; otherwise returns 0
     * @throws IllegalArgumentException
     */
    @Override
    public int compareTo(GenreSet other)
    {
        if (other == null)
        {
            throw new IllegalArgumentException();
        }
        int thisSum = this.pop + this.rock + this.country;
        int otherSum = other.getPop() + other.getRock() + other.getCountry();
        return thisSum - otherSum;
    }


    /**
     * Gets percentages of country genre
     * 
     * @return country
     */
    public int getCountry()
    {
        return country;
    }
}
