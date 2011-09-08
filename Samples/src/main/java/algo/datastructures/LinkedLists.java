package main.java.algo.datastructures;

public class LinkedLists {

	Node header = null;

	// header node is created as the object is created.
	// so header will never be null
	public LinkedLists() {
		header = new Node();
	}

	public int size() {
		int size = 0;
		ListIterator itr = new ListIterator(header);
		while (itr.isvalid()) {
			itr.advance();
			size++;
		}
		return size;
	}

	public void insert(Object o) {
		if (o == null)
			return;
		Node n = new Node(o);
		n.next = header.next;
		header.next = n;
	}

	public void remove(Object o) {
		ListIterator itr = new ListIterator(header);
		Node prev = null;
		Node current = null;
		while (itr.isvalid()) {
			prev = itr.retrieveNode();
			itr.advance();
			current = itr.retrieveNode();
			if (current.data.equals(o)) {
				prev.next = current.next;
				break;
			}
		}
	}

	public boolean isEmpty() {
		return (header.next == null);
	}

	public void makeEmpty() {
		header.next = null;
	}

	public Object getFirst() {
		if (header != null)
			return header.next;
		return null;
	}

	public boolean find(Object o) {
		ListIterator itr = new ListIterator(header);
		while (itr.isvalid()) {
			itr.advance();
			if (itr.retrieveData().equals(o))
				return true;
		}
		return false;
	}

	public Object findPrevious(Object o) {
		ListIterator itr = new ListIterator(header);
		Object prev;
		while (itr.isvalid()) {
			prev = itr.retrieveData();
			itr.advance();
			if (itr.retrieveData().equals(o))
				return prev;
		}
		return false;
	}
}

/************Linked List Iterator**************/


class ListIterator {

	Node current = null;

	public ListIterator(Node n) {
		current = n;
	}

	public boolean isvalid() {
		return current != null;
	}

	public Object retrieveData() {
		if (isvalid())
			return current.data;
		return null;
	}

	public Node retrieveNode() {
		if (isvalid())
			return current;
		return null;
	}

	public void advance() {
		if (isvalid())
			current = current.next;
	}
}

/************ Linked List Node **************/

class Node {

	Object data = null;;
	Node next = null;

	public Node() {
		this(null, null);
	}

	public Node(Object o) {
		this(o, null);
	}

	public Node(Object o, Node n) {
		this.data = o;
		this.next = n;
	}

}