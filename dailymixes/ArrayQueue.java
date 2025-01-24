package dailymixes;

import queue.QueueInterface;
import queue.EmptyQueueException;

// -------------------------------------------------------------------------
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Aadarsh Ganta (aadarshg@vt.edu)
// -------------------------------------------------------------------------
/**
 * Implements the queue interface to represent a circular-based array that
 * contains one empty slot.
 * 
 * @author Aadarsh
 * @version Oct 31, 2023
 * @param <T>
 */

public class ArrayQueue<T>
    implements QueueInterface<T>
{
    /**
     * Sets default capacity to 20
     */
    // ~ Fields ................................................................
    public static final int DEFAULT_CAPACITY = 20;
    private T[] queue;
    private int dequeueIndex;
    private int size;
    private int enqueueIndex;

    // ~ Constructors ..........................................................
    /**
     * Takes the capacity as input and initializes the queue to have a length
     * that is one greater than the capacity. Also initializes enqueue index to
     * be the last index in the array, dequeue index to be the first index, and
     * the size to zero.
     * 
     * @param initialCapacity
     *            capacity of the array queue
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int initialCapacity)
    {
        queue = (T[])new Object[initialCapacity + 1];
        enqueueIndex = queue.length - 1;
        dequeueIndex = 0;
        size = 0;
    }


    /**
     * Default constructor that has a default capacity of 20
     */
    public ArrayQueue()
    {
        this(DEFAULT_CAPACITY);
    }


    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Clears the array queue
     */
    @Override
    @SuppressWarnings("unchecked")
    public void clear()
    {
        queue = (T[])new Object[DEFAULT_CAPACITY + 1];
        enqueueIndex = queue.length - 1;
        dequeueIndex = 0;
        size = 0;
    }


    /**
     * Increases the index to account for wrap around
     * 
     * @param index
     *            index to be increased
     */
    private int incrementIndex(int index)
    {
        return ((index + 1) % queue.length);
    }


    /**
     * Creates string representation of array queue
     * 
     * @return array queue as a string
     */
    public String toString()
    {
        if (isEmpty())
        {
            return "[]";
        }
        StringBuilder str = new StringBuilder();
        str.append('[');
        boolean firstItem = true;
        int j = 0;
        for (int i = dequeueIndex; i != enqueueIndex + 1; i = incrementIndex(i))
        {
            if (!firstItem)
            {
                str.append(", ");
            }
            else
            {
                firstItem = false;
            }
            str.append(String.valueOf(queue[i]));
            j++;
        }
        str.append(']');
        return str.toString();
    }


    /**
     * Checks if two ArrayQueue objects are equal
     * 
     * @param obj
     *            object to be compared with current object
     * @return true if they are equal; otherwise returns false
     */
    @SuppressWarnings("unchecked")
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
        if (this.getClass().equals(obj.getClass()))
        {
            ArrayQueue<T> other = (ArrayQueue<T>)obj;
            if (this.size == 0 && other.getSize() == 0)
            {
                return true;
            }
            if (this.size == other.getSize())
            {
                Object[] thisArray = this.toArray();
                Object[] otherArray = other.toArray();
                for (int i = 0; i < thisArray.length; i++)
                {
                    if (!thisArray[i].equals(otherArray[i]))
                    {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }


    // ----------------------------------------------------------
    /**
     * Checks if the array queue is empty
     * 
     * @return true if empty; otherwise returns false
     */
    @Override
    public boolean isEmpty()
    {
        return (incrementIndex(enqueueIndex) == dequeueIndex);
    }


    // ----------------------------------------------------------
    /**
     * Removes the first element in the array queue
     * 
     * @throws EmptyQueueException
     */
    @Override
    public T dequeue()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
        T removed = queue[dequeueIndex];
        queue[dequeueIndex] = null;
        dequeueIndex = incrementIndex(dequeueIndex);
        size--;
        return removed;
    }


    /**
     * Doubles the length and capacity of the array if it is full
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity()
    {
        if (isFull())
        {
            T[] oldQueue = queue;
            int oldLength = oldQueue.length;
            int oldCapacity = oldLength - 1;
            int newCapacity = 2 * oldCapacity;
            int newLength = newCapacity + 1;
            queue = (T[])new Object[newLength];
            int j = dequeueIndex;
            for (int i = 0; i < oldLength - 1; i++)
            {
                queue[i] = oldQueue[j];
                j = incrementIndex(j);
            }
            dequeueIndex = 0;
            enqueueIndex = oldLength - 2;
        }
    }


    // ----------------------------------------------------------
    /**
     * Adds an element to the end of the array queue
     * 
     * @param newEntry
     *            item to be added
     */
    @Override
    public void enqueue(T newEntry)
    {
        ensureCapacity();
        enqueueIndex = incrementIndex(enqueueIndex);
        queue[enqueueIndex] = newEntry;
        size++;
    }


    /**
     * Gets the length of the array queue
     * 
     * @return length of array queue
     */
    public int getLengthOfUnderlyingArray()
    {
        return queue.length;
    }


    // ----------------------------------------------------------
    /**
     * Gets the first element in the array queue
     * 
     * @return first element
     * @throws EmptyQueueException
     */
    @Override
    public T getFront()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
        return queue[dequeueIndex];
    }


    /**
     * Gets the size of the array queue
     * 
     * @return size
     */
    public int getSize()
    {
        return size;
    }


    /**
     * Checks if the array queue is full
     * 
     * @return true if full; otherwise return false
     */
    private boolean isFull()
    {
        return (enqueueIndex + 2) % queue.length == dequeueIndex;
    }


    /**
     * Creates a new array that contains the elements from the first index
     * 
     * @return array of elements
     * @throws EmptyQueueException
     */
    @SuppressWarnings("unchecked")
    public Object[] toArray()
    {
        if (isEmpty())
        {
            throw new EmptyQueueException();
        }
        T[] copy = (T[])new Object[size];
        int j = 0;
        for (int i = dequeueIndex; i != enqueueIndex + 1; i = incrementIndex(i))
        {
            copy[j] = queue[i];
            j++;
        }
        return copy;
    }
}
