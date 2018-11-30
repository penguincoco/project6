package project6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

import project6.BST.BSTNode;
import project6.LinkedList.MyIterator;
import project6.LinkedList.Node;

public class BST< E extends Comparable<E>> implements Collection<E>, Iterable<E> {
	private int size;
	private BSTNode<E> root; 
	
	//private, internal BSTNode class
	static class BSTNode<E extends Comparable<E>> implements Comparable<BSTNode<E>> {
		private E data; 
		private BSTNode<E> left;
		private BSTNode<E> right; 
		
		//BSTNode constructors
		public BSTNode(E data) {
			this.data = data;
		}
		
		public BSTNode(E data, BSTNode<E> left, BSTNode<E> right) {
			this.data = data; 
			this.left = left;
			this.right = right; 
		}

		@Override
		public int compareTo(BSTNode<E> arg0) {
			return data.compareTo(arg0.data);
		}
		
		public E getData() {
			return this.data;
		}
	}
	
	//private, internal Iterator class
	//preorder traversal: Root first, left subtree, right subtree
	class BSTIterator<E extends Comparable<E>> implements Iterator<E> {
		BSTNode<E> pointer;
		Stack<BSTNode<E>> iteratorStack; 
		E[] iteratorArray;
		int index = 0; 
		
		public BSTIterator(E[] array) {
			this.iteratorArray = array;
//			iteratorStack = new Stack<>();
//			this.pointer = (BSTNode<E>) root; 
		}
		
		@Override
		public boolean hasNext() {
			if (index < iteratorArray.length) {
				return false; 
			}
			else {
				return true; 
			}
		}

		@Override
		public E next() {
			index++;
			return iteratorArray[index - 1];
		}
	}
	
	/**
	 * 
	 * @param value - an E type element to search for in the tree
	 * @return E - the reference to the element equal to the value or null if it doesn't exist
	 */
	public E get(E value) {
		BSTNode<E> current = root;
		
		while (current != null) {
			//if the data in the current node matches the parameter value, return the reference
			if (current.data.equals(value)) {
				return current.data;
			}
			
			//else, keep checking values down the tree
			//if compareTo returns a negative number, it means the param value is smaller than the current node so go right
			//if compareTo returns a positive number, it means the param value is larger than the current node so go left
			if (current.data.compareTo(value) < 0) {
				current = current.right;
			}
			else if (current.data.compareTo(value) > 0) {
				current = current.left;
			}
		}
		
		//if current eventually points to null, the value was not found. Return null. 
		return null;
	}
	
	/**
	 * 
	 * @param none
	 * @return String - a string representation of this collection
	 */
	public String toString() {
		
		return ""; 
	}
	
//	public String toStringTreeFormat() {
//		
//	}
	
	
	@Override
	public boolean add(E arg0) {
		
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Object arg0) {
		BSTNode<E> current = root; 
		
		
		
//		MyIterator containsIterator = new MyIterator();
//		boolean isFound = false; 
//		
//		//use iterator to iterate through the list and search for the element
//		while (containsIterator.hasNext()) 
//		{
//			if (containsIterator.next().equals(arg0)) 
//			{
//				isFound = true; 
//			}
//		}
		
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean equals(Object o) {
		return false;
	}

	/**
	 * 
	 * @param none
	 * @return String - a string representation of this collection
	 */
	@Override
	public boolean isEmpty() {
		if (root == null) {
			return true;
		}
		else {
			return false; 
		}
	}
	
	@Override
	public BSTIterator<E> iterator() {
		E[] inorderArray = (E[])new Object[size]; 
		BSTIterator<E> inorderIterator = new BSTIterator(iteratorHelper(root));
		return inorderIterator;
	}
	
	public E[] iteratorHelper(BSTNode<E> root) {
		if (root == null) {
			return null;
		}
		iteratorHelper(root.left);
		list.add(root.element);
	      inorder(root.right);
		return null;
	}
	
	public Iterator<E> preorderIterator() {
		return null;
	}
	
	public Iterator<E> postorderIterator() {
		return null;
	}

	@Override
	public boolean remove(Object arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
