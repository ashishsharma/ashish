package main.java.strings;

import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicates {
	
	// if not inplace -
	// use hashing algo
	// create a new hashing table, write hashcode
	// for every character, calc the hash and store it at array[hash]
	// if any other character with same hash comes, do not put into array[hash]
	// same algo can be used to count how many times elements were repeated
	
	// to main the order after removing duplicates, hash array should be an array of object (object holds character and its index in original array)
	// OR write hashcode method such that for intems with lower index, hascode is smaller than hashcode of items with bigger array index in original array
	// like 31 * (1000000/index) + intem.data
	
	// it will be done in O(n)
	
	//FOR IN-PLACE, follow the algos below

	public static void main(String[] args) {
		RemoveDuplicates d = new RemoveDuplicates();
		String s = "abcde";
		char[] c = s.toCharArray();
		d.removeDupInPlaceArrangeUniques(c);
		System.out.println(c.toString());
		for (char e : c) {
			System.out.println(e);
		}
	}

	// Remove duplicates
	// in place
	// mark duplicates with 0
	
	// O(n^2)
	public void removeDupInPlace(char[] s) {
		
	    // for every char in outer loop check if that char is already seen.
		// for every char we look from that character -to-> start and see the same character is already present earlier
		// if yes, then the current character is a duplicate

		// i starts from 1, as i=0 is first character and no sense of duplicacy there
		for (int i = 1; i < s.length; i++) {
			
			// j loops from i-1 to 0
			for (int j = i - 1; j >= 0; j--) {
				if ((s[i] != '0') && (s[j] != '0') && (s[i] == s[j])) {
					s[i] = '0';
				}
			}
		}
	}

	
	// Remove duplicates
	// in place
	// mark duplicates with 0
	// arrange uniques on one side
	
	// O(n^2)
	public void removeDupInPlaceArrangeUniques(char[] s) {

		// from 0 - > tail, we will keep all uniques, just like insertion/selection sort add elements to end of a list
		// add
		int tail = 1;
		boolean found = false;
		for (int i = 1; i < s.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				found = false;
				if ((s[i] != '0') && (s[j] != '0') && (s[i] == s[j])) {
					found = true;
					s[i] = '0';
					break;
				}
			}
			// if not a duplicate, we want to keep in the unique list 0 - > tail
			// this will make sure that uniques are on left and 0's are towards right
			// in the loop, when it is go00le - when i = 4, l was not found in 0->3, thus found=false and it goes into.
			// at this point tail would be 3
			// it will replace 0 with l.. making gol00e
			if (!found) {
				swap(s, tail, i);
				tail++;
			}
		}
	}

	public void swap(char[] a, int i, int j) {
		char tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}

	public void removeDuplicatesSet(char[] s)
	{
		// any data types mentioned in <..> is of type object
		// primitives are NOT allowed
		// So, convert into Character object before passing.
		// even in Arrays.asList(...) parameter should be some OBJECT array
		
		Set<Character> resultSet = convertToSet(s);
		
		// set.toArray gives Object Array
		Object[] o = resultSet.toArray();
		
		// this is mistake ->
		// return (char[]) o;
		// Cannot cast from Object[] to char[]
	
		// in set, cannot access elements via index
		// only way is via Iterator or like this
		// first populate the array from set, the 0 all the remaining array spaces
		// or create a new array of size setSize and start populating it from set
		
		int idx = 0;
		for (Character c : resultSet) {
			s[idx] = c;
			idx++;
		}
		
		// 0'ing all the remaining array spaces
		int setSize = resultSet.size();
		while(idx < setSize) {
			s[idx] = '0';
			idx++;
		}
		
	}
	
	public Set<Character> convertToSet(char[] charArray) {
	      Set<Character> resultSet = new HashSet<Character>();
	      for (int i = 0; i < charArray.length; i++) {
	          resultSet.add(new Character(charArray[i]));
	      }
	      return resultSet;
	  }
}
