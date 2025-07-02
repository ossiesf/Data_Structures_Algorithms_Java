/**
 * Fall 2024 Semester @ CCSF: DATA STRUCTURES AND ALGORITHMS: JAVA CS 111C All code are
 * implementations written by me as a part of my coursework Author: Ossie Finnegan
 * https://www.linkedin.com/in/theconstantvariable/
 **/
import java.util.ArrayList;
import java.util.List;

public class ListFrontBackCappedList<T> implements FrontBackCappedList<T> {

    private List<T> list;
    private int capacity;

    public ListFrontBackCappedList(int capacity) {
        this.capacity = capacity;
        list = new ArrayList<>(capacity);
    }

    @Override
    public boolean addFront(T newEntry) {
        if (list.size() < capacity) {
            list.add(0, newEntry);
            if (list.get(0).equals(newEntry)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addBack(T newEntry) {
        if (list.size() < capacity) {
            list.add(newEntry);
            if (list.get(list.size() - 1).equals(newEntry)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public T removeFront() {
        if (list.size() > 0) {
            return list.remove(0);
        }
        return null;
    }

    @Override
    public T removeBack() {
        if (list.size() > 0) {
            return list.remove(list.size() - 1);
        }
        return null;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public T getEntry(int givenPosition) {
        if (givenPosition >= 0 && givenPosition < list.size()) {
            return list.get(givenPosition);
        }
        return null;
    }

    @Override
    public int indexOf(T anEntry) {
        return list.indexOf(anEntry);
    }

    @Override
    public int lastIndexOf(T anEntry) {
        return list.lastIndexOf(anEntry);
    }

    @Override
    public boolean contains(T anEntry) {
        return list.contains(anEntry);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.size() == 0;
    }

    @Override
    public boolean isFull() {
        return list.size() == capacity;
    }

    public String toString() {
        // Output must contain size, capacity/max size, and display all elements in list
        // format: "size=0; capacity=10; []"
        if (list.size() > 0) {
            String content = "[";
            for (T entry : list) {
                if (entry != null) {
                    content += entry + ", ";
                }
            }
            content = content.substring(0, content.length() - 2);
            return "size=" + size() + "; " + "capacity=" + capacity + ";   " + content + "]";
        } else {
            return "size=" + size() + "; " + "capacity=" + capacity + ";   []";
        }
    }
}
