package datastructures.worklists;

import cse332.interfaces.worklists.FIFOWorkList;

import java.util.NoSuchElementException;

/**
 * See cse332/interfaces/worklists/FIFOWorkList.java
 * for method specifications.
 */
public class ListFIFOQueue<E> extends FIFOWorkList<E> {
    private int size;
    private ListNode first;
    private ListNode last;

    private class ListNode{
        private E work;
        private ListNode next;
        public ListNode(E work) {
            this.work = work;
            this.next = null;
        }
    }

    public ListFIFOQueue() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }

    @Override
    public void add(E work) {
        ListNode node = new ListNode(work);
        // empty list
        if(this.last == null) {
            this.first = node;
            this.last = node;
        } else {
            this.last.next = node; // add node to back
            this.last = this.last.next;   // update last node
        }
        this.size++;
    }

    /**
     *
     * @return the first item to work on
     */
    @Override
    public E peek() {
        if(this.hasWork()) {
            return this.first.work;
        } else {
            throw new NoSuchElementException();
        }

    }

    /**
     *
     * @return Removes and returns the next item to work on
     */
    @Override
    public E next() {
        if(this.hasWork()) {
            ListNode next = this.first;
            this.first = this.first.next;
            this.size--;
            return next.work;
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
        this.first = null;
        this.last = null;
        this.size = 0;
    }
}


