/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C All code are
 * implementations written by me as a part of my coursework Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/
import java.util.EmptyStackException;


public final class LinkedStack<T> implements StackInterface<T> {
	private Node topNode;

	public LinkedStack() {
		topNode = null;
	}

	public void push(T element) {
		Node newNode = new Node(element, topNode);
		topNode = newNode;
		// topNode = new Node(element, topNode); // Alternate code
	}

	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return topNode.data;
		}
	}

	public T pop() {
		T top = peek(); // Might throw EmptyStackException

		// Assertion: topNode != null
		topNode = topNode.next;

		return top;
	}

	public boolean isEmpty() {
		return topNode == null;
	}

	public void clear() {
		while (topNode != null) {
			topNode.data = null;
			topNode = topNode.next;
		}
	}

	public boolean priorityPush(T element) {

		if (topNode == null) {
			push(element);
			return false;
		}

		Node current = topNode;
		boolean found = false;

		if (!current.data.equals(element)) {
			while (current.next != null) {
				if (current.next.data.equals(element)) {
					found = true;
					break;
				}
				current = current.next;
			}
		} else {
			return true;
		}

		this.push(element);

		if (found) {
			if (current.next.next != null) {
				current.next = current.next.next;
			} else {
				current.next = null;
			}
		}
		return found;
	}

	public T peekNext() {
		if (isEmpty()) {
			throw new EmptyStackException();
		} else if (topNode.next == null) {
			return null;
		}
		return topNode.next.data;
	}

	private class Node {
		private T data;
		private Node next;

		private Node(T data) {
			this(data, null);
		}

		private Node(T data, Node next) {
			this.data = data;
			this.next = next;
		}

		private T getData() {
			return data;
		}

		private void setData(T data) {
			this.data = data;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node next) {
			this.next = next;
		}
	}
}
