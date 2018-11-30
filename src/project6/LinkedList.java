package project6;

/**
 * Self written Linked List that has a Node class to hold elements in the Linked List

 * 
 * @author Sammy Chuang
 * @version 2018.12.05
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class LinkedList<E> implements Collection<E>, Iterable<E> {
	private int size;
	private Node<E> head; 
	private Node<E> tail; 
	
	//constructor
	public LinkedList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}
	
	/**
	 * Nested class for the Node. 
	 * 
	 * @author Samantha Chuang
	 * @version 2018.10.20
	 */
	static class Node<E> {
		E data; 
		Node<E> nextNode; 
		
		//Node Constructor 
		public Node(E data) 
		{
			this.data = data;
			this.nextNode = null;
			//this.nextNode = nextNode;
		}
		
		/**
		 * gets the Node after the one this method is being called on
		 * 
		 * @param none
		 * 
		 * @return Node. 
		 */
		public Node<E> getNextNode() 
		{
			return this.nextNode;
		}
		
		/**
		 * gets the node that the current Node points to 
		 * 
		 * @param Node
		 * 
		 * @return 
		 */
		public void setNextNode(Node<E> nextNode) 
		{
			this.nextNode = nextNode;
		}
		
		/**
		 * gets the data that the Node holds
		 * 
		 * @param none
		 * 
		 * @return E. 
		 */
		public E getData() 
		{
			return this.data;
		}
	}
	
	/**
	 * Self-written Iterator that implements Iterator interface. 
	 * hasNext() method will check if the current Node in the list points to null or another Node
	 * next() retrieves the data in the Node. 
	 * 
	 * @author Samantha Chuang
	 * @version 2018.10.
	 */
	class MyIterator implements Iterator<E> {
		
		Node<E> pointer;
		int currentIndex; 
		
		//constructor
		public MyIterator() 
		{
			pointer = head;
			currentIndex = 0; 
		}
		
		/**
		 * returns true if the current node doesn't point to null. false if 
		 * the current node points to null
		 * 
		 * @param none
		 * 
		 * @return boolean. 
		 */
		@Override
		public boolean hasNext() 
		{
			if (pointer == null) 
			{
				return false;
			}
			else if (pointer.getNextNode() == null && pointer != tail) 
			{
				return false;
			}
			else {
				return true;
			}
		}

		/**
		 * the element that the node is holding. 
		 * 
		 * @param none
		 * 
		 * @return E. 
		 */
		@Override
		public E next() 
		{
			E currentData; 
			//get the data in the pointer 
			currentData = pointer.getData();
			
			//moves the pointer on to the next node
			pointer = pointer.getNextNode();
			return currentData;
		}
	}
	
	/**
	 * creators an iterator for the thing that it's called on. 
	 * 
	 * @param none
	 * 
	 * @return Iterator. 
	 */
	@Override
	public Iterator<E> iterator() 
	{
		return new MyIterator();
	}
	
	/**
	 * gets the index of an object (if it exists in the list). 
	 * 
	 * @param Object. 
	 * 
	 * @return int.
	 */
	public int indexOf( Object o ) 
	{
		int currentIndex = 0; 
		Node<E> currentNode = head; 
		
		if (this.size() == 0) 
		{
			return -1;
		}
		
		while (currentNode.getNextNode() != null) 
		{
			if (currentNode.getData().equals(o)) 
			{
				return currentIndex; 
			}
			currentIndex += 1; 
			currentNode = currentNode.getNextNode();
		}
		return -1; 
	}
	
	/**
	 * gets the element held by node at a specific index
	 * 
	 * @param int.
	 * 
	 * @return E. 
	 */
	public E get (int index) 
	{
		int currentIndex = 0; 
		Node<E> pointer = head;
		
		//throw an exception if the index is out of bounds
		if (index > size || index < 0 || index == size) 
		{
			throw new IndexOutOfBoundsException("Invalid index!");
		}
		
		if (index == 0) 
		{
			return pointer.getData();
		}
		
		//iterate through the list until we get to the index and then get the data
		while (currentIndex < index) 
		{
			pointer = pointer.nextNode;
			currentIndex += 1; 
		}
		return pointer.getData();
	}
	
	/**
	 * returns all of the elements in the list as one, long string
	 * 
	 * @param none
	 * 
	 * @return String. 
	 */
	public String toString() {
		//Iterator<E> collectionIterator = iterator(); 
		MyIterator collectionIterator = new MyIterator();
		StringBuilder returnString = new StringBuilder();
		
		Node<E> pointer = head;
		while (pointer.getNextNode() != null) 
		{
			returnString.append(pointer.getData() + ", ");
		}
//		while (collectionIterator.hasNext()) 
//		{
//			returnString.append(collectionIterator + ", ");
//		}
		
		return returnString.toString();
	}
	
	/**
	 * sorts the list that it's called on 
	 * 
	 * @param none
	 * 
	 * @return void. 
	 * 
	 * Author: Joanna Klukowska
	 */
	@SuppressWarnings("unchecked")
	public void sort() 
	{
		Object[] array = toArray();
		Arrays.sort(array);
		this.clear();
		for (Object o : array) 
		{
			this.add((E)o);
		}
	}

	/**
	 * Adds an element to the list and returns true if the operation was successful. 
	 * 
	 * @param E
	 * 
	 * @return boolean. 
	 */
	@Override
	public boolean add(E arg0) 
	{
		
		//if the list is empty, add the very first Node and have head point to it. 
		if (this.size() == 0) 
		{
			Node<E> newNode = new Node<E>(arg0);
			this.head = newNode;
			this.tail = newNode;
			this.size += 1;
			return true;
		}
		
		//if the list isn't empty, create a node with the data, point the last node in the list to that new one 
		else 
		{
			Node<E> newNode = new Node<E>(arg0);
			this.tail.setNextNode(newNode);
			this.tail = newNode;
			this.size += 1;
			return true;
		}
	}
	
	/**
	 * returns the size of the list
	 * 
	 * @param none
	 * 
	 * @return int. 
	 */
	@Override
	public int size() 
	{
		return this.size;
	}
	
	/**
	 * removes an object and returns true if the operation was successful. 
	 * 
	 * @param Object
	 * 
	 * @return boolean. 
	 */
	@Override
	public boolean remove(Object arg0) {	
		
		//can't remove anything if the list is size 0
		if (this.size() == 0) 
		{
			return false;
		}
		
		//if there's only one node in the list, then the list goes back to being empty and head points to null
		else if (this.size() == 1) 
		{
			if (this.head.equals(arg0)) 
			{
				this.head = null;
				this.tail = null;
				size = 0;
				return true; 
			}
			else 
			{
				return false;
			}
		}
		
		//removing the head from a populated list and changing what head points to
		else if (this.head.getData().equals(arg0)) 
		{
			this.head = this.head.getNextNode();
			size -= 1;
			return true;
		}
		
		Node<E> iteratorNode = head; 
		
		//otherwise, removing a node from elsewhere in the list
		while (iteratorNode.getNextNode() != null) 
		{

			if (iteratorNode.getNextNode().getData().equals(arg0)) 
			{
				if (iteratorNode.getNextNode().getNextNode() == null) 
				{
					this.tail = iteratorNode;
				}
				iteratorNode.setNextNode(iteratorNode.getNextNode().getNextNode());
				size -= 1;
				return true;
			}
			
			iteratorNode = iteratorNode.getNextNode();
		}
		
		return false;
	}
	
	/**
	 * clears the list of all elements
	 * 
	 * @param none
	 * 
	 * @return none. 
	 */
	@Override
	public void clear() 
	{
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	/**
	 * checks to see if two lists contain the same elements and are therefore equal. returns true if the lists
	 * and the elements the nodes hold are identical
	 * 
	 * @param LinkedList<E>
	 * 
	 * @return boolean. 
	 */
	public boolean equals(LinkedList<E> toCompareToList) 
	{
		//if the sizes aren't equal then they automatically aren't 
		if (this.size() != toCompareToList.size()) 
		{
			return false;
		}
		
		//return true if both lists are empty
		if (this.size() == 0 && toCompareToList.size() == 0) 
		{
			return true;
		}
		
		boolean isSame = true;
		
		//iterate through each list and compare items
		for (int i = 0; i < this.size; i++) 
		{
			if (this.get(i) != toCompareToList.get(i))
			{
				isSame = false;
			}
		}
		return isSame;
	}

	/**
	 * checks to see if the list contains a certain element. 
	 * 
	 * @param Object.
	 * 
	 * @return boolean. 
	 */
	@Override
	public boolean contains(Object arg0) 
	{
		MyIterator containsIterator = new MyIterator();
		boolean isFound = false; 
		
		//use iterator to iterate through the list and search for the element
		while (containsIterator.hasNext()) 
		{
			if (containsIterator.next().equals(arg0)) 
			{
				isFound = true; 
			}
		}
		
		return isFound;
	}

	/**
	 * checks to see if this list contains all of the elements in the collection given as a parameter
	 * 
	 * @param Collection<?>
	 * 
	 * @return boolean. 
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) 
	{
		//Iterator<?> containsAllIterator = iterator();
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

	/**
	 * checks to see if the list is empty. returns true if it is.
	 * 
	 * @param none
	 * 
	 * @return boolean. 
	 */
	@Override
	public boolean isEmpty() 
	{
		if (this.size() == 0) 
		{
			return true;
		}
		return false;
	}
	
	@Override
	public boolean retainAll(Collection<?> arg0) 
	{
		return false;
	}

	/**
	 * takes all of the elements in the list and puts them into an array
	 * 
	 * @param none
	 * 
	 * @return Object[]. 
	 */
	@Override
	public Object[] toArray() 
	{
		//create an array of the size that matches the size of the LinkedList
		Object[] elements = new Object[this.size()];
		
		//add the LinkedList elements to the new Array
		for (int i = 0; i < elements.length; i++) 
		{
			elements[i] = this.get(i);
		}
		
		return elements;
	}

	/**
	 * takes all of the elements in the list and puts them into the array given as a parameter.
	 * grows the array if it's not big enough to hold all of the elements.
	 * 
	 * @param T[]
	 * 
	 * @return T[]. 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] arg0) 
	{
		//This was taken from the Java LinkedList implementation 
		 if (arg0.length < size) 
		 {
			 arg0 = (T[])java.lang.reflect.Array.newInstance(arg0.getClass().getComponentType(), size);
		 }
		 //end of what was taken from Java LinkedList implementation  
		 
		int index = 0;
		
		MyIterator arrayElementIterator = new MyIterator();
		
		while (arrayElementIterator.hasNext()) 
		{
			arg0[index] = (T) arrayElementIterator.next();
			index += 1;
		}
		
		return arg0;
	}
	
	//optional method
	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		return true;
	}
}