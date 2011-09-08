package main.java.concepts;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;

public class HashTable_HasMap_HashSet {

	public static void main(String[] args) {

		
		
		// all - hashtable, hashset, hashmap - use java's hashing to store objects
		
		// Hashtable
		//
		// Hashtable is basically a datastructure to retain values of key-value
		// pair.
		//
		// It didn’t allow null for both key and value.
		// You will get NullPointerException (at run-time) if you add null value.
		// It is synchronized. So it comes with its cost. Only one thread can
		// access in one time

		Hashtable<Integer, String> cityTable = new Hashtable<Integer, String>();
		cityTable.put(1, "aaa");
		cityTable.put(2, "bbb");
		cityTable.put(3, null); /* NullPointerEcxeption at runtime */

		System.out.println(cityTable.get(1));
		System.out.println(cityTable.get(2));
		System.out.println(cityTable.get(3));

		// HashMap

		// Like Hashtable it also accepts key value pair.
		//
		// It allows null for both key and value, but can be only one null key and any null values
		// It is unsynchronized. So come up with better performance

		HashMap<Integer, String> productMap = new HashMap<Integer, String>();
		productMap.put(1, "Keys");
		productMap.put(2, null);

		// HashSet
		//
		// HashSet does not allow duplicate values.
		// It provides add method rather put method.
		// You also use its contain method to check whether the object is
		// already available in HashSet.
		// HashSet can be used where you want to maintain a unique list.

		HashSet<String> stateSet = new HashSet<String>();
		stateSet.add("CA");
		stateSet.add("WI");
		stateSet.add("NY");

		if (stateSet.contains("AA")) /*
									 * if CA, it will not add but shows
									 * following message
									 */
			System.out.println("Already found");
		else
			stateSet.add("AA");

	}
}

// Note -
// One of HashMap's subclasses is LinkedHashMap, so in the event that you'd want predictable iteration order (which is insertion order by default), you could easily swap out the HashMap for a LinkedHashMap. This wouldn't be as easy if you were using Hashtable.
