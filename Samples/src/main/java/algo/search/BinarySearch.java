package main.java.algo.search;

public class BinarySearch {

	public static void main(String[] args) {

		BinarySearch s = new BinarySearch();

		int[] a = { 1, 3, 5, 6, 12, 15, 16, 17, 19, 20 };

		System.out.println(s.binarySearch(a, 3, 0, a.length - 1));
	}

	
	// for Binary search, the array has to be sorted first
	public int binarySearch(int[] a, int val, int leftIdx, int rightIdx) {

		// if the element is not there and it is less than the first (smallest) or more than the last (greatest) element
		// then when match is not found, we will keep on doing mid+1 or mid-1. so we need a check to stay within the array.
		if (leftIdx > rightIdx)
			return -1;

		int midIdx = (leftIdx + rightIdx) / 2;

		// element found
		if (val == a[midIdx])
			return midIdx;

		// element lies on left of midIdx.. start -> mid-1
		else if (val < a[midIdx])
			return binarySearch(a, val, leftIdx, midIdx - 1);
		
		// element lies on right of midIdx.. mid+1 -> end
		else if (val > a[midIdx])
			return binarySearch(a, val, midIdx + 1, rightIdx);

		return -1;
	}
}

// Worst case performance	O(log n)
// Best case performance	O(1) // the first mid element matches
// Average case performance	O(log n)
// Worst case space complexity O(1)

