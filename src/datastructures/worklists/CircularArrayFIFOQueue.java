package datastructures.worklists;

import cse332.exceptions.NotYetImplementedException;
import cse332.interfaces.worklists.FixedSizeFIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FixedSizeFIFOWorkList.java
 * for method specifications.
 */
public class CircularArrayFIFOQueue<E> extends FixedSizeFIFOWorkList<E> {
    private E[] array;
    private int first;
    private int last;
    private int size;

    public CircularArrayFIFOQueue(int capacity) {
        super(capacity);
        this.array = (E[])new Comparable[capacity];
        this.first = 0; //index of first work in array
        this.last = 0;  //index of last work in array + 1
        this.size = 0;  //number of works in array
    }

    @Override
    public void add(E work) {
        if(this.isFull()){
            throw new IllegalStateException();
        } else {
            this.array[last] = work;
            last = (last+1) % this.array.length;
            this.size++;
        }
    }

    /**
     *
     * @return Returns the next item to work on
     */
    @Override
    public E peek() {
        if(this.hasWork()) {
            return this.array[first];
        } else {
            throw new NoSuchElementException();
        }
    }

    /**
     *
     * @param i
     *            the index of the element to peek at
     * @return
     *            return peeked element if it exist, otherwise throw exception
     */
    @Override
    public E peek(int i) {

        if(i >= 0 && i < this.size) {
            if(this.hasWork()) {
                // transfer index in list to index in array
                int index = (this.first + i) % this.array.length;
                return this.array[index];
            } else {
                throw  new NoSuchElementException();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    
    @Override
    public E next() {
        if(this.hasWork()) {
            int next = this.first;
            // move first to next
            first = (first +1) % this.array.length;
            this.size--;
            return this.array[next];
        } else {
            throw new NoSuchElementException();
        }
    }
    
    @Override
    public void update(int i, E value) {

        if(i >= 0 && i < this.size) {
            if(this.hasWork()) {
                int index = (this.first + i)%this.array.length;
                this.array[index] = value;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    
    @Override
    public int size() {
        return this.size;
    }
    
    @Override
    public void clear() {
        this.size = 0;
        this.first = 0;
        this.last = 0;
    }

    @Override
    public int compareTo(FixedSizeFIFOWorkList<E> other) {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        // You will finish implementing this method in project 2. Leave this method unchanged for project 1.
        if (this == obj) {
            return true;
        }
        else if (!(obj instanceof FixedSizeFIFOWorkList<?>)) {
            return false;
        }
        else {
            // Uncomment the line below for p2 when you implement equals
            // FixedSizeFIFOWorkList<E> other = (FixedSizeFIFOWorkList<E>) obj;

            // Your code goes here

            throw new NotYetImplementedException();
        }
    }

    @Override
    public int hashCode() {
        // You will implement this method in project 2. Leave this method unchanged for project 1.
        throw new NotYetImplementedException();
    }
}
