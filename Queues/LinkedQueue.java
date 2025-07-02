/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C
 * All code are implementations written by me as a part of my coursework 
 * Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/
public class LinkedQueue<T> implements QueueInterface<T> {

	protected Node firstNode;
	protected Node lastNode;

	public LinkedQueue() {
		firstNode = null;
		lastNode = null;
	}

	public void enqueue(T element) {
		Node newNode = new Node(element);

		if (isEmpty()) {
			firstNode = newNode;
		} else {
			lastNode.next = newNode;
		}

		lastNode = newNode;
	}

	public T getFront() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			return firstNode.data;
		}
	}

	public T dequeue() {
		T front = getFront(); // will throw EmptyQueueException when empty

		// Assertion: firstNode != null
		firstNode.data = null;
		firstNode = firstNode.next;

		if (firstNode == null) {
			lastNode = null;
		}

		return front;
	}

	public boolean isEmpty() {
		return (firstNode == null) && (lastNode == null);
	}

	public void clear() {
		while (firstNode != null) {
			firstNode.data = null;
			firstNode = firstNode.next;
		}
		lastNode = null;
	}

	public void splice(LinkedQueue<T> anotherQueue) {
		if (!anotherQueue.isEmpty()) {
			Node tempHead = anotherQueue.firstNode;
			while (tempHead != anotherQueue.lastNode) {
				enqueue(tempHead.data);
				tempHead = tempHead.next;
			}
			enqueue(tempHead.data);
		}
	}

	public T getSecond() {
		if (!isEmpty() && firstNode.next != null) {
			return firstNode.next.data;
		} else {
			throw new EmptyQueueException();
		}
	}

	public class Node {
		public T data;
		public Node next;

		public Node(T data) {
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

		private Node getNext() {
			return next;
		}

		private void setNext(Node next) {
			this.next = next;
		}
	}
}
