package datastructures.worklists;

import cse332.interfaces.worklists.LIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/LIFOWorkList.java
 * for method specifications.
 */
public class ArrayStack<E> extends LIFOWorkList<E> {
    private int size;   // number of works in stack
    private E[] array;  // an array of works with first work at front

    public ArrayStack() {
        this.size = 0;
        this.array = (E[])new Object[10];
    }

    /**
     *
     * @param work a new work that need to add to stack
     */
    @Override
    public void add(E work) {
        // expand stack if full
        if(size == array.length){
            E[] newArray = (E[])new Object[this.size() * 2]; //make a new array with double size
            // transfer data to new array
            for(int i = 0; i < this.size(); i++){
                newArray[i] = this.array[i];
            }
            this.array = newArray;
        }
        this.array[this.size()] = work;   //add new work
        this.size++;    // update size
    }

    @Override
    public E peek() {
        if(this.hasWork()) {
            return this.array[this.size()-1];    // return last(top) work
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public E next() {
        if(this.hasWork()) {
            this.size--;
            return this.array[this.size()];
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
    }

}
