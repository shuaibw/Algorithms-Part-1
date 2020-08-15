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
        q[tail++] = item;
        n++;
        if (n == q.length) resize(q.length * 2);
        tail = tail % q.length;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        int idx = StdRandom.uniform(head, head + n);
        idx = idx % q.length;

        Item item = q[idx];
        exch(q, idx, head);
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
        int idx = StdRandom.uniform(head, head + n);
        idx = idx % q.length;
        return q[idx];
    }

    private void exch(Item[] ara, int i, int j) {
        Item temp = ara[i];
        ara[i] = ara[j];
        ara[j] = temp;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandIterator();
    }

    private class RandIterator implements Iterator<Item> {
        private int i;
        private final Item[] copy;

        public RandIterator() {
            copy = (Item[]) new Object[n];
            int idx = 0;
            for (int i = head; idx < n; i++) {
                copy[idx++] = q[i % q.length];
            }
            for (int i = 0; i<n; i++) {
                int r = StdRandom.uniform(i + 1);
                exch(copy, i, r);
            }
            i = 0;
        }

        @Override
        public boolean hasNext() {
            return i < n;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy[i++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
//        test2();
//        StdOut.println();
        test1();
    }

    private static void test2() {
        int n = 3;
        String green = "\u001B[32m";
        String cyan = "\u001B[36m";
        String reset = "\u001B[0m";
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < n; i++)
            queue.enqueue(i);
        for (Integer i : queue) {
            StdOut.print(cyan + i + " ");
            for (Integer j : queue) {
                StdOut.print(green + j + " ");
            }
        }
    }

    private static void test1() {
        int n = 16;
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