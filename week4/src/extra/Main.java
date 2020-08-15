package extra;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Main {

    private static void sink(Comparable[] a, int k, int N) {
        while (2 * k <= N) {
            int j = 2 * k;
            if (j < N && less(a, j, j + 1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    private static void exch(Comparable[] ara, int a, int b) {
        Comparable key = ara[a];
        ara[a] = ara[b];
        ara[b] = key;
    }

    private static boolean less(Comparable[] ara, int a, int b) {
        return ara[a].compareTo(ara[b]) < 0;
    }

    private static void HeapSort(Comparable[] ara) {
        int N = ara.length - 1;
        for (int k = N / 2; k >= 1; k--) {
            sink(ara, k, N);
        }
        while (N > 1) {
            exch(ara, 1, N--);
            sink(ara, 1, N);
        }
    }

    private static boolean isSorted(Comparable[] a) {
        int N = a.length - 1;
        for (int i = 1; i < N; i++) {
            if (less(a, i + 1, i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        int size = 15;
        MaxPQ<Integer> pq = new MaxPQ<>(size);
        for (int i = 0; i < size; i++) {
            pq.insert(i);
        }
        for (int i = 0; i < size; i++) {
            System.out.print(pq.delMax() + " ");
        }
    }

    private static void test2() {
        int size = 500;
        Integer[] ara = new Integer[size + 1];
        for (int i = 1; i <= size; i++) {
            ara[i] = StdRandom.uniform(0, 101);
        }
        HeapSort(ara);
        StdOut.println(isSorted(ara));
    }
}
