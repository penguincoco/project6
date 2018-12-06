package project6;

/**
 * This is a self-implemented generic Binary Search Tree that has various methods that can be called on it. 
 * This implementation implements the Collection interface, Iterable interface and Cloneable interface.
 * The class has an internal class for Binary Search Tree Node (BSTNode) and three Iterator classes (Inorder, Preorder, Postorder)
 *   
 * @author Sammy Chuang
 * @version 2018.12.05
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class BST<E extends Comparable<E>> implements Collection<E>, Iterable<E>, Cloneable {
	private int size;
	private BSTNode<E> root;
	
	/**
	 * Nested class for the Binary Search Tree Node. The E generic type has the restriction that anything that inherits
	 * from BST also has to extend Comparable interface. Each node stores a reference to its own data, its left child 
	 * and its right child. 
	 * 
	 * @author Sammy Chuang
	 * @version 2018.12.05
	 */
	static class BSTNode<E extends Comparable<E>> implements Comparable<BSTNode<E>> {
		private E data; 
		private BSTNode<E> left;
		private BSTNode<E> right; 
		
		/**
		 * Constructor for the BSTNode class that sets the data passed in as a parameter to the data field for the BSTNode
		 * object
		 * 
		 * @param E data
		 * @return none
		 */
		public BSTNode(E data) {
			this.data = data;
		}
		
		/**
		 * Constructor for the BSTNode class that sets the first parameter passed to the data field for the BSTNode object,
		 * the second parameter to the left child and the third parameter as the right child.
		 * 
		 * @param E data, BSTNode<E> left, BSTNode<E> right
		 * @return none
		 */
		public BSTNode(E data, BSTNode<E> left, BSTNode<E> right) {
			this.data = data; 
			this.left = left;
			this.right = right; 
		}

		/**
		 * compareTo for the Node class to be able to compare the data of generic type stored inside each BSTNode. 
		 * Every class that extends BST has to implement Comparable interface.
		 * 
		 * @param E data, BSTNode<E> left, BSTNode<E> right
		 * @return int 
		 */
		@Override
		public int compareTo(BSTNode<E> arg0) {
			return data.compareTo(arg0.data);
		}
		
		/**
		 * public getter to retrieve the data stored inside of a BSTNode
		 * @return
		 */
		public E getData() {
			return this.data;
		}
	}
	
	/**
	 * Nested class for the Preorder Traversal Iterator that implements Iterator<E> interface (and by extension, 
	 * implements the required methods and a few extra helpers).
	 * 
	 * @author Sammy Chuang 
	 * @version 2018.12.05
	 */
	class PreorderBSTIterator implements Iterator<E> {
		BSTNode<E> pointer;
		ArrayList<E> iteratorArrayList;
		int index = 0; 
		
		/**
		 * Constructor for the preorder iterator. Creates a new ArrayList that will hold the data and creates a pointer
		 * that starts at root. Also calls a recursive method (preorder()) that adds all data to the ArrayList in the 
		 * correct (pre)order.
		 * 
		 * @param none
		 * @return none
		 */
		public PreorderBSTIterator() {
			iteratorArrayList = new ArrayList<E>();
			this.pointer = root;
			
			//recursive call to recurse through the tree and add the data to the iteratorArrayList in the right order
			preorder(pointer);
		}
		
		/**
		 * private helper method that recurses through the tree and adds all of the data to the iteratorArrayList in 
		 * the correct (pre)order. This helper was based off of the BST.java source code. 
		 * 
		 * @param BSTNode<E> a pointer node to know where in the structure we currently are
		 * @return none
		 */
		private void preorder(BSTNode<E> pointer) {
			//break the recursive call
			if (pointer == null) {
				return; 
			}
			//add the data to the iteratorArrayList
			iteratorArrayList.add(pointer.data);
			
			//continue down the left subtree first, then the right and add the data to iteratorArrayList
			//until pointer is null 
			preorder(pointer.left);
			preorder(pointer.right);
		}
		
		/**
		 * hasNext() method--as required by the Iterator<E> interface--implementation. Checks if the next element 
		 * in the iteratorArrayList exists/is not null.
		 * 
		 * @param none
		 * @return boolean. True if the next BSTNode exists (is not null) and false if it is null. 
		 */
		@Override
		public boolean hasNext() {
			if (index < iteratorArrayList.size()) {
				return true; 
			}
			else {
				return false; 
			}
		}

		/** 
		 * next() method--as required by the Iterator<E> interface--implementation. Returns the data of the BSTNode
		 * at iteratorArrayList index.
		 */
		@Override
		public E next() {
			index++;
			return iteratorArrayList.get(index - 1);
		}
	}
	
	/**
	 * Nested class for the Postorder Traversal Iterator that implements Iterator<E> interface (and by extension, 
	 * implements the required methods and a few extra helpers).
	 * 
	 * @author Sammy Chuang 
	 * @version 2018.12.05
	 */
	class PostorderBSTIterator implements Iterator<E> {
		BSTNode<E> pointer;
		ArrayList<E> iteratorArrayList;
		int index = 0; 
		
		/**
		 * Constructor for the preorder iterator. Creates a new ArrayList that will hold the data and creates a pointer
		 * that starts at root. Also calls a recursive method (preorder()) that adds all data to the ArrayList in the 
		 * correct (pre)order.
		 * 
		 * @param none
		 * @return none
		 */
		public PostorderBSTIterator() {
			iteratorArrayList = new ArrayList<E>();
			this.pointer = (BSTNode<E>) root;
			
			//recursive call to recurse through the tree and add the data to the iteratorArrayList in the right order
			postorder(pointer);
		}
		
		/**
		 * private helper method that recurses through the tree and adds all of the data to the iteratorArrayList in 
		 * the correct (post)order. This helper was based off of the BST.java source code. 
		 * 
		 * @param BSTNode<E> a pointer node to know where in the structure we currently are
		 * @return none
		 */
		private void postorder(BSTNode<E> node) {
			//break the recursive call
			if (node == null) {
				return; 
			}
			
			//starting from the left-most leaf, go up the tree in the pattern: left child, right child, parent and add the
			//data to the iteratorArrayList
			postorder(node.left);
			postorder(node.right);
			iteratorArrayList.add(node.data);
		}
		
		/**
		 * hasNext() method--as required by the Iterator<E> interface--implementation. Checks if the next element 
		 * in the iteratorArrayList exists/is not null.
		 * 
		 * @param none
		 * @return boolean. True if the next BSTNode exists (is not null) and false if it is null. 
		 */
		@Override
		public boolean hasNext() {
			if (index < iteratorArrayList.size()) {
				return true; 
			}
			else {
				return false; 
			}
		}

		/** 
		 * next() method--as required by the Iterator<E> interface--implementation. Returns the data of the BSTNode
		 * at iteratorArrayList index.
		 */
		@Override
		public E next() {
			index++;
			return iteratorArrayList.get(index - 1);
		}
	}
	
	/**
	 * Nested class for the Inorder Traversal Iterator that implements Iterator<E> interface (and by extension, 
	 * implements the required methods and a few extra helpers).
	 * 
	 * @author Sammy Chuang 
	 * @version 2018.12.05
	 */
	class MyIterator implements Iterator<E> {
		ArrayList<E> iteratorArrayList;
		int index = 0; 
		
		public MyIterator() {
			iteratorArrayList = new ArrayList<E>();
			BSTNode<E> pointer = root;
			
			//recursive call 
			inorder(root);
		}
		
		/**
		 * private helper method that recurses through the tree and adds all of the data to the iteratorArrayList in 
		 * the correct (in)order. This helper was based off of the BST.java source code. 
		 * 
		 * @param BSTNode<E> a pointer node to know where in the structure we currently are
		 * @return none
		 */
		public void inorder(BSTNode<E> node) {
			//break the recursive call
			if (node == null) {
				return; 
			}
			
			//recurse through the tree getting the very left most element, adding it to the iteratorArrayList, then visiting
			//the right subtree
			inorder(node.left);
			iteratorArrayList.add(node.data);
			inorder(node.right);
		}
		
		/**
		 * hasNext() method--as required by the Iterator<E> interface--implementation. Checks if the next element 
		 * in the iteratorArrayList exists/is not null.
		 * 
		 * @param none
		 * @return boolean. True if the next BSTNode exists (is not null) and false if it is null. 
		 */
		@Override
		public boolean hasNext() {
			if (index < iteratorArrayList.size()) {
				return true; 
			}
			else {
				return false; 
			}
		}

		/** 
		 * next() method--as required by the Iterator<E> interface--implementation. Returns the data of the BSTNode
		 * at iteratorArrayList index.
		 */
		@Override
		public E next() {
			index++;
			return iteratorArrayList.get(index - 1);
		}
	}
	
	/**
	 * Retrieves the data living in the BSTNode that holds the data equal to the parameter
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
	 * Returns a string representation of this collection. The string representation consists of a list of the collection’s elements in the
	 * order they are returned by its iterator (in this class this is the order of the inorder traversal), enclosed in square brackets ("[]").
	 * Adjacent elements are separated by the characters ", " (comma and space). 
	 * 
	 * @param none
	 * @return String - a string representation of this collection
	 */
	public String toString() {
		//the tree is empty
		if (root == null) {
			return "";
		}
		
		//create an iterator to iterate through the string and StringBuilder to create the string representation of
		//the collection
		MyIterator toStringIterator = this.iterator();
		StringBuilder returnStringBuilder = new StringBuilder();
		
		//Build the string with brackets on each end an ", " separating each value
		returnStringBuilder.append("[");
		while (toStringIterator.hasNext()) {
			returnStringBuilder.append(toStringIterator.next().toString() + ", ");
		}
		
		//the return String will have a trailing space and comma. Chop it off! 
		String returnString = returnStringBuilder.substring(0, returnStringBuilder.length() - 2);
	
		returnString = returnString + "]";
		
		//return the string representation
		return returnString.toString();
	}
	
	/**
	 * Returns the string that prints out a visual of what the tree looks like. This code was taken directly from the specifics
	 * as was allowed
	 *  
	 * @author Joanna Klukowska 
	 * @param none
	 * @return String
	 */
	public String toStringTreeFormat() {
		StringBuilder s = new StringBuilder();
		preOrderPrint(root, 0, s);
		return s.toString();
	}

	/**
	 * Uses the preorder traversal to produce a tree-like representation of this BST
	 *  
	 * @author Joanna Klukowska 
	 * @param Node<E> the root of the current subtree, int the depth, StringBuilder the string
	 * this function builds
	 * @return String
	 */
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
	
	/** 
	 * Checks to make sure the parameter is valid (not null) and set the rooti in the case that the tree
	 * is empty 
	 * 
	 * @param E. the data to store in a BSTNode<E> to add to the tree.
	 * @return true if the element was able to be added, false if not. 
	 */
	@Override
	public boolean add(E arg0) {
		//check for a null argument passed in 
		if (arg0 == null) {
			return false; 
		}
		
		//if the tree is empty, make a BSTNode<E> with that data and make it the root
		if (this.isEmpty()) {
			root = new BSTNode<E>(arg0);
			size++;
			return true;
		}
		
		//otherwise, call the addHelper to find out where it should go in the tree 
		return addHelper(arg0, root);
	}
	
	/** 
	 * Private, helper method that adds a new BSTNode<E> holding arg0 as the data 
	 * if it doesn't already exist in the BST and isn't null
	 * 
	 * @param E. the data to store in a BSTNode<E> to add to the tree.
	 * @return true if the element was able to be added, false if not. 
	 */
	private boolean addHelper(E arg0, BSTNode<E> pointer) {
		//compare the data to add (arg0) to what BSTNode<E> the pointer is currently looking at and 
		//continue travelling down left or right accordingly 
		//if the data is already in the BST, don't add it and return false
		if (pointer.getData().compareTo(arg0) == 0) {
			return false; 
		}
		
		//figure out which direction to travel in and if the correct location is found, add the (new) BSTNode!
		else if (pointer.getData().compareTo(arg0) > 0) {
			if (pointer.left == null) {
				pointer.left = new BSTNode<E>(arg0);
				size++;
				return true;
			}
			else {
				return addHelper(arg0, pointer.left);
			}
		}
		else {
			if (pointer.right == null) {
				pointer.right = new BSTNode<E>(arg0);
				size++;
				return true;
			}
			else {
				return addHelper(arg0, pointer.right);
			}
		}
	}

	/** 
	 * Adds all items in the collection parameter to the BST
	 * 
	 * @param a collection 
	 * @return boolean. True if it was able to add the collection, false if not.
	 * @throws UnsupportedOperationException throws if the user tries to call this method
	 */
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException("Unsupported Operation");
	}

	/**
	 * Clears the current tree by pointing root to null and resets the size to 0 
	 * 
	 * @param none
	 * @return none
	 */
	@Override
	public void clear() {
		root = null; 
		size = 0;
	}
	/**
	 * Checks to see if the current tree is empty and if the parameter arg0 is null. If not, make the recursive call 
	 * to check if arg0 exists in the BST. 
	 * 
	 * @param Object arg0
	 * @return boolean. True if the Object is found, false if it is not (by calling the recursive method containsHelper)
	 * @throws ClassCastException might throw on line 523 trying to cast the Object arg0 to E
	 */
	@Override
	public boolean contains(Object arg0) throws ClassCastException{
		//invalid parameter or empty tree
		if (arg0 == null || root == null) {
			return false; 
		}
		
		//this line might throw an exception 
		E arg0Cast = (E)arg0; 
		
		//recursive call to check if the element exists
		return containsHelper(arg0Cast, root);
	}
	
	/**
	 * the contains method helper that recursively goes through the tree and checks to see if a certain element (arg0)
	 * exists in it.
	 * 
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
		
		//if less than, continue going down left
		if (pointer.getData().compareTo(arg0) > 0) {
			return containsHelper(arg0, pointer.left);
		} 
		
		//if greater than, continue going down right
		else if (pointer.getData().compareTo(arg0) < 0) {
			return containsHelper(arg0, pointer.right);
		}
		
		//if equal to 0, data was found and return true
		else {
			return true; 
		}
	}

	/**
	 * Checks to see if every element in the collection parameter (arg0) exists in the current BST
	 * 
	 * @return boolean. True if every element exists, false if not.
	 * @param Collection<?> arg0 the collection of data to check for 
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) {
		boolean isFound = true; 
		
		//for every Object in the given collection, if this does not BST contain it, update isFound
		for (Object item : arg0) 
		{
			if (this.contains(item) == false) 
			{
				isFound = false;
			}
		}
		
		return isFound;
	}
	
	/**
	 * Compares the specified object with this collection for equality.
	 * 
	 * @param Object o the object to compare this BST to 
	 * @return boolean. True if they both contain the same data, false otherwise. 
	 */
	@Override
	public boolean equals(Object o) {
		boolean isEqual = true; 
		
		if (o instanceof BST<?>) {
			MyIterator thisBST = this.iterator();
			MyIterator oBST = ((BST) o).iterator();
			
			//if the two aren't the same size, they definitely are not equal 
			if (this.size() != ((BST<E>) o).size()) {
				return false;
			}
			
			//if they hold the same data set, the inorder traversals for each should produce the values in the 
			//same order so compare their values
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

	/**
	 * Returns the hash code value for this collection
	 * @param none 
	 * @return int
	 * @throws UnsupportedOperationException
	 */
	public int hashCode() {
		throw new UnsupportedOperationException("Unsupported Operation");
	}
	
	/**
	 * checks to see if the tree is empty.
	 * 
	 * @param none
	 * @return boolean. True if the tree is empty (root is null), otherwise returns false.
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
	
	/**
	 * method that returns MyIterator--an inorder traversal of the tree. Returns an iterator over the elements 
	 * in this collection. Elements returned in order.
	 * 
	 * @param none
	 * @return MyIterator. the inorder iterator object
	 */
	@Override
	public MyIterator iterator() {
		return new MyIterator();
	}
	
	/**
	 * method that returns MyIterator--an preorder traversal of the tree. Returns an iterator over the elements 
	 * in this collection. Elements returned in order.
	 * 
	 * @param none
	 * @return MyIterator. the preorder iterator object
	 */
	public PreorderBSTIterator preorderIterator() {
		return new PreorderBSTIterator();
	}
	
	/**
	 * method that returns MyIterator--an postorder traversal of the tree. Returns an iterator over the elements 
	 * in this collection. Elements returned in order.
	 * 
	 * @param none
	 * @return MyIterator. the postorder iterator object
	 */
	public PostorderBSTIterator postorderIterator() {
		return new PostorderBSTIterator();
	}

	/**
	 * Goes through the BST, searches for the Object arg0 and removes it if it exists. 
	 * 
	 * @param Object. the element to remove from the BST
	 * @return boolean. True if the element existed and could be removed. False if the element didn't exist or is null. 
	 */
	@Override
	public boolean remove(Object arg0) {
		//pointer is what we have to remove
		//previous is parent of pointer
		
		//checks if the BST contains the element at all
		if (!this.contains(arg0)) {
			return false;
		}
		
		//manages the case that the tree only contains the root and the root is the thing we want to remove
		if (root.data.equals(arg0) && this.size() == 1) {
			this.clear();
			return true;
		}
		
		//manages the case that the tree only has the root, the root only has one child and removing the root
		if (root.data.equals(arg0) && this.size() == 2) {
			if (root.left == null) {
				root = root.right;
				size--;
				return true;
			}
			else {
				root = root.left; 
				size--;
				return true;
			}
		}
		
		//save a reference to the parent (previous) node and the current (pointer)
		BSTNode<E> previous = root;
		BSTNode<E> pointer = root;
		
		//while the pointer data is not equal to arg0, figure out which direction (left or right) to continue down
		//set the parent to the pointer then advance the pointer
		while (!pointer.data.equals(arg0)) {
			if (pointer.getData().compareTo((E) arg0) > 0) {
				previous = pointer; 
				pointer = pointer.left; 
			}
			else {
				previous = pointer;
				pointer = pointer.right;
			}
		}
		
		//manages the case that the BSTNode<E> that we want to remove has two children 
		if (pointer.left != null && pointer.right != null) {
			//left child to eventually re-attach and right child to eventually re-attach
			BSTNode<E> leftChild = pointer.left;
			BSTNode<E> rightChild = pointer.right;
			
			//rightmost BSTNode<E> and rightmost parent BSTNode<E>
			BSTNode<E> newNode = pointer.left;
			BSTNode<E> newParent = pointer.left; 
			
			while (newNode.right != null) {
				//set rightmost and rightmost parent
				newParent = newNode;
				newNode = newNode.right;
			}
			
			if (newParent.equals(newNode)) {
				//make pointer equal to the left child
				pointer.data = newNode.data;
				//make pointer point to left child's left
				pointer.left = pointer.left.left;
				//give pointer right
				pointer.right = rightChild;
				size--;
				return true;
			}
			else {
				//the left child has a right child
				pointer.data = newNode.data;
				//set top to rightmost
				newParent.right = null;
				//chop off the rightmost 
				pointer.left = leftChild;
				pointer.right = rightChild;
				//re-attach left and right 
				size--;
				return true;
			}
		}
		
		//manages the case that the BSTNode<E> that we want to remove has 0 or one children
		else {
			//find where pointer is and compare it to the parent
			if (previous.left != null && previous.left.data.compareTo(pointer.data) == 0) {
				//pointer is left child
				if (pointer.left == null && pointer.right == null) {
					previous.left = null;
					size--;
					return true;
				} 
				else if (pointer.left == null) {
					previous.left = pointer.right;
					size--;
					return true;
				}
				else {
					previous.left = pointer.left;
					size--;
					return true;
				}
			} 
			else {
				//pointer is right child 
				if (pointer.left == null && pointer.right == null) {
					previous.right = null;
					size--;
					return true;
				} 
				else if (pointer.left == null) {
					previous.right = pointer.right;
					size--;
					return true;
				}
				else {
					previous.right = pointer.left;
					size--;
					return true;
				}
			}
		}
	}

	/**
	 * Removes all of this collection’s elements that are also contained in the specified collection.
	 * 
	 * @param Collection<?> a collection of elements to remove from the BST
	 * @return boolean. True if able to remove all elements, false if not. 
	 * @throws UnsupportedOperationException if the user/something tries to call the method
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Unsupported Operation");
	}
	
	/**
	 * Retains only the elements in this collection that are contained in the specified collection.
	 * 
	 * @param Collection<?> a collection of elements to remove from the BST
	 * @return boolean. True if able to remove all elements, false if not. 
	 * @throws UnsupportedOperationException if the user/something tries to call the method
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException("Unsupported Operation");
	}

	/**
	 * gets the current size of the BST 
	 * 
	 * @param none
	 * @return int size. the current size of the BST
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * takes all of the elements in the list and puts them into an array
	 * 
	 * @param none
	 * 
	 * @return Object[]. 
	 */
	@Override
	public Object[] toArray() {
		MyIterator toArrayIterator = this.iterator();
		
		//create the array
		Object[] returnArray = new Object[size];
		int index = 0; 
		
		//populate it 
		while (toArrayIterator.hasNext()) {
			returnArray[index] = toArrayIterator.next();
			index++;
		}
		
		return returnArray;
	}

	/**
	 * takes all of the elements in the list and puts them into the array given as a parameter and grows the array if 
	 * it's not big enough to hold all of the elements. The indicated part of this method was taken from LinkedList.java 
	 * source code
	 * 
	 * @param E[]. An empty array to populate with BST data.
	 * 
	 * @return E[]. An array populated with BST data.
	 */
	@Override
	public <E> E[] toArray (E[] arg0) {
		if (arg0 == null) {
			return null;
		}
		//creates a new the Array if the given one is not large enough
		//This was taken from the Java LinkedList implementation 
		 if (arg0.length < size) 
		 {
			 arg0 = (E[])java.lang.reflect.Array.newInstance(arg0.getClass().getComponentType(), size);
		 }
		 //end of what was taken from Java LinkedList implementation 
		 
		 MyIterator toArrayIterator = this.iterator();
		 
		 int index = 0;
		 
		 //populate the array
		 while (toArrayIterator.hasNext()) {
			 E thisData = (E)toArrayIterator.next();
			 arg0[index] = thisData;
			 index++;
		 }
		 
		return arg0;
	}
	
	/**
	 * Returns the least element in this set greater than or equal to the given element, or null if there is no such element.
	 * 
	 * @param E. the value to find the first instance of/smallest number greater than it
	 * @return E. the found value
	 */
	public E ceiling (E e) {
		if (e == null) {
			return null;
		}
		
		MyIterator ceilingIterator = this.iterator();
		
		//iterate through the data and find the least element that is >= e
		while (ceilingIterator.hasNext()) {
			E thisElement = ceilingIterator.next();
			if (thisElement.compareTo(e) >= 0) {
				return thisElement;
			}
		}
		
		return null;
	}
	
	/**
	 * Creates a shallow copy of the current BST (different tree, references point to the same data)
	 * 
	 * @param none
	 * @return Object. The copy of the tree
	 * @throws CloneNotSupportedException
	 */
	public Object clone() throws CloneNotSupportedException {
		//create a new tree
		BST<E> cloneTree = (BST<E>) super.clone();
		cloneTree = new BST<E>();
		
		//pass the original BST's root as a parameter to the helper method  
		cloneTree.root = cloneHelper(this.root);
		
		//set the clone's size equal to the original's size
		cloneTree.size = this.size;
		
		return cloneTree;
	}
	
	/**
	 * the clone helper that returns a BSTNode<E> that will be the root for the cloneTree
	 * 
	 * @param BSTNode<E>. The original BST's root
	 * @return BSTNode<E>. The BSTNode<E> that access the rest of the tree.
	 */
	private BSTNode<E> cloneHelper(BSTNode<E> pointer) {
		//break the recursive call
		if (pointer == null) {
			return null; 
		}
		
		//
		BSTNode<E> cloneNode = new BSTNode<E>(pointer.data); 
		
		//make the BSTNode<E> (cloneNode) that we're looking at (as long as it isn't null) point their left and right to something
		//(whether it be a child or another BSTNode<E> and then assign that BSTNode<E> (cloneNode) to the left or 
		//right child accordingly as it's returning back upwards
		cloneNode.left = cloneHelper(pointer.left);
		cloneNode.right = cloneHelper(pointer.right);
		
		return cloneNode;
	}
	
	/** 
	 * Returns the first (lowest) element currently in this set. Done iteratively.
	 * 
	 * @param none
	 * @return E. the lowest element currently in the set
	 */
	public E first() {
		//return null if there are no BSTNode<E>
		if (this.isEmpty()) {
			return null;
		}
		
		BSTNode<E> pointer = root;
		
		//go down as far left possible and return the leftmost element (or the root if there is only a root in the tree)
		while (pointer.left != null) {
			pointer = pointer.left;
		}
		
		return pointer.data;
	}
	
	/** 
	 * Returns the greatest element in this set less than or equal to the given element, or null if there is no such element.
	 * 
	 * @param E. the value to find the first instance of/largest number smaller than it
	 * @return
	 */
	public E floor(E e) {
		if (e == null) {
			return null;
		}
		
		MyIterator floorIterator = this.iterator();
		
		//iterate through the data and find the least element that is <= e
		while (floorIterator.hasNext()) {
			E thisElement = floorIterator.next();
			if (thisElement.compareTo(e) <= 0) {
				return thisElement;
			}
		}
		
		return null;
	}
	
	/** 
	 * Returns the least element in this set strictly greater than the given element, or null if there is no such element.	  
	 * 
	 * @param E. the value to find the smallest number that is greater than e
	 * @return E. the found value. 
	 */
	public E higher(E e) {
		if (e == null) {
			return null;
		}
		
		MyIterator higherIterator = this.iterator();
		
		//iterate through the data and find the least element that is > e
		while (higherIterator.hasNext()) {
			E thisElement = higherIterator.next();
			if (thisElement.compareTo(e) > 0) {
				return thisElement;
			}
		}
		
		return null;
	}
	
	/**
	 * Retrieve the last (highest) element currently in this set. Done recursively. 
	 *
	 * @return E. The highest element in the set. 
	 */
	public E last() {
		if (this.isEmpty()) {
			return null;
		}
		return lastHelper(root);
	}
	
	/** 
	 * returns value of the highest element in the BST
	 * 
	 * @param pointer
	 * @return E. The highest element in the set
	 */
	private E lastHelper(BSTNode<E> pointer) {
		//go down as far right possible and return the rightmost element (or the root if there is only a root in the tree)
		if (pointer.right == null) {
			return pointer.data;
		}
		
		return lastHelper(pointer.right);
	}
	
	/** 
	 * Returns the greatest element in this set strictly less than the given element, or null if there is no such element.
	 * 
	 * @param E. the value to find the largest element smaller than the parameter (e).
	 * @return E. value found or null if it doesn't exist. 
	 */
	public E lower(E e) {
		if (e == null) {
			return null;
		}
		
		MyIterator lowerIterator = this.iterator();
		
		//iterate through the data and find the least element that is <= e
		while (lowerIterator.hasNext()) {
			E thisElement = lowerIterator.next();
			if (thisElement.compareTo(e) < 0) {
				return thisElement;
			}
		}
		return null;
	}
}