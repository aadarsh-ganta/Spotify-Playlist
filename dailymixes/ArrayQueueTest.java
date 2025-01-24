package dailymixes;

import student.TestCase;
import queue.EmptyQueueException;

//-------------------------------------------------------------------------
//Virginia Tech Honor Code Pledge:
//
//As a Hokie, I will conduct myself with honor and integrity at all times.
//I will not lie, cheat, or steal, nor will I accept the actions of those who
//do.
//-- Aadarsh Ganta (aadarshg@vt.edu)
// -------------------------------------------------------------------------
/**
 * Test class for ArrayQueue
 * 
 * @author Aadarsh
 * @version Oct 31, 2023
 */
public class ArrayQueueTest
    extends TestCase
{
    // ~ Fields ................................................................
    private ArrayQueue<String> emptyQueue;
    private ArrayQueue<String> queue;
    private ArrayQueue<String> circularQueue;

    // ~Public Methods ........................................................
    /**
     * Sets up test methods
     */
    public void setUp()
    {
        emptyQueue = new ArrayQueue<String>();
        queue = new ArrayQueue<String>();
        queue.enqueue("item1");
        queue.enqueue("item2");
        circularQueue = new ArrayQueue<String>(3);
        circularQueue.enqueue("item1");
        circularQueue.enqueue("item2");
        circularQueue.dequeue();
        circularQueue.enqueue("item3");
        circularQueue.enqueue("item4");
        circularQueue.enqueue("item5");
        circularQueue.dequeue();
    }


    /**
     * Test method for clear
     */
    public void testClear()
    {
        queue.enqueue("item1");
        queue.enqueue("item2");
        queue.clear();
        assertTrue(queue.isEmpty());
    }


    /**
     * Test method for toString
     */
    public void testToString()
    {
        assertEquals("[]", emptyQueue.toString());
        assertEquals("[item1, item2]", queue.toString());
        assertEquals("[item3, item4, item5]", circularQueue.toString());       
    }


    /**
     * Test method for equals
     */
    public void testEquals()
    {
        ArrayQueue<String> otherQueue = new ArrayQueue<String>();
        otherQueue.enqueue("item1");
        assertFalse(queue.equals(otherQueue));

        otherQueue.enqueue("item2");
        assertTrue(queue.equals(otherQueue));
        
        queue.enqueue("item3");
        otherQueue.enqueue("last item");
        assertFalse(queue.equals(otherQueue));

        assertFalse(queue.equals(null));
        assertTrue(queue.equals(queue));

        assertFalse(queue.equals(""));
        
        ArrayQueue<String> newQueue = new ArrayQueue<String>();
        assertTrue(newQueue.equals(emptyQueue));
        assertFalse(otherQueue.equals(emptyQueue));        
    }


    /**
     * Test method for isEmpty
     */
    public void testIsEmpty()
    {
        assertTrue(emptyQueue.isEmpty());
        assertFalse(queue.isEmpty());
    }


    /**
     * Test method for dequeue
     */
    public void testDequeue()
    {
        EmptyQueueException caughtException = null;
        try
        {
            emptyQueue.dequeue();
        }
        catch (EmptyQueueException e)
        {
            caughtException = e;
        }
        assertNotNull(caughtException);
        assertEquals("item1", queue.dequeue());
    }


    /**
     * Test method for enqueue
     */
    public void testEnqueue()
    {
        assertEquals(2, queue.getSize());
        queue.enqueue("item3");
        assertEquals(3, queue.getSize());
    }


    /**
     * Test method for getLengthOfUnderlyingArray
     */
    public void testGetLengthOfUnderlyingArray()
    {
        for (int i = 0; i < 20; i++)
        {
            emptyQueue.enqueue("item");
        }
        assertEquals(21, queue.getLengthOfUnderlyingArray());
        emptyQueue.enqueue("item");
        assertEquals(41, emptyQueue.getLengthOfUnderlyingArray());
        assertEquals(7, circularQueue.getLengthOfUnderlyingArray());
    }


    /**
     * Test method for getFront
     */
    public void testGetFront()
    {
        EmptyQueueException caughtException = null;
        try
        {
            emptyQueue.getFront();
        }
        catch (EmptyQueueException e)
        {
            caughtException = e;
        }
        assertNotNull(caughtException);
        assertEquals("item1", queue.getFront());
        
        assertEquals("item3", circularQueue.getFront());
    }


    /**
     * Test method for getSize
     */
    public void testGetSize()
    {
        assertEquals(0, emptyQueue.getSize());
        assertEquals(2, queue.getSize());
        for (int i = 0; i < 20; i++)
        {
            emptyQueue.enqueue("item");
        }
        assertEquals(20, emptyQueue.getSize());
        emptyQueue.enqueue("item");
        assertEquals(21, emptyQueue.getSize());
        
        assertEquals(3, circularQueue.getSize());

    }


    /**
     * Test method for toArray
     */
    public void testToArray()
    {
        EmptyQueueException caughtException = null;
        try
        {
            emptyQueue.toArray();
        }
        catch (EmptyQueueException e)
        {
            caughtException = e;
        }
        assertNotNull(caughtException);
        
        assertEquals("item1", queue.toArray()[0]);
        assertEquals("item2", queue.toArray()[1]);    
        assertEquals("item3", circularQueue.toArray()[0]);
    }
}
