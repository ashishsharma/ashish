package main.java.algo.sorting;


// First it divides a data set in half, and sorts each half separately. 
// Next, the first elements from each of the two lists are compared. 
// The lesser element is then removed from its list and added to the final result list.


// Why divide & conquer ?
// 1) A small list will take fewer steps to sort than a large list.
// 2) Fewer steps are required to construct a sorted list from two sorted lists than from two unsorted lists. 

public class MergeSort {
	
	public static void main(String[] args) {
		
		int[] data = {18,  6,  9,  1,  4, 15, 12,  5,  6,  7, 11};

		
		MergeSort m = new MergeSort();
		int[] sorted = m.mergeSort(data);
		for(int a: sorted) {
			System.out.println(a);
		}		
	}
	
	/*
	 * answer[] = ms ({18,  6,  9,  1,  4, 15, 12,  5,  6,  7, 11})

				left[] = ms ({18,  6,  9,  1,  4})
							
							left[] = ms [{18,  6})
								
									left[] = ms({18}) = 18
									right[]	= ms({6}) = 6
									merge(left,right) = merge(18,6) 
									= {6,18}
							
							right[] = ms ({9,1,4})
									
									left[] = ms({9}) = 9
									right[]	= ms({1,4})
											
												left[] = ms ({1}) = 1
												right[] ms ({4}) = 4
												merge(left,right) = merge(1,4) 
												= {1,4}
											
									merge(left,right) = merge({9},{1,4}) 
									= {1,4,9}
									
							merge(left,right) = merge({6,18},{1,4,9}) 
							={1,4,6,9,18}
							
				right[] = ms ({15, 12,  5,  6,  7, 11})
							....
							= {5,6,7,11,12,15}
							
				merge(left,right) = merge({1,4,6,9,18},{5,6,7,11,12,15}) 
				
				= {1,4,5,6,7,9,11,12,15,18}
				
				
	 * 
	 * */

	public int[] mergeSort(int[] data) {
		// since it is recursion - always remember to write the base case
		if(data == null || data.length == 1)
			return data;
		
		int middleIdx = data.length/2;
		
		//start-index : inclusive, end-index : exclusive
		//Divide array into left & right till we reach the smallest array (with only one element)
		int left[] = mergeSort(subArray(data, 0, middleIdx));
		int right[] = mergeSort(subArray(data, middleIdx, data.length));
		
		//consider the array represented as a binary tree
		//then at every level - sort using merge sort.
		//when the recursion moves backwards - we sort smallest groups, merging them into bigger and so on.. till we get the elements in the input array.
		int res[] =  merge(left, right);
		for(int a: res) {
			System.out.println(a);
		}
		System.out.println("===============");
		return res;
	}
	
	/*
	 * We merge from bottom to top, means smaller sub arrays gets merged (and sorted).
	 * On very 1st merge(..) call - there will be inly one element in input arrays. left={18} and right={6} gives sorted {6,18} and so on..
	 * So left[] and right[] params at any point are sorted in itself and we need to merge them.
	 * Thus, the smallest element among left[] and right[] - must sit at top of one of the lists. 
	 * It can be removed and put into result[]. The 2nd smallest again lies in the top position in one of the left or right arrays. 
	 * After removing elements, if one list is exhausted - blindly copy the elements from the other list to the result.
	 */
	private int[] merge(int[] left, int[] right) {

		if (left == null || left.length == 0)
			return right;
		else if (right == null || right.length == 0)
			return left;

		int resultSize = left.length + right.length;
		int[] result = new int[resultSize];
		int lIdx = 0, rIdx = 0, rsltIdx = 0;
		while (rsltIdx < resultSize) {
			if (rIdx == right.length) {
				result[rsltIdx] = left[lIdx];
				lIdx++;
			} else if (lIdx == left.length) {
				result[rsltIdx] = right[rIdx];
				rIdx++;
			} else if (left[lIdx] < right[rIdx]) {
				result[rsltIdx] = left[lIdx];
				lIdx++;
			} else if (right[rIdx] < left[lIdx]) {
				result[rsltIdx] = right[rIdx];
				rIdx++;
			} else {
				//same elements on top in left & right
				//moving only one element
				result[rsltIdx] = left[lIdx];
				lIdx++;
			}
			rsltIdx++;
		}
		return result;
	}
	
	
	//start-index : inclusive, end-index : exclusive
	private int[] subArray(int[] data, int startIdx, int endIdx) {
		int[] subArray = new int[endIdx - startIdx];
		//populating the sub-array
		//dataIdx < endIdx : since end-index is exclusive
		for (int dataIdx = startIdx, subArrayIdx = 0; dataIdx < endIdx; dataIdx++, subArrayIdx++) {
			subArray[subArrayIdx] = data[dataIdx];
		}
		return subArray;
	}
	
	
	
}

// Sorting in-place - is possible but complicated -- use linklist instead of array


/*
 * COMPLEXITY ANALYSIS
 * 
 * MergeSort is a divide and conquer algorithm. 
 * 
 * If the running time of merge sort for a list of length n is T(n), then the recurrence T(n) = 2T(n/2)... unto kth recurrence = 2^k T (n/2^k) 
 * 
 * Time Complexity - O(n log n)
 * 
 *  	- Loop executes recursively log n times -- O(log n)
 * 		- In Every loop, a merge function in called used to merge n elements ([k] and [n-k]) -- O(n)
 * 		- Total =  O(n) * O(log n)   =   O(n log n)
 * 
 * Space complexity = O(n) 
 * 
 * 		- O(n) - used in merge function for the result array
 * 		- O(log n) - used in stack for log n recurrsive calls
 * 		- O(n) + O(log n)  =  O(n) 
 * 	
 * Best Case = O(n log n) 
 * 
 * 
 * Worst case = O(n log n) 
 * 
 * 
 * Average Case - O(n log n) 
 * 

 * 
 * */


//************************************************ 

// Comparisons
// heapsort has same time complexity as merge sort, but requires O(1) space for sorting [once the heap is created], as compared to O(n) and is faster in implementation.

//************************************************ 

// Sorting Linked list

// -  Merge sort is best for any sequential data (lists, hard disks) 
//	- It is easy to maintain merge sort that it requires only O(1) space.
//	- Coz of slow random access of linked list nodes - 
// 		- 	quick sort - performs poorly
//  	- 	heap sort - impossible

// ************************************************ 

// Online Sorting

// Merge sort's merge operation is useful in online sorting, where the list to be sorted is received a piece at a time, instead of all at the beginning. 
// In this application, we sort each new piece that is received using any sorting algorithm, and then merge it into our sorted list so far using the merge operation. 

// However, this approach can be expensive in time and space if the received pieces are small compared to the sorted list Ñ a better approach in this case is to 
// store the list in a self-balancing binary search tree and add elements to it as they are received.

//************************************************ 

// Merge sort parallelizes better; the most trivial way of parallelizing merge sort achieves close to linear speedup, while there is no obvious way to parallelize heapsort at all.
