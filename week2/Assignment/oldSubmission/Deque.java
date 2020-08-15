import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    private Node first;
    private Node last;
    private int n;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        first.next = oldFirst;
        if (isEmpty()) last = first;
        else oldFirst.prev = first;
        n++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        n++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        else first.prev = null;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();
        Item item = last.item;
        last = last.prev;
        n--;
        if (isEmpty()) first = null;
        else last.next = null;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> deque1 = new Deque<>();
        deque1.addFirst("Shuaib");
        deque1.addFirst("Bashir");
        deque1.addFirst("Anwarul");
        for (String s : deque1) {
            StdOut.println(s);
        }
        StdOut.println(deque1.size());
        deque1.removeFirst();
        deque1.removeLast();
        for (String s : deque1) {
            StdOut.println(s);
        }
        StdOut.println("********************");
        Deque<String> deque2 = new Deque<>();
        deque2.addLast("Anwarul");
        deque2.addLast("Bashir");
        deque2.addLast("Shuaib");
        for (String s : deque2) {
            StdOut.println(s);
        }
        StdOut.println(deque2.size());
        deque2.removeLast();
        deque2.removeFirst();
        for (String s : deque2) {
            StdOut.println(s);
        }
        StdOut.println(deque2.removeFirst());
        StdOut.println(deque1.removeLast());
    }

}