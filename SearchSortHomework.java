/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C All code are
 * implementations written by me as a part of my coursework Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/

 import java.text.NumberFormat;
import java.util.Arrays;

@SuppressWarnings({"rawtypes", "unchecked"})
public class SearchSortHomework {

	public static double sortedness(Comparable[] array) {
		double sortedness = 1.0;
		if (array.length > 1) {
			sortedness = sortednessHelper(array, sortedness, 0);
		}
		return sortedness;
	}

	public static double sortednessHelper(Comparable[] array, double sortedness, int index) {

		if (array.length > index + 2) {
			sortedness = sortednessHelper(array, sortedness, index + 1);
		}

		// Reduce sortedness rating if array[index] > array[index+1]
		if (array[index].compareTo(array[index + 1]) > 0) {
			sortedness -= 1.0 / (array.length - 1);
		}

		return sortedness;
	}

	public static double sortedness(Node<Comparable> node) {
		double sortedness = 0.0;
		double count = 0.0;
		if (node != null) {
			sortedness = sortednessLinkedNodesHelper(node, count, sortedness);
		} else {
			sortedness = 1.0;
		}
		// NOTE: If you want to use a local Node variable, declare it as: Node<Comparable> temp
		return sortedness;
	}

	public static double sortednessLinkedNodesHelper(Node<Comparable> node, double count,
			double sortedness) {

		double result = 0.0;
		if (node.next != null) {
			count++;
			if (node.data.compareTo(node.next.data) <= 0) {
				sortedness++;
			}

			node = node.next;
			if (node.next != null) {
				result = sortednessLinkedNodesHelper(node, count, sortedness);
			} else {
				result = sortedness / count;
			}
		} else {
			result = 1;
		}
		return result;
	}