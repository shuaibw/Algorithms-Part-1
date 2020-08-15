package extra;

import java.util.Iterator;

public class MaxPQ<Key extends Comparable<Key>> implements Iterable<Key> {
    private final Key[] pq;
    private int N;

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key key) {
        pq[++N] = key;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1];
        exch(1, N--);
        sink(1);
        pq[N + 1] = null;
        return max;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private void exch(int a, int b) {
        Key key = pq[a];
        pq[a] = pq[b];
        pq[b] = key;
    }

    private boolean less(int a, int b) {
        return pq[a].compareTo(pq[b]) < 0;
    }

    @Override
    public Iterator<Key> iterator() {
        return new PQIterator();
    }

    private class PQIterator implements Iterator<Key> {
        int i = 1;

        @Override
        public boolean hasNext() {
            return i < N;
        }

        @Override
        public Key next() {
            return pq[i++];
        }
    }
}
