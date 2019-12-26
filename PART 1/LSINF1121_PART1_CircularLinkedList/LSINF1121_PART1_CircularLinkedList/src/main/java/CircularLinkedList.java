
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

    public boolean isEmpty() { return n == 0;}

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
        Node NewNode = new Node();
        NewNode.item=item;

        if(n==0){
            last = NewNode;
            NewNode.next=last;
        }
        else {
            NewNode.next = last.next;
            last.next=NewNode;
            last=NewNode;
        }
        n++;
        nOp++;
    }

    /**
     * Removes the element at the specified position in this list.
     * Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     */
    public Item remove(int index) {
        if(index>=n||n<=0){throw new IndexOutOfBoundsException();}

        //cas 1 element dans la file
        if(n==1){
            Item temp = last.item;
            last=null;
            n--;
            nOp++;
            return temp;
        }
        Item temp;

        //cas >1 element dans la file

        if(index==n-1){
            temp = last.item;
            Node Runner = last;
            for (int i =0; i<n-2; i++){
                Runner=Runner.next;
            }
            if(n==2){
                Runner.next = Runner;
                last = Runner.next;
            }
            else {
                Runner.next = Runner.next.next;
                last = Runner.next;
            }
        }
        else {
            Node Runner = last;
            for (int i = 0; i < index; i++) {
                Runner = Runner.next;
            }
            temp = Runner.next.item;
            if(n>2){
            Runner.next = Runner.next.next;}
            else{
                Runner.next=Runner;
                last=Runner;
            }
        }
        n--;
        nOp++;
        return temp;
    }


    /**
     * Returns an iterator that iterates through the items in FIFO order.
     * @return an iterator that iterates through the items in FIFO order.
     */
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node runner = last;
        private int N = n;
        private long lnOp= nOp;

        @Override
        public boolean hasNext() throws ConcurrentModificationException {
            if(lnOp!=nOp){throw new ConcurrentModificationException();}
            if(N==0){return false;}
                return true;
        }
        @Override
        public Item next() throws NoSuchElementException, ConcurrentModificationException {
            if(lnOp!=nOp){throw new ConcurrentModificationException();}
            if (!hasNext()){throw new NoSuchElementException();}
            runner = runner.next;
            Item i = runner.item;
            N--;
            return i;
        }
        @Override
        public void remove() throws UnsupportedOperationException{
            throw new UnsupportedOperationException();
        }
    }
}