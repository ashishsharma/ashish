package main.java.algo.datastructures.binarytree;

public class Node {

	public Node() {
		this(null, null, null);
	}

	// every node is left or right child to parent on the basis of its value
	// compared to parent.
	// so we need some way to compare objects.
	// data type has to be either primitive OR should have implemented
	// comparable.
	// taking the generalized approach - we proceed with comparable type
	Comparable<Object> data = null;
	Node left = null;
	Node right = null;

	// extra tip: the below will give compilation error
	// constructor call this(...) should always be the 1st statement in a
	// constructor

	/*
	 * public Node(Object o) { if(o instanceof Comparable) this
	 * ((Comparable<Object>) o); }
	 */

	public Node(Object o) {
		this((Comparable<Object>) o);
	}

	public Node(Comparable<Object> o) {
		this(o, null, null);
	}

	public Node(Comparable<Object> o, Node left, Node right) {
		this.data = o;
		this.left = left;
		this.right = right;
	}

}
