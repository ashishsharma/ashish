package main.java.algo.datastructures;

/*************************************************************************
 *  A generic queue, implemented using a linked list.
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  Queue is (FIFO)
 *  It supports enqueue, dequeue + peeking at the top item, is empty, iterate FIFO
 *  All queue operations except iteration are constant time.
 */
public class Queue<T> implements Iterable<T> {
    private int size;         // number of elements on queue
    private Node first;    // beginning of queue
    private Node last;     // end of queue

    // helper linked list class
    // Note - next pointer links to the element in direction OPPOSITE to which elements go out.
	// It is the next element to be moved out (popped or dequeued)
    // In Queue e.g. {1,2,3,4,5}, elements move out in <-, so next points to element on right 
    // In Stack e.g. {1,2,3,4,5}, elements move out in ->, next points to element on left

    private class Node {
        private T item;
        private Node next;
    }

    public Queue() {
        first = null;
        last  = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;     
    }

   /**
     * Return the item least recently added to the queue.
     * Throw an exception if the queue is empty.
     */
	public T peek() {
		if (isEmpty())
			throw new RuntimeException("Queue underflow");
		return first.item;
	}

   /**
     * Add the item to the queue.
     */
	
    // In Queue e.g. {1,2,3,4,5}, elements move out in <-, so next points to element on right 
	public void enqueue(T item) {
		Node x = new Node();
		x.item = item;
		if (isEmpty()) {
			first = x;
			last = x;
		} else {
			last.next = x;
			last = x;
		}
		size++;
	}

   /**
     * Remove and return the item on the queue least recently added.
     * Throw an exception if the queue is empty.
     */
	public T dequeue() {
		if (isEmpty())
			throw new RuntimeException("Queue underflow");
		T item = first.item;
		first = first.next;
		size--;
		if (isEmpty())
			last = null; // to avoid loitering
		return item;
	}

   /**
     * Return string representation.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this)
            s.append(item + " ");
        return s.toString();
    } 
 

   /**
     * Return an iterator that iterates over the items on the queue in FIFO order.
     */
    public Iterator<T> iterator()  {
        return new ListIterator();  
    }

    // an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<T> {
		private Node current = first;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			T item = current.item;
			current = current.next;
			return item;
		}
	}
	
}


