
/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C
 * All code are implementations written by me as a part of my coursework 
 * Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/
public class BinarySearchTree<T extends Comparable<? super T>> extends BinaryTree<T> {

	public BinarySearchTree() {
		super();
	}

	public BinarySearchTree(T rootEntry) {
		super(rootEntry);
	}

	public T getEntry(T anEntry) {
		return findEntry(root, anEntry);
	}

	private T findEntry(BinaryNode<T> currentRootNode, T anEntry) {
		T result = null;

		if (currentRootNode != null) {
			T rootEntry = currentRootNode.getData();

			if (anEntry.equals(rootEntry)) {
				result = rootEntry;
			} else if (anEntry.compareTo(rootEntry) < 0) { // anEntry < currentRootNode.data
				result = findEntry(currentRootNode.getLeftChild(), anEntry);
			} else { // anEntry.compareTo(rootEntry) > 0 // anEntry > currentRootNode.data
				result = findEntry(currentRootNode.getRightChild(), anEntry);
			}
		}
		return result;
	}

	public boolean contains(T entry) {
		return getEntry(entry) != null;
	}

	// does not allow duplicates!
	// true- the element was added
	// false- the element was NOT added because it was a duplicate
	public boolean add(T newEntry) {
		if (isEmpty()) {
			root = new BinaryNode<>(newEntry);
			return true;
		} else {
			return addEntry(root, newEntry);
		}
	}

	// adds anEntry to the nonempty subtree rooted at currentRootNode.
	private boolean addEntry(BinaryNode<T> currentRootNode, T anEntry) {

		BinaryNode<T> newNodeToAdd = new BinaryNode<>(anEntry);
		int comparison = anEntry.compareTo(currentRootNode.getData());

		// If the item is already in there, return false / don't add
		if (comparison == 0) { // anEntry == currentRootNode.data
			return false;
		} else if (comparison < 0) { // anEntry < currentRootNode.data
			if (currentRootNode.hasLeftChild()) {
				return addEntry(currentRootNode.getLeftChild(), anEntry);
			} else {
				currentRootNode.setLeftChild(newNodeToAdd);
				return true;
			}
		} else { // anEntry > currentRootNode.data
			if (currentRootNode.hasRightChild()) {
				return addEntry(currentRootNode.getRightChild(), anEntry);
			} else {
				currentRootNode.setRightChild(newNodeToAdd);
				return true;
			}
		}
	}

	// removes anEntry and returns anEntry (or null, if anEntry was not in the tree)
	public T remove(T anEntry) {
		ReturnObject removedEntry = new ReturnObject(null);
		BinaryNode<T> newRoot = removeEntry(root, anEntry, removedEntry);
		root = newRoot;

		return removedEntry.get();
	}

	// Removes an entry from the tree rooted at a given node.
	// Returns: The root node of the resulting tree;
	// if anEntry was in the tree, removedEntry's data is the entry that was removed from the tree;
	// otherwise, removedEntry's data is null.
	private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T anEntry,
			ReturnObject removedEntry) {
		if (rootNode != null) {
			T rootData = rootNode.getData();
			int comparison = anEntry.compareTo(rootData);

			if (comparison == 0) {// anEntry == rootData: we've found the entry to remove
				removedEntry.set(rootData); // save the data
				BinaryNode<T> newRoot = removeRoot(rootNode); // remove the current root and get the
																// new root
				return newRoot;
			} else if (comparison < 0) { // anEntry < root entry; keep looking for our entry in the
											// left subtree
				BinaryNode<T> leftChild = rootNode.getLeftChild();
				BinaryNode<T> leftSubtreeNewRoot = removeEntry(leftChild, anEntry, removedEntry);
				rootNode.setLeftChild(leftSubtreeNewRoot);
				return rootNode;
			} else {// anEntry > root entry; keep looking for our entry in the right subtree
				BinaryNode<T> rightChild = rootNode.getRightChild();
				BinaryNode<T> righSubtreeNewRoot = removeEntry(rightChild, anEntry, removedEntry);
				rootNode.setRightChild(righSubtreeNewRoot);
				return rootNode;
			}
		} else { // rootNode is null
			return rootNode;
		}
	}

	// Removes the root of a subtree
	// Returns the new root node of the subtree
	private BinaryNode<T> removeRoot(BinaryNode<T> rootNode) {

		// Case 1: rootNode has two children
		if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {

			// Find node with largest entry in left subtree (Option B from our notes)
			BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
			BinaryNode<T> largestNodeInLeftSubtree = findLargest(leftSubtreeRoot);
			T largestDataInLeftSubtree = largestNodeInLeftSubtree.getData();

			// Replace entry in root
			rootNode.setData(largestDataInLeftSubtree);

			// Remove node with largest entry in left subtree
			BinaryNode<T> newLeftSubtreeRoot = removeLargest(leftSubtreeRoot);

			// the removal above will change the leftSubtreeRoot, so update that
			rootNode.setLeftChild(newLeftSubtreeRoot);
		}
		// Case 2: rootNode has one right child
		else if (rootNode.hasRightChild()) {
			rootNode = rootNode.getRightChild();
		}
		// Case 3: rootNode has left child
		else if (rootNode.hasLeftChild()) {
			rootNode = rootNode.getLeftChild();
		}
		// Case 4: rootNode is a leaf
		else {
			rootNode = null;
		}

		return rootNode;
	}

	private BinaryNode<T> findLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			return findLargest(rootNode.getRightChild());
		} else {
			return rootNode;
		}
	}

	// Removes the node containing the largest entry in a given tree
	// Returns the root node of the revised tree (with the largest element removed)
	private BinaryNode<T> removeLargest(BinaryNode<T> rootNode) {
		if (rootNode.hasRightChild()) {
			BinaryNode<T> rightChild = rootNode.getRightChild();
			BinaryNode<T> newRightChild = removeLargest(rightChild);
			rootNode.setRightChild(newRightChild);
			return rootNode;
		} else {
			return rootNode.getLeftChild();
		}
	}



	private class ReturnObject {
		private T item;

		private ReturnObject(T entry) {
			item = entry;
		}

		public T get() {
			return item;
		}

		public void set(T entry) {
			item = entry;
		}
	}

}
