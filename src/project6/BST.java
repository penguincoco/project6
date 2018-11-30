package project6;

import java.util.Collection;
import java.util.Iterator;

import project6.LinkedList.Node;

public class BST<E> implements Collection<E>, Iterable<E> {
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
	}
	
	/**
	 * 
	 * @param value - an E type element to search for in the tree
	 * @return E - the reference to the element equal to the value or null if it doesn't exist
	 */
	public E get(E value) {
		BSTNode<E> current = root;
		
		while (current != null) {
			if (current.data.equals(value)) {
				return current.data;
			}
			if (current.data.compareTo(value) < 0) {
				current = current.right;
			}
			else if (current.data.compareTo(value) > 0) {
				current = current.left;
			}
		}
		
		return null;
	}
	
	
	
	/**
	 * 
	 * @param none
	 * @return String - a string representation of this collection
	 */
//	public String toString() {
//	}
	
//	public String toStringTreeFormat() {
//		
//	}
	
	@Override
	public boolean add(E arg0) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean equals(Object o) {
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
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
