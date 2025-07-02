/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C All code are
 * implementations written by me as a part of my coursework Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/
import java.util.*;
/*
 * An expandable, array-based implementation of a multiset.
 */
public class ExpandableArrayMultiset<T> extends ArrayMultiset<T> implements Multiset<T> {
	
	
	public ExpandableArrayMultiset(int capacity) {
		super(capacity);
		if(capacity<=0) {
			throw new IllegalArgumentException("Invalid Capacity of " + capacity);
		}
	}
	
	public ExpandableArrayMultiset() {
		super();
	}
	
	@Override
	public boolean add(T element) {
		if (isArrayFull()) {
			expandArray();
		}
		return super.add(element);
	}


	public boolean isFull() {
		return false;
	}


	public void trimToSize() 
	{
		if(this.size() < this.setArray.length && this.size() > 0)
		{
			T[] temp = this.setArray;
			this.setArray = (T[]) new Object[this.size()];
			for(int i = 0; i < this.setArray.length; i++)
			{
				this.setArray[i] = temp[i];
			}
		}
	}
	
	private boolean isArrayFull() {
		return size()==setArray.length;
	}
	
	private void expandArray() {		
		setArray = Arrays.copyOf(setArray, setArray.length * 2);
		
	}
	

}
