package main.java.algo.search;

public class RotatedSortedArrayBinarySearch {
	
	// What about duplicates?
	// See the note at bottom of program.
	
	public static void main(String[] args) {
		int[] a = { 15, 16, 17, 19, 20, 1, 3, 5, 6, 12 };
		//int[] a = {2,2,2,2,3,2,2};
		RotatedSortedArrayBinarySearch s = new RotatedSortedArrayBinarySearch();

		System.out.println(s.search(a, 3, 0, a.length - 1));

	}
	
	// used similar binary search logic, but with a twist
	// see main.java.algo.search.BinarySearch.java first

	// return the index of found element
	public int search(int[] a, int val, int startIdx, int endIdx) {

		if (startIdx > endIdx)
			return -1;

		int midIdx = (startIdx + endIdx) / 2;

		if (val == a[midIdx])
			return midIdx;

		// in a rotated array, from leftIdx to midIdx, 
		//	-	values will be increasing
		//	-	OR will increase to the max and then start from actual first of non-rotated array e.g. 17, 19, 20, 1, 3, 5
		//	-	so any number falling in this, will satisfy val >= a[startIdx] && val < a[midIdx]
		
		// note it should be >= .. equalTo is because the element we are trying to find can be on 0th index
		
		if (val >= a[startIdx] && val < a[midIdx])
			return search(a, val, startIdx, midIdx - 1);

		else
			return search(a, val, midIdx + 1, endIdx);

	}
	
	// Incase of duplicates and rotated sorted array :::
	
	// It doesnÕt give you an efficient result in case of duplicate elements	
	// However, if your array has duplicate entries then we canÕt do better than O(n) which is as good as linear search
	// For example, if the array is [2,2,2,2,2,2,2,2, 3 ,2,2,2,2,2,2,2,2,2,2], there is NO way to find element 3 until you do a linear search


}
