package main.java.algo.datastructures.binarytree;

/* 

                 5

           /           \

         2               8

       /   \           /   \

     1       4       7       9

   / \      /       /

0   1.5     3       6


 */


// name of class is BT but implementations are for BST

public class BinaryTrees {

	Node root = null;

	public BinaryTrees() {
		root = new Node();
	}
	
	public Node getRoot() {
		return root;
	}

	public void makeEmpty() {
		root = null;
	}

	public boolean isEmpty() {
		return root == null;
	}

	/******************************* Search *******************************/

	public boolean lookup(Object obj) {
		return lookup(root, obj);
	}

	public boolean lookup(Node node, Object obj) {

		// if root is null - means tree is empty
		// node.right or node.left is null - means node was the leaf. node.right
		// or node.left was not existing.
		if (node == null)
			return false;

		// using compareTo (since object implements comparable)
		// if data was primitive, we could have used a< b kind of comparisons

		// if the object is equal to the current node - is success.
		else if (node.data.compareTo(obj) == 0)
			return true;

		// if the current node is greater than object, object should be looked
		// in the left subtree
		else if (node.data.compareTo(obj) > 0)
			return lookup(node.left, obj);

		// if the current node is less than object, object should be looked in
		// the right subtree
		else
			return lookup(node.right, obj);
	}

	/******************************* Insert *******************************/

	public Node insert(Object obj) throws Exception {
		return insert(root, obj);
	}

	/*
	 * Recursive insert -- given a node pointer, recur down and insert the given
	 * data into the tree. Returns the new node pointer (the standard way to
	 * communicate a changed pointer back to the caller).
	 */
	
	// In a BST, all values are already ordered - that means, for any node, 
	// left child is always less than the parent and right is greater.
	// This any node node when added - can be added at the end as a LEAF node.

	public Node insert(Node node, Object obj) throws Exception {
		if (node == null)
			node = new Node(obj);
		else if (node.data.compareTo(obj) == 0)
			throw new Exception("Node duplicate");
		else if (node.data.compareTo(obj) > 0)
			node.left = insert(node.left, obj);
		else
			node.right = insert(node.right, obj);
		return node;
	}

	/*
	 * Earlier I wrote this. But it was wrong. Why?
	 * 
	 * I was doing a return. No doubt when it will reach the right position in a
	 * tree, it will create a new node.But that node has to be node.left or
	 * node.right of its parent. This pointer connection was not happening with
	 * below approach.So, the one mentioned above - insert(Node node, Object
	 * obj) - creates a new and also maintains the pointer connections for newly
	 * created items.
	 * 
	 * public Node insertWrongImplementation (Node node, Object obj) throws
	 * Exception { if (node == null) return new Node(obj); else
	 * if(node.data.compareTo(obj) == 0) throw new Exception("Node duplicate");
	 * else if(node.data.compareTo(obj) > 0) return (insert(node.left, obj));
	 * else return (insert(node.right, obj)); }
	 */
	
	// Also, note here that I am using pointers node.left=... node.right=... 
	// This is also consuming O(n) space complexity. To avoid this, try something like --
	
	public void insertWithLessMemory (Node node, Object obj) throws Exception {
		 if (node.data.compareTo(obj) > 0) {
			 if(node.left == null)
				 node.left = new Node(obj);
			 insertWithLessMemory(node.left, obj); 
		 } else {
			 if(node.right == null)
				 node.right = new Node(obj);
			 insertWithLessMemory(node.right, obj);
		 }
	}

	/******************************* Find Minimum *******************************/
	
	public Node findMinumum() {
		return findMinumum(root);
	}

	public Node findMinumum(Node node) {
		if (node == null)
			return null;
		// we need to check if actually the left most node exists, only then
		// move it to the left
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	/******************************* Remove Minimum *******************************/

	// Remove minimum. After removal return the new minimum node.

	public Node removeMinmum() {
		return removeMinmum(root);
	}

	// The below logic works well for the tree on top os this class.
	// But what if the element 0 was not there, 1 had no left child but had only
	// right child 1.5
	// Then this logic breaks
	public Node removeMinmumWrongImplemetation(Node node) {
		if (node == null)
			return null;
		while (node.left.left != null) {
			node = node.left;
		}
		node.left = null;
		return node;
	}
	
	// so this will work now
	public Node removeMinmum2(Node node) {
		if (node == null)
			return null;
		
		while (node.left.left != null) {
			node = node.left;
		}
		
		if(node.left.right != null)
			node.left = node.left.right;
		else
			node.left = null;
		
		return node;
	}

	/* **********************

			 5
    		/
		   4
		  /
		 2
		  \
		    3
	
	 ********************** */


	// Note: Whenever nodes are added/removed/rearranged - the node.left, node.right pointer 
	// connections are re-established, so make sure these pointers are also created in add/remove/rearrange
	public Node removeMinmum(Node node) {
		if (node == null)
			return null;
		if (node.left != null) {
			node.left = removeMinmum(node.left);
			return node;
		} else {
			return node.right;
		}
	}

	/******************************* Remove *******************************/

	public Node remove(Object x) {
		return remove(root, x);
	}

	public Node remove(Node node, Object x) {
		if (node == null)
			return null;
		
		//it is important to maintain the pointers node.left = ... node.right = ... 
		//After the node is removed, we need to make sure that pointers are set correct as well.
		else if (node.data.compareTo(x) > 0)
			node.left = remove(node.left, x);
		else if (node.data.compareTo(x) < 0)
			node.right = remove(node.right, x);

		// when the node if found - to be deleted
		else {
			// internal node - with 2 children
			
			// in this case - we don't need to touch the left subtree. in the right tree
			// values increase towards the end. so pick up the least item from
			// right tree. it can be directly replaced with current node. and
			// node.data < node.right.data and node.data > node.left.data will
			// also hold good.
			if (node.left != null && node.right != null) {
				node.data = findMinumum(node.right).data;
				//CHECK IF THIS IS CORRECT
				removeMinmum(node.right);
				//CHECK IF THIS IS CORRECT
				//node.right = removeMinmum(node.right);
			}
			// external node with no or one child.
			
			// in this case - replace current node with right or left child, which ever is present.
			else {
				node = (node.left != null) ? node.left : node.right;
			}
		}
		return node;
	}

	
}
