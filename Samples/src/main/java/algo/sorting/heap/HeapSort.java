package main.java.algo.sorting.heap;


/*Heap Sort is another implementation of selection sort using the right data structure.
 * It optimizes the way to pick minimum element in the logic. From O(n) to find min in selection sort, it used O(log n)*/

public class HeapSort {
	
	public int[] heapSort(int[] data) throws HeapException {
		BinaryMinHeap_PriorityQueue heap = new BinaryMinHeap_PriorityQueue(data.length);
		
		//first create heap from raw array
		for (int i = 0; i < data.length; i++) {
			heap.insert(data[i]);
		}
		
		//extract min value using heap's remove min.
		for (int i = 0; i < data.length; i++) {
			data[i] = heap.extractMin();
		}
		return data;
	}

}

/*COMPLEXITY:
 * create heap:: inserting one element is O(log n). Creating heap from n elements is O(n log n).
 * extract min:: for one element is O(log n). For extracting n elements, worst case is O(n log n).
 * So, complexity is :: O(n log n) + O(n log n) = O(n log n)
 * */

// Heapsort relies strongly on random access, and its poor locality of reference makes it very slow on media with long access times

// just fyi -- heap works on the same funda
// A tree sort is a sort algorithm that builds a binary search tree from the keys to be sorted, and then traverses the tree (in-order) so that the keys come out in sorted order.