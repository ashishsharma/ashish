package main.java.algo.sorting;

public class SelectionSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SelectionSort s = new SelectionSort();
		int[] array = { 5, 1, 12, -5, 16, 2, 12, 14 };
		array = s.selectionSort(array);
		for (int a : array) {
			System.out.println(a);
		}
	}

	/*
	 * ALGORITHM: 
	 * Array is imaginary divided into two parts - sorted one and
	 * unsorted one. At the beginning, sorted part is empty, while unsorted one
	 * contains whole array. At every step, algorithm finds minimal element in
	 * the unsorted part and adds it to the end of the sorted one. When unsorted
	 * part becomes empty, algorithm stops.
	 */

	private int[] selectionSort(int[] array) {
		int length = array.length;
		int tmp = 0, minIndex = 0;
		for (int i = 0; i < length; i++) {
			minIndex = i;
			for (int j = i + 1; j < length; j++) {
				if (array[j] < array[minIndex]) {
					minIndex = j;
				}
			}
			// if in the above loop no min index was found, that means that
			// array[i] was at its correct sorted position. eg. array[1] = 1.
			// swapping should be done, only if there is something to be swapped
			if (minIndex != i) {
				tmp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = tmp;
			}
		}
		return array;
	}

	/*
	 * COMPLEXITY: 
	 * The outer loop goes n times. 
	 * The inner loop goes n - i - 1
	 * times. i is the count of the outer loop. 
	 * Thus, total iterations for all values of i 0-to->n = (n-1) + (n-2) + (n-3) + (n-4).... 2 + 1 
	 * which is sum of n-1 numbers = Sum(n-1) = (n-1)(n-1 - 1)/2 ~ n square
	 * Thus complexity O(n^2)
	 * 
	 * Space complexity - It is inplace with use of very few constants.
	 * Thus -> In Place. O(1)
	 * 
	 *  *******************************
	 * 
	 * Data structure	Array
	 * Worst case performance	О(n2) // (even when list is "descending'ly" sorted, it has to find min looking through all)
	 * Best case performance	О(n2) // (even when list is "ascending'ly" sorted, it has to find min looking through all)
	 * Average case performance	О(n2) // because it has to find min looking through all.
	 * 
	 * Worst case space complexity	О(1) -  in place
	 * 
	 * *******************************
	 */
}
