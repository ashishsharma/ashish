package main.java.algo.datastructures;

import javax.naming.ldap.HasControls;

// THIS CAN BE USED FOR HASHMAP / HASHTABLE / HASHSET
// NOTE - IN add features explained at HashTable_HasMap_HashSet for HASHMAP / HASHTABLE / HASHSET

// Reference http://www.algolist.net/Data_structures/Hash_table

// It is internally an ARRAY of objects (with key and value)
// and on which index of array the object is stored - is determined by hashing

// Each slot of the array contains a link to a singly-linked list containing key-value pairs with the same hash. 
// New key-value pairs are added to the end of the list. Lookup algorithm searches through the list to find matching key. 
// Initially table slots contain nulls. List is being created, when value with the certain hash is added for the first time.

// Collision - resolution by chaining
// if hash function returns the same hash value for different keys? It yields an effect, called collision. 
// Each slot of the hash table contains a link to another data structure (i.e. linked list), which stores 
// key-value pairs with the same hash. When collision occurs, this data structure is searched for key-value pair, which matches the key.

// Dynamic resizing
// Basic underlying data strucutre used to store hash table is an array. 
// The load factor is the ratio between the number of stored items and array's size. 
// Hash table can whether be of a constant size or being dynamically resized, when load factor exceeds some threshold. Resizing is done.

// HashMap does not guarantee that the order of the map will remain constant over time. 

// COMPLEXITY of insertion, removal and lookup operations is constant - O(1)

// Counts number of words in a paragraph - can be done via hashing

public class HashMap<K, V> {

	// underlying data structure is array for hastable/hashmap
	// its just that the elements are stored on indexes calculated by hashcode
	Entry<K, V>[] map;
	
	// this is for dynamic resizing - if array grew to its threshold - we expand it
	int threshold = 0;
	float loadFactor = 0.75f;
	int filledSize = 0;
	
	public static final int DEFAULT_CAPACITY = 128;
	
	// array can have its size only in integer
	// and we are dynamically increasing size. To avoid integer overflow (size > max integer value), we use it.
	public static final int MAX_CAPACITY = Integer.MAX_VALUE;

	// if size is supposed to get bigger than Integer.MAX_VALUE, then we should not use array. Probably the next possible solution is to use the linked list as the underlying data structure.
	
	// How about achieving LinkedHashMap - where order also matters.
	// Then we can use the same approach, but use 2 pointers in Entry Object - next (we'll store current pointer. next  of current points to new added element) and siblings (if hashcode of new elements was same as existing, we'll create a new object and point the sibbling to it.) 
	
	int tableSize = DEFAULT_CAPACITY;

	public HashMap() {
		map = new Entry[tableSize];
		threshold = (int) (tableSize * loadFactor);
	}

	public void put(K key, V value) throws Exception {
		
		if(filledSize == MAX_CAPACITY)
			throw new Exception("Array overflow Exception. Max array size reached");
		
		int hash = calcIndexForPut(key);
		// for the 1st entry on any hashcode index
		if (map[hash] == null) {
			map[hash] = new Entry<K,V>(key, value, null);
			filledSize++;
		}
		else {
			Entry<K, V> n = map[hash];
			//elements are stored as linked-lists, so traverse through the list to add the element
			while (n.next != null && !genericEquals(n.key, key)) {
				n = n.next;
			}
			// if the key was already present, just update the same key with new value
			if (genericEquals(n.key, key)) {
				n.value = value;
			} else {
				// this key was not present on hashcode - add it as a new element to the linked list
				n.next = new Entry<K, V>(key, value, null);
				filledSize++;
			}
		}
		//dynamic resizing
		if(filledSize > threshold)
			resize();
	}

	public V get(K key) {
		// elements are stored on indexes calculated by hashcode
		int hashIndex = calcIndexForGet(key);
		if(hashIndex == -1)
			return null;
		Entry<K, V> e = map[hashIndex];
		
		//no element on hascode index.
		if (e == null)
			return null;
		else {
			while (e.next != null && !genericEquals(e.key,key))
				e = e.next;
			if (genericEquals(e.key,key))
				return e.value;
		}
		return null;
	}

	public void remove(K key) {
		int hashIndex = calcIndexForGet(key);
		if(hashIndex == -1)
			return;
		Entry<K, V> e = map[hashIndex];
		if (e == null)
			return;
		else {
			// search for element
			// it is found - do prev.next = current.next;
			Entry<K, V> prevNode = null;
			
			// traverse till element is found
			while (e.next != null && !genericEquals(e.key,key)) {
				prevNode = e;
				e = e.next;
			}
			if (genericEquals(e.key,key)) {
				// if it was the first element in the linked list
				if (prevNode == null)
					map[hashIndex] = e.next;
				else // if somewhere inbetween the list
					prevNode.next = e.next;
				
				filledSize--;
				// note - when size increased above threshold, we resize it by increasing and re-copying the array. Shouldn't we reduce the size when items are removed it becomes below some threshold. But This will be tricky here, because the indexes will not remain same if we re-copy after some missing elements in between.  
			}
		}
	}

	// Hash function is very important part of hash table design. Hash function is considered to be good, if it provides uniform distribution of hash values.
	// it decides on which index of array, the key-value object is stored
	
	// Underlying array has constant size to store 128 elements and each slot contains key-value pair.
	// Here, we are using an array of 128 length. So to fit elemnts in this size we use - the remainder of division by 128.
	private int calcIndexForPut(K key) {
		return key.hashCode() % tableSize;
	}
	
	// hashmap allows null keys. So, in that case, we need special handling of null key in calculating hashcode.
	
	// since table size keep on increasing, we need to know at what index actually actually a Key was stored
	// that can be found when we find the first table size, where division gave 0.
	// e.g. if default size is 100, then K with hashcode say 215 will be stored in range 200 - 300. and % by 300 will give the actual index.
	// note - while putting keys to hashmap we dont need this function. At that time, just our calcIndexForPut will be enuf. 
	private int calcIndexForGet(K key) {
		int hashcode = key.hashCode();
		for (int i = DEFAULT_CAPACITY; i <= tableSize; i += DEFAULT_CAPACITY) {
			if((hashcode / i) == 0)
				return hashcode % i;
		}
		return -1;
	}
	
	
	// How to check if generic values are equal? - is K key1 and K key2 equal?
	// this is how we can do this.
	private <T> boolean genericEquals(T t1, T t2){
		if(t1.hashCode() == t2.hashCode())
			return true;
		return false;
	}
	
	
	
	// not here we are not making int size=0 - it has to stay where it was
	// array that resizes itself as needed while still providing O(1) access
	// Each doubling takes O(n) time, but happens so rarely that its put/get/remove operation time is still O(1)

	public void resize() throws Exception {
		int newSize = tableSize + DEFAULT_CAPACITY;
		threshold = (int) (newSize * loadFactor);
		// there is only this way - to create new array of resized length and copy old array to it
		Entry<K, V>[] oldArray = map;
		map = new Entry[newSize];
		for (int i = 0; i < oldArray.length; i++) {
			// note - just passing keys and values and NOT .next. 
			// .next is itself constructed again
			put(oldArray[i].key, oldArray[i].value);
		}
	}
	
	// COMPLEXITY analysis - Dynamic resizing
	// resizing is done at once and operation, which triggers resizing, takes O(n) time to complete, where n is a number of entries in the table. 
	// This fact may make dynamic-sized hash tables inappropriate for real-time applications.

}


class Entry<K, V> {
	K key;
	V value;
	Entry<K, V> next;

	public Entry(K key, V value, Entry<K, V> next) {
		this.key = key;
		this.value = value;
		this.next = next;
	}

	public boolean equals(Object obj) {
		try {
			if (this == obj)
				return true;
			else if (obj == null || this.getClass() != obj.getClass())
				return false;
			Entry<K, V> o = (Entry<K, V>) obj;
			// Equals for generics - It is implemented by the actual object passed in place of  generic. E.g. If K is of type String. Then String.equals will be applicable here.
			if (this.key.equals(o.key) && (this.value.equals(o.value)))
				return true;
		} catch (Exception e) {
			return false;
		}
		return false;
	}


	public int hashCode() {
		if (this.key == null)
			return 0;
		int hash = 7;
		hash = (31 * hash) + this.key.hashCode();
		hash = (31 * hash) + this.value.hashCode();
		return hash;
	}

}