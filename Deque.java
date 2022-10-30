import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldHead = head;
        head = new Node();
        head.item = item;
        head.next = oldHead;

        if (tail == null) tail = head;
        else head.next.prev = head;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldTail = tail;
        tail = new Node();
        tail.item = item;
        tail.prev = oldTail;

        if (head == null) head = tail;
        else tail.prev.next = tail;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item popHeadItem = head.item;
        size--;

        if (isEmpty()) {
            head = null;
            tail = null;
        }
        else {
            head = head.next;
            head.prev = null;
        }

        return popHeadItem;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item popTailItem = tail.item;
        size--;

        if (isEmpty()) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }

        return popTailItem;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
