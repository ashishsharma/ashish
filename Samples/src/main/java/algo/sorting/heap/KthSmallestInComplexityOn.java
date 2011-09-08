package main.java.algo.sorting.heap;


// STILL NOT FIXED. CORRECT THE ALGO

/* Given a binary heap - Find the Kth smallest element greater that X - with complexity O(n)
 * */
public class KthSmallestInComplexityOn {

	/*
	 **********************************************
	                      1 {0}

		             /             \
		
		         3 {1}                6 {2}
		
		        /      \              /   
		
		     5 {3}      7 {4}      8 {5}      

	{i} -- this is the index in array
	
	array representation : {1,3,6,5,7,8}
	
	Find: 4th smallest element
	**********************************************
	*/
	
	public static void main(String[] args) {
		
		KthSmallestInComplexityOn sm = new KthSmallestInComplexityOn();
		int[] array = {1,3,6,5,7,8};
		System.out.println(sm.kthSmallest(array, 4, 8, 0, 0));

	}
	
	
	public int kthSmallest2 (int[] heap, int kth, int gtThanX, int index, int counter) {

		boolean validIndex = index >= 0 && index < heap.length;
		boolean validValue = validIndex && heap[index] < gtThanX;

		if (validValue && counter < kth) {
			counter++;
			kthSmallest(heap, kth, gtThanX, (2 * index) + 1, counter);
			kthSmallest(heap, kth, gtThanX, (2 * index) + 2, counter);
		} else if(counter == kth) {
			return heap[index];
		} 
		
		

		return -1;
		
	}	
	
	public int kthSmallest (int[] heap, int kth, int gtThanX, int index, int counter) {

		boolean validIndex = index >= 0 && index < heap.length;
		if(!validIndex || counter == kth)
			return counter;
		
		if(heap[index] < gtThanX && counter < kth) {
			counter++;
			counter = kthSmallest(heap, kth, gtThanX, (2 * index) + 1, counter);
			counter = kthSmallest(heap, kth, gtThanX, index + 1, counter);
		}
		
		return counter;
		
		
	}	


}
