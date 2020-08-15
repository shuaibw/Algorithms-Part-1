import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int head;
    private int tail;
    private int n = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        head = 0;
        tail = 0;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = q[(head + i) % q.length];
        }
        q = copy;
        head = 0;
        tail = n;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (n == q.length) resize(q.length * 2);
        q[tail++] = item;
        tail = tail % q.length;
        n++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = q[head];
        q[head] = null;
        head++;
        n--;
        head = head % q.length;
        if (n > 0 && n == q.length / 4) resize(q.length / 2);
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();
        return q[(head + StdRandom.uniform(n)) % q.length];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandIterator();
    }

    private class RandIterator implements Iterator<Item> {
        private int i = 0;
        private final int start = head;

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = q[(i + start) % q.length];
            i++;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        int n = 10;
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (Integer i : queue) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        queue.dequeue();
        queue.dequeue();
        queue.enqueue(11);
        for (Integer i : queue) {
            StdOut.print(i + " ");
        }
        StdOut.println();
        StdOut.println(queue.sample());
        StdOut.println(queue.size());
        StdOut.println(queue.isEmpty());

        while (queue.size() != 0) StdOut.print(queue.dequeue() + " ");
    }

}