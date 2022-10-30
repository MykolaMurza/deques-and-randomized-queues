import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] data;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        data = (Item[]) new Object[2];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == data.length) resize(data.length * 2);

        if (size == 0) {
            data[size++] = item;
        }
        else {
            int random = StdRandom.uniformInt(size);
            Item tmp = data[random];
            data[random] = item;
            data[size++] = tmp;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        if (size == data.length / 4) resize(data.length / 2);

        int random = StdRandom.uniformInt(size);
        Item item = data[random];
        data[random] = data[--size];
        data[size] = null;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        return data[StdRandom.uniformInt(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {

    }

    private void resize(int newSize) {
        Item[] itemsTmp = (Item[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            itemsTmp[i] = data[i];
        }
        data = itemsTmp;
    }

    private class ArrayIterator implements Iterator<Item> {
        private int[] randomIndx;
        private int i;

        public ArrayIterator() {
            randomIndx = new int[size];
            i = 0;
            for (int j = 0; j < size; j++) {
                randomIndx[j] = j;
            }
            StdRandom.shuffle(randomIndx);
        }

        @Override
        public boolean hasNext() {
            return i < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return data[randomIndx[i++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
