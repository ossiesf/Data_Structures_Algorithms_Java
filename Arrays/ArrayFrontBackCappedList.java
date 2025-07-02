/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C All code are
 * implementations written by me as a part of my coursework Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/
import java.util.Arrays;

public class ArrayFrontBackCappedList<T> implements FrontBackCappedList<T> {

    private T[] list;
    private int numberOfElements = 0;
    private int capacity;

    public ArrayFrontBackCappedList(int capacity) {
        this.list = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public boolean addFront(T newEntry) {
        if (numberOfElements < capacity) {
            for (int i = numberOfElements - 1; i >= 0; i--) {
                list[i + 1] = list[i];
            }
            list[0] = newEntry;
            numberOfElements++;
            return true;
        }
        return false;
    }

    @Override
    public boolean addBack(T newEntry) {
        if (numberOfElements < capacity) {
            list[numberOfElements] = newEntry;
            numberOfElements++;
            return true;
        }
        return false;
    }

    @Override
    public T removeFront() {
        T item = null;
        if (numberOfElements > 0) {
            item = list[0];
            for (int i = 1; i < numberOfElements; i++) {
                list[i - 1] = list[i];
            }
            numberOfElements--;
            list[numberOfElements] = null;
        }
        return item;
    }

    @Override
    public T removeBack() {
        T item = null;
        if (numberOfElements > 0) {
            item = list[numberOfElements - 1];
            list[numberOfElements - 1] = null;
            numberOfElements--;
        }
        return item;
    }

    @Override
    public void clear() {
        this.list = (T[]) new Object[capacity];
        this.numberOfElements = 0;
    }

    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition >= 0 && givenPosition <= capacity) {
            return list[givenPosition];
        }
        return null;
    }

    @Override
    public int indexOf(T anEntry) {
        for (int i = 0; i < numberOfElements; i++) {
            if (list[i].equals(anEntry)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T anEntry) {
        int position = -1;
        for (int i = 0; i < numberOfElements; i++) {
            if (list[i].equals(anEntry)) {
                position = i;
            }
        }
        return position;
    }

    @Override
    public boolean contains(T anEntry) {
        for (T entry : list) {
            if (entry.equals(anEntry)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.numberOfElements;
    }

    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    @Override
    public boolean isFull() {
        return numberOfElements == capacity;
    }

    public String toString() {
        // Output must contain size, capacity/max size, and display all elements in list
        // format: "size=0; capacity=10; []"
        if (numberOfElements > 0) {
            String content = "[";
            for (T entry : list) {
                if (entry != null) {
                    content += entry + ", ";
                }
            }
            content = content.substring(0, content.length() - 2);
            return "size=" + numberOfElements + "; " + "capacity=" + capacity + ";  " + content
                    + "]";
        } else {
            return "size=" + numberOfElements + "; " + "capacity=" + capacity + ";   []";
        }
    }
}
