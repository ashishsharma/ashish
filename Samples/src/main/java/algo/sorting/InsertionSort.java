package main.java.algo.sorting;

public class InsertionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InsertionSort s = new InsertionSort();
		int[] array = { 7, -5, 2, 16, 4 };
		array = s.insertionSort(array);
		for (int a : array) {
			System.out.println(a);
		}
	}

	/* ALGORITHM:
	 * Insertion sort algorithm somewhat resembles selection sort. Array is
	 * imaginary divided into two parts - sorted one and unsorted one. At the
	 * beginning, sorted part contains first element of the array and unsorted
	 * one contains the rest. At every step, algorithm takes first element in
	 * the unsorted part and inserts it to the right place of the sorted one.
	 * When unsorted part becomes empty, algorithm stops.
	 */

	private int[] insertionSort(int[] array) {
		int length = array.length;
		int tmp = 0;
		for (int i = 1; i < length; i++) {
			for (int j = i; j > 0; j--) {
				if (array[j -1] > array[j]) {
					tmp = array[j];
					array[j] = array[j-1];
					array[j-1] = tmp;
				}
			}
		}
		return array;
	}
	
	/*
	 * COMPLEXITY:
	 * The outer loop goes n times
	 * Inner nexted loop goes i-1 times. where i is the counter of outer loop.
	 * Since i ranges 0 -to->n , for complexity we take the worst case for i=n
	 * Thus total iterations = n * (n-1)
	 * Complexity = n square
	 * 
	 * Space complexity - It is inplace with use of very few constants.
	 * Thus -> In Place. O(1)
	 * 
	 * Best Case = O(n) // occurs when the list is already sorted ascending - it picks up the next element and just inserts into the last spot of sorted array.
	 * Worst case = O(n^2) // occurs when the list is already sorted descending - when it picks up the new element, it has to loop through entire sorted array and place the element at 1st place
	 * Average Case - O(n^2) // although the average-case running time is approximately half of the worst-case running time, it is still a quadratic function of n.
	 * 
	 * */
	
	
	// COMPARISON
	//
	// 1) Lookups
	//		- In every iteration, Selection for always looks through the remaining elements to find min.
	//		- But Insertion sort, picks the element and just loops through k-1 times to fit the element at its place.
	//		- This Insertion sort is faster in performance.	
	//
	// 2) Writes
	//		- Because of above reason, Selection sort always does 1 swap per iteration	
	//		- Insertion does swap k-1 times till it has found the rough place for picked element
	//		- Thus insertion sort should be avoided when writes are expensive compared to reads.
	//	
	// 3) Variants
	//		- Selection sort does not vary in best/worst case or the oredering of list
	//		- Insertion sort performs better in desc ordered rather than asc ordered.
	//	
	// 4) Use selection fort 10-20 elements
	//	Use Insertion sort for 20-80 elements.
	//	
	//	
	//	
}
