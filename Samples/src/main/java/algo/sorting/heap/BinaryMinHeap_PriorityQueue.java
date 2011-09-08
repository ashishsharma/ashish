package main.java.algo.sorting.heap;

/** This class is about Priority Queue, which is implemented using Binary Min Heap. 
 * So here we'll implement Binary Min Heap being used as Priority Queue**/

public class BinaryMinHeap_PriorityQueue {

	/*
	 * Heap Property:
	 * - It is a complete Tree
	 * - It is a Binary Tree
	 * - Every node is smaller than its left and right child. Known as MIN-HEAP.
	 * 
	 * - If every node is bigger than its left and right child. Known as MAX-HEAP.
	 * 
	 * Heap is Loosely Sorted
	 * - In heap, we know that root is always smaller than left and right child, but there is no mention if left child is smaller than right or not.
	 * - Good to maintain min or max at run time.
	 * - Good to maintain median at run time (if we keep 2 heaps - one as MAX HEAP having half smaller 
	 * 		elements : example{1,2,3,4,5}, and other as MIN HEAP that other half greater elements.{6,7,8,9,10})
	 *
	 * Heaps are data structures that enable us to represent Binary trees without any pointers. 
	 * Thus no extra memory is required to store pointers in heaps, as we do it in a normal Binary tree.
	 * 
	 * A heap is a complete binary tree, which leads to the idea of storing it using an array. 
	 * By utilizing array-based representation, we can reduce memory costs while tree navigation remains quite simple.
	 * 
	 * Though it saves memory but is less flexible than using pointers. 
	 * We cannot move the nodes around by just changing pointers. So it does not provide us the benefits 
	 * of Binary Search tree, but works out well for heaps. Thus it is not good for searching, since we 
	 * don't have pointers - we cannot do log n search, but we can anyways do a liner search when required.
	 * 
	 */
	
	
	/*
	 **********************************************
	                      1 {0}

		             /             \
		
		         3 {1}                6 {2}
		
		        /      \              /   
		
		     5 {3}      9 {4}      8 {5}      

	{i} -- this is the index in array
	
	array representation : {1,3,6,5,9,8}
	index :                [0,1,2,3,4,5]
	
	Index of LEFT Child of a element at index i :: Left(i) = (2 * i) + 1;
	Left child of array[1] is array[3];
	
	Index of RIGHT Child of a element at index i :: Right(i) = (2 * i) + 2;
	Right child of array[1] is array[4];
	
	Index of PARENT of a element at index i :: Parent(i) = (int) (i-1)/2;
	Parent of array[4] is array[1];
	Parent of array[5] is array[2];
	
	
	**********************************************
	*/
	
	
	int[] data;
	int heapSize;
	
	public BinaryMinHeap_PriorityQueue(int size) {
		data = new int[size];
		heapSize = 0;
	}
		
	
	public int getLeftChildindex(int nodeIndex) { 
		return (2 * nodeIndex) + 1;
	}
	
	public int getRightChildindex(int nodeIndex) { 
		return (2 * nodeIndex) + 2;
	}
	
	public int getParentindex(int nodeIndex) { 
		return (int) (nodeIndex - 1)/2;
	}
	
	
	/*******************************INSERTION**********************************/
	
	/* INSERTION ALGO:
	 * 
	 * 1) Insert the new element to the end of array
	 * 2) Keep shifting it UP - till the heap property is not achieved. 
	 * Shifting up means - compare the node with its parent, if they are not as per heap property - swap them.
	 * 
	 * 
	 * Insert -2 into the above heap --
	 * 
	                      1 {0}

		             /             \
		
		         3 {1}                6 {2}
		
		        /      \              /   \
		
		     5 {3}      9 {4}      8 {5}   -2 {6}
		    
	array representation : {1,3,6,5,9,8,-2}
		     
	Heap property is broken, so keep shifting new element UP
	
	
		                  1 {0}

		             /             \
		
		         3 {1}                -2 {2}
		
		        /      \              /   \
		
		     5 {3}      9 {4}      8 {5}   6 {6}
		     
	array representation : {1,3,-2,5,9,8,6}	     
		     
	Heap property is still broken, so keep shifting new element UP
		     		       
		     		       
		     		       -2 {0}

		             /             \
		
		         3 {1}                1 {2}
		
		        /      \              /   \
		
		     5 {3}      9 {4}      8 {5}   6 {6}
		     
	array representation : {-2,3,1,5,9,8,6}
	Now the heap property is achieved. Items in Order. No more shifting required.  
	
	
	COMPLEXITY
	Complexity of the insertion operation is O(h), where h is heap's height 
	AND h = log n, where n is number of elements in a heap.
	Thus, complexity O(h) = O(log n)
	 
	 *
	 */
	
	
	public void insert (int value) throws HeapException {
		if(heapSize == data.length)
			throw new HeapException("Heap Overflow");
		heapSize++;
		int currentIndex = heapSize - 1;
		data[currentIndex] = value;
		bubbleUP(currentIndex);
	}
	
	public void bubbleUP(int nodeIndex) {
		if(nodeIndex == 0)
			return;
		int indexOfParent = getParentindex(nodeIndex);
		if((data[indexOfParent] > data[nodeIndex]) && indexOfParent >= 0) {
			int tmp = data[indexOfParent];
			data[indexOfParent] = data[nodeIndex];
			data[nodeIndex] = tmp;
			nodeIndex = indexOfParent;
			bubbleUP(nodeIndex);
		} else
			return;
	}
	
	
	public void insertWithoutRecursion(int value) {
		
		heapSize++;
		
		int currentIndex = heapSize - 1;
		data[currentIndex] = value;
		
		int tmp;
		int indexOfParent = getParentindex(currentIndex);
		
		while ((data[indexOfParent] > data[currentIndex]) && indexOfParent >= 0) {
			tmp = data[indexOfParent];
			data[indexOfParent] = data[currentIndex];
			data[currentIndex] = tmp;
			currentIndex = indexOfParent;
			indexOfParent = getParentindex(currentIndex);
		}

	}
	
	
	/*******************************REMOVE MINIMUM**********************************/
	
	/*REMOVE MINIMUM ALGO:
	 * 
	 * Min in a MIN-HEAP is always the root element
	 * 
	 * 1) copy the last element in the array to the root
	 * 2) decrease the heapsize by 1
	 * 3) bubbleDOWN till the heap property is achieved
	 * 		- If there are no children, sifting down is over.
	 * 		- If there is one child, check the heap property with it and shift down if required.
	 * 		- If there are 2 children, check the heap property and if not met, swap with smaller of the children.
	 * 
	 * 
	 * * Remove minimum from this heap --
	 * 
	                      1 {0}

		             /             \
		
		         3 {1}                6 {2}
		
		        /      \              /   
		
		     5 {3}      9 {4}      8 {5}   
		    
	array representation : {1,3,6,5,9,8}
		     
	Copy the last value array[5] = 8 to the root and decrease heapSize by 1.
	
	
	                      8 {0}

		             /             \
		
		         3 {1}                6 {2}
		
		        /      \                
		
		     5 {3}      9 {4}       
		     
	array representation : {8,3,6,5,9}	     
		     
	Heap property is still broken, so keep shifting down element 8
		     		       
		     		       
	                      3 {0}

		             /             \
		
		         8 {1}                6 {2}
		
		        /      \                
		
		     5 {3}      9 {4}       
		     
	Heap property is still broken, so keep shifting down element 8
			     
		     	          3 {0}

		             /             \
		
		         5 {1}                6 {2}
		
		        /      \                
		
		     8 {3}      9 {4}       
		     
	array representation : {3,5,6,8,9}
	Now the heap property is achieved. Items in Order. No more shifting required.  
	
	
	COMPLEXITY
	Complexity of the removal operation is O(h), where h is heap's height 
	AND h = log n, where n is number of elements in a heap.
	Thus, complexity O(h) = O(log n)
	 
	 * 
	 * */
	
	public int extractMin() {
		removeMin();
		if(heapSize > 0)
			return data[0];
		return -1;
	}
	
	public void removeMin() {
		if(heapSize == 0)
			return;
		data[0] = data[heapSize -1];
		heapSize--;
		if(heapSize > 0)
			bubbleDOWN(0);
	}

	public void bubbleDOWN(int nodeIndex) {
		int leftChildIndex = getLeftChildindex(nodeIndex);
		int rightChildIndex = getRightChildindex(nodeIndex);
		int smallerValueIndex = -1;
		if (leftChildIndex < heapSize && rightChildIndex < heapSize) {
			smallerValueIndex = (data[leftChildIndex] - data[rightChildIndex]) < 0 ? leftChildIndex : rightChildIndex;
		} else if (leftChildIndex < heapSize) {
			smallerValueIndex = leftChildIndex;
		} else if (rightChildIndex < heapSize) {
			smallerValueIndex = rightChildIndex;
		} else {
			return;
		}
		if (smallerValueIndex >= 0 && data[smallerValueIndex] < data[nodeIndex]) {
			int tmp = data[nodeIndex];
			data[nodeIndex] = data[smallerValueIndex];
			data[smallerValueIndex] = tmp;
			nodeIndex = smallerValueIndex;
			bubbleDOWN(nodeIndex);
		}
	}
	

	/*******************************CREATE HEAP **********************************/

	public void makeHeap(int[] array) throws HeapException {
		BinaryMinHeap_PriorityQueue heap = new BinaryMinHeap_PriorityQueue(array.length);
		for (int i = 0; i < array.length; i++) {
			heap.insert(array[i]);
		}
	}
	
}




class HeapException extends Exception {
	public HeapException(String message) {
		super(message);
	}
}
