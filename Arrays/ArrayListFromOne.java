/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C All code are
 * implementations written by me as a part of my coursework Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/
import java.util.Arrays;

/*
 * An expandable, array-based implementation of a list that is 1-indexed.
 */
public class ArrayListFromOne<T> implements ListFromOne<T> {
	
	public T[] listArray; // will store elements starting at index 1
	private int size;
	
	public static final int DEFAULT_CAPACITY = 100;
	
	public ArrayListFromOne(int capacity) {
		listArray = (T[]) new Object[capacity+1]; // listArray[0] will always store null
		size = 0;
	}
	
	public ArrayListFromOne() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(T element) {
		return add(size+1, element);
	}

	@Override
	public boolean add(int position, T element) {
		if (isValidPosition(position) || position == size + 1) {
			if (isArrayFull()) {
				expandArray();
			}
			makeASpace(position);
			listArray[position] = element;
			size++;
			return true;
		} else {
			throw new IndexOutOfBoundsException(position + " is invalid for adding from a list of size " + size + ".");
		}
	}

	@Override
	public boolean contains(T element) {
		return getIndexOf(element)>=1;
	}

	@Override
	public T remove(int position) {
		if(isValidPosition(position)) {
			T elementToRemove = listArray[position];
			shiftToCoverSpace(position);
			listArray[size] = null;
			size--;
			return elementToRemove;
		} else {
			throw new IndexOutOfBoundsException(position + " is invalid for removing from a list of size " + size + ".");
		}
	}

	@Override
	public boolean remove(T element) {
		int removeIndex = getIndexOf(element);
		if(removeIndex < 1) {
			return false;
		} else {
			remove(removeIndex);
			return true;
		}
	}

	@Override
	public T set(int position, T element) {
		if(isValidPosition(position)) {
			T originalElement = listArray[position];
			listArray[position] = element;
			return originalElement;
		} else {
			throw new IndexOutOfBoundsException(position + " is invalid for setting in a list of size " + size + ".");
		}
	}

	@Override
	public T get(int position) {
		if(isValidPosition(position)) {
			return listArray[position];
		} else {
			throw new IndexOutOfBoundsException(position + " is an invalid index for a list of size " + size + ".");
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public void clear() {
		for(int i=1; i<=size; i++) {
			listArray[i] = null;
		}
		size = 0;		
	}
	
	@Override
	public String toString() {
		String s = "[";
		for(int i=1; i<=size; i++) {
			s += listArray[i] + ", ";
		}
		if(!isEmpty()) {
			s = s.substring(0, s.length()-2);
		}
		s += "]";
		return s;
	}
	
	
	
		
	private boolean isArrayFull() {
		return (size+1)==listArray.length;
	}
	
	private void expandArray() {
		T[] tempArray = (T[]) new Object[listArray.length * 2];
		for(int i=1; i<=size; i++) {
			tempArray[i] = listArray[i];
		}
		listArray = tempArray;
		
		// could also use:
		// listArray = Arrays.copyOf(listArray, listArray.length * 2);
		
	}
	
	// assumes that 1 <= position <= size
	private void makeASpace(int position) {
		if(size==listArray.length) {
			expandArray();
		}
		for(int i=size; i>=position; i--) {
			listArray[i+1] = listArray[i];
		}
	}
	
	// assumes that 1 <= position < size
	private void shiftToCoverSpace(int position) {
		if(position<=listArray.length) {
			for(int i=position; i<size; i++) {
				listArray[i] = listArray[i+1];
			}
		}
	}
	
	private boolean isValidPosition(int position) {
		return 1 <= position && position <= size;
	}
	
	private int getIndexOf(T element) {
		for(int i=1; i<=size; i++) {
			if(listArray[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}

}
