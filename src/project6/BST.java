package project6;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.stream.Stream;

public class BST<E extends Comparable<E>> implements Collection<E>, Iterable<E> {
	private int size;
	private BSTNode<E> root; 
	
	public static void main(String[] args) {
		System.out.println("running BST");
	}
	
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
	class PreorderBSTIterator<E extends Comparable<E>> implements Iterator<E> {
		BSTNode<E> pointer;
		ArrayList<E> iteratorArrayList;
		int index = 0; 
		
		public PreorderBSTIterator() {
			iteratorArrayList = new ArrayList<E>();
			this.pointer = (BSTNode<E>) root;
			
			preorder(pointer);
			
			index = 0;
		}
		
		public void preorder(BSTNode<E> node) {
			if (node == null) {
				return; 
			}
			iteratorArrayList.add(node.data);
			index++; 
			preorder(node.left);
			preorder(node.right);
		}
		
		@Override
		public boolean hasNext() {
			if (index < iteratorArrayList.size()) {
				return true; 
			}
			else {
				return false; 
			}
		}

		@Override
		public E next() {
			index++;
			return iteratorArrayList.get(index - 1);
		}
	}
	
	class PostorderBSTIterator<E extends Comparable<E>> implements Iterator<E> {
		BSTNode<E> pointer;
		ArrayList<E> iteratorArrayList;
		int index = 0; 
		
		public PostorderBSTIterator() {
			iteratorArrayList = new ArrayList<E>();
			this.pointer = (BSTNode<E>) root;
			
			postorder(pointer);
			
			index = 0;
		}
		
		public void postorder(BSTNode<E> node) {
			if (node == null) {
				return; 
			}
			postorder(node.left);
			postorder(node.right);
			index++; 
			iteratorArrayList.add(node.data);
		}
		
		@Override
		public boolean hasNext() {
			if (index < iteratorArrayList.size()) {
				return true; 
			}
			else {
				return false; 
			}
		}

		@Override
		public E next() {
			index++;
			return iteratorArrayList.get(index - 1);
		}
	}
	
	class MyIterator<E extends Comparable<E>> implements Iterator<E> {
		BSTNode<E> pointer;
		ArrayList<E> iteratorArrayList;
		int index = 0; 
		
		public MyIterator() {
			iteratorArrayList = new ArrayList<E>();
			this.pointer = (BSTNode<E>) root;
			
			inorder(pointer);
			
			index = 0;
		}
		
		public void inorder(BSTNode<E> node) {
			if (node == null) {
				return; 
			}
			inorder(node.left);
			iteratorArrayList.add(node.data);
			inorder(node.right);
			index++; 
		}
		
		@Override
		public boolean hasNext() {
			if (index < iteratorArrayList.size()) {
				return true; 
			}
			else {
				return false; 
			}
		}

		@Override
		public E next() {
			index++;
			return iteratorArrayList.get(index - 1);
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
		if (root == null) {
			return "";
		}
		
		return toStringHelper(root);
	}
	
	private String toStringHelper(BSTNode<E> pointer) {
		if (pointer == null) {
			return "";
		}
		else {
			return toStringHelper(pointer.left) + toStringHelper(pointer.right);
		}
	}
	//don't forget to document that this was taken from the specs
	public String toStringTreeFormat() {
		StringBuilder s = new StringBuilder();
		preOrderPrint(root, 0, s);
		return s.toString();
	}

	//don't forget to document that this was taken from the specs
	private void preOrderPrint(BSTNode<E> tree, int level, StringBuilder output) {
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++) {
					spaces += " ";
				}
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.data);
			preOrderPrint(tree.left, level + 1, output);
			preOrderPrint(tree.right, level + 1, output);
		} 
		else { 
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++) {
					spaces += " ";
				}
				spaces += "|--";
			}
			output.append(spaces);
			output.append("null");
		}
	}
	
	
	@Override
	public boolean add(E arg0) {
		if (arg0 == null) {
			return false; 
		}
		root = addHelper(arg0, root);
		
		return true;
	}
	
	private BSTNode<E> addHelper(E arg0, BSTNode<E> pointer) {
		//create a new node and make it the root if the tree is empty
		if (root == null) {
			size += 1; 
			return new BSTNode<E>(arg0);
		}
		
		//don't allow equal elements; 
		if (pointer.data.equals(arg0)) {
			return root;
		}
		else if (root.data.compareTo(arg0) < 0) {
			root.right = addHelper(arg0, root.right);
		}
		else {
			root.left = addHelper(arg0, root.left);
		}
		
		return root;
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException("Unsupported Operation");
	}

	@Override
	public void clear() {
		root = null; 
	}
	/**
	 * @param Object arg0
	 * @return boolean. true if the Object is found, false if it is not (by calling the recursive 
	 * method containsHelper
	 * @throws ClassCastException 
	 */
	@Override
	public boolean contains(Object arg0) throws ClassCastException{
		if (arg0 == null || root == null) {
			return false; 
		}
		
		E arg0Cast = (E)arg0; //this line might throw an exception 
		BSTNode<E> pointer = root; 
		
		return containsHelper(arg0Cast, pointer);
	}
	
	/**
	 * @param E arg0
	 * @param BSTNode<E> pointer
	 * @return returns true if the element is found, determines if it needs to go left or right 
	 * and then calls itself again until it is null. If it reaches null, the element was not
	 * found and it will return false. 
	 */
	private boolean containsHelper(E arg0, BSTNode<E> pointer) {
		//if the element is never found 
		if (pointer == null) {
			return false; 
		}
		
		if (arg0.compareTo(pointer.data) < 0) {
			return containsHelper(arg0, pointer.right);
		} 
		else if (arg0.compareTo(pointer.data) > 0) {
			return containsHelper(arg0, pointer.left);
		}
		else {
			return true; 
		}
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		boolean isFound = true; 
		
		for (Object item : arg0) 
		{
			if (this.contains(item) == false) 
			{
				isFound = false;
			}
		}
		
		return isFound;
	}
	
	@Override
	public boolean equals(Object o) {
		boolean isEqual = true; 
		
		if (o instanceof BST<?>) {
			MyIterator<E> thisBST = this.iterator();
			MyIterator<E> oBST = ((BST) o).iterator();
			
			if (this.size() != ((BST<E>) o).size()) {
				return false;
			}
			
			while (thisBST.hasNext()) {
				if (thisBST.next().equals(oBST.next())) {
					continue;
				}
				else {
					isEqual = false;
					break;
				}
			}
		}
		
		return isEqual;
	}

	public int hashCode() {
		throw new UnsupportedOperationException("Unsupported Operation");
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
	public MyIterator<E> iterator() {
		return new MyIterator<E>();
	}
	
	public PreorderBSTIterator<E> preorderIterator() {
		return new PreorderBSTIterator<E>();
	}
	
	public Iterator<E> postorderIterator() {
		return new PostorderBSTIterator<E>();
	}

	@Override
	public boolean remove(Object arg0) {
		if (arg0 == null) {
			return false; 
		}
		
		E arg0Cast = (E)arg0; //this throws an exception
		BSTNode<E> pointer = root; 
		
		return true;
	}

//	private boolean removeHelper(E arg0, BSTNode<E> pointer) {
//		s
//	}
	
	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Unsupported Operation");
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Unsupported Operation");
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		MyIterator<E> toArrayIterator = this.iterator();
		
		@SuppressWarnings("unchecked")
		Object[] returnArray = (E[])new Object[size];
		int index = 0; 
		
		while (toArrayIterator.hasNext()) {
			returnArray[index] = toArrayIterator.next();
			index++;
		}
		
		return returnArray;
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Returns the least element in this set greater than or equal to the given element, 
	//or null if there is no such element.
	public E ceiling (E e) {
		MyIterator<E> ceilingIterator = this.iterator();
		
		while (ceilingIterator.hasNext()) {
			E thisElement = ceilingIterator.next();
			if (thisElement.compareTo(e) >= 0) {
				return thisElement;
			}
		}
		
		return null;
	}
	
	public Object clone() {
		return null;
	}
	
	public E first() {
		if (this.isEmpty()) {
			return null;
		}
		BSTNode<E> pointer = root;
		
		while (pointer.left != null) {
			pointer = pointer.left;
		}
		
		return pointer.data;
	}
	
	public E floor(E e) {
		MyIterator<E> floorIterator = this.iterator();
		
		while (floorIterator.hasNext()) {
			E thisElement = floorIterator.next();
			if (thisElement.compareTo(e) <= 0) {
				return thisElement;
			}
		}
		
		return null;
	}
	
	public E higher(E e) {
		MyIterator<E> higherIterator = this.iterator();
		
		while (higherIterator.hasNext()) {
			E thisElement = higherIterator.next();
			if (thisElement.compareTo(e) > 0) {
				return thisElement;
			}
		}
		
		return null;
	}
	
	public E last() {
		if (this.isEmpty()) {
			return null;
		}
		return lastHelper(root);
	}
	
	private E lastHelper(BSTNode<E> pointer) {
		if (pointer.right == null) {
			return pointer.data;
		}
		
		return lastHelper(pointer.right);
	}
	
	public E lower(E e) {
		MyIterator<E> lowerIterator = this.iterator();
		
		while (lowerIterator.hasNext()) {
			E thisElement = lowerIterator.next();
			if (thisElement.compareTo(e) < 0) {
				return thisElement;
			}
		}
		return null;
	}
}
