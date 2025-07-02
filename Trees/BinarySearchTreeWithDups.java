/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C All code are
 * implementations written by me as a part of my coursework Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/
import java.util.*;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T> {

	public BinarySearchTreeWithDups() {
		super();
	}

	public BinarySearchTreeWithDups(T rootEntry) {
		super(rootEntry);
	}

	@Override
	public boolean add(T newEntry) {
		if (isEmpty()) {
			return super.add(newEntry);
		} else {
			return addEntryHelperNonRecursive(newEntry);
		}
	}

	// IMPLEMENT THIS METHOD; THIS METHOD CANNOT BE RECURSIVE
	private boolean addEntryHelperNonRecursive(T newEntry) {
		BinaryNode<T> current = root;

		while (current != null) {
			if (newEntry.compareTo(current.getData()) < 0) {
				// Go left
				if (current.hasLeftChild()) {
					current = current.getLeftChild();
				} else {
					current.setLeftChild(new BinaryNode<T>(newEntry));
					return true;
				}
			} else if (newEntry.compareTo(current.getData()) > 0) {
				// Go right
				if (current.hasRightChild()) {
					current = current.getRightChild();
				} else {
					current.setRightChild(new BinaryNode<T>(newEntry));
					return true;
				}
			} else {
				// Go to left subtree
				if (current.hasLeftChild()) {
					current = current.getLeftChild();
				} else {
					current.setLeftChild(new BinaryNode<T>(newEntry));
					return true;
				}
			}

		}
		return false;
	}

	// THIS METHOD CANNOT BE RECURSIVE.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countIterative(T target) {
		int count = 0;
		BinaryNode<T> currentNode = root;

		while (currentNode != null) {
			if (target.compareTo(currentNode.getData()) < 0) {
				currentNode = currentNode.getLeftChild();
			} else if (target.compareTo(currentNode.getData()) > 0) {
				currentNode = currentNode.getRightChild();
			} else {
				count++;
				currentNode = currentNode.getLeftChild();
			}
		}
		return count;
	}

	// THIS METHOD MUST BE RECURSIVE!
	// You are allowed to create a private helper.
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterRecursive(T target) {
		// YOUR CODE HERE!

		// this initial code is meant as a suggestion to get your started- use it or delete it!
		BinaryNode<T> rootNode = root;
		return countGreaterRecursiveHelper(rootNode, target);
	}

	public int countGreaterRecursiveHelper(BinaryNode<T> root, T target) {
		int left = 0;
		int right = 0;
		if (root != null) {
			if (root.getData().compareTo(target) > 0) {
				left += countGreaterRecursiveHelper(root.getLeftChild(), target);
				right += countGreaterRecursiveHelper(root.getRightChild(), target);
				return left + right + 1;
			} else {
				return countGreaterRecursiveHelper(root.getRightChild(), target);
			}
		}
		return 0;
	}

	// THIS METHOD CANNOT BE RECURSIVE.
	// Hint: use a stack!
	// Make sure to take advantage of the sorted nature of the BST!
	public int countGreaterIterative(T target) {
		int count = 0;
		BinaryNode<T> rootNode = root;
		Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();

		while (rootNode != null || !nodeStack.isEmpty()) {
			if (rootNode != null) {
				if (rootNode.getData().compareTo(target) > 0) {
					// Traverse left if bigger than target
					nodeStack.push(rootNode);
					rootNode = rootNode.getLeftChild();
				} else {
					// Go right if left tree smaller than target
					rootNode = rootNode.getRightChild();
				}
			} else {
				rootNode = nodeStack.pop();
				count++;
				rootNode = rootNode.getRightChild();
			}
		}
		return count;
	}


	// For full credit, the method should be O(n).
	// You are allowed to use a helper method.
	// The method can be iterative or recursive.
	// If you make the method recursive, you might need to comment out the call to the method in
	// Part B.
	public int countUniqueValues() {
		int count = 0;
		BinaryNode<T> current = root;
		HashSet<T> valueSet = new HashSet<>();

		return countUniqueValuesHelper(current, count, valueSet); // placeholder: replace with your
																	// own code
	}

	private int countUniqueValuesHelper(BinaryNode<T> current, int count, HashSet<T> valueSet) {
		if (current != null) {
			valueSet.add(current.getData());
			countUniqueValuesHelper(current.getLeftChild(), count, valueSet);
			countUniqueValuesHelper(current.getRightChild(), count, valueSet);
		}
		return valueSet.size();
	}

}
