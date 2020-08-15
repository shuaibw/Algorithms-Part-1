package extra;

import edu.princeton.cs.algs4.StdRandom;

public class Main {
    private static final int CUTOFF_MERGE = 7;
    private static final int CUTOFF_QUICK = 10;

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] ara, int i, int j) {
        Comparable swap = ara[i];
        ara[i] = ara[j];
        ara[j] = swap;
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo; i < hi; i++) {
            if (less(a[i + 1], a[i])) return false;
        }
        return true;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    private static void selectionSort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (less(a[j], a[min]))
                    min = j;
            }
            exch(a, i, min);
        }
    }

    private static void insertionSortHelper(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo; j--) {
                if (less(a[j], a[j - 1])) exch(a, j, j - 1);
                else break;
            }
        }
    }

    private static void insertionSort(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0; j--) {
                if (less(a[j], a[j - 1])) exch(a, j, j - 1);
                else break;
            }
        }
    }

    private static void shellSort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h)
                    exch(a, j, j - h);
            }
            h /= 3;
        }
    }

    private void shuffle_(Comparable[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = StdRandom.uniform(i + 1);
            exch(a, i, r);
        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid + 1, hi);
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (less(aux[j], aux[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
        assert isSorted(a, lo, hi);
    }

    private static void recurse_sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= (lo + CUTOFF_MERGE - 1)) {
            insertionSortHelper(a, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        recurse_sort(a, aux, lo, mid);
        recurse_sort(a, aux, mid + 1, hi);
        if (!less(a[mid + 1], a[mid])) return;
        merge(a, aux, lo, mid, hi);
    }

    public static void mergeSort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        recurse_sort(a, aux, 0, a.length - 1);
    }

    public static void mergeBottomUp(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int len = 1; len < N; len = len + len) {
            for (int lo = 0; lo < (N - len); lo += (len + len)) {
                merge(a, aux, lo, lo + len - 1, Math.min(lo + len + len - 1, N - 1));
            }
        }
    }

    public static int partition(Comparable[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        while (true) {
            while (less(a[++i], a[lo]))
                if (i == hi) break;
            while (less(a[lo], a[--j]))
                if (j == lo) break;

            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }

    private static void quickRecurse(Comparable[] a, int lo, int hi) {
        if (hi <= (lo + CUTOFF_QUICK - 1)) {
            insertionSortHelper(a, lo, hi);
            return;
        }
//        int j = partition(a, lo, hi);
//        quickRecurse(a, lo, j - 1);
//        quickRecurse(a, j + 1, hi);
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else i++;
        }
        quickRecurse(a, lo, lt - 1);
        quickRecurse(a, gt + 1, hi);
    }

    public static void quickSort(Comparable[] a) {
        StdRandom.shuffle(a);
        quickRecurse(a, 0, a.length - 1);
    }

    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else return a[k];
        }
        return a[k];
    }

    public static void main(String[] args) {
        Test[] t = new Test[3];
        t[0] = new Test("Shuaib", 123);
        t[1] = new Test("Bashir", 123);
        t[2] = new Test("Anwarul", 123);

//        selectionSort(t);
//        insertionSort(t);
//        mergeSort(t);
//        System.out.println(isSorted(t));
//        for (Test test : t) {
//            System.out.println(test);
//        }
        test2();
    }

    public static void test2() {
        int size = 16;
        Integer[] ara = new Integer[size];
        for (int i = 0; i < size; i++) {
            ara[i] = StdRandom.uniform(100000);
        }
        quickSort(ara);
        System.out.println(isSorted(ara));
//        for (Integer i : ara) {
//            System.out.println(i);
//        }
//        for (int i = 10000; i < 10100; i++) {
//            System.out.println(ara[i]);
//        }
    }

}
