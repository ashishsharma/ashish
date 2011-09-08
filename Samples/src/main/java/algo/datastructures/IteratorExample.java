package main.java.algo.datastructures;

// Before you can access a collection through an iterator, you must obtain one. 
// Each of the collection classes provides an iterator( ) method that returns an iterator to the start of the collection. 
// By using this iterator object, you can access each element in the collection, one element at a time. 
// In general, to use an iterator to cycle through the contents of a collection, follow these steps:
//
// Obtain an iterator to the start of the collection by calling the collection's iterator( )
// Set up a loop that makes a call to hasNext( ). Have the loop iterate as long as hasNext( ) returns true.
// Within the loop, obtain each element by calling next( ).
// For collections that implement List, you can also obtain an iterator by calling ListIterator. 
// As explained, a list iterator gives you the ability to access the collection in either the forward or backward direction and lets you modify an element. Otherwise, ListIterator is used just like Iterator.
//

/**
 *Output: 
Original contents of al: C A E B D F 
Modified list backwards: F+ D+ B+ E+ A+ C+ 

 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class IteratorExample {
  public static void main(String args[]) {
    ArrayList<String> al = new ArrayList<String>();

    al.add("C");
    al.add("A");
    al.add("E");
    al.add("B");
    al.add("D");
    al.add("F");

    System.out.print("Original contents of al: ");
    Iterator<String> itr = al.iterator();
    while (itr.hasNext()) {
      String element = itr.next();
      System.out.print(element + " ");
    }
    System.out.println();

    ListIterator<String> litr = al.listIterator();
    while (litr.hasNext()) {
      String element = litr.next();
      litr.set(element + "+");
    }

    // Now, display the list backwards.
    System.out.print("Modified list backwards: ");
    while (litr.hasPrevious()) {
      String element = litr.previous();
      System.out.print(element + " ");
    }
  }
}

     