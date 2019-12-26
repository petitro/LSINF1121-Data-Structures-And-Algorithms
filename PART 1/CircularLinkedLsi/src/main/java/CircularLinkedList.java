package student;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<Item> implements Iterable<Item> {
    private long nOp = 0; // count the number of operations
    private int n;          // size of the stack
    private Node  last;   // trailer of the list

    // helper linked list class
    private class Node {
        private Item item;
        private Node next;
    }

    public CircularLinkedList() {
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    private long nOp() {
        return nOp;
    }



    /**
     * Append an item at the end of the list
     * @param item the item to append
     */
    public void enqueue(Item item) {
        Node add = new Node();
        add.item=item;
        if(n==0) { //empty list
            add.next = add;
        }
        else {  //cas n>0
            add.next=last.next;
            last.next=add;
        }
        last=add;
        n++;
        nOp++;
        return;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) {
        if(index<0||index>n-1){throw new IndexOutOfBoundsException();}
        Item item;
        if(n>1) {
            Node runner = last;
            for (int i = 0; i < index - 1; i++) {
                runner = runner.next;
            }
            Node tmp = runner.next;
            runner.next = runner.next.next;
            tmp.next = null;
            item = tmp.item;

            if(n==index){
                last=runner;
            }
        }
        else{ //(n==1)
             item=last.item;
            last=null;
        }

        n--;
        nOp++;
        return item;
    }


    /**
     * Returns an iterator that iterates through the items in FIFO order.
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    /**
     * Implementation of an iterator that iterates through the items in FIFO order.
     *
     */
    private class ListIterator implements Iterator<Item> {
        private Node runner = last;
        private int N = n;
        private long lnOp= nOp;

        @Override
        public boolean hasNext() {
            if(nOp != lnOp){throw new ConcurrentModificationException();}
            if(N==0){return false;}
            return true;
        }

        @Override
        public Item next() {
            if(nOp != lnOp){throw new ConcurrentModificationException();}
            if(hasNext()==false){throw new  NoSuchElementException();}
            runner=runner.next;
            N--;
            return runner.item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}