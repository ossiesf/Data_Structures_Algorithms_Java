/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C
 * All code are implementations written by me as a part of my coursework 
 * Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/

/*
 * A fixed-size, array-based implementation of a multiset.
 */
public class ArrayMultiset<T> implements Multiset<T> {
	
	protected T[] setArray;
	private int size;
	
	public static final int DEFAULT_CAPACITY = 100;
	
	public ArrayMultiset(int capacity) {
		setArray = (T[]) new Object[capacity];
		size = 0;
	}
	
	public ArrayMultiset() {
		this(DEFAULT_CAPACITY);
	}
	
	@Override
	public boolean add(T element) {
		if(!isFull()) {
			setArray[size] = element;
			size++;
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public T remove() {
		if(isEmpty()) {
			return null;
		} else {
			T elementToRemove = setArray[lastPosition()];
			removeElement(lastPosition());
			return elementToRemove;
		}
	}

	@Override
	public boolean remove(T element) {
		int position = getPosition(element);
		if(position>=0) {
			removeElement(position);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getOccurrencesOf(T element) {
		int count = 0;
		for(int i=0; i<size; i++) {
			if(setArray[i].equals(element)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public boolean contains(T element) {
		return getPosition(element) >= 0;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}
	
	public boolean isFull() {
		return size==setArray.length;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		for(int i=0; i<size; i++) {
			setArray[i] = null;
		}
		size = 0;
	}
	
	@Override
	public String toString() {
		String s = "[";
		for(int i=0; i<size; i++) {
			s += setArray[i] + ", ";
		}
		if(!isEmpty()) {
			s = s.substring(0, s.length()-2);
		}
		s += "]";
		return s;
	}
	
	private int lastPosition() {
		return size - 1;
	}
	
	// assumes that 0 <= position < size
	private void removeElement(int position) {
		setArray[position] = setArray[lastPosition()];
		setArray[lastPosition()] = null;
		size--;
	}
	
	private int getPosition(T element) {
		for(int i=0; i<size; i++) {
			if(setArray[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}
	
	

}
