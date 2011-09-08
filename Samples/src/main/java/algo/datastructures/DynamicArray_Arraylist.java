package main.java.algo.datastructures;

// Dynamic Resizing Array OR ArrayList

// This is not so good. check the dynamic resizing logic in main.java.algo.datastructures.HashMap.java
// there we use threshold, loadfactor, intital-size

// An ArrayList, or a dynamically resizing array, is an array that resizes itself as needed while still providing O(1) access	
// Each doubling takes O(n) time, but happens so rarely that its amortized time is still O(1)

// Vector is synchronized whereas arraylist is not.


public class DynamicArray_Arraylist {

	private int[] array;
	private int[] tmp;
	private int initArraySize = 1;

	public DynamicArray_Arraylist() {
		array = new int[initArraySize];
	}

	public static void main(String[] args) {
		DynamicArray_Arraylist da = new DynamicArray_Arraylist();
		da.populateElementsUsingDynamicArray();
		for (int e : da.array) {
			System.out.println(e);
		}
	}

	private void populateElementsUsingDynamicArray() {
		int len = array.length;
		for (int i = 0; i < 100; i++) {
			if (i == len) {
				tmp = new int[2 * len];
				//can also be done using Array.copy(...)
				for (int j = 0; j < len; j++) {
					tmp[j] = array[j];
				}
				array = null;
				array = tmp;
				tmp = null;
				len = array.length;
			}
			array[i] = 10 + i;
		}
	}

}
