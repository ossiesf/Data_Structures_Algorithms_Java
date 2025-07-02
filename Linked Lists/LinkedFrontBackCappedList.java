/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C
 * All code are implementations written by me as a part of my coursework 
 * Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/

 public class LinkedFrontBackCappedList<T extends Comparable<? super T>>
		implements FrontBackCappedList<T>, Comparable<LinkedFrontBackCappedList<T>> {

	private Node head, tail;
	private int capacity;
	private int size = 0;

	public LinkedFrontBackCappedList(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public int compareTo(LinkedFrontBackCappedList<T> other) {
		Node tempHead = head;
		Node tempOther = other.head;
		if (head != null && other.head != null) {
			while (tempHead != null && tempOther != null) {
				if (tempHead.data.compareTo(tempOther.data) != 0) {
					if (tempHead.data.compareTo(tempOther.data) > 0) {
						return 1;
					} else {
						return -1;
					}
				}
				tempHead = tempHead.next;
				tempOther = tempOther.next;
			}
		}
		if (tempHead != null) {
			return 1;
		} else if (tempOther != null) {
			return -1;
		}
		return 0;
	}

	@Override
	public boolean addFront(T newEntry) {
		if (size < capacity) {
			if (head != null) {
				Node temp = new Node(newEntry);
				temp.next = head;
				head = temp;
			} else {
				head = new Node(newEntry);
				tail = head;
			}
			size++;
			return true;
		}
		return false;
	}

	@Override
	public boolean addBack(T newEntry) {
		if (size < capacity) {
			if (tail != null) {
				tail.next = new Node(newEntry);
				tail = tail.next;
			} else if (head != null) {
				tail = new Node(newEntry);
				head.next = tail;
			} else {
				head = new Node(newEntry);
				tail = head;
			}
			size++;
			return true;
		}
		return false;
	}

	@Override
	public T removeFront() {
		if (size > 0) {
			T result = head.data;
			if (size == 1) {
				clear();
			} else {
				head = head.next;
				size--;
			}
			return result;
		}
		return null;
	}

	@Override
	public T removeBack() {
		if (size > 0) {
			T result = tail.data;
			if (size == 1) {
				clear();
			} else {
				Node temp = head;
				for (int i = 0; i < size - 2; i++) {
					temp = temp.next;
				}
				tail = temp;
				size--;
			}
			return result;
		}
		return null;
	}

	@Override
	public void clear() {
		if (size > 0) {
			this.head = null;
			this.tail = null;
			size = 0;
		}
	}

	@Override
	public T getEntry(int givenPosition) {
		T item = null;
		if (givenPosition >= 0 && givenPosition <= size - 1) {
			Node current;
			if (givenPosition < size - 1) {
				current = head;
				for (int i = 0; i <= givenPosition - 1; i++) {
					current = current.next;
				}
				item = current.data;
			} else if (givenPosition == size - 1) {
				item = tail.data;
			}

		}
		return item;
	}

	@Override
	public int indexOf(T anEntry) {
		if (size > 0) {
			Node temp = head;
			for (int i = 0; i < size; i++) {
				if (temp.data.equals(anEntry)) {
					return i;
				}
				temp = temp.next;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(T anEntry) {
		int result = -1;
		if (size > 0) {
			Node temp = head;
			for (int i = 0; i < size; i++) {
				if (temp.data.equals(anEntry)) {
					result = i;
				}
				temp = temp.next;
			}
		}
		return result;
	}

	@Override
	public boolean contains(T anEntry) {
		if (size > 0) {
			Node temp = head;
			while (temp != null) {
				if (temp.data.equals(anEntry)) {
					return true;
				}
				temp = temp.next;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == capacity;
	}

	private String getElementsString() {
		String result = "";
		if (head != null) {
			Node current = head;
			if (size > 0) {
				if (size == 1) {
					return result + current.data;
				}
				while (current.next != null) {
					result = result + current.data + ", ";
					current = current.next;
				}
				result = result + current.data;
			}
		}
		return result;
	}

	public String toString() {
		String result =
				"[" + getElementsString() + "]" + "	size=" + size + "  capacity=" + capacity;
		if (size > 0) {
			result = result + " 	head=" + head.data + "	 tail=" + tail.data;
		}
		return result;
	}

	public class Node {
		public T data;
		public Node next;

		private Node(T dataValue) {
			data = dataValue;
			next = null;
		}

		private Node(T dataValue, Node nextNode) {
			data = dataValue;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}
}
