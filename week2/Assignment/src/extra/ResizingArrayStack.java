package extra;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {
    private Item[] s;
    private int N = 0;

    public ResizingArrayStack() {
        s = (Item[]) new Object[1];
    }

    public void push(Item item) {
        if (N == s.length) resize(s.length * 2);
        s[N++] = item;
    }

    public Item pop() {
        Item item = s[--N];
        s[N] = null;
        if (N > 0 && N == s.length / 4) resize(s.length / 2);
        return item;
    }

    public void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        int i = N;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return s[--i];
        }
    }
}
