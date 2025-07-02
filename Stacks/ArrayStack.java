/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C All code are
 * implementations written by me as a part of my coursework Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/
import java.util.Arrays;
import java.util.EmptyStackException;


public final class ArrayStack<T> implements StackInterface<T> {

	private T[] stack;
	private int topIndex;
	public static final int DEFAULT_INITIAL_CAPACITY = 50;

	public ArrayStack() {
		this(DEFAULT_INITIAL_CAPACITY);
	}


	public ArrayStack(int initialCapacity) {
		stack = (T[]) new Object[initialCapacity];
		topIndex = -1;
	}

	public void push(T element) {
		if (isArrayFull()) {
			expandArray();
		}
		stack[topIndex + 1] = element;
		topIndex++;
	}

	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return stack[topIndex];
		}
	}

	public T pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			T top = stack[topIndex];
			stack[topIndex] = null;
			topIndex--;
			return top;
		}
	}

	public boolean isEmpty() {
		return topIndex < 0;
	}

	public void clear() {
		for (int i = 0; i < topIndex; i++) {
			stack[i] = null;
		}
		topIndex = -1;
	}

	public T peekNext() {
		if (isEmpty() || topIndex == 0) {
			throw new EmptyStackException();
		}
		return stack[topIndex - 1];
	}

	private boolean isArrayFull() {
		return topIndex >= stack.length - 1;
	}

	private void expandArray() {
		stack = Arrays.copyOf(stack, stack.length * 2);
	}

}
