package main.java.algo.datastructures;


/*************************************************************************
 *  A generic stack, implemented using a linked list.
 *************************************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 *  Stack is (LIFO).
 *  It supports push O(1), pop O(1) + peek O(1), isEmpty O(1), iterate O(N)
 *  All stack operations except iteration are constant time O(1).
 */

public class Stack<T> implements Iterable<T> {
    
	// helper linked list class
    // Note - next pointer links to the element in direction OPPOSITE to which elements go out.
	// It is the next element to be moved out (popped or dequeued)
    // In Queue e.g. {1,2,3,4,5}, elements move out in <-, so next points to element on right 
    // In Stack e.g. {1,2,3,4,5}, elements move out in ->, next points to element on left
    private class Node {
        private T data;
        private Node next;
    }
	
	private int size;          // size of the stack
    private Node top;     // top of stack

    // constructor
    public Stack() {
        top = null;
        size = 0;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

   /**
     * Add the item to the stack.
     */
    public void push(T item) {        
        Node n = new Node();
        n.data = item;
        n.next = top;
        top = n;
        
        size++;
    }

   /**
     * Delete and return the item most recently added to the stack.
     * Throw an exception if no such item exists because the stack is empty.
     */
	public T pop() {
		if (isEmpty())
			throw new RuntimeException("Stack underflow");
		T data = top.data; // save item to return
		top = top.next; // delete first node
		size--;
		return data; // return the saved item
	}


   /**
     * Return the item most recently added to the stack.
     * Throw an exception if no such item exists because the stack is empty.
     */
	public T peek() {
		if (isEmpty())
			throw new RuntimeException("Stack underflow");
		return top.data;
	}

   /**
     * Return string representation.
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Object item : this)
            s.append(item + " ");
        return s.toString();
    }
       

   /**
     * Return an iterator to the stack that iterates through the items in LIFO order.
     */
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator implements Iterator<T> {
		private Node current = top;

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public T next() {
			if (!hasNext())
				throw new NoSuchElementException();
			T item = current.data;
			current = current.next;
			return item;
		}
	}
}