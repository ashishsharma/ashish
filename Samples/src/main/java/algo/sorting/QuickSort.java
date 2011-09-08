package main.java.algo.sorting;

/*
 * Algorithm
 * 
 * The divide-and-conquer strategy is used in quicksort.
 * 
 * 1) Find Pivot: 
 * We take the VALUE of the middle element as pivot value, but it can be any
 * value, which is in range of sorted values, even if it doesn't present in
 * the array. It is the value of element not any index. (because indexes keep changing when we move items to left & right of pivot)
 * 
 * 2) Partition.
 * Rearrange elements in such a way, that all elements
 * which are lesser than the pivot go to the left part of the array and all
 * elements greater than the pivot, go to the right part of the array.
 * Values equal to the pivot can stay in any part of the array. Notice, that
 * array may be divided in non-equal parts. 
 * 
 * 3) Sort both parts. 
 * Apply quicksort
 * algorithm recursively to the left and the right parts.
 */

public class QuickSort {

	public static void main(String[] args) {
		
		int[] array = { 1, 12, 5, 26, 0, 14, 3, 7, 2 };
		
		new QuickSort().quickSort(array);
		
		for (int a : array) {
			System.out.println(a);
		}
	}

	public void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
	}

	void quickSort(int a[], int startIdx, int endIdx) {
		
		int lIdx = startIdx;
		int rIdx = endIdx;
		int pivotElement;

		// this is the base case of recursion on which the recursion looping exits
		if (endIdx < startIdx)
			return;

		// Note - we are not changing startIdx and endIdx. lIdx and rIdx are being changed when things are moved around. 
		// startIdx and endIdx remain constant and are used to check the start and end of array.
		
		/*
		 * Arbitrarily establishing partition element as the midpoint of the
		 * array.
		 */

		pivotElement = a[(startIdx + endIdx) / 2];

		// loop through the array until indices cross
		while (lIdx <= rIdx) {
			/*
			 * find the first element that is greater than or equal to the
			 * partition element starting from the left Index.
			 */
			// note - here lIdx is checked against endIdx (not pivotIdx - because we compare pivot value and pivot position keeps on changing)

			while ((lIdx < endIdx) && (a[lIdx] < pivotElement))
				++lIdx;

			/*
			 * find an element that is smaller than or equal to the partition
			 * element starting from the right Index.
			 */
			// note - here rIdx is checked against startIdx (not pivotIdx - because we compare pivot value and pivot position keeps on changing)
			while ((rIdx > startIdx) && (a[rIdx] > pivotElement))
				--rIdx;

			// if the indexes have not crossed, swap
			// we kept = condition because we want left and right to increment & cross each other so that while condition can be exited
			if (lIdx <= rIdx) {
				swap(a, lIdx, rIdx);
				++lIdx;
				--rIdx;
			}
		}

		/*
		 * If the right index has not reached the left side of array must now
		 * sort the left partition.
		 */
		if (startIdx < rIdx)
			quickSort(a, startIdx, rIdx);

		/*
		 * If the left index has not reached the right side of array must now
		 * sort the right partition.
		 */
		if (lIdx < endIdx)
			quickSort(a, lIdx, endIdx);

	}
	
	private void swap(int[] array, int idx1, int idx2) {
		int tmp = array[idx1];
		array[idx1] = array[idx2];
		array[idx2] = tmp;
	}

}

/*
 * COMPLEXITY ANALYSIS
 * 
 * Quicksort is a divide and conquer algorithm. In a divide and conquer sorting algorithm the original data is 
 * separated into two parts (divide) which are individually sorted (conquered) and then combined.
 * 
 * Time Complexity - O(n log n)
 * 
 * Space complexity = O(log n) 
 * 
 * 		- actually by partitioning logic I have written - This requires O(1) space.
 * 		- because of the stack layers created for recursion. Since it is divide and conquer, it goes into recursive loop log n times. So O(log n) stack space is used.
 * 		- quicksort's sequential and localized memory references work well with a cache.
 * 
 * Best Case = O(n log n) 
 * 
 * 		- Proof :::
 * 			- occurs when the median divides array in 2 equal parts. It the array contains n elements then the first run will need O(n). 
 * 			- Sorting the remaining two sub-arrays takes 2* O(n/2). 
 * 			- Continuing likewise till the kth step - 2^k T (n/2^k) 
 * 			- This ends up in a performance of O(n log n). (There is a mathematical proof)
 * 
 * Worst case = O(n^2) 
 * 
 * 		- occurs when median is the smallest element and it divides array into 1 and n-1. 
 * 		- Proof ::: 
 * 			- Worst case will occur when the elements are in such an order that anytime it picks a pivot element (depends on the pivot logic also), it always comes to be the smallest element.
 * 			- In that case, it selects only one element in each iteration and has to again call recursion for n-1 elements on pivot's right.
 * 			- So it is O(n) + O(n-1) + (On-2).. O(1) which is equal to O(n^2). (There is a mathematical proof)
 *		- this behavior is very rare. so we dont consider this when we generally talk about QuickSort complexity.
 *		- thus, many people favor to apply quick sort on randomly shuffled array. - even to avoid any such worst case ocurrance.
 * 
 * Average Case - O(n log n) 
 * 
 * 		 - On a randomly shuffled array, it gives O(n log n) - (There is a mathematical proof)
 * 
 *  	- Quicksort is often faster in practice than other O(nlogn) algorithms
 * 
 * */


// heap sort & quick sort - which is better - no direct answer
// heap sort can not be used to access any sequential data like linked List, Quick sort can - but will perform poorly. Best for sequential data is Merge sort.
// quick sort uses log n space. Merge uses n space.