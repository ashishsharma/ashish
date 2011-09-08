package main.java.algo.sorting;

public class BuubleSort {

	/*
	 * Algorithm
	 * 
	 * Compare each pair of adjacent elements from the beginning of an array
	 * and, if they are in reversed order, swap them. If at least one swap has
	 * been done, repeat step 1.
	 */

	public void bubbleSort(int[] arr) {
		boolean swapped = true;
		int j = 0;
		int tmp;
		while (swapped) {
			swapped = false;
			j++;
			// in every one for loop, the largest element is pushed towards the right,
			for (int i = 0; i < arr.length - j; i++) {
				if (arr[i] > arr[i + 1]) {
					tmp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = tmp;
					swapped = true;
				}
			}
		}
	}

}

/*
 * Average and worst case complexity of bubble sort is O(n2). Also, it makes O(n2) swaps in the worst case. Bubble sort is adaptive. It means that for almost sorted array it gives O(n) estimation*/
